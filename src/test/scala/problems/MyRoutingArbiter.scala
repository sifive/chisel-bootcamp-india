
 package problems

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}


class MyRoutingArbiterTester(c: MyRoutingArbiter) extends PeekPokeTester(c) {

  for(i <- 0 until 4) {
    poke(c.io.in(i).valid, 0)
    poke(c.io.in(i).bits, i)
    poke(c.io.out.ready, 1)
  }
    
  expect(c.io.out.valid, 0)
    

  for (i <- 0 until 4) {
    poke(c.io.in(i).valid, 1)
    expect(c.io.out.valid, 1)
    expect(c.io.out.bits, i)
      
    poke(c.io.out.ready, 0)
    expect(c.io.in(i).ready, 0)
      
    poke(c.io.out.ready, 1)
    poke(c.io.in(i).valid, 0)
  }
    
 
  poke(c.io.in(1).valid, 1)
  poke(c.io.in(2).valid, 1)
  expect(c.io.out.bits, 1)
  expect(c.io.in(1).ready, 1)
  expect(c.io.in(0).ready, 0)
    
  poke(c.io.out.ready, 0)
  expect(c.io.in(1).ready, 0)
}




 
class MyRoutingArbiterTests  extends ChiselFlatSpec {
  behavior of "Module Tests "
  backends foreach {backend =>
    it should s"correctly respond to inputs in $backend" in {
      Driver(() => new MyRoutingArbiter(4), backend)(c => new MyRoutingArbiterTester(c)) should be (true)
    }
  }
}                                                                                                                                    

object MyRoutingArbiterMain extends App{
   
iotesters.Driver.execute(args= Array("--backend-name","verilator")  , ()=> new MyRoutingArbiter(4)){
 c=> new MyRoutingArbiterTester(c)
  }
}

