 package problems
 import chisel3._
 import chisel3.util._


class MyRoutingArbiter(numChannels: Int) extends Module {
  val io = IO(new Bundle {
    val in = Vec(numChannels, Flipped(Decoupled(UInt(8.W))))
    val out = Decoupled(UInt(8.W))
  } )
  io := DontCare
}

object MyRoutingArbiter extends App{
    chisel3.Driver.execute(args ,()=>new  MyRoutingArbiter(4))
}
