

import chisel3._
import chisel3.util._


class Gpio (addrWidth:Int , dataWidth:Int) extends MultiIOModule {  
   val apbSel = IO(Input(Bool()))
   val apbEnable = IO(Input(Bool()))
   val apbWrEn = IO(Input(Bool()))
   val apbAddr = IO(Input(UInt(addrWidth.W)))
   val apbWrData = IO(Input(UInt(dataWidth.W)))
   val apbReady = IO(Output(Bool())) 
   val apbRdData = IO(Output(UInt(dataWidth.W)))

   val write = WireInit(false.B)
   val read  = WireInit(false.B)
   val intRegister = Reg(Vec((1 << addrWidth), UInt(dataWidth.W)))
  
   //validWire := apbSel & apbEnable
   val valid =  RegNext(apbSel & apbEnable)
   write := valid & apbWrEn & apbReady
   read := valid & !apbWrEn & apbReady
   apbReady := Mux(valid, true.B,false.B)
   apbRdData := Mux(read, intRegister(apbAddr), 0.U)
   when (write) { intRegister(apbAddr) := apbWrData }

}

