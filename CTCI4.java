import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class CTCI4 {
    public static void main(String args[])
    {
        System.out.println("Solution IV-1");
        SolutionIV1.run();
        System.out.println();

        System.out.println("Solution IV-2");
        SolutionIV2.run();
        System.out.println();

        System.out.println("Solution IV-3");
        SolutionIV3.run();
        System.out.println();

        System.out.println("Solution IV-4");
        SolutionIV4.run();
        System.out.println();

        System.out.println("Solution IV-5");
        SolutionIV5.run();
        System.out.println();
        
        System.out.println("Solution IV-6");
        SolutionIV6.run();
        System.out.println();

        System.out.println("Solution IV-7");
        SolutionIV7.run();
        System.out.println();
        
        System.out.println("Solution IV-8");
        SolutionIV8.run();
        System.out.println();

        System.out.println("Solution IV-9");
        SolutionIV9.run();
        System.out.println();

        System.out.println("Solution IV-10");
        SolutionIV10.run();
        System.out.println();

        System.out.println("Solution IV-11");
        SolutionIV11.run();
        System.out.println();
        
        System.out.println("Solution IV-12");
        SolutionIV12.run();
        System.out.println();
    }
}

// GENERIC TREE CLASS
class Tree
{
    public class Node
    {
        public int data;
        public ArrayList<Node> children;

        public Node(int data, int nChildren)
        {
            this.setData(data);
            this.children = new ArrayList<>();
            
            for (int i = 0; i < nChildren; i++)
                this.children.add(null);
        }

        public void setData(int data) { this.data = data; }
    }

    public Node root;
    public int nNodeChildren;

    public Tree(int nNodeChildren) { this.nNodeChildren = nNodeChildren; }

    public void add(int value)
    {
        this.root = addRecursive(this.root, value);
    }

    private Node addRecursive(Node node, int value)
    {
        // If the given node is null, create a new node and return it
        if (node == null)
            return new Node(value, this.nNodeChildren);

        // If the given value is less than the given node,
        // add a children which node will be created by a recursive call
        if (value < node.data)
        {
            node.children.set(0, addRecursive(node.children.get(0), value));
        }
        // If the given value is more or equal to the given node,
        // add a children which node will be created by a recursive call
        else
        {
            node.children.set(1, addRecursive(node.children.get(1), value));
        }

        // Return the original node
        return node;
    }

    public boolean find(int value)
    {
        return findRecursive(this.root, value);
    }

    private boolean findRecursive(Node node, int value)
    {
        // If the given node is not null...
        if (node != null)
        {
            if (value == node.data)
                return true;
            else if (value < node.data)
                return findRecursive(node.children.get(0), value);
            else
                return findRecursive(node.children.get(1), value);
        }
        // Else, return false
        else
            return false;
    }

    public void remove(int value)
    {
        this.root = removeRecursive(this.root, value);
    }

    private Node removeRecursive(Node node, int value)
    {
        // If the given node is not null...
        if (node != null)
        {
            if (value == node.data)
            {
                // If the node is a leaf
                if (node.children.get(0) == null && node.children.get(1) == null)
                {
                    return null;
                }
                // If the node has two children
                else if (node.children.get(0) != null && node.children.get(1) != null)
                {
                    Node leftChild = node.children.get(0);
                    Node successorNode = node.children.get(1);

                    while (successorNode.children.get(0) != null)
                    {
                        successorNode = successorNode.children.get(0);
                    }

                    successorNode.children.set(1, removeRecursive(node.children.get(1), successorNode.data));
                    successorNode.children.set(0, leftChild);

                    return successorNode;
                }
                // Else, if the node has only one child
                else
                {
                    Node successor;

                    if (node.children.get(0) != null)
                        successor = node.children.get(0);
                    else
                        successor = node.children.get(1);

                    return successor;
                }
            }
            else if (value < node.data)
                node.children.set(0, removeRecursive(node.children.get(0), value));
            else
                node.children.set(1, removeRecursive(node.children.get(1), value));
        }
        return node;
    }

