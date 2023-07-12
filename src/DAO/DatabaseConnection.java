package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection;
    private Statement statement;
    private static DatabaseConnection instance;

    private DatabaseConnection(){
        try {
            this.connection = DriverManager.getConnection(
                    PostgresqlConf.URL,
                    PostgresqlConf.USER,
                    PostgresqlConf.PASSWORD
            );
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement() {
        return this.statement;
    }
    public Connection getConnection() {
        return this.connection;
    }
    public static DatabaseConnection getInstance() {
        if(DatabaseConnection.instance == null){
            DatabaseConnection.instance = new DatabaseConnection();
        }
        return DatabaseConnection.instance;
    }

    //to close the connection
    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
