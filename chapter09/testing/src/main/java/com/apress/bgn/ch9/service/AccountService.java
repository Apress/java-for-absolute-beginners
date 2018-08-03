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

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface AccountService {
    Account createAccount(String holder, String accountNumber, String amount);

    /**
     * returns sum in the account after adding the amount
     * @param holder
     * @param amount
     * @return amount in the account
     */
    int debit(String holder, int amount);

    /**
     * returns sum in the account after subtracting the amount
     * @param holder
     * @param amount
     * @return amount in the account
     */
    int credit(String holder, int amount);

    /**
     * deletes the account for this holder
     * @param holder
     * @returns {@code true} is an account was found and could be deleted (amount is 0)
     * {@code false} otherwise
     */
    boolean closeAccount(String holder);

}
