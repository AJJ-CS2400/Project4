import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    private static int[] data = new int[100]; // data from input file

    public static void main(String[] args) throws IOException
    {
        readData();
        sequentialInsertions();
        optimalMethod();
    } // end main

    public static <T> void sequentialInsertions() throws IOException
    {
        FileWriter appendFile = new FileWriter("output.txt", true);
        PrintWriter outputFile = new PrintWriter(appendFile);

        MaxHeapInterface<Integer> sequentialHeap = new MaxHeapArray<Integer>(100);
        for(int i=0; i<100; i++)
            sequentialHeap.add(data[i]);

        // Output
        outputFile.print("Heap built using sequential insertions: ");
        // output first 10 integers of array
        outputFile.print(sequentialHeap.output());
        outputFile.println("...");

        outputFile.print("Number of swaps in the heap creation: ");
        // output # of swaps
        outputFile.println(sequentialHeap.getSwapCounter());

        outputFile.print("Heap after 10 removals: ");
        // Perform 10 removals
        for(int i=0; i<10; i++)
            sequentialHeap.removeMax();
        // output first 10 integers of array
        outputFile.print(sequentialHeap.output());
        outputFile.println("...");
        outputFile.close();
    } // end sequentialInsertions

    public static void optimalMethod() throws IOException
    {
        FileWriter appendFile = new FileWriter("output.txt", true);
        PrintWriter outputFile = new PrintWriter(appendFile);

        // Output
        outputFile.println("Heap built using optimal method: ");
        // stub. output first 10 integers of array
        outputFile.print("...");

        outputFile.println("Number of swaps in the heap creation: ");
        // stub. output # of swaps

        outputFile.println("Heap after 10 removals: ");
        // stub. Perform 10 removals
        // stub. output first 10 integers of array
        outputFile.print("...");
        outputFile.close();
    } // end optimalMethod

    public static void readData() throws IOException
    {
        File fileLink = new File("data.txt");
        Scanner inputFile = new Scanner(fileLink);
        int index = 0;

        while (inputFile.hasNextInt())
        {
            data[index] = inputFile.nextInt();
            index++;
        }
        inputFile.close();
    } // end readData
} // end Main
