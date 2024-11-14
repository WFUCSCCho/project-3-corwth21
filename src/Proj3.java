import java.awt.datatransfer.SystemFlavorMap;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
/**
 * @file: Proj3.java
 * @description: This program creates a variety of sorting algorithms and tests their running speed given different input lists.
 * ALso prints the resulting run times.
 * @author: Tucker Corwen
 * @date: November 14, 2024
 */


public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort the left half
            mergeSort(a, left, mid);

            // Sort the right half
            mergeSort(a, mid + 1, right);

            // Merge the sorted halves
            merge(a, left, mid, right);
        }

    }


    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
        ArrayList<T> temp = new ArrayList<>(a);

        int i = left;      // Initial index of the left subarray
        int j = mid + 1;   // Initial index of the right subarray
        int k = left;      // Initial index of the merged subarray

        // Merge the two halves into temp
        while (i <= mid && j <= right) {
            if (temp.get(i).compareTo(temp.get(j)) <= 0) {
                a.set(k++, temp.get(i++));
            } else {
                a.set(k++, temp.get(j++));
            }
        }

        // Copy any remaining elements of the left half
        while (i <= mid) {
            a.set(k++, temp.get(i++));
        }

        // Copy any remaining elements of the right half
        while (j <= right) {
            a.set(k++, temp.get(j++));
        }

    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        if (left < right) {
            int pivotIndex = partition(a, left, right);
            quickSort(a, left, pivotIndex - 1);  // Sort left partition
            quickSort(a, pivotIndex + 1, right); // Sort right partition
        }
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me
        T pivot = a.get(right);  // Choose the rightmost element as the pivot
        int i = left - 1;  // Pointer for the greater element

        for (int j = left; j < right; j++) {
            if (a.get(j).compareTo(pivot) <= 0) {  // If current element is less than or equal to the pivot
                i++;  // Move the pointer forward
                swap(a, i, j);  // Swap the elements
            }
        }
        swap(a, i + 1, right);  // Place the pivot in the correct position
        return i + 1;  // Return the pivot index
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
        //Build the initial heap
        for (int i = (right - 1) / 2; i >= left; i--) {
            heapify(a, i, right);
        }

        //Extract elements from the heap one by one
        for (int i = right; i > left; i--) {
            // Swap the root (maximum value) of the heap with the last element
            T temp = a.get(left);
            a.set(left, a.get(i));
            a.set(i, temp);

            //Call heapify on the reduced heap
            heapify(a, left, i - 1);
        }

    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
        int largest = left;       // Initialize largest as root
        int leftChild = 2 * left + 1;
        int rightChild = 2 * left + 2;

        // If left child is larger than root
        if (leftChild <= right && a.get(leftChild).compareTo(a.get(largest)) > 0) {
            largest = leftChild;
        }

        // If right child is larger than largest so far
        if (rightChild <= right && a.get(rightChild).compareTo(a.get(largest)) > 0) {
            largest = rightChild;
        }

        // If largest is not root
        if (largest != left) {
            T swap = a.get(left);
            a.set(left, a.get(largest));
            a.set(largest, swap);

            // Recursively heapify the affected subtree
            heapify(a, largest, right);
        }
    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me
        int swapCount = 0;
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (a.get(j).compareTo(a.get(j + 1)) > 0) {
                    // Use swap method
                    swap(a, j, j + 1);
                    swapCount++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return swapCount;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
        int swapCount = 0;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            // Odd indexed pass
            for (int i = 1; i < size - 1; i += 2) {
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    swapCount++;
                    sorted = false;
                }
            }

            // Even indexed pass
            for (int i = 0; i < size - 1; i += 2) {
                if (a.get(i).compareTo(a.get(i + 1)) > 0) {
                    swap(a, i, i + 1);
                    swapCount++;
                    sorted = false;
                }
            }
        }
        return swapCount;
    }

    // Method to check if the string is numeric
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public static void main(String [] args)  throws IOException {
        //...
        // Finish Me
        //...

        //CREATE ARRAYLIST

        //Take file input
        String inputFileName = args[0];
        String algoType = args[1];
        int numLines = Integer.parseInt(args[2]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //Create a Scanner class that reads the data csv
        Scanner csvScanner = new Scanner(new File(inputFileName));
        //Create ArrayList
        ArrayList<Candidate> candidates = new ArrayList<>();

        boolean isFirstLine = true;

        for (int i = 0; i <= numLines; i++) {

            //Read CSV line
            String line = csvScanner.nextLine().trim();

            if(!isFirstLine) {
                //Create empty string array
                String[] info = null;

                //If line is empty move to next line
                if (line.isEmpty()) continue;

                //Split line into sections that are bounded by commas
                info = line.split(",", -1);


                //Check csv data points, if missing create N/A or 0 entries
                float year = Float.parseFloat(info[0].isEmpty() ? "0" : info[0]);
                String name = info[1].isEmpty() ? "N/A" : info[1];
                String college = info[2].isEmpty() ? "N/A" : info[2];
                String position = info[3].isEmpty() ? "N/A" : info[3];

                float heightInches = 0;
                if (isNumeric(info[4])) {
                    Float.parseFloat(info[4].isEmpty() ? "0" : info[4]);
                }

                float weightLbs = Float.parseFloat(info[5].isEmpty() ? "0" : info[5]);
                float handSize = Float.parseFloat(info[6].isEmpty() ? "0" : info[6]);
                float armLength = Float.parseFloat(info[7].isEmpty() ? "0" : info[7]);
                float wonderlic = Float.parseFloat(info[8].isEmpty() ? "0" : info[8]);
                float fortyYard = Float.parseFloat(info[9].isEmpty() ? "0" : info[9]);
                float benchPress = Float.parseFloat(info[10].isEmpty() ? "0" : info[10]);
                float verticalLeap = Float.parseFloat(info[11].isEmpty() ? "0" : info[11]);
                float broadJump = Float.parseFloat(info[12].isEmpty() ? "0" : info[12]);
                float shuttle = Float.parseFloat(info[13].isEmpty() ? "0" : info[13]);
                float threeCone = Float.parseFloat(info[14].isEmpty() ? "0" : info[14]);
                float sixtyYardShuttle = Float.parseFloat(info[15].isEmpty() ? "0" : info[15]);

                //Create the object and set the values
                Candidate newCandidate = new Candidate(info);
                newCandidate.setYear(year);
                newCandidate.setName(name);
                newCandidate.setCollege(college);
                newCandidate.setPos(position);
                newCandidate.setHeight_in(heightInches);
                newCandidate.setWeight_lbs(weightLbs);
                newCandidate.setHand_size_in(handSize);
                newCandidate.setArm_length_in(armLength);
                newCandidate.setWonderlic(wonderlic);
                newCandidate.setForty_yard(fortyYard);
                newCandidate.setBench_press(benchPress);
                newCandidate.setVert_leap_in(verticalLeap);
                newCandidate.setBroad_jump_in(broadJump);
                newCandidate.setShuttle(shuttle);
                newCandidate.setThree_cone(threeCone);
                newCandidate.setSixty_yd_shuttle(sixtyYardShuttle);

                //Insert object to ArrayList
                candidates.add(newCandidate);
            }
            isFirstLine = false;
        }

        //CREATE DIFFERENT ARRAY TYPES
        //Create sorted Array
        Collections.sort(candidates);
        ArrayList<Candidate> candidates_sorted = new ArrayList<>(candidates);

        //Create shuffled Array
        Collections.shuffle(candidates);
        ArrayList<Candidate> candidates_shuffled = new ArrayList<>(candidates);

        //Create reversed Array
        Collections.sort(candidates, Collections.reverseOrder());
        ArrayList<Candidate> candidates_reversed = new ArrayList<>(candidates);

        //PRINT EVERYTHING
        FileWriter writer = new FileWriter("analysis.txt", true);
        PrintWriter printWriter = new PrintWriter(writer);



        switch(algoType.toLowerCase()){
            case "bubble" -> {
                System.out.println(algoType);
                //Find duration for shuffled data set
                long startTime = System.nanoTime();
                int bubble_comparisons_shuffled = bubbleSort(candidates_shuffled, numLines);
                long endTime = System.nanoTime();
                long shuffled_bubbleSort_duration = endTime - startTime;

                //Find duration for sorted data set
                startTime = System.nanoTime();
                int bubble_comparisons_sorted = bubbleSort(candidates_sorted, numLines);
                endTime = System.nanoTime();
                long sorted_bubbleSort_duration = endTime - startTime;

                //Find duration for reversed data set
                startTime = System.nanoTime();
                int bubble_comparisons_reversed = bubbleSort(candidates_reversed, numLines);
                endTime = System.nanoTime();
                long reversed_bubbleSort_duration = endTime - startTime;

                printWriter.println("-------------------------------------------------------");
                printWriter.println("BUBBLE SORT results for " + numLines + " lines:");
                printWriter.println("Shuffled dataset runtime (ns): " + shuffled_bubbleSort_duration);
                printWriter.println("Shuffled dataset # of comparisons: " + bubble_comparisons_shuffled);
                printWriter.println();
                printWriter.println("Sorted dataset runtime (ns): " + sorted_bubbleSort_duration);
                printWriter.println("Sorted dataset # of comparisons: " + bubble_comparisons_sorted);
                printWriter.println();
                printWriter.println("Reversed dataset runtime (ns): " + reversed_bubbleSort_duration);
                printWriter.println("Reversed dataset # of comparisons: " + bubble_comparisons_reversed);

                printWriter.println("-------------------------------------------------------");

                // Close the writer
                printWriter.close();

                //Print the shuffled array
                printArrayWeights(candidates_shuffled);

            } case "oddeven" -> {
                System.out.println(algoType);
                //Find duration for shuffled data set
                long startTime = System.nanoTime();
                int oddeven_comparisons_shuffled = transpositionSort(candidates_shuffled, numLines);
                long endTime = System.nanoTime();
                long shuffled_oddeven_duration = endTime - startTime;

                //Find duration for sorted data set
                startTime = System.nanoTime();
                int oddeven_comparisons_sorted = transpositionSort(candidates_sorted, numLines);
                endTime = System.nanoTime();
                long sorted_oddeven_duration = endTime - startTime;

                //Find duration for reversed data set
                startTime = System.nanoTime();
                int oddeven_comparisons_reversed = transpositionSort(candidates_reversed, numLines);
                endTime = System.nanoTime();
                long reversed_oddeven_duration = endTime - startTime;

                printWriter.println("-------------------------------------------------------");
                printWriter.println("ODDEVEN SORT results for " + numLines + " lines:");
                printWriter.println("Shuffled dataset runtime (ns): " + shuffled_oddeven_duration);
                printWriter.println("Shuffled dataset # of comparisons: " + oddeven_comparisons_shuffled);
                printWriter.println();
                printWriter.println("Sorted dataset runtime (ns): " + sorted_oddeven_duration);
                printWriter.println("Sorted dataset # of comparisons: " + oddeven_comparisons_sorted);
                printWriter.println();
                printWriter.println("Reversed dataset runtime (ns): " + reversed_oddeven_duration);
                printWriter.println("Reversed dataset # of comparisons: " + oddeven_comparisons_reversed);
                printWriter.println("-------------------------------------------------------");

                // Close the writer
                printWriter.close();

                //Print the shuffled array
                printArrayWeights(candidates_shuffled);

            } case "heap" -> {
                System.out.println(algoType);
                //Find duration for shuffled data set
                long startTime = System.nanoTime();
                heapSort(candidates_shuffled, 0, candidates_shuffled.size() - 1);
                long endTime = System.nanoTime();
                long shuffled_heap_duration = endTime - startTime;

                //Find duration for sorted data set
                startTime = System.nanoTime();
                heapSort(candidates_sorted, 0, candidates_sorted.size() - 1);
                endTime = System.nanoTime();
                long sorted_heap_duration = endTime - startTime;

                //Find duration for reversed data set
                startTime = System.nanoTime();
                heapSort(candidates_reversed, 0, candidates_reversed.size() - 1);
                endTime = System.nanoTime();
                long reversed_heap_duration = endTime - startTime;

                printWriter.println("-------------------------------------------------------");
                printWriter.println("HEAP SORT results for " + numLines + " lines:");
                printWriter.println("Shuffled dataset runtime (ns): " + shuffled_heap_duration);
                printWriter.println();
                printWriter.println("Sorted dataset runtime (ns): " + sorted_heap_duration);
                printWriter.println();
                printWriter.println("Reversed dataset runtime (ns): " + reversed_heap_duration);
                printWriter.println("-------------------------------------------------------");

                // Close the writer
                printWriter.close();

                //Print the shuffled array
                printArrayWeights(candidates_shuffled);

            } case "quick" -> {
                System.out.println(algoType);
                //Find duration for shuffled data set
                long startTime = System.nanoTime();
                quickSort(candidates_shuffled, 0, candidates_shuffled.size() - 1);
                long endTime = System.nanoTime();
                long shuffled_quick_duration = endTime - startTime;

                //Find duration for sorted data set
                startTime = System.nanoTime();
                quickSort(candidates_sorted, 0, candidates_sorted.size() - 1);
                endTime = System.nanoTime();
                long sorted_quick_duration = endTime - startTime;

                //Find duration for reversed data set
                startTime = System.nanoTime();
                quickSort(candidates_reversed, 0, candidates_reversed.size() - 1);
                endTime = System.nanoTime();
                long reversed_quick_duration = endTime - startTime;

                printWriter.println("-------------------------------------------------------");
                printWriter.println("QUICK SORT results for " + numLines + " lines:");
                printWriter.println("Shuffled dataset runtime (ns): " + shuffled_quick_duration);
                printWriter.println();
                printWriter.println("Sorted dataset runtime (ns): " + sorted_quick_duration);
                printWriter.println();
                printWriter.println("Reversed dataset runtime (ns): " + reversed_quick_duration);
                printWriter.println("-------------------------------------------------------");

                // Close the writer
                printWriter.close();

                //Print the shuffled array
                printArrayWeights(candidates_shuffled);

            } case "merge" -> {

                //Find duration for shuffled data set
                long startTime = System.nanoTime();
                mergeSort(candidates_shuffled, 0, candidates_shuffled.size() - 1);
                long endTime = System.nanoTime();
                long shuffled_merge_duration = endTime - startTime;

                //Find duration for sorted data set
                startTime = System.nanoTime();
                mergeSort(candidates_sorted, 0, candidates_sorted.size() - 1);
                endTime = System.nanoTime();
                long sorted_merge_duration = endTime - startTime;

                //Find duration for reversed data set
                startTime = System.nanoTime();
                mergeSort(candidates_reversed, 0, candidates_reversed.size() - 1);
                endTime = System.nanoTime();
                long reversed_merge_duration = endTime - startTime;

                //Print to analysis.txt
                printWriter.println("-------------------------------------------------------");
                printWriter.println("MERGE SORT results for " + numLines + " lines:");
                printWriter.println("Shuffled dataset runtime (ns): " + shuffled_merge_duration);
                printWriter.println();
                printWriter.println("Sorted dataset runtime (ns): " + sorted_merge_duration);
                printWriter.println();
                printWriter.println("Reversed dataset runtime (ns): " + reversed_merge_duration);
                printWriter.println("-------------------------------------------------------");

                //Print to screen
                System.out.println("-------------------------------------------------------");
                System.out.println("MERGE SORT results for " + numLines + " lines:");
                System.out.println("Shuffled dataset runtime (ns): " + shuffled_merge_duration);
                System.out.println();
                System.out.println("Sorted dataset runtime (ns): " + sorted_merge_duration);
                System.out.println();
                System.out.println("Reversed dataset runtime (ns): " + reversed_merge_duration);
                System.out.println("-------------------------------------------------------");

                // Close the writer
                printWriter.close();

                //Print the shuffled array
                printArrayWeights(candidates_shuffled);

            }

            default-> System.out.println("Invalid algorithm type: " + algoType);
        }


    }

    //Print the array
    public static void printArrayWeights(ArrayList<Candidate> candidates) throws IOException {

        FileWriter writer = new FileWriter("sorted.txt", true);
        PrintWriter printWriter = new PrintWriter(writer);

        clearFile("./sorted.txt");

        printWriter.println("START");
        printWriter.println("-------------------------");
        for(int i = 0; i < candidates.size(); i++) {
            printWriter.println(candidates.get(i).getName() + ": " + candidates.get(i).getWeight_lbs());
        }
        printWriter.println("-------------------------");
        printWriter.println("END");
        printWriter.close();

    }


    public static void clearFile(String fileName) throws IOException {
        try (FileWriter fw = new FileWriter(fileName, false)) {
            fw.write(""); // Overwrite the file with an empty string, effectively clearing it
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if an exception occurs
        }
    }
}
