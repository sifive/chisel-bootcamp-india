// See LICENSE for license details.

package examples

import chisel3._
import chisel3.util.{DeqIO, EnqIO, log2Ceil}

object Router {
  val addressWidth    = 32
  val dataWidth       = 64
  val headerWidth     =  8
  val routeTableSize  = 15
  val numberOfOutputs =  4
}

class ReadCmd extends Bundle {
  val addr = UInt(Router.addressWidth.W)
}

class WriteCmd extends ReadCmd {
  val data = UInt(Router.addressWidth.W)
}

class Packet extends Bundle {
  val header = UInt(Router.headerWidth.W)
  val body   = UInt(Router.dataWidth.W)
}

/**
  * The router circuit IO
  * It routes a packet placed on its single input port to one of n output ports
  *
  * @param n is the number of fanned outputs for the routed packet
  */
class RouterIO(val n: Int) extends Bundle {
  val read_routing_table_request   = DeqIO(new ReadCmd())
  val read_routing_table_response  = EnqIO(UInt(Router.addressWidth.W))
  val load_routing_table_request   = DeqIO(new WriteCmd())
  val in                           = DeqIO(new Packet())
  val outs                         = Vec(n, EnqIO(new Packet()))
}

/**
  * routes packets by using their header as an index into an externally loaded and readable table,
  * The number of addresses recognized does not need to match the number of outputs
  */
class Router extends Module {
}
