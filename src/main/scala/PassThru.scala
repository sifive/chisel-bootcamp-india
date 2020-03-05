
import chisel3._
import chisel3.util._

 class PassThru (width:Int)  extends MultiIOModule {
   val in = IO(Input(UInt(width.W)))
   val out = IO(Output(UInt(width.W)))

   val register = RegInit(0.U(width.W))
   register := in
   out := register
  }

