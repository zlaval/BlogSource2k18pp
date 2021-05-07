package com.zlrx.algorithms.iddfs

import java.util.Stack

/*
        /****************
         *       A      *
         *      / \     *
         *     E  D     *
         *    / \  \    *
         *   C  B  F    *
         ***************/
 */
fun main() {
    val node5 = Node("F", emptyList())
    val node2 = Node("C", emptyList())
    val node1 = Node("B", emptyList())
    val node3 = Node("D", listOf(node5))
    val node4 = Node("E", listOf(node2, node1))
    val root = Node("A", listOf(node4, node3))
    val iddfs = IDDFS(node1)
    iddfs.run(root)
}

data class Node(
    val name: String,
    val adjecencies: List<Node>,
    var depth: Int = 0
) {


    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}


class IDDFS(val target: Node) {
    private var found: Boolean = false

    fun run(root: Node) {
        var depth = 0
        while (!found) {
            println()
            dfs(root, depth)
            depth++
        }
    }

    private fun dfs(source: Node, depth: Int) {
        val stack = Stack<Node>()
        source.depth = 0
        stack.push(source)
        while (stack.isNotEmpty()) {
            val actual = stack.pop()
            println("Actual: $actual")
            if (actual == target) {
                println("Node has been found")
                found = true
                return
            } else if (actual.depth >= depth) {
                continue
            }
            actual.adjecencies.forEach {
                it.depth = actual.depth + 1
                stack.push(it)
            }

        }
    }

}