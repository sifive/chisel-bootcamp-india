
 /*Exercise: Arbiter
 Task: You will likely need binary operators to complete this exercise
  useful example : Router Project 
   */
 package problems
 import chisel3._
 import chisel3.util._


class Arbiter extends Module {
  val io = IO(new Bundle {
    
    val fifo_valid = Input(Bool())
    val fifo_ready = Output(Bool())
    val fifo_data  = Input(UInt(16.W))
    
    
    val pe0_valid  = Output(Bool())
    val pe0_ready  = Input(Bool())
    val pe0_data   = Output(UInt(16.W))
    
    
    val pe1_valid  = Output(Bool())
    val pe1_ready  = Input(Bool())
    val pe1_data   = Output(UInt(16.W))
  })
  
  io := DontCare 


}
// runMain problems.Arbiter
object  Arbiter extends App{
     chisel3.Driver.execute(args, ()=> new Arbiter)
}



