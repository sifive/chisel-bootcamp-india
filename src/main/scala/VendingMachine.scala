
import chisel3._
import chisel3.util._

class VendingMachine extends MultiIOModule {
  val nickel = IO(Input(UInt(1.W)))
  val dime   = IO(Input(UInt(1.W)))
  val vend   = IO(Output(UInt(1.W)))

  val state0::state5::state10::state15::state20::Nil = Enum(5)
  val state = RegInit(state0)

  vend := 0.U
  switch(state) {
    is (state0) { 
                  state := state0
                  vend  := 0.U
                  switch(nickel) { is (1.U) { state := state5  }}
                  switch(dime)   { is (1.U) { state := state10 }} 
                  }
    is (state5) { 
                  state := state5
                  vend  := 0.U
                  switch(nickel) { is (1.U) { state := state10 }}
                  switch(dime)   { is (1.U) { state := state15 }} 
                  }
    is (state10){ 
                  state := state10
                  vend  := 0.U
                  switch(nickel) { is (1.U) { state := state15 }}
                  switch(dime)   { is (1.U) { state := state20 }} 
                  }
    is (state15){ 
                  state := state15
                  vend  := 0.U
                  switch(nickel) { is (1.U) { state := state20 }}
                  switch(dime)   { is (1.U) { state := state20 }} 
                  }
    is (state20) { 
                  state := state0
                  vend  := 1.U
                  }
  }
}

