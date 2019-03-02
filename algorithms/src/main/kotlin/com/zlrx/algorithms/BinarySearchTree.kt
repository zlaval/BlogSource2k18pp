package com.zlrx.algorithms

//refactor
fun main(args: Array<String>) {
    val tree = BinarySearchTree()
    tree.insert(24)
    tree.insert(20)
    tree.insert(17)
    tree.insert(22)
    tree.insert(31)
    tree.insert(40)
    tree.insert(26)
    tree.insert(21)
    tree.insert(7)
    tree.insert(18)

    tree.traverseInOrder()
    val node = tree.get(22)
    println(node?.data)
    println("Min is ${tree.min()?.data}")
    println("Max is ${tree.max()?.data}")

    tree.delete(20)

    tree.traverseInOrder()
}

class TreeNode(var data: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    fun insert(value: Int) {
        if (value != data) {
            if (value < data) {
                if (left == null) {
                    left = TreeNode(value)
                } else {
                    left?.insert(value)
                }
            } else {
                if (right == null) {
                    right = TreeNode(value)
                } else {
                    right?.insert(value)
                }
            }
        }
    }

    fun traverseInOrder() {
        left?.traverseInOrder()
        println("Node = $data")
        right?.traverseInOrder()
    }

    fun min(): TreeNode? {
        return if (left == null) {
            this
        } else {
            left?.min()
        }
    }

    fun max(): TreeNode? {
        return if (right == null) {
            this
        } else {
            right?.max()
        }
    }

    fun get(value: Int): TreeNode? {
        if (value == data) {
            return this
        }
        if (value < data) {
            return left?.get(value)
        } else {
            return right?.get(value)
        }
    }


}


class BinarySearchTree {

    var root: TreeNode? = null

    fun insert(value: Int) {
        if (root == null) {
            root = TreeNode(value)
        } else {
            root?.insert(value)
        }
    }

    fun traverseInOrder() {
        root?.traverseInOrder()
    }

    fun get(value: Int): TreeNode? {
        return root?.get(value)
    }

    fun min(): TreeNode? {
        return root?.min()
    }

    fun max(): TreeNode? {
        return root?.max()
    }

    fun delete(value: Int) {

        fun delete(subTreeRoot: TreeNode?, value: Int): TreeNode? {
            if (subTreeRoot == null) {
                return null
            }
            if (value < subTreeRoot.data) {
                subTreeRoot.left = delete(subTreeRoot.left, value)
            } else if (value > subTreeRoot.data) {
                subTreeRoot.right = delete(subTreeRoot.right, value)
            } else {
                if (subTreeRoot.left == null) {
                    return subTreeRoot.right
                } else if (subTreeRoot.right == null) {
                    return subTreeRoot.left
                }
                subTreeRoot.data = subTreeRoot.right!!.min()!!.data
                subTreeRoot.right = delete(subTreeRoot.right, subTreeRoot.data)
            }
            return subTreeRoot
        }

        root = delete(root, value)

    }

}