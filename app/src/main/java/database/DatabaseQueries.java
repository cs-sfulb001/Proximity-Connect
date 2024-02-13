/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package database;

import java.sql.*;
public class DatabaseQueries {
    Connection PCDatabase;
    public DatabaseQueries(){
        String url="jdbc:mysql://98.166.255.71:3306/proximity_connect";
        String username="YellowTeam";
        String password="Yellow411w";
        try{
            PCDatabase = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void AddUser(int id, String name, String password, String Date){
        try{
            PCDatabase.createStatement().execute("insert into users (user_id, username, user_password, created_at) values("+id+", \'"+name+"\', \'"+password+"\', \'"+Date+"\')");
        }
        catch (Exception e){
            System.out.println("Add user Error:\n    "+e);
        }

    }
    public int getNextID(){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select max(user_id) from users");
            if(results.next())
                return results.getInt(1) + 1;
            else
                return 1;
        }
        catch (Exception e){
            System.out.println("GetNextID Error:\n    "+e);
        }
        return -1;
    }
    public int Login(String username, String password){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select user_id from users where username=\'"+username+"\' and user_password=\'"+password+"\'");
            if(results.next())
                return results.getInt("user_id");
        }
        catch (Exception e){
            System.out.println("GetNextID Error:\n    "+e);
        }
        return -1;
    }
    public void updatePassword(int user_id, String oldPassword, String newpassword){
        try{
            PCDatabase.createStatement().execute("update users set user_password=\'"+newpassword+"\' where user_id="+user_id+" and user_password=\'"+oldPassword+"\'");
        }
        catch (Exception e){
            System.out.println("GetUpdatePassword Error:\n    "+e);
        }
    }
}
