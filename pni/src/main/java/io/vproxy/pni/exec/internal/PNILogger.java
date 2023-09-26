package io.vproxy.pni.exec.internal;

import io.vproxy.base.util.LogType;
import io.vproxy.base.util.Logger;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.WarnType;
import io.vproxy.pni.exec.ast.AstAnno;
import io.vproxy.pni.exec.type.AnnoSuppressTypeInfo;

import java.util.List;

public class PNILogger {
    private PNILogger() {
    }

    public static void debug(CompilerOptions opts, String msg) {
        if (opts.isVerbose()) {
            Logger.trace(LogType.ALERT, msg);
        }
    }

    public static void warn(CompilerOptions opts, WarnType warnType, String msg) {
        warn(null, null, opts, warnType, msg);
    }

    public static void warn(List<String> errors, String path, CompilerOptions opts, WarnType warnType, String msg) {
        warn(errors, path, null, opts, warnType, msg);
    }

    public static void warn(List<String> errors, String path, List<AstAnno> annos, CompilerOptions opts, WarnType warnType, String msg) {
        var prefix = (path == null ? "" : path + ": ");
        msg = prefix + msg;

        if (warnType.check(opts.getWarningFlags())) {
            if (annos != null) {
                if (suppressed(annos, warnType)) {
                    if (opts.isVerbose()) {
                        debug(opts, "[suppressed] " + msg);
                        return;
                    }
                }
            }

            if (warnType.check(opts.getWarningAsErrorFlags())) {
                if (errors == null) {
                    Logger.error(LogType.ALERT, "[-Werror=" + warnType.name + "] " + msg);
                    throw new RuntimeException(msg);
                } else {
                    if (opts.isVerbose()) {
                        debug(opts, "[-Werror=" + warnType.name + "] " + msg);
                    }
                    errors.add(msg + " [-Werror=" + warnType.name + "]");
                }
            } else {
                Logger.warn(LogType.ALERT, "[-W" + warnType.name + "] " + msg);
            }
        } else {
            if (opts.isVerbose()) {
                debug(opts, "[-Wno-" + warnType.name + "] " + msg);
            }
        }
    }

    private static boolean suppressed(List<AstAnno> annos, WarnType warnType) {
        var annoOpt = annos.stream().filter(s -> s.typeRef instanceof AnnoSuppressTypeInfo).findFirst();
        if (annoOpt.isEmpty()) {
            return false;
        }
        var anno = annoOpt.get();
        var vOpt = anno.values.stream().filter(v -> v.name.equals("value")).findFirst();
        if (vOpt.isEmpty()) {
            return false;
        }
        var value = vOpt.get().value;
        if (!(value instanceof List)) {
            return false;
        }
        for (var o : (List<?>) value) {
            if (!(o instanceof String)) {
                return false;
            }
        }
        //noinspection unchecked
        var ls = (List<String>) value;
        if (ls.isEmpty()) {
            return false;
        }
        for (var s : ls) {
            if (s.equals(warnType.name)) {
                return true;
            }
        }
        return false;
    }
}
