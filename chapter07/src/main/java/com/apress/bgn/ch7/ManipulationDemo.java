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
package com.apress.bgn.ch7;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class ManipulationDemo {
    public static final int arr[] = {5, 1, 4, 2, 3};

    public static void main(String... args) {
        for (int i = 0; i < arr.length; ++i) {
            if (i == 3) {
                System.out.println("Bye bye!");
                break;
            }
            System.out.println("arr[" + i + "] = " + arr[i]);
        }

        System.out.println("-------------");
        for (int i = 0; i < 2; ++i) {
            HERE:
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 2; ++k) {
                    if (i == j && j == k) {
                        break HERE;
                    }
                    System.out.println("(i, j, k) = (" + i + "," + j + "," + k + ")");
                }
            }
        }

        System.out.println("-------------");
        for (int i = 0; i < arr.length; ++i) {
            if (i % 2  != 0) {
                continue;
            }
            System.out.println("arr[" + i + "] = " + arr[i]);
        }


        System.out.println("-------------");
        for (int i = 0; i < 3; ++i) {
            HERE:
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    if (k == 1) {
                        continue HERE;
                    }
                    System.out.println("(i, j, k) = (" + i + "," + j + "," + k + ")");
                }
            }
        }

    }
}
