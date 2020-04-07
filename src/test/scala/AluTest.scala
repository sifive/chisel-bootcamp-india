import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest._

import chiseltest.internal.WriteVcdAnnotation
import chiseltest.internal.VerilatorBackendAnnotation
import chisel3.tester.experimental.TestOptionBuilder._

class AluTest extends  FlatSpec with ChiselScalatestTester {
behavior of "ALU Test"

it should "ALU Test all inputs" in {
  test(new alu) { c =>
//   test(new alu).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { c =>
	c.ALU_In_a.poke(100.U)
	c.ALU_In_b.poke(155.U)
	c.ALU_Sel.poke(0.U)
	c.clock.step(1)
	c.ALU_Out.expect(255.U)

	c.ALU_In_a.poke(155.U)
	c.ALU_In_b.poke(100.U)
	c.ALU_Sel.poke(1.U)
	c.clock.step(1)
	c.ALU_Out.expect(55.U)

	c.ALU_In_a.poke(35.U)
	c.ALU_In_b.poke(65.U)
	c.ALU_Sel.poke(2.U)
	c.clock.step(1)
	c.ALU_Out.expect(2275.U)

	c.ALU_In_a.poke(250.U)
	c.ALU_In_b.poke(10.U)
	c.ALU_Sel.poke(3.U)
	c.clock.step(1)
	c.ALU_Out.expect(25.U)

	c.ALU_In_a.poke(2.U)
	c.ALU_Sel.poke(4.U)
	c.clock.step(1)
	c.ALU_Out.expect(4.U)

	c.ALU_In_a.poke(4.U)
	c.ALU_Sel.poke(5.U)
	c.clock.step(1)
	c.ALU_Out.expect(2.U)

	c.ALU_In_a.poke(2.U)
	c.ALU_Sel.poke(6.U)
	c.clock.step(1)
	c.ALU_Out.expect(4.U)

	c.ALU_In_a.poke(4.U)
	c.ALU_Sel.poke(7.U)
	c.clock.step(1)
	c.ALU_Out.expect(2.U)

	c.ALU_In_a.poke(255.U)
	c.ALU_In_b.poke(255.U)
	c.ALU_Sel.poke(8.U)
	c.clock.step(1)
	c.ALU_Out.expect(255.U)

    c.ALU_In_a.poke(255.U)
	c.ALU_In_b.poke(0.U)
	c.ALU_Sel.poke(9.U)
	c.clock.step(1)
	c.ALU_Out.expect(255.U)

	c.ALU_In_a.poke(100.U)
	c.ALU_In_b.poke(100.U)
	c.ALU_Sel.poke(10.U)
	c.clock.step(1)
	c.ALU_Out.expect(0.U)

	c.ALU_In_a.poke(0.U)
	c.ALU_In_b.poke(0.U)
	c.ALU_Sel.poke(11.U)
	c.clock.step(1)
	c.ALU_Out.expect(65535.U)

	c.ALU_In_a.poke(65535.U)
	c.ALU_In_b.poke(0.U)
	c.ALU_Sel.poke(12.U)
	c.clock.step(1)
	c.ALU_Out.expect(65535.U)

	c.ALU_In_a.poke(100.U)
	c.ALU_In_b.poke(100.U)
	c.ALU_Sel.poke(13.U)
	c.clock.step(1)
	c.ALU_Out.expect(65535.U)

	c.ALU_In_a.poke(101.U)
	c.ALU_In_b.poke(100.U)
	c.ALU_Sel.poke(14.U)
	c.clock.step(1)
	c.ALU_Out.expect(1.U)

	c.ALU_In_a.poke(100.U)
	c.ALU_In_b.poke(101.U)
	c.ALU_Sel.poke(14.U)
	c.clock.step(1)
	c.ALU_Out.expect(0.U)

    c.ALU_In_a.poke(100.U)
	c.ALU_In_b.poke(100.U)
	c.ALU_Sel.poke(15.U)
	c.clock.step(1)
	c.ALU_Out.expect(1.U)

	c.ALU_In_a.poke(100.U)
	c.ALU_In_b.poke(200.U)
	c.ALU_Sel.poke(15.U)
	c.clock.step(1)
	c.ALU_Out.expect(0.U)

	}
   }

}