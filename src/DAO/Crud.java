package DAO;

import Model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Crud {
    public DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    // TODO : insert a new todo into the database
    public boolean insertTodo(Todo todo){
        try {
            String sql = "INSERT INTO todo(id, priority, title, description, deadline, done) VALUES (?,?,?,?,?,?);";
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, todo.getId());
            statement.setInt(2, todo.getPriority());
            statement.setString(3, todo.getTitle());
            statement.setString(4, todo.getDescription());
            statement.setTimestamp(5,todo.getDeadline());
            statement.setBoolean(6, todo.isDone());
            statement.executeUpdate();

            return this.findTodoById(todo.getId()) != null;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a single todo using its ID
    public Todo findTodoById(int id){
        try{
            String sql = "SELECT * FROM todo WHERE id = " + id;
            Statement statement = databaseConnection.getConnection().createStatement();
            ResultSet row = statement.executeQuery(sql);

            if(row.next()){
                return new Todo(
                    row.getInt("id"),
                    row.getInt("priority"),
                    row.getString("title"),
                    row.getString("description"),
                    row.getTimestamp("deadline"),
                    row.getBoolean("done")
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // TODO : get all todos in the database
    public List<Todo> findAllTodos(){
        try {
            ArrayList<Todo> result = new ArrayList<>();
            String sql = "SELECT * FROM todo ORDER BY id;";
            Statement statement = this.databaseConnection.getConnection().createStatement();
            ResultSet rows = statement.executeQuery(sql);

            while(rows.next()){
                 result.add(new Todo(
                    rows.getInt("id"),
                    rows.getInt("priority"),
                    rows.getString("title"),
                    rows.getString("description"),
                    rows.getTimestamp("deadline"),
                    rows.getBoolean("done")
                ));
            }

            return result;
        } catch (SQLException error) {
            System.out.println(error);
            return null;
        }
    }

    // TODO : delete a single todo using its ID
    public boolean deleteTodo(int id){
        try {
            String sql = "DELETE from todo WHERE id = ?;";
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            return findTodoById(id) == null;
        } catch (SQLException error) {
            System.out.println(error);
            return false;
        }
    }

    public boolean deleteAllTodos(){
        try {
            String sql = "delete from todo;";
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            statement.executeUpdate();
            return this.findAllTodos().size() == 0;
        }
        catch (SQLException error){
            System.out.println(error);
        }
        return false;
    }

    // TODO : update a single todo by using its id, and giving the new informations.
    public boolean updateTodo(int id, int priority, String title, String description, Timestamp deadline, boolean done){
        try {
            String sql = "UPDATE todo SET priority = ? , title = ?, description = ? , deadline = ?, done = ? WHERE id = ?;";
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, priority);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setTimestamp(4, deadline);
            statement.setBoolean(5, done);
            statement.setInt(6, id);
            statement.executeUpdate();

            Todo temptodo = findTodoById(id);
            if( temptodo != null){
                return temptodo.getTitle().equals(title) &&
                        temptodo.getDescription().equals(description) &&
                        temptodo.isDone() == done &&
                        temptodo.getDeadline().toString().equals(deadline.toString());
            }

            return true;
        } catch (Exception error) {
            System.out.println(error);
        }
        return false;
    }
}