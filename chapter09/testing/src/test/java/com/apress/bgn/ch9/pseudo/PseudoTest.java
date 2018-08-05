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
package com.apress.bgn.ch9.pseudo;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class PseudoTest {

    private static final Logger log = LoggerFactory.getLogger(PseudoTest.class);

    @BeforeAll
    public static void loadCtx() {
        log.info("Loading general test context.");
    }

    @BeforeEach
    public  void setUp(){
        log.info("Prepare  single test context.");
    }

    @Test
    @DisplayName("test one")
    public void testOne() {
        log.info("Executing test one.");
        assertTrue(true);
    }

    @Test
    @DisplayName("test two")
    public void testTwo() {
        log.info("Executing test two.");
        assertFalse(false);
    }

    @AfterEach
    public void tearDown(){
        log.info("Destroy  single test context.");
    }


    @AfterAll
    public static void unloadCtx(){
        log.info("UnLoading general test context.");
    }
}
