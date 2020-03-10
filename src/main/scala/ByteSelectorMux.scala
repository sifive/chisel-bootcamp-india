
import chisel3._
import chisel3.util._

class ByteSelectorMux extends MultiIOModule {
  val in = IO(Input(UInt(32.W)))
  val sel = IO(Input(UInt(2.W)))
  val out = IO(Output(UInt(8.W)))

  out := Mux( sel(1), Mux (sel(0),in(31,24), in(23,16)),
                      Mux (sel(0),in(15,8), in(7,0)))

}

