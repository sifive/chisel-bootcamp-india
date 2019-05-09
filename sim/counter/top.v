

`define RANDOMIZE_MEM_INIT
`define RANDOMIZE_REG_INIT
`define RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE_DELAY=2


`timescale 1ns/1ns
module top (
      input clock,
      input reset
  );

  Counter i_Counter (
         .clock (clock)
        ,.reset (reset)
        ,.io_pulse_out ()
        );

endmodule
