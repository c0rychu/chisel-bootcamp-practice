package ch2p2

import chisel3._

// Exercise: Arbiter
// The following circuit arbitrates data coming from a FIFO into two parallel processing units.
// The FIFO and processing elements (PEs) communicate with ready-valid interfaces.
// Construct the arbiter to send data to whichever PE is ready to receive data,
// prioritizing PE0 if both are ready to receive data.
// Remember that the arbiter should tell the FIFO that it's ready to receive data when at least one of the PEs can receive data.
// Also, wait for a PE to assert that it's ready before asserting that the data are valid.
// You will likely need binary operators to complete this exercise.

class Arbiter extends Module {
  val io = IO(new Bundle {
    // FIFO
    val fifo_valid = Input(Bool())
    val fifo_ready = Output(Bool())
    val fifo_data  = Input(UInt(16.W))
    
    // PE0
    val pe0_valid  = Output(Bool())
    val pe0_ready  = Input(Bool())
    val pe0_data   = Output(UInt(16.W))
    
    // PE1
    val pe1_valid  = Output(Bool())
    val pe1_ready  = Input(Bool())
    val pe1_data   = Output(UInt(16.W))
  })


  // My Anser
  io.fifo_ready := io.pe0_ready | io.pe1_ready
  io.pe0_valid := io.pe0_ready & io.fifo_valid
  io.pe1_valid := io.pe1_ready & io.fifo_valid & !io.pe0_ready
  io.pe0_data := io.fifo_data
  io.pe1_data := io.fifo_data


  // Solution
  // io.fifo_ready := io.pe0_ready || io.pe1_ready
  // io.pe0_valid := io.fifo_valid && io.pe0_ready
  // io.pe1_valid := io.fifo_valid && io.pe1_ready && !io.pe0_ready
  // io.pe0_data := io.fifo_data
  // io.pe1_data := io.fifo_data
}