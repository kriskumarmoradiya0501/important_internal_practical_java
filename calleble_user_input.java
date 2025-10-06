/*CREATE OR REPLACE PROCEDURE insertStudent (
    p_sno IN NUMBER,
    p_sname IN VARCHAR2,
    p_sage IN NUMBER,
    p_saddress IN VARCHAR2,
    p_scontactno IN VARCHAR2
) AS
BEGIN
    INSERT INTO Student VALUES (p_sno, p_sname, p_sage, p_saddress, p_scontactno);
END;

CREATE OR REPLACE PROCEDURE updateStudent (
    p_sno IN NUMBER,
    p_sname IN VARCHAR2,
    p_sage IN NUMBER
) AS
BEGIN
    UPDATE Student SET Sname = p_sname, Sage = p_sage WHERE Sno = p_sno;
END;

CREATE OR REPLACE PROCEDURE deleteStudent (
    p_sno IN NUMBER
) AS
BEGIN
    DELETE FROM Student WHERE Sno = p_sno;
END;
*/
import java.sql.*;
import java.util.Scanner;

public class StudentCallableCRUD {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Adjust if needed
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
                    sc.nextLine();
                    System.out.print("Enter Sname: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Sage: ");
                    int sage = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Saddress: ");
                    String saddress = sc.nextLine();
                    System.out.print("Enter Scontactno: ");
                    String scontactno = sc.nextLine();

                    CallableStatement csInsert = con.prepareCall("{call insertStudent(?, ?, ?, ?, ?)}");
                    csInsert.setInt(1, sno);
                    csInsert.setString(2, sname);
                    csInsert.setInt(3, sage);
                    csInsert.setString(4, saddress);
                    csInsert.setString(5, scontactno);
                    csInsert.execute();
                    System.out.println("Record inserted successfully.");
                    break;

                case 2: // UPDATE
                    System.out.print("Enter Sno to update: ");
                    int usno = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Sname: ");
                    String usname = sc.nextLine();
                    System.out.print("Enter new Sage: ");
                    int usage = sc.nextInt();

                    CallableStatement csUpdate = con.prepareCall("{call updateStudent(?, ?, ?)}");
                    csUpdate.setInt(1, usno);
                    csUpdate.setString(2, usname);
                    csUpdate.setInt(3, usage);
                    csUpdate.execute();
                    System.out.println("Record updated successfully.");
                    break;

                case 3: // DELETE
                    System.out.print("Enter Sno to delete: ");
                    int dsno = sc.nextInt();

                    CallableStatement csDelete = con.prepareCall("{call deleteStudent(?)}");
                    csDelete.setInt(1, dsno);
                    csDelete.execute();
                    System.out.println("Record deleted successfully.");
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
