import java.util.ArrayList;

public class CTCI8
{
    public static void main(String[] args)
    {
        System.out.println("Solution VIII-1");
        SolutionVIII1.run();
        System.out.println();

        System.out.println("Solution VIII-2");
        SolutionVIII2.run();
        System.out.println();
    }
}

// Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
// steps at a time. Implement a method to count how many possible ways the child can run up the
// stairs.
class SolutionVIII1
{
    public static void run()
    {
        int n = 5;
        System.out.println("The steps that can build " + n + " are:");
        tripleStep(n);
    }

    private static void tripleStep(int n)
    {
        ArrayList<ArrayList<Integer>> stepsTaken = new ArrayList<>();
        buildStep(n, stepsTaken, new ArrayList<>());
        for (ArrayList<Integer> steps : stepsTaken)
        {
            for (int i : steps)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    private static void buildStep(int n, ArrayList<ArrayList<Integer>> stepsTaken, ArrayList<Integer> potentialStep)
    {
        // Count total steps
        int total = 0;
        for (int i : potentialStep)
            total += i;

        // If the total is n, add it to the list of steps
        if (total == n)
            stepsTaken.add(potentialStep);
        else
        {
            // If the total exceeds n, do not recurse
            if (total > n)
                return;
            // If not, create a copy of the potential step, then recurse for each option
            else
            {
                // Create a copy of the potential step, then add a single step
                ArrayList<Integer> potentialStepCopy1 = new ArrayList<>();
                for (int i : potentialStep)
                    potentialStepCopy1.add(i);
                potentialStepCopy1.add(1);
                buildStep(n, stepsTaken, potentialStepCopy1);

                // Create a copy of the potential step, then add a double step
                ArrayList<Integer> potentialStepCopy2 = new ArrayList<>();
                for (int i : potentialStep)
                    potentialStepCopy2.add(i);
                potentialStepCopy2.add(2);
                buildStep(n, stepsTaken, potentialStepCopy2);

                // Create a copy of the potential step, then add a triple step
                ArrayList<Integer> potentialStepCopy3 = new ArrayList<>();
                for (int i : potentialStep)
                    potentialStepCopy3.add(i);
                potentialStepCopy3.add(3);
                buildStep(n, stepsTaken, potentialStepCopy3);
            }
        }
    }
}

// Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
// The robot can only move in two directions, right and down, but certain cells are "off limits" such that
// the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
// the bottom right.
class SolutionVIII2
{
    public static void run()
    {
        int[][] grid = {
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 1},
            {0, 0, 1, 1, 1}
        };
        robotInAGrid(grid);
    }

    private static void robotInAGrid(int[][] grid)
    {
        ArrayList<String> foundPath = new ArrayList<>();

        findPath(grid, foundPath, new ArrayList<>(), 0, 0);

        if (foundPath.size() > 0)
        {
            System.out.println("The movement needed to reach the other end is: ");
            for (String dir : foundPath)
                System.out.print(dir + " ");
            System.out.println();
        }
        else
        {
            System.out.println("There is no path found.");
        }
            
    }

    private static void findPath(int[][] grid, ArrayList<String> foundPath, ArrayList<String> potentialPath, int currentRow, int currentCol)
    {
        // If the robot has reached its destination, set the found path
        if (currentRow == grid.length - 1 && currentCol == grid[0].length - 1)
        {
            for (String dir : potentialPath)
                foundPath.add(dir);
            return;
        }
        // If not...
        else
        {
            // If going down is possible, recurse with the copy
            if (currentRow < grid.length - 1 && grid[currentRow + 1][currentCol] == 1)
            {
                ArrayList<String> potentialPathCopy = new ArrayList<>();
                for (String dir : potentialPath)
                    potentialPathCopy.add(dir);
                potentialPathCopy.add("DOWN");

                findPath(grid, foundPath, potentialPathCopy, currentRow + 1, currentCol);
            }
            
            // If going right is possible, recurse with the copy
            if (currentCol < grid[0].length - 1 && grid[currentRow][currentCol + 1] == 1)
            {
                ArrayList<String> potentialPathCopy = new ArrayList<>();
                for (String dir : potentialPath)
                    potentialPathCopy.add(dir);
                potentialPathCopy.add("RIGHT");

                findPath(grid, foundPath, potentialPathCopy, currentRow, currentCol + 1);
            }
        }
    }
}