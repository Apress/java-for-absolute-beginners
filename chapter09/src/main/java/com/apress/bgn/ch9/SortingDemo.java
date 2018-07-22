package com.apress.bgn.ch9;

import com.apress.bgn.ch9.algs.IntSorter;
import com.apress.bgn.ch9.algs.MergeSort;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 * Class <code>SortingDemo</code> is the entry point of this application.<p>
 *
 * @author Iuliana Cosmina
 * since 1.0
 */
public class SortingDemo {

    private static final Logger log = Logger.getLogger(SortingDemo.class.getName());

    static{
        try {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(new FileInputStream("./chapter09/src/main/resources/logging.properties"));
        } catch (IOException exception) {
            log.log(Level.SEVERE, "Error in loading configuration",exception);
        }
    }

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

        log.info("Sorting  an array with merge sort");
        IntSorter mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length-1);

        StringBuilder sb = new StringBuilder("Sorted: ");
        Arrays.stream(arr).forEach(i -> sb.append(i+ " "));
        log.info(sb.toString());
    }
}
