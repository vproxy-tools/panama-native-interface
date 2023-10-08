package io.vproxy.pni.sample;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIEnv;

import java.io.IOException;
import java.lang.foreign.ValueLayout;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        System.loadLibrary("pnisample");
        System.out.println("library loaded: pnisample");

        int port = 80;

        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);

            int serverFD = NativeFunctions.get().openIPv4TcpSocket(env);
            System.out.println("open ipv4 tcp socket fd: " + serverFD);

            NativeFunctions.get().bindIPv4(env, serverFD, 0, port);
            NativeFunctions.get().listen(env, serverFD, 128);
            System.out.println("bind 0.0.0.0:" + port + " succeeded");

            System.out.println("begin to accept connections ...");

            //noinspection InfiniteLoopStatement
            while (true) {
                int fd = NativeFunctions.get().accept(env, serverFD);
                System.out.println("accepted connection, fd: " + fd);

                new Thread(() -> handleClientConnection(fd), "conn-" + fd).start();
            }
        }
    }

    private static void handleClientConnection(int fd) {
        System.out.println("begin to handle connection " + fd + " on thread " + Thread.currentThread().getName());
        try (var allocator = Allocator.ofConfined()) {
            var env = new PNIEnv(allocator);
            int LEN = 4096;
            var buf = allocator.allocate(LEN);

            while (true) {
                int n;
                try {
                    n = NativeFunctions.get().read(env, fd, buf, 0, LEN);
                } catch (IOException e) {
                    System.err.println("got exception from " + fd + ", closing ...");
                    e.printStackTrace();
                    NativeFunctions.get().close(env, fd);
                    break;
                }

                if (n == 0) {
                    System.out.println("received EOF from " + fd + ", closing ...");
                    NativeFunctions.get().close(env, fd);
                    break;
                }
                System.out.println("read " + n + " bytes from " + fd);

                if (n == LEN) {
                    buf.set(ValueLayout.JAVA_BYTE, LEN - 1, (byte) 0);
                } else {
                    buf.set(ValueLayout.JAVA_BYTE, n, (byte) 0);
                }
                var str = buf.getUtf8String(0);
                System.out.println("received from " + fd);
                System.out.println(str);

                var byteBuffer = buf.asByteBuffer();
                if (str.startsWith("GET / ") || str.startsWith("GET /index.html ")) {
                    var page = """
                        <!DOCTYPE html>
                        <html>
                        <head>
                        <title>Welcome to Panama Native Interface!</title>
                        <style>
                            body {
                                width: 35em;
                                margin: 0 auto;
                                font-family: Tahoma, Verdana, Arial, sans-serif;
                            }
                        </style>
                        </head>
                        <body>
                        <h1>Welcome to Panama Native Interface!</h1>
                        <p>If you see this page, the Panama Native Interface
                        web server is successfully installed and
                        working. Further configuration is required.</p>
                                                
                        <p>For online documentation and support please refer to
                        <a href="https://github.com/vproxy-tools/panama-native-interface">panama-native-interface</a>.</p>
                                                
                        <p><em>Thank you for using Panama Native Interface.</em></p>
                        </body>
                        </html>
                        """.getBytes(StandardCharsets.UTF_8);
                    var headers = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Length: " + page.length + "\r\n" +
                                  "Server: panama-native-interface\r\n" +
                                  "\r\n";
                    byteBuffer.put(headers.getBytes(StandardCharsets.UTF_8));
                    byteBuffer.put(page);
                } else if (str.startsWith("POST /shutdown ")) {
                    var page = """
                        <html>
                        <head></head>
                        <body>
                        <center><h1>Shutdown</h1></center>
                        </body>
                        </html>
                        """.getBytes(StandardCharsets.UTF_8);
                    var headers = "HTTP/1.1 200 OK\r\n" +
                                  "Content-Length: " + page.length + "\r\n" +
                                  "Server: panama-native-interface\r\n" +
                                  "\r\n";
                    byteBuffer.put(headers.getBytes(StandardCharsets.UTF_8));
                    byteBuffer.put(page);
                } else {
                    var page = """
                        <html>
                        <head><title>404 Not Found</title></head>
                        <body>
                        <center><h1>404 Not Found</h1></center>
                        </body>
                        </html>
                        """.getBytes(StandardCharsets.UTF_8);
                    var headers = "HTTP/1.1 404 Not Found\r\n" +
                                  "Content-Length: " + page.length + "\r\n" +
                                  "Server: panama-native-interface\r\n" +
                                  "\r\n";
                    byteBuffer.put(headers.getBytes(StandardCharsets.UTF_8));
                    byteBuffer.put(page);
                }

                System.out.println("will respond to connection: " + fd + " with data length " + byteBuffer.position());
                try {
                    n = NativeFunctions.get().write(env, fd, buf, 0, byteBuffer.position());
                } catch (IOException e) {
                    System.err.println("got exception from " + fd + ", closing ...");
                    e.printStackTrace();
                    NativeFunctions.get().close(env, fd);
                    break;
                }
                System.out.println("wrote " + n + " bytes to " + fd);

                if (str.startsWith("POST /shutdown ")) {
                    System.exit(0);
                }
            }
        }
    }
}
