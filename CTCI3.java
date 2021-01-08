import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class CTCI3 {
    public static void main(String args[])
    {
        System.out.println("Solution III-1");
        SolutionIII1.run();
        System.out.println();

        System.out.println("Solution III-2");
        SolutionIII2.run();
        System.out.println();

        System.out.println("Solution III-3");
        SolutionIII3.run();
        System.out.println();

        System.out.println("Solution III-4");
        SolutionIII4.run();
        System.out.println();

        System.out.println("Solution III-5");
        SolutionIII5.run();
        System.out.println();

        System.out.println("Solution III-6");
        SolutionIII6.run();
        System.out.println();
    }
}

// TEMPLATE STACK CLASS
class Stack
{
    class Node
    {
        int data;
        int minData;
        Node next;

        public Node(int data, int minData)
        {
            this.data = data;
            this.minData = minData;
        }
    }

    Node top;

    public int pop()
    {
        if (top == null)
            throw new EmptyStackException();
        
        int data = top.data;
        top = top.next;

        return data;
    }

    public void push(int data)
    {
        int minData;
        if (top == null || data < top.minData)
            minData = data;
        else
            minData = top.minData;

        Node newNode = new Node(data, minData);
        newNode.next = top;
        top = newNode;
    }

    public int peek()
    {
        if (top == null)
            throw new EmptyStackException();

        return top.data;
    }

    public int min()
    {
        return top.minData;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        Node current = top;
        while (current != null)
        {
            stringBuilder.append(current.data + " ");
            current = current.next;
        }
        return stringBuilder.toString();
    }
}

// TEMPLATE QUEUE CLASS
class Queue<T>
{
    class Node
    {
        T data;
        Node next;

        public Node(T data)
        {
            this.data = data;
        }
    }

    Node first;
    Node last;

    public void add(T item)
    {
        Node t = new Node(item);
        if (last != null)
        {
            last.next = t;
        }
        last = t;
        if (first == null)
        {
            first = last;
        }
    }

    public T remove()
    {
        if (first == null)
            throw new NoSuchElementException();

        T data = first.data;
        first = first.next;
        if (first == null)
        {
            last = null;
        }
        return data;
    }

    public T peek()
    {
        if (first == null)
            throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty()
    {
        return first == null;
    }
}

// Three in One: Describe how you could use a single array to implement three stacks.
class SolutionIII1
{
    public static void run()
    {
        // Declare and put some values in the stacks
        ArrayStacks arrayStacks = new ArrayStacks(3, 10);
        for (int i = 0; i < 8; i++)
        {
            arrayStacks.push(0, i + 1);
            arrayStacks.push(1, i + 11);
            arrayStacks.push(2, i + 21);
        }

        System.out.println("Peeking: ");
        System.out.println("Stack 1: " + arrayStacks.peek(0));
        System.out.println("Stack 2: " + arrayStacks.peek(1));
        System.out.println("Stack 3: " + arrayStacks.peek(2));

        System.out.println("Popping: ");
        System.out.println("Stack 1: " + arrayStacks.pop(0));
        System.out.println("Stack 1: " + arrayStacks.pop(0));
        System.out.println("Stack 1: " + arrayStacks.pop(0));
        System.out.println("Stack 2: " + arrayStacks.pop(1));
        System.out.println("Stack 2: " + arrayStacks.pop(1));
        System.out.println("Stack 2: " + arrayStacks.pop(1));
        System.out.println("Stack 3: " + arrayStacks.pop(2));
        System.out.println("Stack 3: " + arrayStacks.pop(2));
        System.out.println("Stack 3: " + arrayStacks.pop(2));
    }
}

class ArrayStacks
{
    private int nStacks;
    private int stackCapacity;
    private int data[];
    private int stackSizes[];

    public ArrayStacks(int nStacks, int capacity)
    {
        this.nStacks = nStacks;
        this.stackCapacity = capacity;
        this.data = new int[nStacks * capacity];
        this.stackSizes = new int[nStacks];
    }

    public int getNStacks() { return this.nStacks; }

    public void push(int stackNum, int data)
    {
        if (this.isFull(stackNum))
            return;

        this.stackSizes[stackNum]++;
        this.data[this.indexOfTop(stackNum)] = data;
    }

    public int pop(int stackNum)
    {
        if (this.isEmpty(stackNum))
            throw new EmptyStackException();
        else
        {
            int outputData = this.data[this.indexOfTop(stackNum)];
            this.stackSizes[stackNum]--;
            return outputData;
        }
    }

    public int peek(int stackNum)
    {
        if (this.isEmpty(stackNum))
            throw new EmptyStackException();
        else
            return this.data[this.indexOfTop(stackNum)];
    }

    public boolean isEmpty(int stackNum)
    {
        return this.stackSizes[stackNum] == 0;
    }

