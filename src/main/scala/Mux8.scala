
import chisel3._
import chisel3.util._

class Mux4 (width:Int) extends MultiIOModule {
  val in = IO(Input(Vec(4,UInt(width.W))))
  val sel = IO(Input(UInt(2.W)))
  val out = IO(Output(UInt(width.W)))

  out := Mux(sel(1), Mux(sel(0), in(3), in(2)),
                     Mux(sel(0), in(1), in(0)))

}

class Mux8 (width:Int) extends MultiIOModule {

  val in = IO(Input(Vec(8,UInt(width.W))))
  val sel = IO(Input(UInt(3.W)))
  val out = IO(Output(UInt(width.W)))

  val mux4 = Seq.fill(2)(Module (new Mux4(width)))
  for ( i <- 0 to 1) {
    for (j <- 0 to 3) mux4(i).in(j) := in((i*4) + j)
    mux4(i).sel := sel(1,0)
  }
  out := Mux(sel(2), mux4(1).out, mux4(0).out)
}

