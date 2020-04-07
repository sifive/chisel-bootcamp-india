import chisel3._
import chisel3.util._


class alu extends MultiIOModule{

   val ALU_In_a = IO(Input(UInt(16.W)))
   val ALU_In_b = IO(Input(UInt(16.W)))
   val ALU_Sel = IO(Input(UInt(4.W)))
   val ALU_Out = IO(Output(UInt(16.W)))
   
   ALU_Out:= 0.U

   switch (ALU_Sel) {
     is (0.U) { ALU_Out := ALU_In_a + ALU_In_b }            
     is (1.U) { ALU_Out := ALU_In_a - ALU_In_b }
     is (2.U) { ALU_Out := ALU_In_a * ALU_In_b }
     is (3.U) { ALU_Out := ALU_In_a / ALU_In_b }
     is (4.U) { ALU_Out := 1.U << ALU_In_a }
     is (5.U) { ALU_Out := ALU_In_a >> 1.U }
     is (6.U) { ALU_Out := Cat(ALU_In_a(14,0),ALU_In_a(15)) }
     is (7.U) { ALU_Out := Cat(ALU_In_a(0), ALU_In_a(15,1)) }
     is (8.U) { ALU_Out := ALU_In_a & ALU_In_b }
     is (9.U) { ALU_Out := ALU_In_a | ALU_In_b }
     is (10.U) { ALU_Out := ALU_In_a ^ ALU_In_b }
     is (11.U) { ALU_Out := ~(ALU_In_a | ALU_In_b) }
     is (12.U) { ALU_Out := ~(ALU_In_a & ALU_In_b) }
     is (13.U) { ALU_Out := ~(ALU_In_a ^ ALU_In_b) }
     is (14.U) { ALU_Out := Mux ((ALU_In_a > ALU_In_b),1.U,0.U) }
     is (15.U) { ALU_Out := Mux ((ALU_In_a === ALU_In_b),1.U,0.U) }
   }
}