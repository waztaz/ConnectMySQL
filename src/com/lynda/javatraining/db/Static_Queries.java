package com.lynda.javatraining.db;

        import java.sql.*;
        import java.util.Scanner;
public class Static_Queries {

    private static final String USERNAME = "waztaz";
    private static final String PASSWORD = "soccer123";
    private static final String CONN_STRING =
            "jdbc:mysql://waztaz123.cwede4peo40a.us-west-2.rds.amazonaws.com:3306/soccer";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner( System.in );
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String query= "Select * " +
                    "From Nationality\n";
            rs = stmt.executeQuery(query);

            System.out.println("Running the query: "+ query);
            System.out.println();
            System.out.println("Output of the query is: ");

            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();

                buffer.append("Country Name: " + rs.getString("Country_name"));
                buffer.append(" & Continent Name: " + rs.getString("Continent"));



                System.out.println(buffer.toString());
                System.out.println();
            }

            rs.last();




        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}