package com.zlrx.algorithms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class MazeResolver {


    /**
     * 1=wall
     * 2=start
     * 3=finish
     * 0=free path
     * <p>
     * {1, 1, 1, 1, 1, 1},
     * {1, 2, 1, 3, 0, 1},
     * {1, 0, 1, 1, 0, 1},
     * {1, 0, 0, 0, 0, 1},
     * {1, 1, 1, 1, 1, 1}
     */
    public static void main(String[] args) {
        MazeResolver mazeResolver = new MazeResolver();
        mazeResolver.run();
    }

    private MazeElement start;

    private MazeElement finish;

    private MazeElement[][] maze;

    private void run() {
        maze = createMaze();
        start = maze[1][1];
        finish = maze[1][3];
        printMaze();
        MazeElement solved = solve();
        printPath(solved);
    }

    private MazeElement solve() {
        Queue<MazeElement> queue = new LinkedList();
        start.setVisited(true);
        queue.add(start);

        while (!queue.isEmpty()) {
            MazeElement visiting = queue.remove();

            if (visiting.equals(finish)) {
                return visiting;
            }
            visiting.getNeighbours().forEach(neighbour -> {
                if (!neighbour.isVisited() && !neighbour.isWall()) {
                    neighbour.setVisited(true);
                    neighbour.setVisitedFrom(visiting);
                    queue.add(neighbour);
                }
            });
        }
        return null;
    }

    private void printPath(MazeElement mazeElement) {
        System.out.println("Path:");
        if (Objects.nonNull(mazeElement)) {
            MazeElement parent = mazeElement;
            System.out.print(parent + " --> ");
            while (parent.getVisitedFrom() != null) {
                parent = parent.getVisitedFrom();
                System.out.print(parent + " --> ");
            }
            System.out.print(" END ");
        } else {
            System.out.println("There is no path");
        }
    }

    private void printMaze() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
    }


    private MazeElement[][] createMaze() {
        MazeElement[][] mazeElements = new MazeElement[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                MazeElement mazeElement = new MazeElement(i, j);
                mazeElements[i][j] = mazeElement;
                if (i == 0 || j == 0 || i == 4 || j == 5) {
                    mazeElement.setWall(true);
                }
                if ((i == 1 || i == 2) && j == 2) {
                    mazeElement.setWall(true);
                }
                if (i == 2 && j == 3) {
                    mazeElement.setWall(true);
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                MazeElement mazeElement = mazeElements[i][j];
                addNeighbour(mazeElements, mazeElement, i - 1, j);
                addNeighbour(mazeElements, mazeElement, i, j + 1);
                addNeighbour(mazeElements, mazeElement, i, j - 1);
                addNeighbour(mazeElements, mazeElement, i + 1, j);
            }
        }

        return mazeElements;
    }

    private void addNeighbour(MazeElement[][] mazeElements, MazeElement mazeElement, int nX, int nY) {
        if (nX >= 0 && nY >= 0 && nX < 5 && nY < 6) {
            MazeElement neighbour = mazeElements[nX][nY];
            mazeElement.addNeighbour(neighbour);
        }
    }


    @Getter
    @EqualsAndHashCode(of = {"x", "y"})
    class MazeElement {
        int x;
        int y;

        @Setter
        boolean visited;

        @Setter
        boolean wall;

        @Setter
        MazeElement visitedFrom;

        List<MazeElement> neighbours;

        public MazeElement(int x, int y) {
            this.x = x;
            this.y = y;
            this.visited = false;
            this.neighbours = new ArrayList<>();
        }

        public void addNeighbour(MazeElement mazeElement) {
            neighbours.add(mazeElement);
        }

        @Override
        public String toString() {
            if (isWall()) {
                return " *" + x + "x" + y + "* ";
            } else {
                return " |" + x + "x" + y + "| ";
            }
        }
    }

}
