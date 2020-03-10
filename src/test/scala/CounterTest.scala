
import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._
import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class CounterTest extends FlatSpec with ChiselScalatestTester {
  behavior of "Counter Block" 

  it should "work as expected" in {
     test(new Counter (4)).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      c.clock.step(4)
      c.pulse_out.expect(1.U)
      c.clock.step(2)
      c.pulse_out.expect(0.U)
    }
  }
}