    public Node getRandomNode()
    {
        // Create an array based on the in-order traversal of the tree
        ArrayList<Node> nodeArray = new ArrayList<>();

        inOrderAddToArray(this.root, nodeArray);

        int randomInd = (int)(Math.random() * (nodeArray.size() - 0.001));

        return nodeArray.get(randomInd);
    }

    private void inOrderAddToArray(Node node, ArrayList<Node> nodeArray)
    {
        if (node != null)
        {
            inOrderAddToArray(node.children.get(0), nodeArray);
            nodeArray.add(node);
            inOrderAddToArray(node.children.get(1), nodeArray);
        }
    }

    public String inOrderTraversal()
    {
        StringBuilder stringBuilder = new StringBuilder();
        inOrderTraversalRecursive(this.root, stringBuilder);
        return stringBuilder.toString();
    }

    private void inOrderTraversalRecursive(Node node, StringBuilder stringBuilder)
    {
        if (node != null)
        {
            inOrderTraversalRecursive(node.children.get(0), stringBuilder);
            stringBuilder.append(node.data + " ");
            inOrderTraversalRecursive(node.children.get(1), stringBuilder);
        }
    }
}

// GENERIC GRAPH CLASS
class Graph<T>
{
    public class Node
    {
        public T data;
        public ArrayList<Node> adjacent;
        public boolean visited;

        public Node(T data)
        {
            this.setData(data);
            this.adjacent = new ArrayList<>();
        }

        public Node(T data, ArrayList<Node> adjacent)
        {
            this.setData(data);
            this.adjacent = new ArrayList<>();
            this.setAdjacent(adjacent);
        }

        public void setData(T data) { this.data = data; }
        public void setAdjacent(ArrayList<Node> adjacent) { this.adjacent = adjacent; };
    }

    public ArrayList<Node> nodes;

    public Graph()
    {
        this.nodes = new ArrayList<>();
    }

    public Graph(ArrayList<Node> nodes)
    {
        this.nodes = new ArrayList<>();
        this.setNodes(nodes);
    }

    public void setNodes(ArrayList<Node> nodes) { this.nodes = nodes; }
}

// Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
// route between two nodes.
class SolutionIV1
{
    public static void run()
    {
        // Initialize the parent graph
        Graph<String> graph = new Graph<>();

        // Initialize the graph nodes
        Graph<String>.Node sourceNode = graph.new Node("source");
        Graph<String>.Node node1 = graph.new Node("one");
        Graph<String>.Node node2 = graph.new Node("two");
        Graph<String>.Node node3 = graph.new Node("three");
        Graph<String>.Node node4 = graph.new Node("four");
        Graph<String>.Node node5 = graph.new Node("five");
        Graph<String>.Node node6 = graph.new Node("six");
        Graph<String>.Node destinationNode = graph.new Node("destination");

        // Set adjacency for the graph nodes
        ArrayList<Graph<String>.Node> sourceNodeAdjacency = new ArrayList<>();
        sourceNodeAdjacency.add(node1);
        sourceNodeAdjacency.add(node2);
        sourceNode.setAdjacent(sourceNodeAdjacency);
        ArrayList<Graph<String>.Node> node1Adjacency = new ArrayList<>();
        node1Adjacency.add(node4);
        node1Adjacency.add(node3);
        node1Adjacency.add(sourceNode);
        node1.setAdjacent(node1Adjacency);
        ArrayList<Graph<String>.Node> node2Adjacency = new ArrayList<>();
        node2Adjacency.add(node3);
        node2Adjacency.add(sourceNode);
        node2.setAdjacent(node2Adjacency);
        ArrayList<Graph<String>.Node> node3Adjacency = new ArrayList<>();
        node3Adjacency.add(node6);
        node3Adjacency.add(node5);
        node3Adjacency.add(node1);
        node3Adjacency.add(node2);
        node3.setAdjacent(node3Adjacency);
        ArrayList<Graph<String>.Node> node4Adjacency = new ArrayList<>();
        node4Adjacency.add(node5);
        node4Adjacency.add(node1);
        node4.setAdjacent(node4Adjacency);
        ArrayList<Graph<String>.Node> node5Adjacency = new ArrayList<>();
        node5Adjacency.add(destinationNode);
        node5Adjacency.add(node4);
        node5Adjacency.add(node3);
        node5.setAdjacent(node5Adjacency);
        ArrayList<Graph<String>.Node> node6Adjacency = new ArrayList<>();
        node6Adjacency.add(destinationNode);
        node6Adjacency.add(node3);
        node6.setAdjacent(node6Adjacency);
        ArrayList<Graph<String>.Node> destinationNodeAdjacency = new ArrayList<>();
        destinationNodeAdjacency.add(node5);
        destinationNodeAdjacency.add(node6);
        destinationNode.setAdjacent(destinationNodeAdjacency);

        // Set the nodes in the graph
        ArrayList<Graph<String>.Node> graphNodes = new ArrayList<>();
        graphNodes.add(sourceNode);
        graphNodes.add(node1);
        graphNodes.add(node2);
        graphNodes.add(node3);
        graphNodes.add(node4);
        graphNodes.add(node5);
        graphNodes.add(node6);
        graphNodes.add(destinationNode);
        graph.setNodes(graphNodes);

        // Run the algorithm
        System.out.print(sourceNode.data + " and " + destinationNode.data + " are ");
        System.out.println(isConnected(sourceNode, destinationNode) ? "connected." : "not connected.");
    }

