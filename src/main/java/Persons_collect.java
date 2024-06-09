import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persons_collect {
    private String sql;

    public Persons_collect() {
        DataBaseHelper.CreateNewDataBase();
        DataBaseHelper.CreatePersonTable();
    }

    public boolean addPerson(Persons person) {
        sql = "insert into person(name, age, idNumber) values(?,?,?)";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setString(3, person.getIdNumber());
            pstmt.executeUpdate();
            System.out.println("Person was added");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removePerson(String idNumber) {
        sql = "delete from person where idNumber = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idNumber);
            pstmt.executeUpdate();
            System.out.println("Person removed");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updatePerson(Persons person) {
        sql = "update person set name = ?, age = ? where idNumber = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setString(3, person.getIdNumber());
            pstmt.executeUpdate();
            System.out.println("Person was updated");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void PrintAllPersons() {
        sql = "select * from person";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String idNumber = rs.getString("idNumber");
                System.out.println("Id = " + id + ", Name = " + name + ", Age = " + age + ", IdNumber = " + idNumber);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Persons SearchPersonsByIdNumber(String idNumber) {
        sql = "SELECT name, age, idNumber FROM person WHERE idNumber = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    return new Persons(name, age, idNumber);
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void FindPersonsByName(String name) {
        sql = "SELECT id, name, age, idNumber FROM person WHERE name = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id");
                    String personName = rs.getString("name");
                    int age = rs.getInt("age");
                    String idNumber = rs.getString("idNumber");
                    System.out.println("Id = " + id + ", Name = " + personName + ", Age = " + age + ", IdNumber = " + idNumber);
                } else {
                    System.out.println("Person not found");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
