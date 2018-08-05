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
package com.apress.bgn.ch9.fake;

import com.apress.bgn.ch9.Account;
import com.apress.bgn.ch9.db.DbConnection;
import com.apress.bgn.ch9.fake.db.FakeDBConnection;
import com.apress.bgn.ch9.repo.AccountRepo;
import com.apress.bgn.ch9.repo.AccountRepoImpl;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unfortunately, our Repo class is too well designed to actually get an exception, thus we cannot test for that.
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class AccountRepoTest {
    private static final Logger log = LoggerFactory.getLogger(AccountRepoTest.class);
    private static DbConnection conn;

    private AccountRepo repo;

    @BeforeAll
    public static void prepare() {
        conn = new FakeDBConnection();
    }

    @BeforeEach
    public void setUp(){
        repo = new AccountRepoImpl(conn);

        // inserting an entry so we can test update/findOne
        repo.save(new Account("Pedala", 200, "2345"));
    }

    @AfterEach
    public void tearDown(){
        // delete the entry
        repo.deleteByHolder("Pedala");
    }

    @Test
    public void testFindOneExisting(){
        Optional<Account> expected = repo.findOne("Pedala");
        assertTrue(expected.isPresent());
    }

    @Test
    public void testFindOneNonExisting(){
        Optional<Account> expected = repo.findOne("Dorel");
        assertFalse(expected.isPresent());
    }

    @Test
    public void testFindAll(){
        assertEquals(1, repo.findAll().size());
    }

    @Test
    public void testInsert(){
        Account expected = new Account("Gigi", 100, "12345");
        Account actual = repo.save(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate(){
        Account existing = conn.findByHolder("Pedala");
        int originalSum = existing.getSum();
        existing.setSum(originalSum - 50);
        Account actual = repo.save(existing);

        assertEquals(existing.getSum(),actual.getSum());
    }

    @Test
    public void testDeleteExisting(){
        assertEquals( 0, repo.deleteByHolder("Pedala"));
    }

    @Test
    public void testDeleteNonExisting(){
        assertEquals( 1, repo.deleteByHolder("NotExisting"));
    }

    @AfterAll
    public static void cleanUp(){
        conn = null;
        log.info("All done!");
    }

}
