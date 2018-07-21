package com.apress.bgn.ch9;

import com.apress.bgn.ch9.algs.IntSorter;
import com.apress.bgn.ch9.algs.MergeSort;

import java.util.Arrays;

/**
 * Class <code>SortingDemo</code> is the entry point of this application.<p>
 *
 * @author Iuliana Cosmina
 * since 1.0
 */
public class SortingDemo {

    /**
     * Executes the whole logic of this program.<p>
     * Creates an <code>int[]</code> instance.<p>
     * Creates an {@link MergeSort} instance.<p>
     * Uses the {@link MergeSort#sort(int[], int, int)} to sort the array.
     *
     * @param args program arguments
     */
    public static void main(String... args) {
        int arr[] = {5,1,4,2,3};

        IntSorter mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length-1);

        System.out.print("Sorted: ");
        Arrays.stream(arr).forEach(i -> System.out.print(i+ " "));
    }
}
