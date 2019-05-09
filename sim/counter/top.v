// DESCRIPTION: Verilator: Verilog example module
//
// This file ONLY is placed into the Public Domain, for any use,
// without warranty, 2003 by Wilson Snyder.
// ======================================================================

// This is intended to be a complex example of several features, please also
// see the simpler examples/hello_world_c.

`timescale 1ns/1ns
module top (
      input clock,
      input reset
  );

initial begin
//     repeat(100)@(posedge clock);
    $finish;
end

endmodule