    private static boolean isConnected(Graph<String>.Node sourceNode, Graph<String>.Node destinationNode)
    {   
        // Run a breadth first search traversal and see if the destination can be reached
        Queue<Graph<String>.Node> traversalQueue = new LinkedList<>();

        // Mark the source node as visited and add it to the queue
        sourceNode.visited = true;
        traversalQueue.add(sourceNode);

        while (!traversalQueue.isEmpty())
        {
            Graph<String>.Node current = traversalQueue.remove();

            if (current.data == destinationNode.data)
                return true;

            for (Graph<String>.Node adjacentNode : current.adjacent)
            {
                if (!adjacentNode.visited)
                {
                    adjacentNode.visited = true;
                    traversalQueue.add(adjacentNode);
                }
            }
        }

        return false;
    }
}

// Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
// to create a binary search tree with minimal height.
class SolutionIV2
{
    public static void run()
    {
        int[] inputArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println("Input array: ");
        for (int num : inputArray)
            System.out.print(num + " ");
        System.out.println();

        Tree tree = new Tree(2);
        minimalTree(tree, inputArray);

        System.out.println("Traversing BST in-order: ");
        System.out.println(tree.inOrderTraversal());
    }

    private static void minimalTree(Tree tree, int[] uniqueInts)
    {
        recursiveAdd(tree, uniqueInts, 0, uniqueInts.length - 1);
    }

    private static void recursiveAdd(Tree tree, int[] uniqueInts, int startInd, int endInd)
    {
        int midInd = (startInd + endInd) / 2;

        // Add the middle array element to the tree
        tree.add(uniqueInts[midInd]);

        if (startInd < midInd)
        {
            // Recursively run this function for the smaller part of the array
            recursiveAdd(tree, uniqueInts, startInd, midInd - 1);
        }
        

        if (midInd < endInd)
        {
            // Recursively run this function for the larger part of the array
            recursiveAdd(tree, uniqueInts, midInd + 1, endInd);
        }
    }
}

