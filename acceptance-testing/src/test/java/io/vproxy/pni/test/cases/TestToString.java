package io.vproxy.pni.test.cases;

import io.vproxy.pni.Allocator;
import io.vproxy.pni.PNIFunc;
import io.vproxy.pni.PNIRef;
import io.vproxy.pni.test.ToStringArray;
import io.vproxy.pni.test.ToStringClass;
import io.vproxy.pni.test.ToStringClass2;
import io.vproxy.pni.test.ToStringUnion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestToString {
    @Test
    public void recurse() {
        try (var allocator = Allocator.ofConfined()) {
            var c = new ToStringClass(allocator);
            c.setNum(123);
            c.getCr().setC(c);

            assertEquals("ToStringClass{\n" +
                         "    num => 123,\n" +
                         "    cr => ToStringClassRecurse{\n" +
                         "        num => 0,\n" +
                         "        c => <...>@" + Long.toString(c.MEMORY.address(), 16) + ",\n" +
                         "        arri => IntArray[]@" + Long.toString(c.getCr().getArri().MEMORY.address(), 16) + "\n" +
                         "    }@" + Long.toString(c.getCr().MEMORY.address(), 16) + "\n" +
                         "}@" + Long.toString(c.MEMORY.address(), 16), c.toString());
        }
    }

    @Test
    public void class2() {
        var ref = PNIRef.of(777);
        var func = PNIFunc.VoidFunc.of(v -> 1);
        try (var allocator = Allocator.ofConfined()) {
            var c = new ToStringClass2(allocator);
            c.setNum(123);
            c.setRef(ref);
            c.setFunc(func);
            var arrc = new ToStringClass.Array(allocator, 2);
            arrc.get(0).setNum(456);
            arrc.get(1).setNum(789);
            c.setArrc(arrc);

            assertEquals("ToStringClass2{\n" +
                         "    num => 123,\n" +
                         "    ref => PNIRef(777)@" + Long.toString(ref.MEMORY.address(), 16) + ",\n" +
                         "    func => PNIFunc.VoidFunc(" + func.getCallSite() + ")@" + Long.toString(func.MEMORY.address(), 16) + ",\n" +
                         "    arrc => ToStringClass.Array[\n" +
                         "        [0] ToStringClass{\n" +
                         "            num => 456,\n" +
                         "            cr => ToStringClassRecurse{\n" +
                         "                num => 0,\n" +
                         "                c => null,\n" +
                         "                arri => IntArray[]@" + Long.toString(c.getArrc().get(0).getCr().getArri().MEMORY.address(), 16) + "\n" +
                         "            }@" + Long.toString(c.getArrc().get(0).getCr().MEMORY.address(), 16) + "\n" +
                         "        }@" + Long.toString(c.getArrc().get(0).MEMORY.address(), 16) + ",\n" +
                         "        [1] ToStringClass{\n" +
                         "            num => 789,\n" +
                         "            cr => ToStringClassRecurse{\n" +
                         "                num => 0,\n" +
                         "                c => null,\n" +
                         "                arri => IntArray[]@" + Long.toString(c.getArrc().get(1).getCr().getArri().MEMORY.address(), 16) + "\n" +
                         "            }@" + Long.toString(c.getArrc().get(1).getCr().MEMORY.address(), 16) + "\n" +
                         "        }@" + Long.toString(c.getArrc().get(1).MEMORY.address(), 16) + "\n" +
                         "    ]@" + Long.toString(c.getArrc().MEMORY.address(), 16) + "\n" +
                         "}@" + Long.toString(c.MEMORY.address(), 16),
                c.toString());
        } finally {
            ref.close();
        }
    }

    @Test
    public void array() {
        try (var allocator = Allocator.ofConfined()) {
            var s = new ToStringArray(allocator);
            s.getArrcLen2().get(0).setNum(123);
            s.getArrcLen2().get(1).setNum(456);
            var carr = new ToStringClass.Array(allocator, 1);
            s.setParrc(carr);
            carr.get(0).setNum(789);
            assertEquals("ToStringArray{\n" +
                         "    arrc => ToStringClass.Array[]@" + Long.toString(s.getArrc().MEMORY.address(), 16) + ",\n" +
                         "    arrcLen2 => ToStringClass.Array[\n" +
                         "        [0] ToStringClass{\n" +
                         "            num => 123,\n" +
                         "            cr => ToStringClassRecurse{\n" +
                         "                num => 0,\n" +
                         "                c => null,\n" +
                         "                arri => IntArray[]@" + Long.toString(s.getArrcLen2().get(0).getCr().getArri().MEMORY.address(), 16) + "\n" +
                         "            }@" + Long.toString(s.getArrcLen2().get(0).getCr().MEMORY.address(), 16) + "\n" +
                         "        }@" + Long.toString(s.getArrcLen2().get(0).MEMORY.address(), 16) + ",\n" +
                         "        [1] ToStringClass{\n" +
                         "            num => 456,\n" +
                         "            cr => ToStringClassRecurse{\n" +
                         "                num => 0,\n" +
                         "                c => null,\n" +
                         "                arri => IntArray[]@" + Long.toString(s.getArrcLen2().get(1).getCr().getArri().MEMORY.address(), 16) + "\n" +
                         "            }@" + Long.toString(s.getArrcLen2().get(1).getCr().MEMORY.address(), 16) + "\n" +
                         "        }@" + Long.toString(s.getArrcLen2().get(1).MEMORY.address(), 16) + "\n" +
                         "    ]@" + Long.toString(s.getArrcLen2().MEMORY.address(), 16) + ",\n" +
                         "    parrc => ToStringClass.Array[\n" +
                         "        [0] ToStringClass{\n" +
                         "            num => 789,\n" +
                         "            cr => ToStringClassRecurse{\n" +
                         "                num => 0,\n" +
                         "                c => null,\n" +
                         "                arri => IntArray[]@" + Long.toString(s.getParrc().get(0).getCr().getArri().MEMORY.address(), 16) + "\n" +
                         "            }@" + Long.toString(s.getParrc().get(0).getCr().MEMORY.address(), 16) + "\n" +
                         "        }@" + Long.toString(s.getParrc().get(0).MEMORY.address(), 16) + "\n" +
                         "    ]@" + Long.toString(s.getParrc().MEMORY.address(), 16) + "\n" +
                         "}@" + Long.toString(s.MEMORY.address(), 16),
                s.toString());
        }
    }

    @Test
    public void union() {
        try (var allocator = Allocator.ofConfined()) {
            var u = new ToStringUnion(allocator);
            u.setNum(123);

            assertEquals("ToStringUnion(\n" +
                         "    num => 123,\n" +
                         "    c1 => ToStringClass{\n" +
                         "        num => 123,\n" +
                         "        cr => ToStringClassRecurse{\n" +
                         "            num => 0,\n" +
                         "            c => <?>,\n" +
                         "            arri => <?>\n" +
                         "        }@" + Long.toString(u.getC1().getCr().MEMORY.address(), 16) + "\n" +
                         "    }@" + Long.toString(u.getC1().MEMORY.address(), 16) + ",\n" +
                         "    c2 => ToStringClass2{\n" +
                         "        num => 123,\n" +
                         "        ref => <?>,\n" +
                         "        func => <?>,\n" +
                         "        arrc => <?>\n" +
                         "    }@" + Long.toString(u.getC2().MEMORY.address(), 16) + ",\n" +
                         "    cr => ToStringClassRecurse{\n" +
                         "        num => 123,\n" +
                         "        c => <?>,\n" +
                         "        arri => <?>\n" +
                         "    }@" + Long.toString(u.getCr().MEMORY.address(), 16) + ",\n" +
                         "    pc1 => <?>,\n" +
                         "    pc2 => <?>,\n" +
                         "    pcr => <?>\n" +
                         ")@" + Long.toString(u.MEMORY.address(), 16),
                u.toString());
        }
    }
}
