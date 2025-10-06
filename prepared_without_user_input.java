/*CREATE TABLE Student (
    Sno NUMBER PRIMARY KEY,
    Sname VARCHAR2(50),
    Sage NUMBER,
    Saddress VARCHAR2(100),
    Scontactno VARCHAR2(15)
);

*/

import java.sql.*;

public class StudentCRUD {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Adjust if needed
        String user = "system";
        String password = "oracle";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            // INSERT Operation
            String insertSQL = "INSERT INTO Student (Sno, Sname, Sage, Saddress, Scontactno) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertSQL);
            insertStmt.setInt(1, 101);
            insertStmt.setString(2, "Krishkumar");
            insertStmt.setInt(3, 22);
            insertStmt.setString(4, "Petlad");
            insertStmt.setString(5, "9876543210");
            int insertCount = insertStmt.executeUpdate();
            System.out.println(insertCount + " record(s) inserted.");

            // UPDATE Operation
            String updateSQL = "UPDATE Student SET Sname = ?, Sage = ? WHERE Sno = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateSQL);
            updateStmt.setString(1, "Krish Kumar");
            updateStmt.setInt(2, 23);
            updateStmt.setInt(3, 101);
            int updateCount = updateStmt.executeUpdate();
            System.out.println(updateCount + " record(s) updated.");

            // DELETE Operation
            String deleteSQL = "DELETE FROM Student WHERE Sno = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteSQL);
            deleteStmt.setInt(1, 101);
            int deleteCount = deleteStmt.executeUpdate();
            System.out.println(deleteCount + " record(s) deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
