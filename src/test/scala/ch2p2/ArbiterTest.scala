package ch2p2

import chisel3._
import chisel3.simulator.scalatest.ChiselSim
import circt.stage.ChiselStage
import org.scalatest.flatspec.AnyFlatSpec

class ArbiterTest extends AnyFlatSpec with ChiselSim {
  behavior of "Arbiter"

  it should "produce such SystemVerilog" in {
    println("\n\n===== SystemVerilog of 'Arbiter' =====\n")
    println(
      ChiselStage.emitSystemVerilog(
        new Arbiter,
        firtoolOpts = Array(
          "-disable-all-randomization",
          "-strip-debug-info",
          "-default-layer-specialization=enable"
        )
      )
    )
    println("\n===== END =====\n\n")
  }

  it should "be a Arbiter" in
    simulate(new Arbiter) { c =>
      import scala.util.Random
      val data = Random.nextInt(65536)
      c.io.fifo_data.poke(data.U)
      
      for (i <- 0 until 8) {
        c.io.fifo_valid.poke((((i >> 0) % 2) != 0).B)
        c.io.pe0_ready.poke((((i >> 1) % 2) != 0).B)
        c.io.pe1_ready.poke((((i >> 2) % 2) != 0).B)

        c.io.fifo_ready.expect((i > 1).B)
        c.io.pe0_valid.expect((i == 3 || i == 7).B)
        c.io.pe1_valid.expect((i == 5).B)
        
        if (i == 3 || i ==7) {
          c.io.pe0_data.expect((data).U)
        } else if (i == 5) {
          c.io.pe1_data.expect((data).U)
        }
      }
    }
}

