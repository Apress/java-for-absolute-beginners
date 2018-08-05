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
package com.apress.bgn.ch9.service;

import com.apress.bgn.ch9.Account;
import com.apress.bgn.ch9.service.stub.AccountRepoStub;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class AccountServiceTest {

    private static AccountRepoStub repo;
    private AccountService service;

    @BeforeAll
    public static void prepare() {
        repo = new AccountRepoStub();
    }

    @BeforeEach
    public void setUp() {
        service = new AccountServiceImpl(repo);
    }

    @Test
    public void testNonNumericAmountVersionOne() {
        assertThrows(InvalidDataException.class,
                () -> {
                    service.createAccount("Gigi", "223311", "2I00");
                });
    }

    @Test
    public void testNonNumericAmountVersionTwo() {
        InvalidDataException expected = assertThrows(
                InvalidDataException.class, () -> {
                    service.createAccount("Gigi", "223311", "2I00");
                }
        );
        assertEquals("Could not create account with invalid amount!", expected.getMessage());
    }

    @Test
    public void testEmptyAccountNumber() {
        InvalidDataException expected = assertThrows(
                InvalidDataException.class, () -> {
                    service.createAccount("Gigi", "", "2100");
                }
        );
        assertEquals("Could not create account with invalid account number!", expected.getMessage());
    }

    @Test
    public void testNullAccountNumber() {
        InvalidDataException expected = assertThrows(
                InvalidDataException.class, () -> {
                    service.createAccount("Gigi", null, "2100");
                }
        );
        assertEquals("Could not create account with invalid account number!", expected.getMessage());
    }

    @Test
    public void testInvalidAccountNumber() {
        InvalidDataException expected = assertThrows(
                InvalidDataException.class, () -> {
                    service.createAccount("Gigi", "11", "2100");
                }
        );
        assertEquals("Could not create account with invalid account number!", expected.getMessage());
    }

    @Test
    public void testNegativeIntAmount() {
        InvalidDataException expected = assertThrows(
                InvalidDataException.class, () -> {
                    service.createAccount("Gigi", "112233", "-2100");
                }
        );
        assertEquals("Could not create account with invalid account number!", expected.getMessage());
    }

    @Test
    public void testCreateAccount() {
        repo.set(1);
         Account expected = service.createAccount("Gigi", "112233", "2100");
         assertEquals("Gigi", expected.getHolder());
         assertEquals("112233", expected.getNumber());
         assertEquals(2100, expected.getSum());
    }

    @Test
    public void testCreateAccountAlreadyExists() {
        AccountCreationException expected = assertThrows(
                AccountCreationException.class, () -> {
                    service.createAccount("Gigi", "112233", "2100");
                }
        );
        assertEquals("Account already exists for holder Gigi", expected.getMessage());
    }

    @AfterEach
    public void tearDown() {
        repo.set(0);
    }

    @AfterAll
    public static void destroy() {
        repo = null;
    }

}
