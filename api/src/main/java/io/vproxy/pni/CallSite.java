package io.vproxy.pni;

@FunctionalInterface
public interface CallSite<T> {
    int call(T ref);
}
