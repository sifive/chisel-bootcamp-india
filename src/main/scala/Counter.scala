
import chisel3._
import chisel3.util._
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

// Chisel Code: Declare a new module definition
class Counter extends Module {
  val io = IO(new Bundle {
    val pulse_out = Output(UInt(1.W))
  })
  
  def counter(max: UInt) = {
       val x = RegInit(0.asUInt(max.getWidth))
         x := Mux(x === max, 0.U, x + 1.U)
           x
  }
  // Produce pulse every n cycles.
  def pulse(n: UInt) = counter(n - 1.U) === 0.U

  pulse_out := pulse(10)
}

object CounterDriver extends App {
  chisel3.Driver.execute(args, () => new Counter)
}
