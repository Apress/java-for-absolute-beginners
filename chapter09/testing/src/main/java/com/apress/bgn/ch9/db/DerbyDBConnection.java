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
package com.apress.bgn.ch9.db;

import com.apress.bgn.ch9.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class DerbyDBConnection implements DbConnection{
    private static final Logger log = LoggerFactory.getLogger(DerbyDBConnection.class);
    private static String dbURL = "jdbc:derby:memory:testDB;create=true";
    private static String tableName = "accounts";

    private static Connection conn = null;
    private static Statement stmt = null;

    @Override public void connect() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);

            String DDL = "CREATE TABLE "+tableName +" (" +
                    "HOLDER VARCHAR(20) NOT NULL, " +
                    "NUMBER VARCHAR(20) NOT NULL , " +
                    "AMOUNT INT DEFAULT 0, " +
                    "PRIMARY KEY(HOLDER)," +
                    "UNIQUE (NUMBER))";
             stmt = conn.createStatement();
            stmt.executeUpdate(DDL);
        } catch (Exception e) {
            log.error("Could not get connection!", e);
        }
    }

    @Override  public Account insert(Account account) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + "(holder, number, amount)"
                    + " values ('" + account.getHolder() + "','" + account.getNumber() + "'," + account.getSum() + ")");
            stmt.close();
        } catch (SQLException e) {
            log.error("Could not insert!", e);
            throw new DBException("Could not insert " + account, e);
        }
        return account;
    }

    @Override public Account findByHolder(String holder) {
        try {
            Account acc = null;
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName + " where holder='" + holder + "'");
            while (results.next()) {
                String holderdb = results.getString(1);
                String number = results.getString(2);
                int amount = results.getInt(3);
                 acc = new Account(holderdb, amount, number);
            }
            results.close();
            stmt.close();
            return acc;
        } catch (SQLException e) {
            log.error("Could not find!", e);
        }
        return null;
    }

    @Override public  Account update(Account account) {
        try {
            stmt = conn.createStatement();
            stmt.execute("update " + tableName
                    + " set amount=" + account.getSum()
                    + " where holder='" + account.getHolder() + "'");
            stmt.close();
        } catch (SQLException e) {
            log.error("Could not update !", e);
            throw new DBException("Could not insert " + account, e);
        }
        return account;
    }

    @Override public void disconnect() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            log.error("Could not close connection!", e);
        }
    }

    @Override
    public List<Account> findAll() {
        try {
            List<Account> accList = new ArrayList<>();
            Account acc = null;
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            while (results.next()) {
                String holderdb = results.getString(1);
                String number = results.getString(2);
                int amount = results.getInt(3);
                acc = new Account(holderdb, amount, number);
                accList.add(acc);
            }
            results.close();
            stmt.close();
            return accList;
        } catch (SQLException e) {
            log.error("Could not find!", e);
        }
        return List.of();
    }

    @Override
    public void delete(String holder) {
        try {
            stmt = conn.createStatement();
            stmt.execute("delete from " + tableName
                    + " where holder='" + holder + "'");
            stmt.close();
        } catch (SQLException e) {
            log.error("Could not delete !", e);
            throw new DBException("Could not delete account of holder " + holder, e);
        }
    }
}
