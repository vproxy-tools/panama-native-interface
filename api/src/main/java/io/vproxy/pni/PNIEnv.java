package io.vproxy.pni;

import io.vproxy.pni.exception.PNIException;

import java.lang.foreign.*;
import java.lang.invoke.VarHandle;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class PNIEnv implements NativeObject {
    public static final MemoryLayout LAYOUT = MemoryLayout.structLayout(
        PNIExceptionNativeRepresentation.LAYOUT.withName("ex"),
        MemoryLayout.unionLayout(
            ValueLayout.JAVA_BYTE.withName("return_byte"),
            ValueLayout.JAVA_CHAR_UNALIGNED.withName("return_char"),
            ValueLayout.JAVA_DOUBLE_UNALIGNED.withName("return_double"),
            ValueLayout.JAVA_INT_UNALIGNED.withName("return_int"),
            ValueLayout.JAVA_FLOAT_UNALIGNED.withName("return_float"),
            ValueLayout.JAVA_LONG_UNALIGNED.withName("return_long"),
            ValueLayout.JAVA_SHORT_UNALIGNED.withName("return_short"),
            ValueLayout.JAVA_BOOLEAN.withName("return_bool"),
            ValueLayout.ADDRESS_UNALIGNED.withName("return_pointer"),
            PNIBuf.LAYOUT.withName("buf")
        ).withName("union0")
    );

    public final MemorySegment MEMORY;

    @Override
    public MemorySegment MEMORY() {
        return MEMORY;
    }

    private final PNIExceptionNativeRepresentation ex;
    private final PNIBuf buf;

    public PNIEnv(Allocator allocator) {
        this.MEMORY = allocator.allocate(LAYOUT.byteSize());
        this.ex = new PNIExceptionNativeRepresentation(MEMORY.asSlice(0, PNIExceptionNativeRepresentation.LAYOUT.byteSize()));
        this.buf = new PNIBuf(MEMORY.asSlice(PNIExceptionNativeRepresentation.LAYOUT.byteSize()));
    }

    public PNIExceptionNativeRepresentation ex() {
        return ex;
    }

    private static final VarHandle return_byteVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_byte")
    );

    public byte returnByte() {
        return (byte) return_byteVH.get(MEMORY);
    }

    private static final VarHandle return_charVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_char")
    );

    public char returnChar() {
        return (char) return_charVH.get(MEMORY);
    }

    private static final VarHandle return_doubleVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_double")
    );

    public double returnDouble() {
        return (double) return_doubleVH.get(MEMORY);
    }

    private static final VarHandle return_intVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_int")
    );

    public int returnInt() {
        return (int) return_intVH.get(MEMORY);
    }

    private static final VarHandle return_floatVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_float")
    );

    public float returnFloat() {
        return (float) return_floatVH.get(MEMORY);
    }

    private static final VarHandle return_longVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_long")
    );

    public long returnLong() {
        return (long) return_longVH.get(MEMORY);
    }

    private static final VarHandle return_shortVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_short")
    );

    public short returnShort() {
        return (short) return_shortVH.get(MEMORY);
    }

    private static final VarHandle return_boolVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_bool")
    );

    public boolean returnBool() {
        return (boolean) return_boolVH.get(MEMORY);
    }

    private static final VarHandle return_pointerVH = LAYOUT.varHandle(
        MemoryLayout.PathElement.groupElement("union0"),
        MemoryLayout.PathElement.groupElement("return_pointer")
    );

    public MemorySegment returnPointer() {
        var seg = (MemorySegment) return_pointerVH.get(this.MEMORY);
        if (seg.address() == 0) {
            return null;
        }
        return seg;
    }

    public PNIBuf returnBuf() {
        return buf;
    }

    public void reset() {
        buf.setToNull(); // the union will be cleared
        ex.reset();
    }

    public <EX extends Throwable> void throwIf(Class<EX> exClass) throws EX {
        var exType = ex().typeClass();
        var msg = ex().message();
        if (exType == null) {
            throw new PNIException(msg);
        }
        if (!exClass.isAssignableFrom(exType)) {
            return;
        }
        try {
            //noinspection unchecked
            throw (EX) exType.getConstructor(String.class).newInstance(msg);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException e) {
            throw new PNIException("constructing exception object failed, original error message: " + msg, e);
        } catch (InvocationTargetException e) {
            throw new PNIException("constructing exception object failed, original error message: " + msg, e.getCause());
        }
    }

    public void throwLast() {
        var msg = ex().message();
        throw new PNIException(msg);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(sb, 0, new HashSet<>(), false);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder sb, int indent, Set<NativeObjectTuple> visited, boolean corrupted) {
        if (corrupted) {
            sb.append("<?>@").append(Long.toString(MEMORY.address(), 16));
            return;
        }
        sb.append("PNIEnv{\n");
        {
            sb.append(" ".repeat(indent + 4)).append("ex => ");
            ex().toString(sb, indent + 4, visited, corrupted);
        }
        sb.append(",\n");
        {
            sb.append(" ".repeat(indent + 4)).append("return_ => union {\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_byte => ").append(returnByte());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_char => ")
                    .append(PanamaUtils.charToASCIIString(returnChar()));
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_double => ").append(returnDouble());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_int => ").append(returnInt());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_float => ").append(returnFloat());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_long => ").append(returnLong());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_short => ").append(returnShort());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_bool => ").append(returnBool());
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_pointer => ").append(PanamaUtils.memorySegmentToString(returnPointer()));
            }
            sb.append(",\n");
            {
                sb.append(" ".repeat(indent + 8)).append("return_buf => ");
                returnBuf().toString(sb, indent + 8, visited, true);
            }
            sb.append("\n");
            sb.append(" ".repeat(indent + 4)).append("}@").append(Long.toString(MEMORY.address() + PNIExceptionNativeRepresentation.LAYOUT.byteSize(), 16)).append("\n");
        }
        sb.append(" ".repeat(indent)).append("}@").append(Long.toString(MEMORY.address(), 16));
    }
}