// List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
// at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
class SolutionIV3
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(30);

        System.out.println("Binary search tree contents: ");
        System.out.println(tree.inOrderTraversal());

        Hashtable<Integer, LinkedList<Tree.Node>> levelLinkedList = new Hashtable<>();
        listOfDepths(tree, levelLinkedList);
        int level = 0;
        while (levelLinkedList.containsKey(level))
        {
            System.out.print("Nodes for level " + level + ": ");
            for (Tree.Node node : levelLinkedList.get(level))
                System.out.print(node.data + " ");
            System.out.println();
            level++;
        }
    }

    private static void listOfDepths(Tree tree, Hashtable<Integer, LinkedList<Tree.Node>> levelLinkedList)
    {
        recursiveAddToList(tree.root, levelLinkedList, 0);
    }

    private static void recursiveAddToList(Tree.Node node, Hashtable<Integer, LinkedList<Tree.Node>> levelLinkedList, int level)
    {
        // If the given node is not null
        if (node != null)
        {
            // If there is an existing linked list for the given level,
            // get the linked list and push the node into the linked list
            if (levelLinkedList.containsKey(level))
            {
                LinkedList<Tree.Node> currentLinkedList = levelLinkedList.get(level);
                currentLinkedList.push(node);
            }
            // If not, create a new linked list for the given level
            // and then push the node into the linked list.
            else
            {
                LinkedList<Tree.Node> newLinkedList = new LinkedList<>();
                levelLinkedList.put(level, newLinkedList);
                newLinkedList.push(node);
            }

            if (node.children.get(0) != null)
                recursiveAddToList(node.children.get(0), levelLinkedList, level + 1);

            if (node.children.get(1) != null)
                recursiveAddToList(node.children.get(1), levelLinkedList, level + 1);
        }
    }
}

// Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
// this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
// node never differ by more than one.
class SolutionIV4
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(30);
        tree.add(40);
        tree.add(60);
        System.out.println("Binary search tree contents: ");
        System.out.println(tree.inOrderTraversal());
        System.out.print("The tree is ");
        System.out.println(checkBalanced(tree) ? "balanced." : "not balanced.");

        Tree tree2 = new Tree(2);
        tree2.add(10);
        tree2.add(5);
        tree2.add(20);
        tree2.add(3);
        tree2.add(7);
        tree2.add(30);
        tree2.add(40);
        System.out.println("Binary search tree contents: ");
        System.out.println(tree.inOrderTraversal());
        System.out.print("The tree is ");
        System.out.println(checkBalanced(tree) ? "balanced." : "not balanced.");
    }

    private static boolean checkBalanced(Tree tree)
    {
        if (tree.root == null)
            return true;
            
        if (Math.abs(getHeight(tree.root.children.get(1)) - getHeight(tree.root.children.get(0))) <= 1)
            return true;
        else
            return false;
    }

    private static int getHeight(Tree.Node node)
    {
        if (node == null)
            return -1;

        int rightHeight = getHeight(node.children.get(1));
        int leftHeight = getHeight(node.children.get(0));

        if (rightHeight > leftHeight)
            return rightHeight + 1;
        else
            return leftHeight + 1;
    }
}

// Validate BST: Implement a function to check if a binary tree is a binary search tree.
class SolutionIV5
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(30);
        tree.add(40);
        tree.add(60);
        System.out.println("Binary tree contents: ");
        System.out.println(tree.inOrderTraversal());
        System.out.print("The tree is ");
        System.out.println(validateBST(tree) ? "a binary search tree." : "not a binary search tree.");
    }

    private static boolean validateBST(Tree tree)
    {
        return recursiveValidateBST(tree.root, null, null);
    }

    private static boolean recursiveValidateBST(Tree.Node node, Integer min, Integer max)
    {
        if (node == null)
            return true;

        // Check for the noncompliance of the node's data
        if ((min != null && node.data <= min) || (max != null && node.data > max))
            return false;

        if (!recursiveValidateBST(node.children.get(0), min, node.data) || !recursiveValidateBST(node.children.get(1), node.data, max))
            return false;

        return true;
    }
}

