import java.lang.String;
import java.lang.StringBuilder;
import java.util.Hashtable;

public class CTCI2 {
    public static void main(String args[])
    {
        System.out.println("Solution II-1");
        SolutionII1.run();
        System.out.println();

        System.out.println("Solution II-2");
        SolutionII2.run();
        System.out.println();

        System.out.println("Solution II-3");
        SolutionII3.run();
        System.out.println();

        System.out.println("Solution II-4");
        SolutionII4.run();
        System.out.println();

        System.out.println("Solution II-5");
        SolutionII5.run();
        System.out.println();
        
        System.out.println("Solution II-6");
        SolutionII6.run();
        System.out.println();

        System.out.println("Solution II-7");
        SolutionII7.run();
        System.out.println();

        System.out.println("Solution II-8");
        SolutionII8.run();
        System.out.println();
    }
}

// TEMPLATE LINKED LIST CLASS
class LinkedList<T>
{
    public class Node
    {
        Node next = null;
        T data;

        public Node(T data)
        {
            this.data = data;
        }
    }

    public Node head = null;
    public int length = 0;

    public LinkedList()
    {}

    public LinkedList(LinkedList<T> linkedList)
    {
        Node current = linkedList.head;
        while (current != null)
        {
            this.append(current.data);
            current = current.next;
        }
    }

    public Node getNode(int index)
    {
        Node iterator = head;
        for (int i = 0; i < index; i++)
        {
            iterator = iterator.next;
        }
        return iterator;
    }
    
    public void append(T data)
    {
        Node newNode = new Node(data);
        
        if (this.head == null)
        {
            this.head = newNode;
            this.length++;
            return;
        }
        else
        {
            Node iterator = this.head;

            while (iterator.next != null)
            {
                iterator = iterator.next;
            }

            iterator.next = newNode;
            this.length++;
        }
    }

    public boolean delete(int index)
    {
        if (index >= this.length)
            return false;
        else
        {
            // Case when there is only one element
            if (length == 1)
            {
                this.head = null;
                this.length--;
                return true;
            }
            else
            {
                if (index == 0)
                {
                    this.head = this.head.next;
                    this.length--;
                    return true;
                }
                else
                {
                    Node prevIterator = null;
                    Node iterator = this.head;

                    for (int i = 0; i < index; i++)
                    {
                        prevIterator = iterator;
                        iterator = iterator.next;
                    }

                    prevIterator.next = iterator.next;
                    this.length--;

                    return true;
                }
            }
        }
    }

    public void reverse()
    {
        // Set the last element as the head
        this.head = this.internalReverse(this.head, null);
    }

    private Node internalReverse(Node first, Node last)
    {
        if (first == last)
            return null;
        else
        {
            Node current = first;

            // Get the pointer to the last element
            while (current.next != last)
            {
                current = current.next;
            }
            
            // Recursively call this function
            current.next = internalReverse(first, current);

            // Return the last element
            return current;
        }
    }

    @Override
    public String toString()
    {
        if (this.head == null)
        {
            return "empty";
        }
        else
        {
            StringBuilder stringBuilder = new StringBuilder();
            Node iterator = this.head;

            while (iterator != null)
            {
                stringBuilder.append(iterator.data + " ");
                iterator = iterator.next;
            }

            return stringBuilder.toString();
        }
    }
}

