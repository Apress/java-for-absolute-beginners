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
package com.apress.bgn.ch5.refs;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class StringDemo {

    public void sample(String args) {
        char quote = '\'';

        //String text332 = "Special " character ";
        //String text331 = "Special \" character";

        //String text341 = "Special \ character";
        //String text342 = "Special \\ character";

        //String text351 = "Special \a character";
        //String text352 = "Special \\a character";
    }

    public static void main(String... args) {
        String perf = "The singers performing tonight are:"
                + "\n\t Paolo Nutini"
                + "\n\t Seth MacFarlane"
                + "\n\t John Mayer";
        System.out.println(perf);

        String text1 = null;

        String text21 = "two";
        String text22 = "two";
        String text23 = new String ("two");

        String piece1 = "t";
        String piece2 = "wo";
        String text24 = piece1 + piece2;

        char[] twoCh = {'t', 'w', 'o'};
        String text25 = new String(twoCh);


        if (text21 == text21) {
            System.out.println("Equal References");
        } else {
            System.out.println("Different References");
        }

        if (text21.equals(text22)) {
            System.out.println("Equal Objects");
        } else {
            System.out.println("Different Objects");
        }

        System.out.println("----------------------");

        if (text22 == text23) {
            System.out.println("Equal References");
        } else {
            System.out.println("Different References");
        }

        if (text22.equals(text23)) {
            System.out.println("Equal Objects");
        } else {
            System.out.println("Different Objects");
        }
    }
}
