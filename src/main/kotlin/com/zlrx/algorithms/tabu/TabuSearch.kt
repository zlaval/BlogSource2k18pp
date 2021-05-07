package com.zlrx.algorithms.tabu

import org.apache.commons.collections4.IteratorUtils
import org.apache.commons.collections4.queue.CircularFifoQueue
import kotlin.math.exp
import kotlin.math.sin

//TODO refactor

//[-10,10] step 0.1
const val NUMBER_OF_VALUES = 200
const val ITERATIONS = 100_000

//tabu list size
const val TABU_TENURE = 400

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

class TabuList {

    private val tabu = CircularFifoQueue<State>(TABU_TENURE)

    fun add(state: State) {
        tabu.add(state)
    }

    fun contains(state: State) = tabu.contains(state)

    fun tabus() = IteratorUtils.toList(tabu.iterator())

}

class NeighbourSolution() {

    fun bestNeighbour(states: Array<Array<State>>, neighbourStates: MutableList<State>, tabuStates: List<State>): State {
        neighbourStates.removeAll(tabuStates)
        if (neighbourStates.size == 0) {
            return states[100][100]
        }
        var bestSolution = neighbourStates[0]
        neighbourStates.forEach {
            if (it.z < bestSolution.z) {
                bestSolution = it
            }
        }

        println(bestSolution)
        return bestSolution
    }

}

class TabuSearch(val states: Array<Array<State>>) {
    private val tabus = TabuList()
    private val neighbourSolution = NeighbourSolution()

    fun solve(initialState: State): State {
        var bestState = initialState
        var currentState = initialState
        var iterations = 0
        while (iterations < ITERATIONS) {
            val candidates = currentState.neighbours
            val tabu = tabus.tabus()
            val bestNeighbour = neighbourSolution.bestNeighbour(states, candidates, tabu)
            if (bestNeighbour.z < bestState.z) {
                bestState = bestNeighbour
            }
            tabus.add(currentState)
            currentState = bestNeighbour
            iterations++
        }
        return bestState
    }


}

fun main() {

    val states: Array<Array<State>> = Array(NUMBER_OF_VALUES) { x ->
        Array(NUMBER_OF_VALUES) { y ->
            val xc = (x - 100) / 10.0
            val yc = (y - 100) / 10.0
            State(xc, yc, cost(xc, yc))
        }
    }

    (0 until NUMBER_OF_VALUES).forEach {
        states[it][0].neighbours.add(states[it][1])
        states[it][199].neighbours.add(states[it][198])
        states[0][it].neighbours.add(states[1][it])
        states[199][it].neighbours.add(states[198][it])
        if (it < NUMBER_OF_VALUES - 1 && it > 0)
            (1 until NUMBER_OF_VALUES - 1).forEach { j ->
                states[it][j].neighbours.add(states[it - 1][j])
                states[it][j].neighbours.add(states[it + 1][j])
                states[it][j].neighbours.add(states[it][j - 1])
                states[it][j].neighbours.add(states[it][j + 1])
            }
    }

    val tabuSearch = TabuSearch(states)
    val result = tabuSearch.solve(states[100][100])
    println(result)

}