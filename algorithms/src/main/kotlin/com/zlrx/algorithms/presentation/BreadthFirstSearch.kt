package com.zlrx.algorithms.presentation

import java.util.*


fun main(args: Array<String>) {
    BreadthFirstSearch().travel()
}

data class Node(val data: Int, val neighbours: List<Node> = listOf(), var visited: Boolean = false)

class BreadthFirstSearch {

    fun travel() {
        val root = createData()
        bfs(root)
    }

    fun createData(): Node {
        /****************
         *       O      *
         *      / \     *
         *     4  3     *
         *    / \  \    *
         *   2  1  5    *
         ***************/
        val node5 = Node(5)
        val node2 = Node(2)
        val node1 = Node(1)
        val node3 = Node(3, listOf(node5))
        val node4 = Node(4, listOf(node2, node1))
        val root = Node(0, listOf(node4, node3))
        return root
    }

    fun bfs(root: Node) {
        val queue = LinkedList<Node>()
        root.visited = true
        queue.add(root)

        while (!queue.isEmpty()) {
            val node = queue.remove()
            println("The actual node is $node")
            node.neighbours.forEach {
                if (!it.visited) {
                    it.visited = true
                    queue.add(it)
                }
            }
        }
    }


}







