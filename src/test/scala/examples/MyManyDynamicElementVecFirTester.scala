package examples

import java.io.File

import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

import scala.collection._

class MyManyDynamicElementVecFirTester(c: MyManyDynamicElementVecFir) extends PeekPokeTester(c){
  

    poke(c.io.in, 0)
    poke(c.io.consts(0), 1)
    poke(c.io.consts(1), 1)
    poke(c.io.consts(2), 1)
    poke(c.io.consts(3), 1)
   
 
   step(1)     

      expect(c.io.out,2)

     
   }
 


class MyManyDynamicElementVecFirTests extends ChiselFlatSpec {
  behavior of "Module Tests "
  backends foreach {backend =>
    it should s"correctly respond to inputs in $backend" in {
      Driver(() => new MyManyDynamicElementVecFir(4), backend)(c => new MyManyDynamicElementVecFirTester(c)) should be (true)
    }
  }
}                                                                                                                                    

object MyManyDynamicElementVecFirMain extends App{
   
iotesters.Driver.execute(args= Array("--backend-name","verilator")  , ()=> new MyManyDynamicElementVecFir(4)){
 c=> new MyManyDynamicElementVecFirTester(c)
  }
}

  
