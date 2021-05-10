package com.zlrx.algorithms.tabu

import org.apache.commons.collections4.queue.CircularFifoQueue
import kotlin.math.exp
import kotlin.math.sin

//TODO refactor

//[-10,10] step 0.1
const val NUMBER_OF_VALUES = 200
const val ITERATIONS = 100_000
const val TABU_TENURE = 400

//https://academo.org/demos/3d-surface-plotter/?expression=exp(-x*x-y*y)*sin(x)&xRange=-3%2C%203&yRange=-3%2C%203&resolution=100
val cost = { x: Double, y: Double ->
    exp(-x * x - y * y) * sin(x)
}

data class State(
    val x: Double,
    val y: Double,
    val z: Double,
    val neighbours: MutableList<State> = mutableListOf()
) {
    override fun toString(): String {
        return "State(x=$x, y=$y, z=$z)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as State

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + z.hashCode()
        return result
    }

}

class TabuSearch(private val states: Array<Array<State>>) {

    private val tabu = CircularFifoQueue<State>(TABU_TENURE)

    fun solve(initialState: State): State {
        var bestState = initialState
        var currentState = initialState

        for (i in 0 until ITERATIONS) {
            val candidates = currentState.neighbours.filter { !tabu.contains(it) }
            val bestNeighbour = bestNeighbour(candidates)
            if (bestNeighbour.z < bestState.z) {
                bestState = bestNeighbour
            }
            tabu.add(currentState)
            currentState = bestNeighbour
        }

        return bestState
    }

    private fun bestNeighbour(candidates: List<State>): State {
        return if (candidates.isEmpty()) {
            println("All elements around are tabu, jump back to start point.")
            states[NUMBER_OF_VALUES / 2][NUMBER_OF_VALUES / 2]
        } else {
            val bestSolution = candidates.reduce { acc, state -> if (state.z < acc.z) state else acc }
            println(bestSolution)
            bestSolution
        }
    }
}

fun main() {
    val grid = createGrid()
    val center = grid[NUMBER_OF_VALUES / 2][NUMBER_OF_VALUES / 2]
    val tabuSearch = TabuSearch(grid)
    val result = tabuSearch.solve(center)
    println(result)
}


fun createGrid(): Array<Array<State>> {
    val grid: Array<Array<State>> = Array(NUMBER_OF_VALUES) { x ->
        Array(NUMBER_OF_VALUES) { y ->
            val xc = (x - 100) / 10.0
            val yc = (y - 100) / 10.0
            State(xc, yc, cost(xc, yc))
        }
    }

    (0 until NUMBER_OF_VALUES).forEach { x ->
        (0 until NUMBER_OF_VALUES).forEach { y ->
            if (x > 0) grid[x][y].neighbours.add(grid[x - 1][y])
            if (x < NUMBER_OF_VALUES - 1) grid[x][y].neighbours.add(grid[x + 1][y])
            if (y > 0) grid[x][y].neighbours.add(grid[x][y - 1])
            if (y < NUMBER_OF_VALUES - 1) grid[x][y].neighbours.add(grid[x][y + 1])
        }
    }

    return grid
}