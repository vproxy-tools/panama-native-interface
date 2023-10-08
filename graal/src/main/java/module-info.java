module io.vproxy.pni.graal {
    requires org.graalvm.nativeimage;
    requires transitive io.vproxy.pni;

    exports io.vproxy.pni.graal;
}