    public boolean isFull(int stackNum)
    {
        return this.stackSizes[stackNum] == this.stackCapacity;
    }

    private int indexOfTop(int stackNum)
    {
        int indexOfBottom = this.stackCapacity * stackNum;
        return indexOfBottom + this.stackSizes[stackNum] - 1;
    }
}

// Stack Min: How would you design a stack which, in addition to push and pop, has a function min
// which returns the minimum element? Push, pop and min should all operate in 0(1) time.
class SolutionIII2
{
    public static void run()
    {
        Stack stack = new Stack();

        System.out.println("PUSHING");
        for (int i = 0; i < 10; i++)
        {
            int randInt = (int)(Math.random() * 100);
            System.out.println("Pushing " + randInt + " into stack.");
            stack.push(randInt);
            System.out.println("The minimum value is " + stack.min());
        }

        System.out.println("POPPING");
        for (int i = 0; i < 10; i++)
        {
            System.out.println("The minimum value is " + stack.min());
            System.out.println("Popping " + stack.pop() + " from stack.");
        }
    }
}

// Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
// Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
// threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
// composed of several stacks and should create a new stack once the previous one exceeds capacity.
// SetOfStacks. push() and SetOfStacks. pop() should behave identically to a single stack
// (that is, pop() should return the same values as it would if there were just a single stack).
class SolutionIII3
{
    public static void run()
    {
        SetOfStacks setOfStacks = new SetOfStacks(5);
        for (int i = 0; i < 15; i++)
            setOfStacks.push(i + 1);

        System.out.println("Peeking: ");
        System.out.println(setOfStacks.peek());

        System.out.println("Popping: ");
        for (int i = 0; i < 15; i++)
            System.out.println(setOfStacks.pop());
    }
}

class SetOfStacks
{
    private ArrayList<Stack> stacks;
    int lastStackCount;
    int stackCapacity;

    public SetOfStacks(int stackCapacity)
    {
        this.stacks = new ArrayList<>();
        this.lastStackCount = 0;
        this.stackCapacity = stackCapacity;
    }

    public void push(int data)
    {
        // Add a new stack if there is no stacks or if the last stack count
        // exceeds the capacity.
        if (this.stacks.isEmpty() || lastStackCount >= stackCapacity)
        {
            this.stacks.add(new Stack());
            this.lastStackCount = 0;
        }

        // Push data to the last stack in the array & increment the count
        this.stacks.get(this.stacks.size() - 1).push(data);
        this.lastStackCount++;
    }

    public int pop()
    {
        // Throw exception if there are no stacks
        if (this.stacks.isEmpty())
            throw new EmptyStackException();
            
        // Pop the last stack
        int data = this.stacks.get(this.stacks.size() - 1).pop();

        // If the last stack is empty after popping
        if (this.stacks.get(this.stacks.size() - 1).isEmpty())
        {
            // Remove the stack
            this.stacks.remove(this.stacks.size() - 1);

            // Reset the lastStackCount
            this.lastStackCount = this.stackCapacity;
        }

        return data;
    }

    public int peek()
    {
        if (this.stacks.isEmpty())
            throw new EmptyStackException();

        return this.stacks.get(this.stacks.size() - 1).peek();
    }
}

// Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
class SolutionIII4
{
    public static void run()
    {
        QueueViaStack queueViaStack = new QueueViaStack();
        for (int i = 0; i < 5; i++)
        {
            queueViaStack.add(i + 1);
            System.out.println(queueViaStack.peek());
        }

        for (int i = 0; i < 5; i++)
            System.out.println(queueViaStack.remove());
    }
}

class QueueViaStack
{
    Stack frontStack;
    Stack backStack;

    public QueueViaStack()
    {
        this.frontStack = new Stack();
        this.backStack = new Stack();
    }

    public void add(int data)
    {   
        if (!this.frontStack.isEmpty())
            while (!this.frontStack.isEmpty())
                this.backStack.push(this.frontStack.pop());

        this.backStack.push(data);
    }

    public int remove()
    {
        if (this.isEmpty())
            throw new NoSuchElementException();

        if (!this.backStack.isEmpty())
            while (!this.backStack.isEmpty())
                this.frontStack.push(this.backStack.pop());

        return this.frontStack.pop();
    }

    public int peek()
    {
        if (this.isEmpty())
            throw new NoSuchElementException();

        if (!this.backStack.isEmpty())
            while (!this.backStack.isEmpty())
                this.frontStack.push(this.backStack.pop());

        return frontStack.peek();
    }