// Successor: Write an algorithm to find the "next" node (i .e., in-order successor) of a given node in a
// binary search tree. You may assume that each node has a link to its parent.
class SolutionIV6
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(30);
        tree.add(40);
        tree.add(60);
        System.out.println("Binary tree contents: ");
        System.out.println(tree.inOrderTraversal());
        Boolean nodeFound = null;
        successor(tree, tree.root, tree.root.children.get(1), nodeFound);
    }

    private static void successor(Tree tree, Tree.Node node, Tree.Node targetNode, Boolean nodeFound)
    {
        if (node != null)
        {
            successor(tree, node.children.get(0), targetNode, nodeFound);

            if (nodeFound != null && nodeFound)
            {
                System.out.println("Successor to " + targetNode.data + " found: " + node.data);
                nodeFound = false;
            }
                
            if (node == targetNode)
                nodeFound = true;

            successor(tree, node.children.get(1), targetNode, nodeFound);
        }
    }
}

// Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
// projects, where the second project is dependent on the first project). All of a project's dependencies
// must be built before the project is. Find a build order that will allow the projects to be built. If there
// is no valid build order, return an error.
class SolutionIV7
{
    static class DependencyPair
    {
        public char dependentOn;
        public char data;

        public DependencyPair(char dependentOn, char data)
        {
            this.dependentOn = dependentOn;
            this.data = data;
        }
    }

    public static void run()
    {
        ArrayList<Character> projects = new ArrayList<>();
        projects.add('a');
        projects.add('b');
        projects.add('c');
        projects.add('d');
        projects.add('e');
        projects.add('f');
        projects.add('g');
        ArrayList<DependencyPair> dependencies = new ArrayList<>();
        dependencies.add(new DependencyPair('f', 'c'));
        dependencies.add(new DependencyPair('f', 'b'));
        dependencies.add(new DependencyPair('f', 'a'));
        dependencies.add(new DependencyPair('c', 'a'));
        dependencies.add(new DependencyPair('b', 'a'));
        dependencies.add(new DependencyPair('a', 'e'));
        dependencies.add(new DependencyPair('b', 'e'));
        dependencies.add(new DependencyPair('d', 'g'));

        buildOrder(projects, dependencies);
    }

    private static void buildOrder(ArrayList<Character> projects, ArrayList<DependencyPair> dependencies)
    {
        // Create graph nodes and its paths.
        Graph<Character> dependencyGraph = new Graph<>();

        // Create an arraylist of project nodes
        ArrayList<Graph<Character>.Node> projectNodes = new ArrayList<>();
        for (char project : projects)
            projectNodes.add(dependencyGraph.new Node(project));

        // Set the adjacency arraylist for each of the nodes
        for (Graph<Character>.Node currentProject : projectNodes)
        {
            ArrayList<Graph<Character>.Node> adjacentNodes = new ArrayList<>();

            // Iterate through all the dependent pairs
            for (DependencyPair depPair : dependencies)
            {
                // If there is a project dependent on the node...
                if (depPair.dependentOn == currentProject.data)
                {
                    for (Graph<Character>.Node projectNode : projectNodes)
                    {
                        if (projectNode.data == depPair.data)
                            adjacentNodes.add(projectNode);
                    }
                }
            }
            
            currentProject.setAdjacent(adjacentNodes);
        }
            

        // Find the independent nodes
        Queue<Graph<Character>.Node> independentProjects = new LinkedList<>();

        for (Graph<Character>.Node iProjectNode : projectNodes)
        {
            boolean isIndependent = true;

            // Iterate through all the dependent pairs
            for (DependencyPair depPair : dependencies)
            {
                // If there is a project dependent on the node...
                if (depPair.data == iProjectNode.data)
                {
                    isIndependent = false;
                    break;
                }
            }

            if (isIndependent)
                independentProjects.add(iProjectNode);
        }

        // Run Kahn's algorithm
        ArrayList<Graph<Character>.Node> buildOrder = new ArrayList<>();
        
        while (!independentProjects.isEmpty())
        {
            // Add a project from the queue to the arraylist
            Graph<Character>.Node independentProject = independentProjects.remove();
            buildOrder.add(independentProject);

            // Copy the adjacent nodes to a new arraylist
            ArrayList<Graph<Character>.Node> adjacentProjects = new ArrayList<>();
            for (Graph<Character>.Node dependentProject : independentProject.adjacent)
                adjacentProjects.add(dependentProject);
            
            // Clear the adjacent projects for this node
            independentProject.adjacent = new ArrayList<>();

            // Loop through the adjacent nodes
            for (Graph<Character>.Node adjacentProject : adjacentProjects)
            {
                boolean isIndependent = true;
                
                for (Graph<Character>.Node projectNode : projectNodes)
                    for (Graph<Character>.Node projectAdjacentNode : projectNode.adjacent)
                        if (projectAdjacentNode == adjacentProject)
                            isIndependent = false;

                if (isIndependent)
                    independentProjects.add(adjacentProject);
            }
        }

        // If there is an unvisited node, return an error.
        for (Graph<Character>.Node projectNode : projectNodes)
        {
            if (projectNode.adjacent.size() > 0)
            {
                System.out.println("There is no valid build order.");
                return;
            }
        }

        // If not, print the path.
        StringBuilder stringBuilder = new StringBuilder();
        for (Graph<Character>.Node projectNode : buildOrder)
            stringBuilder.append(projectNode.data + " ");
        System.out.println(stringBuilder.toString());
    }
}

