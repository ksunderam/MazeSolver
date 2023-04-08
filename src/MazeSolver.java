/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class MazeSolver {
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
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();
        Stack<MazeCell> stack = new Stack<MazeCell>();
        stack = recursiveMethod(stack, maze.getEndCell());
        int size = stack.size();
        for (int i = 0; i < size; i++)
        {
            solution.add(stack.pop());
        }
        /*for (int i = 0; i < solution.size()-2; i++)
        {
            MazeCell x = solution.get(i);
            String row = Integer.toString(x.getRow());
            String col = Integer.toString(x.getCol());
            System.out.println(row + ", " + col);
        }*/
        return solution;
    }
    public Stack<MazeCell> recursiveMethod(Stack<MazeCell> list, MazeCell mc)
    {
        if (mc == maze.getStartCell().getParent())
        {
            list.push(mc);
            list.push(maze.getStartCell());
            return list;
        }
        /*if (mc == maze.getStartCell())
        {
            list.push(mc);
            return list;
        }*/
        list.push(mc);
        return recursiveMethod(list, mc.getParent());
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    /*public ArrayList<MazeCell> solveMazeDFS()
    {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> toVisit = new Stack<MazeCell>();
        MazeCell current = maze.getStartCell();
        int row = maze.getStartCell().getRow();
        int col = maze.getStartCell().getCol();
        recursiveDFS(toVisit, current, row, col);
        return getSolution();

//        while(current != maze.getStartCell())
//            //North | Going up | row-1
//            if (maze.isValidCell(row-1, col))
//            {
//                current.setParent(maze.getCell(row-1, col));
//            }
//            //East | Going right | col+1
//            if (maze.isValidCell(row, col+1))
//            {
//                current.setParent(maze.getCell(row, col+1));
//            }
//            //South | Going down | row+1
//            if (maze.isValidCell(row+1, col))
//            {
//                current.setParent(maze.getCell(row+1, col));
//            }
//            //West | Going left | col-1
//            if (maze.isValidCell(row, col-1))
//            {
//                current.setParent(maze.getCell(row, col-1));
//            }

//        //North | Going up | row-1
//        if (maze.getCell(current.getRow() - 1, current.getCol()).isWall() && maze.getCell(current.getRow() - 1, current.getCol()).isExplored())
//        {
//
//        }
//        //East | Going right | col+1
//        if (maze.getCell(current.getRow(), current.getCol() + 1).isWall())
//        {
//
//        }
//        //South | Going down | row+1
//        if (maze.getCell(current.getRow() + 1, current.getCol()).isWall())
//        {
//
//        }
//        //West | Going left | col-1
//        if (maze.getCell(current.getRow(), current.getCol() - 1).isWall())
//        {
//
//        }


        //maze.getEndCell().setParent();


//        ArrayList<MazeCell> sol = new ArrayList<MazeCell>();
//        sol.add(maze.getStartCell());
//        int i = 0;
//        int j = 0;
//        int a = 0;
//        int b = 0;
//        int row = maze.getStartCell().getRow();
//        int col = maze.getStartCell().getCol();
//        while (row != maze.getEndCell().getRow() && col != maze.getEndCell().getCol())
//        {
//            while (maze.isValidCell(row, col))
//            {
//                row += i;
//            }
//            while (maze.isValidCell(row, col))
//            {
//                col += j;
//            }
//            while (maze.isValidCell(row, col))
//            {
//                row -= a;
//            }
//            while (maze.isValidCell(row, col))
//            {
//                col -= b;
//            }
//        }
    }

    public void recursiveDFS(Stack<MazeCell> toVisit, MazeCell current, int row, int col)
    {
        if (current == maze.getStartCell()) {
            return;
        }
        //West | Going left | col-1
        if (maze.isValidCell(row, col - 1))
        {
            current.setParent(maze.getCell(row, col - 1));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //South | Going down | row+1
        if (maze.isValidCell(row + 1, col))
        {
            current.setParent(maze.getCell(row + 1, col));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //East | Going right | col+1
        if (maze.isValidCell(row, col + 1))
        {
            current.setParent(maze.getCell(row, col + 1));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //North | Going up | row-1
        if (maze.isValidCell(row - 1, col))
        {
            current.setParent(maze.getCell(row - 1, col));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        MazeCell recurse = toVisit.pop();
        recursiveDFS(toVisit, recurse, recurse.getRow(), recurse.getCol());
        return;


//        //North | Going up | row-1
//        if (maze.isValidCell(row-1, col))
//        {
//            current.setParent(maze.getCell(row-1, col));
//            recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
//        }
//        //East | Going right | col+1
//        if (maze.isValidCell(row, col+1))
//        {
//            current.setParent(maze.getCell(row, col+1));
//            recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
//        }
//        //South | Going down | row+1
//        if (maze.isValidCell(row+1, col))
//        {
//            current.setParent(maze.getCell(row+1, col));
//            recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
//        }
//        //West | Going left | col-1
//        if (maze.isValidCell(row, col-1))
//        {
//            current.setParent(maze.getCell(row, col-1));
//            recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
//        }
    }*/

    public ArrayList<MazeCell> solveMazeDFS()
    {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> toVisit = new Stack<MazeCell>();
        MazeCell current = maze.getStartCell();
        int row = maze.getStartCell().getRow();
        int col = maze.getStartCell().getCol();
        recursiveDFS(toVisit, current, row, col);
        return getSolution();
    }

    public void recursiveDFS(Stack<MazeCell> toVisit, MazeCell current, int row, int col)
    {
        if (current == maze.getStartCell())
        {
            return;
        }
        //West | Going left | col-1
        if (maze.isValidCell(row, col - 1))
        {
            current.setParent(maze.getCell(row, col - 1));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //South | Going down | row+1
        if (maze.isValidCell(row + 1, col))
        {
            current.setParent(maze.getCell(row + 1, col));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //East | Going right | col+1
        if (maze.isValidCell(row, col + 1))
        {
            current.setParent(maze.getCell(row, col + 1));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //North | Going up | row-1
        if (maze.isValidCell(row - 1, col))
        {
            current.setParent(maze.getCell(row - 1, col));
            toVisit.push(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        MazeCell recurse = toVisit.pop();
        recursiveDFS(toVisit, recurse, recurse.getRow(), recurse.getCol());
        //return;
    }


    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS()
    {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        //Stack<MazeCell> toVisit = new Stack<MazeCell>();
        Queue<MazeCell> toVisit = new LinkedList<MazeCell>();
        MazeCell current = maze.getStartCell();
        int row = maze.getStartCell().getRow();
        int col = maze.getStartCell().getCol();
        recursiveBFS(toVisit, current, row, col);
        return getSolution();
    }
    public void recursiveBFS(Queue<MazeCell> toVisit, MazeCell current, int row, int col)
    {
        if (current == maze.getStartCell()) {
            return;
        }
        //West | Going left | col-1
        if (maze.isValidCell(row, col - 1))
        {
            current.setParent(maze.getCell(row, col - 1));
            toVisit.add(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //South | Going down | row+1
        if (maze.isValidCell(row + 1, col))
        {
            current.setParent(maze.getCell(row + 1, col));
            toVisit.add(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //East | Going right | col+1
        if (maze.isValidCell(row, col + 1))
        {
            current.setParent(maze.getCell(row, col + 1));
            toVisit.add(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        //North | Going up | row-1
        if (maze.isValidCell(row - 1, col))
        {
            current.setParent(maze.getCell(row - 1, col));
            toVisit.add(current.getParent());
            //recursiveDFS(current.getParent(), current.getParent().getRow(), current.getParent().getCol());
        }
        MazeCell recurse = toVisit.remove();
        recursiveBFS(toVisit, recurse, recurse.getRow(), recurse.getCol());
        //return;
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

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
