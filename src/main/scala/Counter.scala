
import chisel3._
import chisel3.util._

class Counter (count:Int)  extends MultiIOModule {

  val pulse_out = IO(Output(UInt(1.W)))

  def counter(max: UInt) = {
    val x = RegInit(0.asUInt((max.getWidth).W))
    x := Mux(x === max, 0.U, x + 1.U)
    x
  }

  pulse_out := counter(count.U - 1.U) === 0.U

}

