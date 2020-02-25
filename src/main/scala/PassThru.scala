
import chisel3._
import chisel3.util._

 class PassThru extends MultiIOModule {
   val in = IO(Input(UInt(4.W)))
   val out = IO(Output(UInt(4.W)))
    out := in
    //printf(p"test = $in \n")
  }