// First Common Ancestor: Design an algorithm and write code to find the first common ancestor
// of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
// necessarily a binary search tree.
class SolutionIV8
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(30);
        tree.add(40);
        tree.add(25);
        tree.add(15);

        System.out.println("Binary search tree contents: ");
        System.out.println(tree.inOrderTraversal());

        Tree.Node fca = firstCommonAncestor(tree.root, tree.root.children.get(0).children.get(1), tree.root.children.get(1).children.get(1).children.get(0));
        System.out.println("The first common ancestor is: " + fca.data);
    }

    private static Tree.Node firstCommonAncestor(Tree.Node node, Tree.Node nodeA, Tree.Node nodeB)
    {
        // If the target nodes can be reached from this node,
        // try going down the children and see which node still can reach the target nodes
        // If one of the children still can reach both the target nodes, return the children node
        // If not, then return this node

        //System.out.println(node.data);
        if (findNode(nodeA, node) && findNode(nodeB, node))
        {
            
            if (firstCommonAncestor(node.children.get(0), nodeA, nodeB) != null)
                return firstCommonAncestor(node.children.get(0), nodeA, nodeB);

            if (firstCommonAncestor(node.children.get(1), nodeA, nodeB) != null)
                return firstCommonAncestor(node.children.get(1), nodeA, nodeB);

            return node;
        }

        return null;
    }

    private static boolean findNode(Tree.Node targetNode, Tree.Node currentNode)
    {
        if (currentNode != null)
        {
            if (findNode(targetNode, currentNode.children.get(0)))
                return true;
            if (targetNode == currentNode)
                return true;
            if (findNode(targetNode, currentNode.children.get(1)))
                return true;

            return false;
        }
        return false;
    }
}

