import java.util.Scanner;

public class Program
{
 public static void main(String[] args)
 {
     Persons_collect persons_collect = new Persons_collect();
     System.out.println("Enter commands: add, remove, update, search, print, find or exit");
     Scanner scanner = new Scanner(System.in);
     String command = scanner.nextLine();
     while (!command.equals("exit"))
     {
         switch (command)
         {
             case "add":
                 System.out.println("Enter name, age and IdNumber");
                 persons_collect.addPerson(new Persons(scanner.next(), scanner.nextInt(), scanner.next()));
                 break;
             case "remove":
                 System.out.println("Enter IdNumber");
                 persons_collect.removePerson(scanner.next());
                 break;
             case "update":
                 System.out.println("Enter name, age want to updated" + " + IdNumber which you want to update");
                 System.out.println("Person was updated " +
                         persons_collect.updatePerson(new Persons(scanner.next(), scanner.nextInt(), scanner.next())));
                 break;
             case "search":
                 System.out.println("Inter IdNumber: ");
                 Persons persons = persons_collect.SearchPersonsByIdNumber(scanner.next());
                 System.out.println(persons);
                 break;
             case "print":
                 persons_collect.PrintAllPersons();
                 break;
             case "find":
                 System.out.println("Enter name: " + scanner.next());
                 persons_collect.FindPersonsByName(scanner.next());
                 break;
         }
         command = scanner.nextLine();
     }
 }
}
