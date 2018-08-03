package com.apress.bgn.ch9.db;

import com.apress.bgn.ch9.Account;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public interface DbConnection {
    void connect();

    Account insert(Account account);

    Account findByHolder(String holder);

    List<Account> findAll();

    Account update(Account account);

    void delete(String holder);

    void disconnect();

}
