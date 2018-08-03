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
package com.apress.bgn.ch9;

import com.apress.bgn.ch9.db.DbConnection;
import com.apress.bgn.ch9.db.DerbyDBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        Account a1 = new Account("Gigi", 100, "12345");
        Account a2 = new Account("Pedala", 200, "2345");
        DbConnection conn = new DerbyDBConnection();
        try {
            conn.connect();

            conn.insert(a1);

            Account acc = conn.findByHolder(a1.getHolder());
            log.info("Found: {}", a1.equals(acc));

            acc = conn.insert(a2);
            log.info("Inserted:  {}", a2);

            log.info("All: ");
            conn.findAll().forEach(a -> log.info(a.toString()));

            a2.setSum(a2.getSum() - 50);
            conn.update(a2);
            log.info("Updated:  {}", a2);

            conn.delete(a2.getHolder());
            log.info("Deleted:  {}", a2);

            conn.findAll().forEach(a -> log.info(a.toString()));
        } catch (Exception e) {
            log.error("Operations failed!", e);
        } finally {
            conn.disconnect();
        }
    }
}
