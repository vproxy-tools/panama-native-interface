package io.vproxy.pni.graal;

import io.vproxy.pni.GraalHelper;
import io.vproxy.pni.PanamaUtils;
import org.graalvm.nativeimage.CurrentIsolate;
import org.graalvm.nativeimage.ImageInfo;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPointLiteral;
import org.graalvm.nativeimage.c.function.CFunctionPointer;
import org.graalvm.nativeimage.c.type.VoidPointer;

import java.lang.foreign.MemorySegment;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GraalUtils {
    private GraalUtils() {
    }

    public static void setThread() {
        if (!ImageInfo.inImageCode()) {
            return;
        }

        init();

        var thread = CurrentIsolate.getCurrentThread();
        var mem = MemorySegment.ofAddress(thread.rawValue());

        try {
            SetPNIGraalThread.invokeExact(mem);
        } catch (Throwable e) {
            throw new RuntimeException("should not happen", e);
        }
    }

    private static volatile MethodHandle SetPNIGraalThread = null;

    public static void init() {
        if (!ImageInfo.inImageCode()) {
            return;
        }

        if (SetPNIGraalThread == null) {
            synchronized (GraalUtils.class) {
                if (SetPNIGraalThread == null) {
                    PanamaUtils.loadLib();
                    SetPNIGraalThread = PanamaUtils.lookupPNICriticalFunction(true, void.class, "SetPNIGraalThread", MemorySegment.class);
                }
            }
        }

        GraalHelper.setInvokeFunc(GraalPNIFunc.getInvokeFunctionPointer());
        GraalHelper.setReleaseFunc(GraalPNIFunc.getReleaseFunctionPointer());
        GraalHelper.setReleaseRef(GraalPNIRef.getReleaseFunctionPointer());
    }

    public static CEntryPointLiteral<CFunctionPointer> defineCFunctionByName(Class<?> declaringClass, String name) {
        var methods = declaringClass.getDeclaredMethods();
        Method candidate = null;
        for (Method m : methods) {
            if (m.getName().equals(name)) {
                if (candidate == null) {
                    candidate = m;
                } else {
                    throw new IllegalArgumentException("more than one method in " + declaringClass + " has name " + name);
                }
            }
        }
        if (candidate == null) {
            throw new IllegalArgumentException(new NoSuchMethodException(declaringClass + "#" + name));
        }
        return defineCFunction(candidate);
    }

    public static CEntryPointLiteral<CFunctionPointer> defineCFunction(Class<?> declaringClass, String name, Class<?>... paramTypes) {
        Method method;
        try {
            method = declaringClass.getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
        return defineCFunction(method);
    }

    public static CEntryPointLiteral<CFunctionPointer> defineCFunction(Method method) {
        var access = method.getModifiers();
        if ((access & Modifier.STATIC) != Modifier.STATIC) {
            throw new IllegalArgumentException("method " + method + " is not static and cannot be used to define a C function");
        }
        if (!method.canAccess(null)) {
            throw new IllegalArgumentException("method " + method + " is not accessible from " + GraalUtils.class.getName());
        }
        // use do-while to implement goto
        //noinspection ConstantValue,LoopStatementThatDoesntLoop
        do {
            var returnType = method.getReturnType();
            if (returnType.isPrimitive()) {
                break;
            }
            if (returnType == VoidPointer.class) {
                break;
            }
            throw new IllegalArgumentException(returnType + " is not allowed for building a C function");
        } while (false);
        for (var p : method.getParameterTypes()) {
            if (p.isPrimitive()) {
                continue;
            }
            if (p == VoidPointer.class) {
                continue;
            }
            if (p == IsolateThread.class) {
                continue;
            }
            throw new IllegalArgumentException(p + " is not allowed for building a C function");
        }
        return CEntryPointLiteral.create(method.getDeclaringClass(), method.getName(), method.getParameterTypes());
    }
}
