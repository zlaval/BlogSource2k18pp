package com.zlrx.algorithms.astar

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

fun buildGraph(): Pair<Node, Node> {
    val a = Node("A", 0.0, 0.0)
    val b = Node("B", 10.0, 20.0)
    val c = Node("C", 20.0, 40.0)
    val d = Node("D", 30.0, 10.0)
    val e = Node("E", 40.0, 30.0)
    val f = Node("F", 50.0, 10.0)
    val g = Node("G", 50.0, 40.0)

    val ab = Edge(10.0, b)
    val ad = Edge(50.0, d)
    val bc = Edge(10.0, c)
    val bd = Edge(20.0, d)
    val ce = Edge(10.0, e)
    val cg = Edge(30.0, g)
    val df = Edge(80.0, f)
    val ed = Edge(30.0, d)
    val ef = Edge(50.0, f)
    val eg = Edge(10.0, g)
    val gf = Edge(10.0, f)

    a.adjacencies = listOf(ab, ad)
    b.adjacencies = listOf(bc, bd)
    c.adjacencies = listOf(ce, cg)
    d.adjacencies = listOf(df)
    e.adjacencies = listOf(ed, ef, eg)
    g.adjacencies = listOf(gf)

    return Pair(a, f)
}


fun main() {
    val graph = buildGraph()
    val astar = AStar(graph.first, graph.second)
    astar.findPath()
    astar.printSolution()
}

data class Edge(
    val weight: Double,
    val target: Node
)

data class Node(
    val name: String,
    val x: Double,
    val y: Double,
    var adjacencies: List<Edge> = emptyList(),
    //f(x)=g(x)+h(x) f is a cost function, h is heuristic estimate (approximated cost from x to destination state), g is moving from start to x state
    var g: Double = 0.0,
    var f: Double = Double.MAX_VALUE,
    var parent: Node? = null
) {
    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (name != other.name) return false
        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }


}

class NodeComparator : Comparator<Node> {
    override fun compare(o1: Node, o2: Node): Int =
        o1.f.compareTo(o2.f)
}


class AStar(val start: Node, val target: Node) {

    private val explored = mutableSetOf<Node>()
    private val queue: PriorityQueue<Node> = PriorityQueue(NodeComparator())

    fun findPath() {
        queue.add(start)
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            explored.add(current)
            if (current == target) {
                break
            }
            current.adjacencies.forEach {
                val child = it.target
                val cost = it.weight
                val g = current.g + cost
                val f = g + heuristic(current, target)
                if (!explored.contains(child) || f < child.f) {
                    queue.remove(child)
                    child.parent = current
                    child.g = g
                    child.f = f
                    queue.add(child)
                }
            }
        }
    }

    fun printSolution() {
        var actual: Node? = target
        while (actual != null) {
            print("$actual <-- ")
            actual = actual.parent
        }
        println()
    }

    //euclidean distance
    private fun heuristic(current: Node, target: Node): Double = sqrt((current.x - target.x).pow(2) + (current.y - target.y).pow(2))

}