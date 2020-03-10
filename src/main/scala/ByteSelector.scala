
import chisel3._
import chisel3.util._

class ByteSelector extends MultiIOModule {
  val in = IO(Input(UInt(32.W)))
  val sel = IO(Input(UInt(2.W)))
  val out = IO(Output(UInt(8.W)))

    when(sel === 0.U) {
      out := in(7,0)
    }
    .elsewhen(sel === 1.U ) {
      out := in(15,8)
    }
    .elsewhen(sel === 2.U ) {
      out := in(23,16)
    }
    .otherwise {
      out := in(31,24)
    }

}

