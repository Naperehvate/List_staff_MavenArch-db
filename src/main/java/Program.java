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
                 persons_collect.addPerson(new Persons(scanner.nextInt(), scanner.next(), scanner.nextInt(), scanner.next()));
                 break;
             case "remove":
                 persons_collect.removePerson(new Persons(scanner.nextInt(), scanner.next(), scanner.nextInt(), scanner.next()));
                 break;
             case "update":
                 persons_collect.updatePerson(new Persons(scanner.nextInt(), scanner.next(), scanner.nextInt(), scanner.next()));
                 break;
             case "search":
                 persons_collect.SearchPersonsByIdNumber(scanner.next());
                 break;
             case "print":
                 persons_collect.PrintAllPersons();
                 break;
             case "find":
                 persons_collect.FindPersonsByName(scanner.next());
                 break;
         }
         command = scanner.nextLine();
     }
 }
}
