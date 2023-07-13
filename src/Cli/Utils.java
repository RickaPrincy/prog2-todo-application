package Cli;

import Model.Todo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static boolean getBoolean(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextBoolean();
    }

    public static int getResponse(List<Integer> validInput){
        System.out.print("What you wanna do ? : ");
        int result = Utils.getInt();
        if(!validInput.contains(result)){
            System.out.println("[ ERROR ] ----> That's not a valid option !!!\n");
            return -1;
        }
        return result;
    }

    public static void printList(List<String> list){
        list.forEach(System.out::println);
    }

    public static Todo inputAllInformation(boolean shouldAskId){
        int id = 0, priority;
        boolean done = false;
        Timestamp deadline;
        String title, description;
        System.out.println("Input all information about the todo!!");
        if(shouldAskId){
            System.out.print("Id : ");
            id = getInt();
        }
        System.out.print("Title : ");
        title = Utils.getString();
        System.out.print("Description : ");
        description = Utils.getString();
        System.out.print("Deadline (YY-MM-DD HH:MM:SS) :");
        deadline = Timestamp.valueOf(Utils.getString());
        System.out.print("Priority : ");
        priority = Utils.getInt();
        if(!shouldAskId){
            System.out.print("Done : ");
            done = Utils.getBoolean();
        }
        return new Todo(id,priority,title,description, deadline,done);
    }
}
