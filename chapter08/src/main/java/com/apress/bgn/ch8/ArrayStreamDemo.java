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
package com.apress.bgn.ch8;

import java.util.Arrays;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ArrayStreamDemo {

    public static void main(String... args) {
       int[] arr = { 50, 10, 250, 100, 23, 45, 33, 55, 67, 83, 90, 92, 94, 74, 200, 40052, 3467, 125};

        Arrays.stream(arr, 3,6).forEach(
                i -> System.out.println(Thread.currentThread().getName() + ": " + i)
        );

        Arrays.stream(arr).parallel().forEach(
                i -> System.out.println(Thread.currentThread().getName() + ": " + i)
        );

        String [] strArr = {"aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh", "iii", "jjj"};

        Arrays.stream(strArr).forEach(
                i -> System.out.println(Thread.currentThread().getName() + ": " + i)
        );
    }
}
