import java.util.Arrays;

public final class MaxHeapArray<T extends Comparable <? super T>> implements MaxHeapInterface<T>
{
    private T[] heap;       // Array of heap entries
    private int lastIndex;  // Index of last entry
    private int swapCounter = 0;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeapArray()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public MaxHeapArray(int initialCapacity)
    {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
            checkCapacity(initialCapacity);

        // The cast is safe because the new array contains all null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    } // end constructor

    public MaxHeapArray(T[] entries)
    {
        this(entries.length); // Call other constructor
        assert initialized = true;

        // Copy given array to data field
        for (int index = 0; index < entries.length; index++)
        {
            heap[index + 1] = entries[index];
        }
        lastIndex = 100;

        // Create heap
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
        {
            reheap(rootIndex);
        }
    } // end constructor

    public void add(T newEntry)
    {
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
            swapCounter++;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } // end add

    public T removeMax()
    {
        checkInitialization();
        T root = null;

        if (!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        } // end if
        return root;
    } // end removeMax

    public T getMax()
    {
        checkInitialization();
        T root = null;
        if (!isEmpty())
            root = heap[1];
        return root;
    } // end getMax

    public boolean isEmpty()
    {
        return lastIndex < 1;
    } // end isEmpty

    public int getSize()
    {
        return lastIndex;
    } // end getSize

    public void clear()
    {
        checkInitialization();
        while (lastIndex > -1)
        {
            heap[lastIndex] = null;
            lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear

    public int getSwapCounter(){return swapCounter;}
    public String output()
    {
        String result = "";
        for(int i=1; i<11; i++)
        {
            result += heap[i] + ",";
        } // end for
        return result;
    } // end output

    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex; // Assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
                swapCounter++;
            }
            else
                done = true;
        } // end while
        heap[rootIndex] = orphan;
    } // end reheap

    // Doubles capacity of heap if limit is reached
    private void ensureCapacity() {
        if (lastIndex >= heap.length - 1) // If array is full, double its size
        {
            int newLength = 2 * heap.length;
            checkCapacity(newLength);
            heap = Arrays.copyOf(heap, newLength);
        } // end if
    } // end ensureCapacity

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a heap whose capacity exceeds " +
                    "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    // Throws an exception if receiving object is not initialized.
    private void checkInitialization() {
        if (!initialized)
            throw new SecurityException("MaxHeapArray object is corrupt.");
    } // end checkIntegrity
} // end MaxHeapArray
