package ch2p2

import chisel3._
import chisel3.simulator.scalatest.ChiselSim
import org.scalatest.flatspec.AnyFlatSpec

class MACTest extends AnyFlatSpec with ChiselSim {
  behavior of "MAC"

  it should "be a MAC" in
    simulate(new MAC) { c =>
      val cycles = 100
      import scala.util.Random
      for (_ <- 0 until cycles) {
        val in_a = Random.nextInt(16)
        val in_b = Random.nextInt(16)
        val in_c = Random.nextInt(16)
        c.io.in_a.poke(in_a.U)
        c.io.in_b.poke(in_b.U)
        c.io.in_c.poke(in_c.U)
        c.io.out.expect((in_a * in_b + in_c).U)
      }
    }
}

