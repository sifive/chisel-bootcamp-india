

 import chisel3._
 import chisel3.util._
 import chisel3.iotesters.{PeekPokeTester, Driver}

 class PassThru extends Module {
   val io = IO (new Bundle {
     val in = Input(UInt(4.W))
     val out = Output(UInt(4.W))
   })

	io.out := io.in
//   val regIn = RegInit(0.U(4.W))
//   regIn = io.in
//   io.out = regIn
 }

 class PassThruTest (c:PassThru) extends PeekPokeTester(c) {
   poke(c.io.in, 4.U)
   step(1)
   expect(c.io.out,4.U)
   step(1)
 }
  
 object PassThruTester {
   def main(args:Array[String]): Unit = {
     if (!Driver(() => new PassThru(),"verilator")(c => new PassThruTest(c))) System.exit(1)
   }
 }

