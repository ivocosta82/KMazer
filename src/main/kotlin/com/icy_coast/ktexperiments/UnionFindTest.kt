package com.icy_coast.ktexperiments

import java.util.*


fun main(args: Array<String>) {
    val SIZE = 200
    val rnd = Random(System.nanoTime())
    val grid = Array(SIZE, { IntArray(SIZE) })

    grid[0][SIZE/2] = 1
    grid[SIZE-1][SIZE/2] = 2

    var count = 3

    while (grid[0][SIZE/2] != grid[SIZE-1][SIZE/2]) {
        val x = rnd.nextInt(SIZE-2)+1
        val y = rnd.nextInt(SIZE-2)+1

        val up = grid[x - 1][y]
        val down = grid[x + 1][y]
        val left = grid[x][y-1]
        val right = grid[x][y+1]

        count += 1
        grid[x][y] = count

        if (up != 0) {
            grid.unionFind(count, up)
        }

        if (down != 0) {
           grid.unionFind(count, down)
        }

        if (left != 0) {
            grid.unionFind(count, left)
        }

        if (right != 0) {
            grid.unionFind(count, right)
        }
    }

    grid.forEach { it.forEach { print("${if (it>0) " " else 0}")}; println("") }
}

fun Array<IntArray>.unionFind(origin:Int, num:Int) {
    for(x in this.indices) {
        for(y in this[x].indices) {
            if(this[x][y] == num) {
                this[x][y] = origin
            }
        }
    }
}

