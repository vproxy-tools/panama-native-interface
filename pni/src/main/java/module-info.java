module io.vproxy.pni.exec {
    requires io.vproxy.base;
    requires org.objectweb.asm;
    requires org.objectweb.asm.tree;

    exports io.vproxy.pni.exec;
    exports io.vproxy.pni.exec.ast;
    exports io.vproxy.pni.exec.extension;
}
