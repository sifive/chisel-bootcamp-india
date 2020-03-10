
import chisel3._
import chisel3.util._

class ByteSelectorSwitch extends MultiIOModule {
  val in = IO(Input(UInt(32.W)))
  val sel = IO(Input(UInt(2.W)))
  val out = IO(Output(UInt(8.W)))

  out := 0.U
  switch (sel) {
    is (0.U) { out := in(7,0)   }
    is (1.U) { out := in(15,8)  }
    is (2.U) { out := in(23,16) }
    is (3.U) { out := in(31,24) }
  }
}

