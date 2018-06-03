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
package com.apress.bgn.ch5;

import java.util.Arrays;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ArraysDemo {

    int array[];

    public static void main(String... args) {
        ArraysDemo ad = new ArraysDemo();
        if (ad.array == null) {
            System.out.println("array unusable");
        }

        // after proper initialization
        ad.array = new int[2];
        for (int i = 0; i < ad.array.length; ++i) {
            System.out.println("array["+ i +"]= " + ad.array[i]);
        }
        // setting elements explicitly

        ad.array[0] = 5;
        for (int i = 0; i < ad.array.length; ++i) {
            System.out.println("array["+ i +"]= " + ad.array[i]);
        }

        // define array with direct initialization
        int another[] = {1,4,3,2};
        for (int i = 0; i < another.length; ++i) {
            System.out.println("array["+ i +"]= " + another[i]);
        }

        //sort array
        Arrays.sort(another);
        for (int i = 0; i < another.length; ++i) {
            System.out.println("array["+ i +"]= " + another[i]);
        }

        //Arrays.stream(another).forEach(element -> System.out.println(element));
        Arrays.stream(another).forEach(System.out::println);

        // multidimensional array
        int[][] intMatrix = {{1,0},{0,1}};
        for (int i = 0; i < intMatrix.length; ++i) {
            for (int j = 0; j < intMatrix[i].length ; ++j) {
                System.out.print( intMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
