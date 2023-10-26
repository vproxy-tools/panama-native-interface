module io.vproxy.pni.exec {
    requires io.vproxy.base;
    requires org.objectweb.asm;
    requires org.objectweb.asm.tree;

    exports io.vproxy.pni.exec;
    exports io.vproxy.pni.exec.ast;
    exports io.vproxy.pni.exec.extension;
    exports io.vproxy.pni.exec.generator;
    exports io.vproxy.pni.exec.internal;
    exports io.vproxy.pni.exec.type;
}
