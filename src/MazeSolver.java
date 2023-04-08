// Kayan Sunderam
/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class MazeSolver
{
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution()
    {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        // Create a stack because we're starting with the end-cell and working backwards
        // So we'll want to add to the returned ArrayList in reverse order, easily done by popping elements off a stack
        Stack<MazeCell> stack = new Stack<MazeCell>();
        // Calls the recursive method that creates the stack that we'll use to input values into the ArrayList
        stack = recursiveMethod(stack, maze.getEndCell());
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        // Popping items off the stack and adding it to the ArrayList solution
        // The top of the stack is the starting cell, since that is what is added to the Stack last
        // The bottom of the stack is the end cell,
        // since that is what we initially pass in and add to the stack in the recursive method
        while (stack.isEmpty() == false)
        {
            solution.add(stack.pop());
        }
        return solution;
    }
    public Stack<MazeCell> recursiveMethod(Stack<MazeCell> list, MazeCell mc)
    {
        // Base Case: if we are at the starting cell, return
        // This is because we start off with the end cell,
        // And keep on going back through each cell's parents, adding them to the stack.
        // The starting cell does not have a parent cell, so that's when we return the stack
        if (mc == maze.getStartCell())
        {
            list.push(mc);
            return list;
        }
        list.push(mc);
        // Recursively calls the cell's parent, until we get to the starting cell
        recursiveMethod(list, mc.getParent());
        return list;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS()
    {
        // This is the stack of cells that we need to Visit next
        // In DFS, a stack is the data structure of "cells to visit"
        Stack<MazeCell> toVisit = new Stack<MazeCell>();
        MazeCell current = maze.getStartCell();
        // We start off with the starting cell and recursively work our way through the maze until we hit the end cell
        recursiveDFS(toVisit, current);
        // Essentially recursiveDFS just sets the parents of each cell
        // And then get solution returns the solution by looking at each cell's parent
        return getSolution();
    }
    public void recursiveDFS(Stack<MazeCell> toVisit, MazeCell current)
    {
        // The Base Case is when we're at the end cell--this means we'ce reached the end of the maze
        if (current == maze.getEndCell())
        {
            return;
        }
        int row = current.getRow();
        int col = current.getCol();
        current.setExplored(true);
        // We add West to the stack first, then South, East, and finally North
        // This means that North will be at the top off the stack, making it be the one that is explored first,
        // Since the cell that we next explore is the one that is popped off from the top of the stack
        // The parents are set and reset every time an adjacent cell is valid,
        // So that we end up with the parent cell being the cell that is at the top of the stack
        // West
        if(maze.isValidCell(row, col-1))
        {
            toVisit.push(maze.getCell(row, col-1));
            maze.getCell(row, col-1).setParent(current);
        }
        // South
        if(maze.isValidCell(row-1, col))
        {
            toVisit.push(maze.getCell(row-1, col));
            maze.getCell(row-1, col).setParent(current);
        }
        // East
        if (maze.isValidCell(row, col+1))
        {
            toVisit.push(maze.getCell(row, col+1));
            maze.getCell(row, col+1).setParent(current);
        }
        // North
        if (maze.isValidCell(row+1, col))
        {
            toVisit.push(maze.getCell(row+1, col));
            maze.getCell(row+1, col).setParent(current);
        }
        MazeCell child = toVisit.pop();
        recursiveDFS(toVisit, child);
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS()
    {
        // This is the queue of cells that we need to Visit next
        // In BFS, a queue is the data structure of "cells to visit"
        Queue<MazeCell> toVisit = new LinkedList<MazeCell>();
        MazeCell current = maze.getStartCell();
        // Again, we start with the starting cell and recursively work our way through the maze till we hit the end cell
        recursiveBFS(toVisit, current);
        return getSolution();
    }
    // RecursiveBFS is exactly the same as RecursiveDFS, just with a queue instead of a stack
    // This is because the data structure used in BFS is a stack, as mentioned above
    // And when we think about it, it makes sense, since what BFS is, is splitting up to find the way through a maze
    // A queue allows us to jump to cells that aren't adjacent to our current cells
    // Unlike a stack, which will only allow you to visit adjacent cells
    // Since we only visit what has been most recently added to a stack
    public void recursiveBFS(Queue<MazeCell> toVisit, MazeCell current)
    {
        if (current == maze.getEndCell())
        {
            return;
        }
        int row = current.getRow();
        int col = current.getCol();
        current.setExplored(true);
        // West
        if(maze.isValidCell(row, col-1))
        {
            toVisit.add(maze.getCell(row, col-1));
            maze.getCell(row, col-1).setParent(current);
        }
        // South
        if(maze.isValidCell(row-1, col))
        {
            toVisit.add(maze.getCell(row-1, col));
            maze.getCell(row-1, col).setParent(current);
        }
        // East
        if (maze.isValidCell(row, col+1))
        {
            toVisit.add(maze.getCell(row, col+1));
            maze.getCell(row, col+1).setParent(current);
        }
        // North
        if (maze.isValidCell(row+1, col))
        {
            toVisit.add(maze.getCell(row+1, col));
            maze.getCell(row+1, col).setParent(current);
        }
        MazeCell child = toVisit.remove();
        recursiveBFS(toVisit, child);
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);
        System.out.print("\n");

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}