// Remove Dups: Write code to remove duplicates from an unsorted linked list.
class SolutionII1
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Integer> testLinkedList = new LinkedList<>();
        testLinkedList.append(23);
        testLinkedList.append(3);
        testLinkedList.append(53);
        testLinkedList.append(100);
        testLinkedList.append(32);
        testLinkedList.append(100);
        testLinkedList.append(50);
        testLinkedList.append(53);
        testLinkedList.append(27);
        testLinkedList.append(20);
        System.out.println("Linked list contents: " + testLinkedList);

        LinkedList<Integer> outLinkedList = removeDups(testLinkedList);
        System.out.println("Duplicate removed linked list: " + outLinkedList);
    }

    public static LinkedList<Integer> removeDups(LinkedList<Integer> linkedList)
    {
        LinkedList<Integer>.Node iterator = linkedList.head;
        int index = 0;

        while (iterator != null)
        {
            LinkedList<Integer>.Node innerIterator = iterator.next;
            int innerIndex = index + 1;

            while (innerIterator != null)
            {
                if (iterator.data == innerIterator.data)
                {
                    innerIterator = innerIterator.next;
                    linkedList.delete(innerIndex);
                }
                else
                {
                    innerIterator = innerIterator.next;
                    innerIndex++;
                }
            }

            iterator = iterator.next;
            index++;
        }

        return linkedList;
    }
}

// Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
class SolutionII2
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.append(23);
        testLinkedList.append(3);
        testLinkedList.append(53);
        testLinkedList.append(100);
        testLinkedList.append(32);
        testLinkedList.append(100);
        testLinkedList.append(50);
        testLinkedList.append(53);
        testLinkedList.append(27);
        testLinkedList.append(20);
        System.out.println("Linked list contents: " + testLinkedList);

        int k = 3;
        System.out.println("The " + k + "th element is: " + kthToLast(k, testLinkedList));
    }

    public static int kthToLast(int k, LinkedList<Integer> linkedList)
    {
        if (k > linkedList.length)
        {
            return linkedList.head.data;
        }
        else
        {
            LinkedList<Integer>.Node head = linkedList.head;
            LinkedList<Integer>.Node tail = linkedList.head;

            for (int i = 0; i < k; i++)
            {
                head = head.next;
            }

            while (head != null)
            {
                head = head.next;
                tail = tail.next;
            }

            return tail.data;
        }
    }
}

// Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any node but
// the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
// that node.
class SolutionII3
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.append(23);
        testLinkedList.append(3);
        testLinkedList.append(53);
        testLinkedList.append(100);
        testLinkedList.append(32);
        testLinkedList.append(100);
        testLinkedList.append(50);
        testLinkedList.append(53);
        testLinkedList.append(27);
        testLinkedList.append(20);
        System.out.println("Linked list contents: " + testLinkedList);

        LinkedList<Integer>.Node deleteNode = testLinkedList.getNode(3);
        testLinkedList = deleteMiddleNode(deleteNode, testLinkedList);
        System.out.println("Linked list contents after deletion: " + testLinkedList);
    }

    public static LinkedList<Integer> deleteMiddleNode(LinkedList<Integer>.Node deleteNode, LinkedList<Integer> linkedList)
    {
        // Return original linked list if the given node is at the start or the end of the linked list
        if (deleteNode == null || deleteNode.next == null)
            return linkedList;
        else
        {
            LinkedList<Integer>.Node nextNode = deleteNode.next;
            deleteNode.data = nextNode.data;
            deleteNode.next = nextNode.next;
            return linkedList;
        }
    }
}

// Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
// before all nodes greater than or equal to x. lf x is contained within the list, the values of x only need
// to be after the elements less than x (see below). The partition element x can appear anywhere in the
// "right partition"; it does not need to appear between the left and right partitions.
class SolutionII4
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Integer> testLinkedList = new LinkedList<Integer>();
        testLinkedList.append(23);
        testLinkedList.append(3);
        testLinkedList.append(53);
        testLinkedList.append(100);
        testLinkedList.append(32);
        testLinkedList.append(100);
        testLinkedList.append(50);
        testLinkedList.append(53);
        testLinkedList.append(27);
        testLinkedList.append(20);
        System.out.println("Linked list contents: " + testLinkedList);

        testLinkedList = partition(50, testLinkedList);
        System.out.println("Linked list contents after partition: " + testLinkedList);
    }

    public static LinkedList<Integer> partition(int value, LinkedList<Integer> linkedList)
    {
        LinkedList<Integer>.Node prevIt = null;
        LinkedList<Integer>.Node it = linkedList.head;
        int count = 0;

        // Keep looping until all elements have been traversed or moved
        while (count <= linkedList.length && it != null)
        {
            // Move the node to the end of the linked list if the node data equals
            // or exceeds the given value
            if (it.data >= value)
            {
                
                // Connect the previous node to the next node first
                if (it == linkedList.head)
                {
                    prevIt = it;
                    prevIt.next = it.next;
                }
                else
                {
                    prevIt.next = it.next;
                }

                // Create an inner iterator to traverse the rest of the list
                LinkedList<Integer>.Node innerIt = it;

                // Get the inner iterator to the last node of the list
                while (innerIt.next != null)
                {
                    innerIt = innerIt.next;
                }

                // Set the last element to connect to the moved node
                innerIt.next = it;
                it.next = null;

                // Reset the iterator to the node next to the previous node
                it = prevIt.next;
            }
            else
            {
                // Move both pointers
                prevIt = it;
                it = it.next;
            }
            count++;
        }

        return linkedList;
    }
}

// Sum Lists: You have two numbers represented by a linked list, where each node contains a single
// digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a
// function that adds the two numbers and returns the sum as a linked list.
class SolutionII5
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Integer> a = new LinkedList<Integer>();
        a.append(6);
        a.append(1);
        a.append(7);

        LinkedList<Integer> b = new LinkedList<Integer>();
        b.append(2);
        b.append(9);
        b.append(5);

        System.out.println(a + " + " + b + " = " + add(a, b));
    }

    private static LinkedList<Integer> add(LinkedList<Integer> a, LinkedList<Integer> b)
    {
        int aDigits = a.length;
        int aInt = 0;
        
        LinkedList<Integer>.Node aIt = a.head;
        while (aIt != null)
        {
            // Get the value of each digit and append it to aInt
            int nodeVal = aIt.data * (int)Math.pow(10, aDigits - 1);
            aInt += nodeVal;
            aDigits--;

            // Move iterator forward
            aIt = aIt.next;
        }

        int bDigits = b.length;
        int bInt = 0;
        
        LinkedList<Integer>.Node bIt = b.head;
        while (bIt != null)
        {
            // Get the value of each digit and append it to bInt
            int nodeVal = bIt.data * (int)Math.pow(10, bDigits - 1);
            bInt += nodeVal;
            bDigits--;

            // Move iterator forward
            bIt = bIt.next;
        }

        LinkedList<Integer> c = new LinkedList<Integer>();
        int cInt = aInt + bInt;
        int cLength = String.valueOf(cInt).length();
        for (int i = cLength; i > 0; i--)
        {
            int divisor = (int)Math.pow(10, i - 1);
            int digit = cInt / divisor;

            c.append(digit);
            cInt = cInt - digit * divisor;
        }

        return c;
    }
}

// Palindrome: Implement a function to check if a linked list is a palindrome.
class SolutionII6
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Character> testLinkedList = new LinkedList<Character>();
        testLinkedList.append('x');
        testLinkedList.append('a');
        testLinkedList.append('n');
        testLinkedList.append('a');
        testLinkedList.append('p');
        System.out.print(testLinkedList + " is ");
        System.out.println(isPalindrome(testLinkedList) ? "a palindrome." : "not a palindrome.");

        LinkedList<Character> testLinkedList2 = new LinkedList<Character>();
        testLinkedList2.append('t');
        testLinkedList2.append('e');
        testLinkedList2.append('n');
        testLinkedList2.append('e');
        testLinkedList2.append('t');
        System.out.print(testLinkedList2 + " is ");
        System.out.println(isPalindrome(testLinkedList2) ? "a palindrome." : "not a palindrome.");
    }

    private static boolean isPalindrome(LinkedList<Character> linkedList)
    {
        // Create a reversed version of the linked list
        LinkedList<Character> reversedLinkedList = new LinkedList<Character>(linkedList);
        reversedLinkedList.reverse();

        LinkedList<Character>.Node current = linkedList.head;
        LinkedList<Character>.Node reversedCurrent = reversedLinkedList.head;
        while (current != null && reversedCurrent != null)
        {
            if (current.data != reversedCurrent.data)
                return false;

            current = current.next;
            reversedCurrent = reversedCurrent.next;
        }
        return true;
    }
}

// Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting
// node. Note that the intersection is defined based on reference, not value. That is, if the kth
// node of the first linked list is the exact same node (by reference) as the jth node of the second
// linked list, then they are intersecting.
class SolutionII7
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Character> testLinkedList = new LinkedList<Character>();
        testLinkedList.append('x');
        testLinkedList.append('a');
        testLinkedList.append('n');
        testLinkedList.append('a');
        testLinkedList.append('p');

        LinkedList<Character> testLinkedList2 = new LinkedList<Character>();
        testLinkedList2.append('t');
        testLinkedList2.append('e');
        testLinkedList2.append('n');
        testLinkedList2.append('e');
        testLinkedList2.append('t');

        LinkedList<Character>.Node intersectingNode = intersection(testLinkedList, testLinkedList2);
        System.out.print(testLinkedList + " and " + testLinkedList2 + " are ");
        System.out.println(intersectingNode != null ? "intersecting." : "not intersecting.");
    }

    private static LinkedList<Character>.Node intersection(LinkedList<Character> a, LinkedList<Character> b)
    {
        // Get the last node of the linked lists
        LinkedList<Character>.Node aPtr = a.head;
        while (aPtr.next != null)
            aPtr = aPtr.next;

        LinkedList<Character>.Node bPtr = b.head;
        while (aPtr.next != null)
            bPtr = bPtr.next;

        // If the last node are different, there are no intersection
        if (aPtr != bPtr)
            return null;
        // If they are the same, find the intersecting node
        else
        {
            // Reset the pointers
            aPtr = a.head;
            bPtr = b.head;

            // Get the difference in length
            int lengthDifference = a.length - b.length;

            // Advance the pointers so that they can move at the same pace
            if (lengthDifference > 0)
                for (int i = 0; i < lengthDifference; i++)
                    aPtr = aPtr.next;
            if (lengthDifference < 0)
                for (int i = 0; i < (0 - lengthDifference); i++)
                    bPtr = bPtr.next;

            // Return the first same node
            while (aPtr != null && bPtr != null)
            {
                if (aPtr == bPtr)
                    return aPtr;
                
                aPtr = aPtr.next;
                bPtr = bPtr.next;
            }

            return aPtr;
        }
    }
}

// Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
// beginning of the loop.
class SolutionII8
{
    public static void run()
    {
        // Create a sample linked list
        LinkedList<Character> testLinkedList = new LinkedList<Character>();
        testLinkedList.append('a');
        testLinkedList.append('b');
        testLinkedList.append('c');
        testLinkedList.append('d');
        testLinkedList.append('e');

        // Intentionally make a loop in the linked list
        LinkedList<Character>.Node lastNode = testLinkedList.head;
        while (lastNode.next != null)
        {
            lastNode = lastNode.next;
        }
        lastNode.next = testLinkedList.head;

        LinkedList<Character>.Node loopNode = loopDetection(testLinkedList);
        System.out.print("There is ");
        System.out.println(loopNode != null ? "a loop in the linked list." : "not a loop in the linked list.");
    }

    private static LinkedList<Character>.Node loopDetection(LinkedList<Character> linkedList)
    {
        LinkedList<Character>.Node current = linkedList.head;
        Hashtable<LinkedList<Character>.Node, Boolean> nodeVisited = new Hashtable<>();

        while (current != null)
        {
            if (nodeVisited.containsKey(current))
                return current;
            else
            {
                nodeVisited.put(current, true);
                // Move the pointer forward
                current = current.next;
            }
        }

        return null;
    }
}