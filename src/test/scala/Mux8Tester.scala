
import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._
import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class Mux8Test extends FlatSpec with ChiselScalatestTester {
  behavior of "Mux8 Block" 

  it should "work as expected" in {
    test(new Mux8 (4)).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      val rnd = new scala.util.Random
      for ( testCount  <- 0 to 10) {
        for ( i <- 0 to 7 ) c.in(i).poke(i.U)
        var expectValue = rnd.nextInt(8).U
        c.sel.poke(expectValue)
        c.clock.step(1)
        c.out.expect(expectValue)
      }
    }
  }
}


