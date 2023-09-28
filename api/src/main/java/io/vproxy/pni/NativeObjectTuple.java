package io.vproxy.pni;

import java.util.Objects;

public class NativeObjectTuple {
    public final Class<?> type;
    public final long address;

    public NativeObjectTuple(Class<?> type, long address) {
        Objects.requireNonNull(type);
        this.type = type;
        this.address = address;
    }

    @Override
    public String toString() {
        return "NativeObjectTuple{" +
               "type=" + type +
               ", address=" + address +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NativeObjectTuple that = (NativeObjectTuple) o;

        if (address != that.address) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (int) (address ^ (address >>> 32));
        return result;
    }
}
