import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._

import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class GpioTest extends  FlatSpec with ChiselScalatestTester {
behavior of "Gpio Test"

it should "Gpio Test all inputs" in {
//  test(new Gpio(2,8)) { c =>
  test(new Gpio(4,16)).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
	c.apbSel.poke(true.B)
    c.apbWrEn.poke(true.B)
 	c.apbAddr.poke(3.U)
 	c.apbWrData.poke("hAAAA".U)
    //c.apbReady.expect(false.B)
 	c.clock.step(1)
    //c.apbReady.expect(false.B)
    c.apbEnable.poke(true.B)
    c.clock.step(1)
    c.apbReady.expect(true.B)

    c.clock.step(1)
	c.apbSel.poke(false.B)
    c.apbWrEn.poke(false.B)
 	c.apbAddr.poke(0.U)
 	c.apbWrData.poke(0.U)
    c.apbEnable.poke(false.B)
    //c.apbReady.expect(false.B)
    c.clock.step(10)


	c.apbSel.poke(true.B)
    c.apbWrEn.poke(false.B)
 	c.apbAddr.poke(3.U)
 	c.apbWrData.poke(0.U)
    //c.apbReady.expect(false.B)
 	c.clock.step(1)
    //c.apbReady.expect(false.B)
    c.apbEnable.poke(true.B)
    c.clock.step(1)
    c.apbReady.expect(true.B)
    c.apbRdData.expect("hAAAA".U)

    c.clock.step(1)
	c.apbSel.poke(false.B)
    c.apbWrEn.poke(false.B)
 	c.apbAddr.poke(0.U)
 	c.apbWrData.poke(0.U)
    c.apbEnable.poke(false.B)
    //c.apbReady.expect(false.B)
    c.clock.step(10)

    }
  } 
}