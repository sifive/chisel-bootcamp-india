import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._

import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._
import gpio._



class GpioBundleTest extends  FlatSpec with ChiselScalatestTester {
behavior of "Gpio Test"

it should "Gpio Test all inputs" in {
//  test(new Gpio(2,8)) { c =>
  test(new GpioBundle(4,16)).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
	c.io.apbSel.poke(true.B)
    c.io.apbWrEn.poke(true.B)
 	c.io.apbAddr.poke(3.U)
 	c.io.apbWrData.poke("hAAAA".U)
    //c.io.apbReady.expect(false.B)
 	c.clock.step(1)
    //c.io.apbReady.expect(false.B)
    c.io.apbEnable.poke(true.B)
    c.clock.step(1)
    c.io.apbReady.expect(true.B)

    c.clock.step(1)
	c.io.apbSel.poke(false.B)
    c.io.apbWrEn.poke(false.B)
 	c.io.apbAddr.poke(0.U)
 	c.io.apbWrData.poke(0.U)
    c.io.apbEnable.poke(false.B)
    //c.io.apbReady.expect(false.B)
    c.clock.step(10)


	c.io.apbSel.poke(true.B)
    c.io.apbWrEn.poke(false.B)
 	c.io.apbAddr.poke(3.U)
 	c.io.apbWrData.poke(0.U)
    //c.io.apbReady.expect(false.B)
 	c.clock.step(1)
    //c.io.apbReady.expect(false.B)
    c.io.apbEnable.poke(true.B)
    c.clock.step(1)
    c.io.apbReady.expect(true.B)
    c.io.apbRdData.expect("hAAAA".U)

    c.clock.step(1)
	c.io.apbSel.poke(false.B)
    c.io.apbWrEn.poke(false.B)
 	c.io.apbAddr.poke(0.U)
 	c.io.apbWrData.poke(0.U)
    c.io.apbEnable.poke(false.B)
    //g.apbReady.expect(false.B)
    c.clock.step(10)

    }
  } 
}