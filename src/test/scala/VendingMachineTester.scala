
import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._
import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class VendingMachineTest extends FlatSpec with ChiselScalatestTester {
  behavior of "VendingMachine Block" 

  it should "work as expected" in {
    test(new VendingMachine).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
      var money = 0
      var isValid = false
      val rnd = new scala.util.Random
      for (t <- 0 until 20) {
        val coin     = rnd.nextInt(3)*5
        val isNickel = coin == 5
        val isDime   = coin == 10

        // Advance circuit
        c.nickel.poke( if (isNickel) 1.U else 0.U)
        c.dime.poke( if (isDime) 1.U else 0.U)
        c.clock.step(1)

        // Advance model
        money = if (isValid) 0 else money + coin
        isValid = money >= 20

        // Compare
        c.vend.expect(if (isValid) 1.U else 0.U)
      }
    }
  }
}
