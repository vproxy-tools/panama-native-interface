package io.vproxy.pni.test;

public class JavaFile {
    private String name;
    private String content;

    public JavaFile() {
    }

    public String getName() {
        return name;
    }

    public JavaFile setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public JavaFile setContent(String content) {
        this.content = content;
        return this;
    }
}
