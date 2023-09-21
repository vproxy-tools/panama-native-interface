package io.vproxy.pni.exec.internal;

import io.vproxy.base.util.LogType;
import io.vproxy.base.util.Logger;
import io.vproxy.pni.exec.CompilerOptions;
import io.vproxy.pni.exec.WarnType;

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
        if (warnType.check(opts.getWarningFlags())) {
            if (warnType.check(opts.getWarningAsErrorFlags())) {
                if (errors == null) {
                    Logger.error(LogType.ALERT, "[-Werror=" + warnType.name + "] " + msg);
                    throw new RuntimeException(msg);
                } else {
                    if (opts.isVerbose()) {
                        debug(opts, "[-Werror=" + warnType.name + "] " + msg);
                    }
                    var prefix = (path == null ? "" : path + ": ");
                    errors.add(prefix + msg + " [-Werror=" + warnType.name + "]");
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
}
