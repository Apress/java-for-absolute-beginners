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
package com.apress.bgn.ch9.mock;

import com.apress.bgn.ch9.Account;
import com.apress.bgn.ch9.repo.AccountRepo;
import com.apress.bgn.ch9.service.AccountCreationException;
import com.apress.bgn.ch9.service.AccountServiceImpl;
import com.apress.bgn.ch9.service.InvalidDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepo mockRepo;

    @BeforeEach
    public void checkMocks() {
        assertNotNull(service);
        assertNotNull(mockRepo);
    }

    @Test
    public void testCreateAccount() {
        Account expected = new Account("Gigi", 2100, "223311");
        when(mockRepo.findOne("Gigi")).thenReturn(Optional.empty());
        when(mockRepo.save(any(Account.class))).thenReturn(expected);

        Account result = service.createAccount("Gigi", "223311", "2100");
        assertEquals(expected, result);
    }

    @Test
    public void testCreateAccountAlreadyExists() {
        Account expected = new Account("Gigi", 2100, "223311");
        when(mockRepo.findOne("Gigi")).thenReturn(Optional.of(expected));

        assertThrows(AccountCreationException.class,
                () -> {
                    service.createAccount("Gigi", "223311", "2100");
                });
    }

    @Test
    public void testDebitAccount() {
        Account expected = new Account("Gigi", 2100, "223311");
        when(mockRepo.findOne("Gigi")).thenReturn(Optional.of(expected));
        when(mockRepo.save(any(Account.class))).thenReturn(expected);
        int currentAmount = service.debit("Gigi", 100);

        assertEquals(2200, currentAmount);
    }

    @Test
    public void testDebitAccountDoesNotExists() {
        Account expected = new Account("Gigi", 2100, "223311");
        when(mockRepo.findOne("Gigi")).thenReturn(Optional.empty());

        assertThrows(InvalidDataException.class,
                () -> {
                    service.debit("Gigi", 100);
                });
    }

}
