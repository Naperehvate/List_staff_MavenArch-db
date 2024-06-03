import java.util.Objects;

public class Persons
{
    private int id;
    private String name;
    private int age;
    private String idNumber;

    public Persons(int id,String name, int age, String idNumber)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
    }

    public int getId() {return id;}
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public String getIdNumber()
    {
        return idNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return Objects.equals(idNumber, persons.idNumber);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(idNumber);
    }

    @Override
    public String toString()
    {
        return "Persons" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", idNumber='" + idNumber + '\'';
    }

}
