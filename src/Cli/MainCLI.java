package Cli;

import DAO.Crud;
import DAO.DatabaseConnection;
import Model.Todo;

import java.sql.SQLException;
import java.util.List;

public class MainCLI {
    private final String userName;
    private int status;
    private final Crud crud;
    public MainCLI(String userName) {
        this.userName = userName;
        this.crud = new Crud();
        this.status = 0;
    }

    private void menu(){
        System.out.println("========== MENU ==========");
        Utils.printList(List.of(
            "1 -> Add Todo",
            "2 -> All Todos",
            "3 -> Find Todo by ID",
            "4 -> Update Todo",
            "5 -> Delete Todo",
            "6 -> Delete All Todo",
            "7 -> Quit"
        ));
        int result = Utils.getResponse(List.of(1,2,3,4,5,6,7));
        if(result != -1)
            this.status = result;
        else
            this.menu();
    }

    private void exit(){
        System.out.println("----- BYE " + this.userName.toUpperCase() + " !!! -----");
        try {
            DatabaseConnection.getInstance().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitAction(){
        System.out.print("Type something ...");
        Utils.getString();
        this.status = 0;
    }

    private void addTodo(){
        if(crud.insertTodo(Utils.inputAllInformation(true)))
            System.out.println("Todo added !!");
        else
            System.out.println("Error,Todo user not added");

        this.waitAction();
    }
    private void allTodo(){
        this.crud.findAllTodos().forEach(System.out::println);
        this.waitAction();
    }

    private void findById(){
        System.out.print("Type the id : ");
        int result = Utils.getInt();
        Todo tempTodo =  this.crud.findTodoById(result);
        if(tempTodo != null) {
            System.out.println(tempTodo);
        }else{
            System.out.println("Todo not found");
        }
        this.waitAction();
    }

    private void updateTodo() {
        System.out.print("Todo with what ID ?? :");
        Todo updateTodo = crud.findTodoById(Utils.getInt());
        if (updateTodo == null) {
            System.out.println("Todo not found");
            this.waitAction();
        } else {
             Todo updateTodoInfo = Utils.inputAllInformation(false);
            if(crud.updateTodo(
                updateTodo.getId(),
                updateTodoInfo.getPriority(),
                updateTodoInfo.getTitle(),
                updateTodoInfo.getDescription(),
                updateTodoInfo.getDeadline(),
                updateTodoInfo.isDone()
            )){
                System.out.println("Todo updated");
            }else{
                System.out.println("Error, todo not updated");
            }
        }
        this.waitAction();
    }

    public void deleteTodo(){
        System.out.print("Todo with what ID ??? : ");
        int id = Utils.getInt();
        if(this.crud.deleteTodo(id)){
            System.out.println("Todo deleted");
            this.waitAction();
        }
        else{
            System.out.println("Todo not found");
        }
    }

    public void deleteAllTodo(){
        Utils.printList(List.of("Are you sure ?", "1 -> Yes","2 -> No"));
        int answer = Utils.getResponse(List.of(1,2));
        if(answer == 1){
            this.crud.deleteAllTodos();
            System.out.println("All user deleted");
            this.waitAction();
            return;
        }
        this.status = 0;
    }
    public void run(){
        System.out.println("---- HELLO " + this.userName.toUpperCase() + " !!! -----\n");
        while(this.status != 7){
            switch (this.status){
                case 0 -> this.menu();
                case 1 -> this.addTodo();
                case 2 -> this.allTodo();
                case 3 -> this.findById();
                case 4 -> this.updateTodo();
                case 5 -> this.deleteTodo();
                case 6 -> this.deleteAllTodo();
                default -> this.status = 0;
            }
            System.out.println();
        }
        this.exit();
    }
}
