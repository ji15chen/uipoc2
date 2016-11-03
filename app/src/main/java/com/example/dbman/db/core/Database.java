package com.example.dbman.db.core;

import com.example.dbman.core.BaseApplication;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcDatabaseConnection;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import com.example.dbman.db.genupdate.schema.*;
import com.example.dbman.db.genupdate.daoimpl.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by jerry on 2016/10/31.
 */
public class Database {

    private void doMain(String[] args) throws Exception {
        ConnectionSource connectionSource = null;
        try {
            setupDatabase(connectionSource);
            System.out.println("\n\nIt seems to have worked\n\n");
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                connectionSource.close();
            }
            //System.exit(-1);

        }
    }

    private void readWriteTest() throws Exception {
//        // construct a query using the QueryBuilder
//        QueryBuilder<Order, Integer> statementBuilder = orderDao.queryBuilder();
//        // should find both of the orders that match the account
//        // ORMLite extracts the id from the account for the query automagically
//        statementBuilder.where().eq(Order.ACCOUNT_ID_FIELD_NAME, account);
//        List<Order> orders = orderDao.query(statementBuilder.prepare());
//        for(BaseDaoImpl daoImplClass:daoImplClasses){
//
//        }
    }
    /**
     * Setup our database and DAOs
     */
    private void setupDatabase(ConnectionSource connectionSource) throws Exception {
//        for (Class schemaClass:schemaClasses){
//            TableUtils.createTableIfNotExists(connectionSource, schemaClass);
//        }
        //storeDetailDao = new StoreDetailDaoImpl(connectionSource);
//        for (StoreDetail storeDetail:storeDetailDao){
//            System.out.println(storeDetail);
//        }
//        DatabaseConnection databaseConnection = connectionSource.getReadOnlyConnection("");
//        JdbcDatabaseConnection jdbcDatabaseConnection = (JdbcDatabaseConnection) databaseConnection;
//
//        Connection connection = jdbcDatabaseConnection.getInternalConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet set = stmt.executeQuery("select * from CheckMntcDetail;");
//        while (set.next()){
//            System.out.println("");
//        }

    }

