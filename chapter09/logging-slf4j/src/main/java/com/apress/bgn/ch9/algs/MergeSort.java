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
package com.apress.bgn.ch9.algs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>MergeSort</code> class contains a single method that is a concrete implementation of {@link IntSorter#sort(int[], int, int)}.<p>
 * Instances of this class can be used to sort an <code>int[]</code> array using the merge-sort algorithm.
 *
 * @author Iuliana Cosmina
 * since 1.0
 * @see IntSorter
 */
public class MergeSort implements IntSorter {
    private static final Logger log = LoggerFactory.getLogger(MergeSort.class.getName());

    /**
     * Sorts <code>arr</code> using the MergeSort algorithm.<p>
     *
     * <a href="https://youtu.be/XaqR3G_NVoo" target="_blank"> How it works</a>
     */
    public void sort(int[] arr, int low, int high) {
        StringBuilder sb = new StringBuilder("Call sort of ")
                .append(": [")
                    .append(low).append(" ").append(high)
                .append("] ");
        for (int i = low; i <= high; ++i) {
            sb.append(arr[i]).append(" ");
        }
        log.debug(sb.toString());

        if (low < high) {

            int middle = (low + high) / 2;

            //sort lower half of the interval
            sort(arr, low, middle);
            //sort upper half of the interval
            sort(arr, middle + 1, high);

            // merge the two intervals
            merge(arr, low, middle, high);
        }
    }

    private void merge(int arr[], int low, int middle, int high) {
        int leftLength = middle - low + 1;
        int rightLength = high - middle;


        int left[] = new int[leftLength];
        int right[] = new int[rightLength];

        for (int i = 0; i < leftLength; ++i) {
            left[i] = arr[low + i];
        }
        for (int i = 0; i < rightLength; ++i) {
            right[i] = arr[middle + 1 + i];
        }

        int i = 0, j = 0;

        int k = low;
        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < rightLength) {
            arr[k] = right[j];
            j++;
            k++;
        }
        StringBuilder sb = new StringBuilder("Called merge of: [")
                    .append(low).append(" ").append(high).append(" ").append(middle)
                .append("],) ");
        for (int z = low; z <= high; ++z) {
            sb.append(arr[z]).append(" ");
        }
        log.debug(sb.toString());
    }
}