// BST Sequences: A binary search tree was created by traversing through an array from left to right
// and inserting each element. Given a binary search tree with distinct elements, print all possible
// arrays that could have led to this tree.
class SolutionIV9
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(4);
        tree.add(2);
        tree.add(5);
        tree.add(1);
        tree.add(3);
        tree.add(6);

        System.out.println("Binary search tree contents: ");
        System.out.println(tree.inOrderTraversal());
        bstSequence(tree);
    }

    private static void bstSequence(Tree tree)
    {
        ArrayList<ArrayList<Tree.Node>> possiblePaths = new ArrayList<>();
        ArrayList<Tree.Node> tentativePath = new ArrayList<>();
        ArrayList<Tree.Node> nodeChoices = new ArrayList<>();

        // Add the root node into the possible nodes.
        nodeChoices.add(tree.root);
        
        // Build the path from the initial given choice.
        buildPath(tentativePath, nodeChoices, possiblePaths);

        // Print each of the paths
        for (int i = 0; i < possiblePaths.size(); i++)
        {
            System.out.print("Path #" + (i + 1) + ": ");
            for (Tree.Node node : possiblePaths.get(i))
                System.out.print(node.data + " ");
            System.out.println();
        }
    }

    private static void buildPath(ArrayList<Tree.Node> tentativePath, ArrayList<Tree.Node> nodeChoices, ArrayList<ArrayList<Tree.Node>> possiblePaths)
    {
        // If there is no longer any choices, then the path is considered final.
        if (nodeChoices.size() == 0)
            possiblePaths.add(tentativePath);

        // For all the node choices
        for (Tree.Node node : nodeChoices)
        {
            // Create a copy of the tentative path and node choice argument
            ArrayList<Tree.Node> tentativePathCopy = new ArrayList<>();
            for (Tree.Node tempNode : tentativePath)
                tentativePathCopy.add(tempNode);

            ArrayList<Tree.Node> nodeChoicesCopy = new ArrayList<>();
            for (Tree.Node tempNode : nodeChoices)
                nodeChoicesCopy.add(tempNode);

            // Remove the node from the choice
            nodeChoicesCopy.remove(node);

            // Add the node to the preceding path
            tentativePathCopy.add(node);

            // Add the children of the node into the node choices if it exist
            if (node.children.get(0) != null)
                nodeChoicesCopy.add(node.children.get(0));
                
            if (node.children.get(1) != null)
                nodeChoicesCopy.add(node.children.get(1));
            
            // Build a path out of them
            buildPath(tentativePathCopy, nodeChoicesCopy, possiblePaths);
        }
    }
}

// Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
// algorithm to determine if T2 is a subtree of Tl.
// A tree T2 is a subtree ofTi if there exists a node n in Ti such that the subtree of n is identical to T2.
// That is, if you cut off the tree at node n, the two trees would be identical.
class SolutionIV10
{
    public static void run()
    {
        Tree bigTree = new Tree(2);
        bigTree.add(4);
        bigTree.add(2);
        bigTree.add(5);
        bigTree.add(1);
        bigTree.add(3);
        bigTree.add(6);

        Tree smallTree = new Tree(2);
        smallTree.add(2);
        smallTree.add(3);
        smallTree.add(1);

        System.out.println("Big BST contents: ");
        System.out.println(bigTree.inOrderTraversal());
        System.out.println("Small BST contents: ");
        System.out.println(smallTree.inOrderTraversal());

        System.out.print("The smaller tree is ");
        System.out.print(checkSubtree(bigTree, smallTree) ? "a subtree" : "not a subtree");
        System.out.println(" of the bigger tree.");
    }

    private static boolean checkSubtree(Tree bigTree, Tree smallTree)
    {
        // Traverse the bigger tree, looking for a node that is similar to the smaller tree's root node.
        // If there is no similar node or if all the similar nodes are fully traversed and there is no match,
        // then the smaller tree is not a subtree.
        return findSubtreeRecursive(bigTree.root, smallTree.root);
    }

    private static boolean findSubtreeRecursive(Tree.Node bigTreeNode, Tree.Node smallTreeNode)
    {
        if (bigTreeNode != null)
        {
            boolean isSubtreeInLeftChild = findSubtreeRecursive(bigTreeNode.children.get(0), smallTreeNode);

            // For every similar node, traverse the smaller tree and the portion of the larger tree at the same time.
            boolean isThisNodeASubtree = (bigTreeNode.data == smallTreeNode.data) && (verifySubtree(bigTreeNode, smallTreeNode));
                
            boolean isSubtreeInRightChild = findSubtreeRecursive(bigTreeNode.children.get(1), smallTreeNode);

            return isSubtreeInLeftChild || isThisNodeASubtree || isSubtreeInRightChild;
        }
        else
            return false;
    }

