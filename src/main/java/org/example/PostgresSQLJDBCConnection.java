package org.example;

import java.sql.*;

public class PostgresSQLJDBCConnection {
    public static void main(String[] args) {
        //Database connection parameters
        String url = "jdbc:postgresql://localhost:4747/sample_db";
        String user = "postgres";
        String password = "1234";

        try {
            //load the PostgresSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            //Establish Connection
            Connection conn = DriverManager.getConnection(url, user, password);

//            if(conn !=null){
//                System.out.println("Connection to PostgresSql successfully!");
//                //conn.close();
//            }

//            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?) ";
//            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)){
//                pstmt.setString(1, "Maina");
//                pstmt.setString(2, "Maina@example.com");
//                int rowsAffected = pstmt.executeUpdate();
//                System.out.println(rowsAffected + " row(s) inserted" );
//            }

//            String sql = "SELECT * FROM users";
//            try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
//                while (rs.next()){
//                    int id = rs.getInt("id");
//                    String name = rs.getString("name");
//                    String email = rs.getString("email");
//                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
//                }
//            }

//            String updateSQL = "UPDATE users SET email = ? WHERE id = ?";
//            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)){
//                pstmt.setString(1, "newemail@example.com");
//                pstmt.setInt(2, 1);
//                int rowsAffected = pstmt.executeUpdate();
//                System.out.println(rowsAffected + " row(s) updated");
//            }

//            String deleteSQL = "DELETE users SET email = ? WHERE id = ?";
//            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)){
//                pstmt.setInt(1, 1);
////                int rowsAffected = pstmt.executeUpdate();
//                System.out.println("deleted.");
//            }
//
//            String sql = "SELECT * FROM users";
//            try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
//                while (rs.next()){
//                    int id = rs.getInt("id");
//                    String name = rs.getString("name");
//                    String email = rs.getString("email");
//                    System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
//                }
//            }

            DatabaseMetaData dbmd = conn.getMetaData();
            System.out.println("DataBase Product Name: " + dbmd.getDatabaseProductName());
            System.out.println("DataBase Product Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverName());

            //list all tables
            ResultSet tables = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
            while (tables.next()){
                System.out.println(tables.getString("TABLE_NAME"));
            }

            String sql = "SELECT * FROM users";
            try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                for(int i = 1; i<=columnCount; i++){
                    System.out.println("Column " + i + ":");
                    System.out.println(" Name: " + rsmd.getColumnName(i));
                    System.out.println("");

                }
            }
        } catch (ClassNotFoundException e){
            System.out.println("PostgresSql JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

    }

    //A method to create/insert user to the db
    public static void addUser(Connection conn, String url, String user, String password) throws  SQLException{
        String sql = "INSERT INTO users (name, email) VALUES (?, ?) ";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, "John Doe");
            pstmt.setString(2, "john@example.com");
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted" );
        }
    }
}
