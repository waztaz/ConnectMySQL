package com.lynda.javatraining.db;

        import java.sql.*;
        import java.util.Scanner;
public class Dynammic_Queries {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "Haider123";
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
            String agentname ="Meryl Streep";
//Get parameter's value from user
            System.out.println( "Enter the name of the agent (Default is Meryl Streep): ");
            agentname = scanner.nextLine();
            String query =  "Select Movie.movieTitle, Movie.movieYear\n" +
                    "From Movie, Stars\n" +
                    "Where Movie.movieTitle =  Stars.movieTitle AND Stars.starName = '";
            query += agentname +"'";
            System.out.println("Running the query: "+ query);
            System.out.println();
            System.out.println("Output of the query is: ");
            //System.out.println(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();

                buffer.append("Movie Title: " + rs.getString("movieTitle"));
                buffer.append(" MovieYear: " + rs.getInt("movieYear"));

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