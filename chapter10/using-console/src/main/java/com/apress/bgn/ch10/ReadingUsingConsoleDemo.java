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
package com.apress.bgn.ch10;

import java.io.Console;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class ReadingUsingConsoleDemo {
    public static void main(String... args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console found.");
            return;
        } else {
            console.writer().print("Hello there! (reply to salute)\n");
            console.flush();
            String hello = console.readLine();
            console.printf("You replied with: '" + hello + "'\n");
            Calendar calendar = new GregorianCalendar();
            console.format("Today is : %1$tm %1$te,%1$tY\n", calendar);
            char[] passwordChar = console.readPassword("Please provide password: ");
            String password =  new String(passwordChar);
            console.printf("Your password starts with '" + password.charAt(0) + "' and ends with '" + password.charAt(password.length()-1) + "'\n");
        }
    }
}
