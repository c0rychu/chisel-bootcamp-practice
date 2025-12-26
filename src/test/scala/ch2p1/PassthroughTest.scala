package demo

import chisel3._
import chisel3.simulator.EphemeralSimulator._
import org.scalatest.flatspec.AnyFlatSpec

class PassthroughTest extends AnyFlatSpec {
  behavior of "Passthrough"
  it should "pass input directly to output" in {
    simulate(new Passthrough) { dut =>
        dut.io.in.poke(0.U)     // Set our input to value 0
        dut.io.out.expect(0.U)  // Assert that the output correctly has 0
        dut.io.in.poke(1.U)     // Set our input to value 1
        dut.io.out.expect(1.U)  // Assert that the output correctly has 1
        dut.io.in.poke(2.U)     // Set our input to value 2
        dut.io.out.expect(2.U)  // Assert that the output correctly has 2
    }
  }
}
