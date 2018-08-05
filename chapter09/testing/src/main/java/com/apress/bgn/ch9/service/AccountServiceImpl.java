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
import com.apress.bgn.ch9.repo.AccountRepo;

import java.util.Optional;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class AccountServiceImpl implements AccountService {

    AccountRepo repo;

    public AccountServiceImpl(AccountRepo repo) {
        this.repo = repo;
    }

    @Override
    public Account createAccount(String holder, String accountNumber, String amount) {
        int intAmount;
        try {
            intAmount = Integer.parseInt(amount);
        } catch (NumberFormatException nfe) {
            throw new InvalidDataException("Could not create account with invalid amount!");
        }

        if (accountNumber == null || accountNumber.isEmpty() || accountNumber.length() < 5 || intAmount < 0) {
            throw new InvalidDataException("Could not create account with invalid account number!");
        }

        Optional<Account> existing = repo.findOne(holder);
        if (existing.isPresent()) {
            throw new AccountCreationException("Account already exists for holder " + holder);
        }
        Account acc = new Account(holder, intAmount, accountNumber);
        return repo.save(acc);
    }

    @Override
    public int debit(String holder, int amount) {
        Optional<Account> accOpt = repo.findOne(holder);
        if (accOpt.isPresent()) {
            Account acc = accOpt.get();
            acc.setSum(acc.getSum() + amount);
            repo.save(acc);
            return acc.getSum();
        } else {
            throw new InvalidDataException("Account for holder " + holder + " does not exist!");
        }
    }

    @Override
    public int credit(String holder, int amount) {
        Optional<Account> accOpt = repo.findOne(holder);
        if (accOpt.isPresent()) {
            Account acc = accOpt.get();
            acc.setSum(acc.getSum() - amount);
            repo.save(acc);
            return acc.getSum();
        } else {
            throw new InvalidDataException("Account for holder " + holder + " does not exist!");
        }
    }

    @Override
    public boolean closeAccount(String holder) {
        Optional<Account> accOpt = repo.findOne(holder);
        if (accOpt.isPresent()) {
            Account acc = accOpt.get();
            if(acc.getSum() ==0) {
                return repo.deleteByHolder(holder) == 0;
            }
        }
        return false;
    }
}