//    /**
//     * Read and write some example data.
//     */
//    private void readWriteData() throws Exception {
//        // create an instance of Account
//        String name = "Jim Coakley";
//        Account account = new Account(name);
//
//        // persist the account object to the database
//        accountDao.create(account);
//        int id = account.getId();
//        verifyDb(id, account);
//
//        // assign a password
//        account.setPassword("_secret");
//        // update the database after changing the object
//        accountDao.update(account);
//        verifyDb(id, account);
//
//        // query for all items in the database
//        List<Account> accounts = accountDao.queryForAll();
//        assertEquals("Should have found 1 account matching our query", 1, accounts.size());
//        verifyAccount(account, accounts.get(0));
//
//        // loop through items in the database
//        int accountC = 0;
//        for (Account account2 : accountDao) {
//            verifyAccount(account, account2);
//            accountC++;
//        }
//        assertEquals("Should have found 1 account in for loop", 1, accountC);
//
//        // construct a query using the QueryBuilder
//        QueryBuilder<Account, Integer> statementBuilder = accountDao.queryBuilder();
//        // shouldn't find anything: name LIKE 'hello" does not match our account
//        statementBuilder.where().like(Account.NAME_FIELD_NAME, "hello");
//        accounts = accountDao.query(statementBuilder.prepare());
//        assertEquals("Should not have found any accounts matching our query", 0, accounts.size());
//
//        // should find our account: name LIKE 'Jim%' should match our account
//        statementBuilder.where().like(Account.NAME_FIELD_NAME, name.substring(0, 3) + "%");
//        accounts = accountDao.query(statementBuilder.prepare());
//        assertEquals("Should have found 1 account matching our query", 1, accounts.size());
//        verifyAccount(account, accounts.get(0));
//
//        // delete the account since we are done with it
//        accountDao.delete(account);
//        // we shouldn't find it now
//        assertNull("account was deleted, shouldn't find any", accountDao.queryForId(id));
//    }
//
//    /**
//     * Example of reading and writing a large(r) number of objects.
//     */
//    private void readWriteBunch() throws Exception {
//
//        Map<String, Account> accounts = new HashMap<String, Account>();
//        for (int i = 1; i <= 100; i++) {
//            String name = Integer.toString(i);
//            Account account = new Account(name);
//            // persist the account object to the database, it should return 1
//            accountDao.create(account);
//            accounts.put(name, account);
//        }
//
//        // query for all items in the database
//        List<Account> all = accountDao.queryForAll();
//        assertEquals("Should have found same number of accounts in map", accounts.size(), all.size());
//        for (Account account : all) {
//            assertTrue("Should have found account in map", accounts.containsValue(account));
//            verifyAccount(accounts.get(account.getName()), account);
//        }
//
//        // loop through items in the database
//        int accountC = 0;
//        for (Account account : accountDao) {
//            assertTrue("Should have found account in map", accounts.containsValue(account));
//            verifyAccount(accounts.get(account.getName()), account);
//            accountC++;
//        }
//        assertEquals("Should have found the right number of accounts in for loop", accounts.size(), accountC);
//    }
//
//    /**
//     * Example of created a query with a ? argument using the {@link SelectArg} object. You then can set the value of
//     * this object at a later time.
//     */
//    private void useSelectArgFeature() throws Exception {
//
//        String name1 = "foo";
//        String name2 = "bar";
//        String name3 = "baz";
//        assertEquals(1, accountDao.create(new Account(name1)));
//        assertEquals(1, accountDao.create(new Account(name2)));
//        assertEquals(1, accountDao.create(new Account(name3)));
//
//        QueryBuilder<Account, Integer> statementBuilder = accountDao.queryBuilder();
//        SelectArg selectArg = new SelectArg();
//        // build a query with the WHERE clause set to 'name = ?'
//        statementBuilder.where().like(Account.NAME_FIELD_NAME, selectArg);
//        PreparedQuery<Account> preparedQuery = statementBuilder.prepare();
//
//        // now we can set the select arg (?) and run the query
//        selectArg.setValue(name1);
//        List<Account> results = accountDao.query(preparedQuery);
//        assertEquals("Should have found 1 account matching our query", 1, results.size());
//        assertEquals(name1, results.get(0).getName());
//
//        selectArg.setValue(name2);
//        results = accountDao.query(preparedQuery);
//        assertEquals("Should have found 1 account matching our query", 1, results.size());
//        assertEquals(name2, results.get(0).getName());
//
//        selectArg.setValue(name3);
//        results = accountDao.query(preparedQuery);
//        assertEquals("Should have found 1 account matching our query", 1, results.size());
//        assertEquals(name3, results.get(0).getName());
//    }

//    /**
//     * Example of created a query with a ? argument using the {@link SelectArg} object. You then can set the value of
//     * this object at a later time.
//     */
//    private void useTransactions(ConnectionSource connectionSource) throws Exception {
//        String name = "trans1";
//        final Account account = new Account(name);
//        assertEquals(1, accountDao.create(account));
//
//        TransactionManager transactionManager = new TransactionManager(connectionSource);
//        try {
//            // try something in a transaction
//            transactionManager.callInTransaction(new Callable<Void>() {
//                @Override
//                public Void call() throws Exception {
//                    // we do the delete
//                    assertEquals(1, accountDao.delete(account));
//                    assertNull(accountDao.queryForId(account.getId()));
//                    // but then (as an example) we throw an exception which rolls back the delete
//                    throw new Exception("We throw to roll back!!");
//                }
//            });
//            fail("This should have thrown");
//        } catch (SQLException e) {
//            // expected
//        }
//
//        assertNotNull(accountDao.queryForId(account.getId()));
//    }

//    /**
//     * Verify that the account stored in the database was the same as the expected object.
//     */
//    private void verifyDb(int id, Account expected) throws SQLException, Exception {
//        // make sure we can read it back
//        Account account2 = accountDao.queryForId(id);
//        if (account2 == null) {
//            throw new Exception("Should have found id '" + id + "' in the database");
//        }
//        verifyAccount(expected, account2);
//    }

//    /**
//     * Verify that the account is the same as expected.
//     */
//    private static void verifyAccount(Account expected, Account account2) {
//        assertEquals("expected name does not equal account name", expected, account2);
//        assertEquals("expected password does not equal account name", expected.getPassword(), account2.getPassword());
//    }
}
