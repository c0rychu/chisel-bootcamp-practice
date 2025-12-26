package ch2p1

import chisel3._
import chisel3.simulator.scalatest.ChiselSim
import circt.stage.ChiselStage
import org.scalatest.flatspec.AnyFlatSpec

class PassthroughTest extends AnyFlatSpec with ChiselSim {
  behavior of "Passthrough"

  it should "produce such SystemVerilog" in {
    println("\n\n===== SystemVerilog of 'Passthrough' =====\n")
    println(
      ChiselStage.emitSystemVerilog(
        new Passthrough,
        firtoolOpts = Array(
          "-disable-all-randomization",
          "-strip-debug-info",
          "-default-layer-specialization=enable"
        )
      )
    )
    println("\n===== END =====\n\n")
  }

  it should "pass input directly to output" in
    simulate(new Passthrough) { dut =>
      dut.io.in.poke(0.U)    // Set our input to value 0
      dut.io.out.expect(0.U) // Assert that the output correctly has 0
      dut.io.in.poke(1.U)    // Set our input to value 1
      dut.io.out.expect(1.U) // Assert that the output correctly has 1
      dut.io.in.poke(2.U)    // Set our input to value 2
      dut.io.out.expect(2.U) // Assert that the output correctly has 2
    }
}

class PassthroughGeneratorTest extends AnyFlatSpec with ChiselSim {
  behavior of "PassthroughGenerator"

  it should "pass input directly to output with width = 10" in
    simulate(new PassthroughGenerator(width = 10)) { dut =>
      dut.io.in.poke(0.U)
      dut.io.out.expect(0.U)
      dut.io.in.poke(((1 << 10) - 1).U)
      dut.io.out.expect(((1 << 10) - 1).U)
    }

  it should "pass input directly to output with width = 20" in
    simulate(new PassthroughGenerator(width = 20)) { dut =>
      dut.io.in.poke(0.U)
      dut.io.out.expect(0.U)
      dut.io.in.poke(((1 << 20) - 1).U)
      dut.io.out.expect(((1 << 20) - 1).U)
    }
}
