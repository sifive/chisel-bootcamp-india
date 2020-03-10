
import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._
import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class ByteSelectorTest extends FlatSpec with ChiselScalatestTester {
  behavior of "Byte Selector Block" 

  it should "work as expected" in {
    test(new ByteSelector).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      c.in.poke(0x12345678.U)
      c.sel.poke(0x0.U)
      c.out.expect(0x78.U)
      c.sel.poke(0x1.U)
      c.out.expect(0x56.U)
      c.sel.poke(0x2.U)
      c.out.expect(0x34.U)
      c.sel.poke(0x3.U)
      c.out.expect(0x12.U)
    }
  }

  it should "work as expected with switch" in {
    test(new ByteSelectorSwitch).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      c.in.poke(0x12345678.U)
      c.sel.poke(0x0.U)
      c.out.expect(0x78.U)
      c.sel.poke(0x1.U)
      c.out.expect(0x56.U)
      c.sel.poke(0x2.U)
      c.out.expect(0x34.U)
      c.sel.poke(0x3.U)
      c.out.expect(0x12.U)
    }
  }

  it should "work as expected with Mux " in {
    test(new ByteSelectorMux).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      c.in.poke(0x12345678.U)
      c.sel.poke(0x0.U)
      c.out.expect(0x78.U)
      c.sel.poke(0x1.U)
      c.out.expect(0x56.U)
      c.sel.poke(0x2.U)
      c.out.expect(0x34.U)
      c.sel.poke(0x3.U)
      c.out.expect(0x12.U)
    }
  }
}


