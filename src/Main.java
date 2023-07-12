import DAO.Crud;
import DAO.DatabaseConnection;
import Model.Todo;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        Todo todo = new Todo(1, 2, "test","test2",
                Timestamp.valueOf("2023-09-03 20:01:00"),
                false);

        Crud crud = new Crud();
        crud.deleteAllTodos();
        crud.insertTodo(todo);
        crud.updateTodo(1,5,"ricka","play",Timestamp.valueOf("2023-10-03 02:01:03"), false);
        System.out.println(crud.findAllTodos());
        DatabaseConnection.getInstance().closeConnection();
    }
}