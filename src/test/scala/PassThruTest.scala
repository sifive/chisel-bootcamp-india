
import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._
import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class PassThruTest extends FlatSpec with ChiselScalatestTester {
  behavior of "PassThru Block" 

  it should "work as expected" in {
    test(new PassThru(4)).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      c.in.poke(4.U)
      c.clock.step(10)
      c.out.expect(4.U)
    }
  }

  it should "Fail" in {
    test(new PassThru(4)).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      c.in.poke(4.U)
      c.clock.step(10)
      c.out.expect(4.U, "Expecting 2")
    }
  }
}


