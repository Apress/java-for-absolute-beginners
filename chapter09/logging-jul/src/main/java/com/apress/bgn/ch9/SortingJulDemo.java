/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy
of this software and associated documentation files (the "Software"),
to work with the Software within the limits of freeware distribution and fair use.
This includes the rights to use, copy, and modify the Software for personal use.
Users are also allowed and encouraged to submit corrections and modifications
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for
commercial use in any way, or for a user's educational materials such as books
or blog articles without prior permission from the copyright holder.

The above copyright notice and this permission notice need to be included
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch9;

import com.apress.bgn.ch9.algs.IntSorter;
import com.apress.bgn.ch9.algs.MergeSort;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


/**
 * Class <code>SortingJulDemo</code> is the entry point of this application.<p>
 *
 * @author Iuliana Cosmina
 * since 1.0
 */
public class SortingJulDemo {

    private static final Logger log = Logger.getLogger(SortingJulDemo.class.getName());

    static {
        try {
            LogManager logManager = LogManager.getLogManager();
            logManager.readConfiguration(new FileInputStream("./chapter09/logging-jul/src/main/resources/logging.properties"));
        } catch (IOException exception) {
            log.log(Level.SEVERE, "Error in loading configuration", exception);
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
        if (args.length == 0) {
            log.severe("No data to sort!");
            return;
        }
        int[] arr = getInts(args);

        final StringBuilder sb = new StringBuilder("Sorting  an array with merge sort: ");
        Arrays.stream(arr).forEach(i -> sb.append(i).append(" "));
        log.info(sb.toString());


        IntSorter mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length - 1);

        final StringBuilder sb2 = new StringBuilder("Sorted: ");
        Arrays.stream(arr).forEach(i -> sb2.append(i).append( " "));
        log.info(sb2.toString());
    }

    /**
     * Transforms a String[] to an int[] array
     * @param args
     * @return an array of integers
     */
    private static int[] getInts(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (String arg : args) {
            try {
                int toInt = Integer.parseInt(arg);
                list.add(toInt);
            } catch (NumberFormatException nfe) {
                log.warning("Element " + arg + " is not an integer and cannot be added to the array!");
            }
        }
        int[] arr = new int[list.size()];
        int j = 0;
        for (Integer elem : list) {
            arr[j++] = elem;
        }
        return arr;
    }
}
