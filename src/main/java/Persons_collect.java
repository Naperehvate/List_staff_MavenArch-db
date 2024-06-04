import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persons_collect
{
    private String sql;
    public Persons_collect()
    {
       DataBaseHelper.CreateNewDataBase();
       DataBaseHelper.CreatePersonTable();
    }
    public boolean addPerson(Persons person)
    {
        sql = "insert into person(name,age,idNumber) values(?,?,?)";
        try(Connection conn = DataBaseHelper.Connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setString(3, person.getIdNumber());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean removePerson(Persons person)
    {
        sql="delete from person where idNumber = ?";
        try(Connection conn = DataBaseHelper.Connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setString(1, person.getIdNumber());
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }
    public boolean updatePerson(Persons person)
    {
        sql = "update person set name = ?, age = ? where idNumber = ?";
        try(Connection conn = DataBaseHelper.Connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            //возвращает количество затронутых строк
            pstmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }
    public void PrintAllPersons()
    {
        sql = "select * from person";
        try(Connection conn = DataBaseHelper.Connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();)
        {
            while (rs.next())
            {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String idNumber = rs.getString("idNumber");
                System.out.println(id + " " + name + " " + age + " " + idNumber);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
    public Persons SearchPersonsByIdNumber(String idNumber)
    {
        sql = "SELECT * FROM person WHERE idNumber = ?";
        try(Connection conn = DataBaseHelper.Connection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);)
        {
            pstmt.setString(1, idNumber);
            if (rs.next())
            {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String id = rs.getString("id");
                return new Persons(Integer.parseInt(id), name, age, idNumber);
            }
            return null;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void FindPersonsByName(String name) {
        sql = "SELECT * FROM person WHERE name = ?";
        try (Connection conn = DataBaseHelper.Connection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();) {
            pstmt.setString(1, name);
            while (rs.next()) {
                String id = rs.getString("id");
                String age = rs.getString("age");
                String idNumber = rs.getString("idNumber");
                System.out.println(id + " " + name + " " + age + " " + idNumber);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
