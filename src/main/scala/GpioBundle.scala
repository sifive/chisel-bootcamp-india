
import chisel3._
import chisel3.util._

package gpio { 

  class apbInterface(val addrWidth:Int , val dataWidth:Int) extends Bundle {
    val apbSel = Input(Bool())
    val apbEnable = Input(Bool())
    val apbWrEn = Input(Bool())
    val apbAddr = Input(UInt(addrWidth.W))
    val apbWrData = Input(UInt(dataWidth.W))
    val apbReady = Output(Bool()) 
    val apbRdData = Output(UInt(dataWidth.W))
  }

  class GpioBundle (val addrWidth:Int = 4 , val dataWidth:Int= 16) extends Module {  

    val io = IO(new apbInterface(addrWidth,dataWidth))

    val write = WireInit(false.B)
    val read  = WireInit(false.B)
    val intRegister = Reg(Vec((1 << addrWidth), UInt(dataWidth.W)))

    //validWire := apbSel & apbEnable
    val valid =  RegNext(io.apbSel & io.apbEnable)  
    //valid :=  io.apbSel & io.apbEnable
    write := valid & io.apbWrEn & io.apbReady
    read := valid & !io.apbWrEn & io.apbReady
    io.apbReady := Mux(valid, true.B,false.B)
    io.apbRdData := Mux(read, intRegister(io.apbAddr), 0.U)
    intRegister(io.apbAddr) := Mux(write, io.apbWrData, intRegister(io.apbAddr))

  }
}
