package com.bsu.service.Impl;

import com.bsu.service.ConnectionPool;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPoolImpl implements ConnectionPool {
    private static ConnectionPoolImpl pool;
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    private ConnectionPoolImpl() {}

    private ConnectionPoolImpl(String url, String user, String password,
                               List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    public static ConnectionPoolImpl getPool(){
        try {
            if(pool == null) {
                Class.forName("com.mysql.jdbc.Driver");
                pool = create("jdbc:mysql://localhost:3306/photo_portal",
                        "root", "root");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pool;
    }

    private static ConnectionPoolImpl create(
            String url, String user, String password) throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPoolImpl(url, user, password, pool);
    }


    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public String getUrl() { return this.url; }

    @Override
    public String getUser() { return this.user; }

    @Override
    public String getPassword() { return this.password; }

    private static Connection createConnection(
            String url, String user, String password) throws SQLException {
        return (Connection) DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
