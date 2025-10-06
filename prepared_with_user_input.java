/*CREATE TABLE Student (
    Sno NUMBER PRIMARY KEY,
    Sname VARCHAR2(50),
    Sage NUMBER,
    Saddress VARCHAR2(100),
    Scontactno VARCHAR2(15)
);

*/
import java.sql.*;
import java.util.Scanner;

public class StudentCRUDWithInput {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Update if needed
        String user = "system";
        String password = "oracle";

        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Choose Operation: 1.Insert  2.Update  3.Delete");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // INSERT
                    System.out.print("Enter Sno: ");
                    int sno = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Sname: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Sage: ");
                    int sage = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Saddress: ");
                    String saddress = sc.nextLine();
                    System.out.print("Enter Scontactno: ");
                    String scontactno = sc.nextLine();

                    String insertSQL = "INSERT INTO Student (Sno, Sname, Sage, Saddress, Scontactno) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStmt = con.prepareStatement(insertSQL);
                    insertStmt.setInt(1, sno);
                    insertStmt.setString(2, sname);
                    insertStmt.setInt(3, sage);
                    insertStmt.setString(4, saddress);
                    insertStmt.setString(5, scontactno);
                    int insertCount = insertStmt.executeUpdate();
                    System.out.println(insertCount + " record(s) inserted.");
                    break;

                case 2: // UPDATE
                    System.out.print("Enter Sno to update: ");
                    int usno = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Sname: ");
                    String usname = sc.nextLine();
                    System.out.print("Enter new Sage: ");
                    int usage = sc.nextInt();

                    String updateSQL = "UPDATE Student SET Sname = ?, Sage = ? WHERE Sno = ?";
                    PreparedStatement updateStmt = con.prepareStatement(updateSQL);
                    updateStmt.setString(1, usname);
                    updateStmt.setInt(2, usage);
                    updateStmt.setInt(3, usno);
                    int updateCount = updateStmt.executeUpdate();
                    System.out.println(updateCount + " record(s) updated.");
                    break;

                case 3: // DELETE
                    System.out.print("Enter Sno to delete: ");
                    int dsno = sc.nextInt();

                    String deleteSQL = "DELETE FROM Student WHERE Sno = ?";
                    PreparedStatement deleteStmt = con.prepareStatement(deleteSQL);
                    deleteStmt.setInt(1, dsno);
                    int deleteCount = deleteStmt.executeUpdate();
                    System.out.println(deleteCount + " record(s) deleted.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