    public boolean isEmpty()
    {
        return frontStack.isEmpty() && backStack.isEmpty();
    }
}

// Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
// an additional temporary stack, but you may not copy the elements into any other data structure
// (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
class SolutionIII5
{
    public static void run()
    {
        Stack stack = new Stack();

        for (int i = 0; i < 10; i++)
        {
            int randInt = (int)(Math.random() * 100);
            stack.push(randInt);
        }

        System.out.println("Initial stack contents: ");
        System.out.println(stack);
        sort(stack);
        System.out.println("Sorted stack contents: ");
        System.out.println(stack);
    }

    private static Stack sort(Stack stack)
    {
        Stack tempStack = new Stack();

        int sorted = 0;

        while (!stack.isEmpty())
        {
            // Find the smallest element of the stack
            int smallest = stack.pop();
            while (!stack.isEmpty())
            {
                if (stack.peek() < smallest)
                {
                    tempStack.push(smallest);
                    smallest = stack.pop();
                }
                else
                {
                    tempStack.push(stack.pop());
                }
            }

            // Return the elements to the original stack
            while (!tempStack.isEmpty())
                stack.push(tempStack.pop());

            // Move the sorted stuff to the temporary stack
            for (int i = 0; i < sorted; i++)
                tempStack.push(stack.pop());
            
            // Add the smallest element to the temporary stack
            tempStack.push(smallest);
            sorted++;
        }

        // Return the elements to the original stack
        while (!tempStack.isEmpty())
            stack.push(tempStack.pop());

        return stack;
    }
}

// Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
// out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
// or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
// that type). They cannot select which specific animal they would like. Create the data structures to
// maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
// and dequeueCat. You may use the built-in Linked List data structure.
class SolutionIII6
{
    public static void run()
    {
        AnimalQueue animalQueue = new AnimalQueue();
        
        // Enqueue 3 cats and 4 dogs
        animalQueue.enqueue("cat", "cat1");
        animalQueue.enqueue("cat", "cat2");
        animalQueue.enqueue("dog", "dog1");
        animalQueue.enqueue("cat", "cat3");
        animalQueue.enqueue("dog", "dog2");
        animalQueue.enqueue("dog", "dog3");
        animalQueue.enqueue("dog", "dog4");

        System.out.println("Dequeued: " + animalQueue.dequeueAny());
        System.out.println("Dequeued: " + animalQueue.dequeueAny());
        System.out.println("Dequeued: " + animalQueue.dequeueAny());
        System.out.println("Dequeued: " + animalQueue.dequeueAny());
        System.out.println("Dequeued: " + animalQueue.dequeueAny());
        System.out.println("Dequeued: " + animalQueue.dequeueAny());
        System.out.println("Dequeued: " + animalQueue.dequeueAny());
    }
}

class AnimalQueue
{
    abstract class Animal
    {
        public String name;
        public int order;
        public Animal(String name, int order)
        { 
            this.name = name;
            this.order = order;
        }
    }

    class Cat extends Animal
    {
        Cat next;
        public Cat(String name, int order) 
        {
            super(name, order);
        }
    }

    class Dog extends Animal
    {
        Dog next;
        public Dog(String name, int order) 
        {
            super(name, order);
        }
    }

    Cat firstCat, lastCat;
    Dog firstDog, lastDog;
    int order;
    int nCat, nDog;

    public void enqueue(String type, String name)
    {
        if (type == "cat")
        {
            Cat newCat = new Cat(name, this.order);

            if (this.lastCat != null)
                this.lastCat.next = newCat;
            this.lastCat = newCat;
            if (this.firstCat == null)
                this.firstCat = this.lastCat;
            
            this.order++;
        }
        else if (type == "dog")
        {
            Dog newDog = new Dog(name, this.order);

            if (this.lastDog != null)
                this.lastDog.next = newDog;
            this.lastDog = newDog;
            if (this.firstDog == null)
                this.firstDog = this.lastDog;

            this.order++;
        }
        else
            return;
    }

    public String dequeueAny()
    {
        if (this.firstCat != null && this.peekCatOrder() < this.peekDogOrder())
            return dequeueCat();
        else
            return dequeueDog();
    }

    public String dequeueCat()
    {
        if (this.firstCat == null)
            throw new NoSuchElementException();

        String data = this.firstCat.name;
        this.firstCat = this.firstCat.next;
        if (this.firstCat == null)
            this.lastCat = null;
        return data;
    }

    public String dequeueDog()
    {
        if (this.firstDog == null)
            throw new NoSuchElementException();

        String data = this.firstDog.name;
        this.firstDog = this.firstDog.next;
        if (this.firstDog == null)
            this.lastDog = null;
        return data;
    }

    private int peekCatOrder()
    {
        if (firstCat == null)
            throw new NoSuchElementException();
        return firstCat.order;
    }

    private int peekDogOrder()
    {
        if (firstDog == null)
            throw new NoSuchElementException();
        return firstDog.order;
    }
}