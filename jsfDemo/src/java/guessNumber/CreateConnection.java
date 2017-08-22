/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jared
 */
public class CreateConnection {
        protected static Connection con = null;

        private static final String URL = "jdbc:derby://localhost:1527/";
        private static final String DB_NAME = "ics321";
        private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
        private static final String USER_NAME = "DBUSER";
        private static final String PASSWORD = "ics321";
        
        protected static void getConnection() throws SQLException {
            con = DriverManager.getConnection(URL + DB_NAME, USER_NAME, PASSWORD);
        }
}