    private static boolean verifySubtree(Tree.Node bigTreeNode, Tree.Node smallTreeNode)
    {
        // If both nodes are null, then it is a subtree.
        if (bigTreeNode == null && smallTreeNode == null)
            return true;
        // If not...
        else
        {
            // If both nodes are not null, verify that the children are subtrees and 
            // that the value in the current node is the same.
            if (bigTreeNode != null && smallTreeNode != null)
            {
                return 
                (
                    verifySubtree(bigTreeNode.children.get(0), smallTreeNode.children.get(0)) &&
                    (bigTreeNode.data == smallTreeNode.data) && 
                    verifySubtree(bigTreeNode.children.get(1), smallTreeNode.children.get(1))
                );
            }
            // If not, then they are not subtrees.
            else
                return false;
        }
    }
}

// Random Node: You are implementing a binary tree class from scratch which, in addition to
// insert, find, and delete, has a method getRandomNode() which returns a random node
// from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
// for getRandomNode, and explain how you would implement the rest of the methods.
class SolutionIV11
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(15);
        tree.add(30);
        tree.add(25);
        tree.add(40);
        tree.add(21);

        System.out.println("BST contents: ");
        System.out.println(tree.inOrderTraversal());
        System.out.print(7 + " is ");
        System.out.println(tree.find(7) ? "found." : "not found.");
        System.out.print(22 + " is ");
        System.out.println(tree.find(22) ? "found." : "not found.");
        System.out.println("Deleting " + 3 + " from the BST.");
        tree.remove(3);
        System.out.println(tree.inOrderTraversal());
        System.out.println("Deleting " + 25 + " from the BST.");
        tree.remove(25);
        System.out.println(tree.inOrderTraversal());
        System.out.println("Deleting " + 20 + " from the BST.");
        tree.remove(20);
        System.out.println(tree.inOrderTraversal());
        System.out.println("Deleting " + 10 + " from the BST.");
        tree.remove(10);
        System.out.println(tree.inOrderTraversal());
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
        System.out.println("A random node from from the BST: " + tree.getRandomNode().data);
    }
}

// Paths with Sum: You are given a binary tree in which each node contains an integer value (which
// might be positive or negative). Design an algorithm to count the number of paths that sum to a
// given value. The path does not need to start or end at the root or a leaf, but it must go downwards
// (traveling only from parent nodes to child nodes).
class SolutionIV12
{
    public static void run()
    {
        Tree tree = new Tree(2);
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(3);
        tree.add(7);
        tree.add(15);
        tree.add(30);
        tree.add(25);
        tree.add(40);
        tree.add(21);

        System.out.println("Binary search tree contents: ");
        System.out.println(tree.inOrderTraversal());

        System.out.println("Number of paths in the BST that sum up to " + 15 + ": " + countPathsWithSum(tree.root, 15));
    }

    private static int countPathsWithSum(Tree.Node node, int sum)
    {
        // If the given node is null, return 0
        if (node == null)
            return 0;

        // Count all the possible paths that start from the given node
        int pathsFromNode = countPathsWithSumFromNode(node, sum, 0);

        // Rerun this function for the left child
        int pathsFromLeftChild = countPathsWithSum(node.children.get(0), sum);

        // Rerun this function for the right child
        int pathsFromRightChild = countPathsWithSum(node.children.get(1), sum);

        // Return the total of the possible paths (this node and its children)
        return pathsFromNode + pathsFromLeftChild + pathsFromRightChild;
    }

    private static int countPathsWithSumFromNode(Tree.Node node, int sum, int currentSum)
    {
        // If the given node is null, return 0
        if (node == null)
            return 0;

        // Add the given node's value to the current running sum
        currentSum += node.data;

        // Keep count of the total path, starting from 0
        int totalPaths = 0;

        // If the running sum equals to the target sum, increment the total path
        if (currentSum == sum)
            totalPaths++;

        // Add the total path with the total path given by running this function to the children of this path
        totalPaths += countPathsWithSumFromNode(node.children.get(0), sum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.children.get(1), sum, currentSum);

        // Return the total path
        return totalPaths;
    }
}