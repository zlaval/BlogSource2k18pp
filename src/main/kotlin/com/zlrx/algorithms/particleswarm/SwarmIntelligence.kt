package com.zlrx.algorithms.particleswarm

import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

const val ITERATIONS = 10_000
const val PARTICLE_COUNT = 10
const val RANGE_MIN = -2.0
const val RANGE_MAX = 2.0
const val W = 0.730
const val C1 = 1.5
const val C2 = 1.5

val cost = { position: Point ->
    val x = position.x
    val y = position.y
    exp(-x * x - y * y) * sin(x)
}


data class Point(
    val x: Double,
    val y: Double
)

data class Particle(
    val position: Point,
    val velocity: Point,
    val bestPosition: Point
)


class SwarmIntelligence {

    private var globalBest = generatePoint()

    private var swarm: List<Particle> = initParticles()

    fun solve(): Point {

        for (i in 0 until ITERATIONS) {
            swarm = swarm.map {
                val velocityX = calculateVelocity(it.position.x, it.velocity.x, it.bestPosition.x, globalBest.x)
                val velocityY = calculateVelocity(it.position.y, it.velocity.y, it.bestPosition.y, globalBest.y)
                val positionX = min(max(it.position.x + velocityX, RANGE_MIN), RANGE_MAX)
                val positionY = min(max(it.position.y + velocityY, RANGE_MIN), RANGE_MAX)
                val position = Point(positionX, positionY)

                val bestPosition = if (cost(position) < cost(it.bestPosition)) {
                    position
                } else {
                    it.bestPosition
                }
                val newParticle = it.copy(
                    velocity = Point(velocityX, velocityY),
                    position = position,
                    bestPosition = bestPosition
                )
                if (cost(bestPosition) < cost(globalBest)) {
                    globalBest = bestPosition
                }
                newParticle
            }
        }
        return globalBest
    }

    private fun calculateVelocity(c: Double, v: Double, bp: Double, gb: Double): Double {
        val rp = Math.random()
        val rg = Math.random()
        return W * v + C1 * rp * (bp - c) + C2 * rg * (gb - c)
    }

    private fun initParticles(): List<Particle> {
        return (0 until PARTICLE_COUNT).map {
            val position = generatePoint()
            Particle(
                position,
                generateVelocity(),
                position
            )
        }
    }

    private fun randomPositionCoord() = randomDouble(RANGE_MIN, RANGE_MAX)
    private fun randomVelocityVec() = randomDouble(-(RANGE_MAX - RANGE_MIN), RANGE_MAX - RANGE_MIN)
    private fun randomDouble(min: Double, max: Double) = min + (max - min) * Math.random()

    private fun generatePoint() = Point(randomPositionCoord(), randomPositionCoord())
    private fun generateVelocity() = Point(randomVelocityVec(), randomVelocityVec())

}


fun main() {
    val si = SwarmIntelligence()
    val result = si.solve()
    println(result)
    println(cost(result))
}

