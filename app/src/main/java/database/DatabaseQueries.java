/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package database;

import java.sql.*;
public class DatabaseQueries {
    Connection PCDatabase;
    /*
     * Constructs the class and connects to the database
     */
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
    /*
     * Adds a new user for to the database
     *  
     * @param id an int that uniquely identifies the new user
     * @param name the user name of the new user
     * @param password the password the user will use to log in
     * @param date the date the user was created formated "yyyy-mm-dd"
     */
    public void AddUser(int id, String name, String password, String Date){
        try{
            PCDatabase.createStatement().execute("insert into users (user_id, username, user_password, created_at) values("+id+", \'"+name+"\', \'"+password+"\', \'"+Date+"\')");
        }
        catch (Exception e){
            System.out.println("AddUser Error:\n    "+e);
        }
        defaultSettings(id);
    }
    /*
     * Querries the database to find the next available ID for a User 
     * 
     * @return      the next avalible user_id as an int
     */
    public int getNextUserID(){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select max(user_id) from users");
            if(results.next())
                return results.getInt(1) + 1;
            else
                return 1;
        }
        catch (Exception e){
            System.out.println("GetNextUserID Error:\n    "+e);
        }
        return -1;
    }
    /*
     * Querries the database to validate login information
     *  
     * @param userID    the id of the user trying to login
     * @param password  the password associated with the userID
     * @return          the user_id if password matches the stored password or -1 on a failed login attempt
     */
    public int Login(int userID, String password){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select user_id from users where user_id="+userID+" and user_password=\'"+password+"\'");
            if(results.next())
                return results.getInt("user_id");
        }
        catch (Exception e){
            System.out.println("GetNextID Error:\n    "+e);
        }
        return -1;
    }
    /*
     * Changes password of the user in the database given the user_id and oldPassword matches
     * in the database
     *  
     * @param user_id       the id of the user modifying their password
     * @param oldPassword   the password stored in the database
     * @param newPassword   the password that will replace the one in the database
     */
    public void updatePassword(int user_id, String oldPassword, String newPassword){
        try{
            PCDatabase.createStatement().execute("update users set user_password=\'"+newPassword+"\' where user_id="+user_id+" and user_password=\'"+oldPassword+"\'");
        }
        catch (Exception e){
            System.out.println("updatePassword Error:\n    "+e);
        }
    }
    /*
     * Creates default usersettings in the database for a given user id
     *  
     * @param UID   the userid to create the default settings for
     */
    public void defaultSettings(int UID){
        try{
            PCDatabase.createStatement().execute("insert into user_settings (user_id, UI_color, UI_Font, first_name, last_name, primary_language) values ("+UID+", \'(255, 255, 255)\', \'Times New Roman\', \'FirstName\', \'LastName\',\'English\')");
        }
        catch (Exception e){
            System.out.println("defaultSettings Error:\n    "+e);
        }
    }
    /*
     * Changes the user settings to the given values
     *  
     * @param UID                   the id of the user for which the settings are being updated
     * @param color                 the new color of the UI formated "(r,g,b)"
     * @param Font                  the font to be used for the UI
     * @param Fname                 the first name of the user
     * @param Lname                 the last name of the user
     * @param primary_language      the primary language spoken by the user
     */
    public void updateUserSettings(int UID, String color, String Font, String Fname, String LName, String primary_language){
        try{
            PCDatabase.createStatement().execute("update user_settings set UI_color=\'"+color+"\', UI_Font=\'"+Font+"\', first_name=\'"+Fname+"\', last_name=\'"+LName+"\', primary_language=\'"+primary_language+"\' where user_id="+UID+"");
        }
        catch (Exception e){
            System.out.println("updateUserSettings Error:\n    "+e);
        }
    }
    /*
     * Querries the database for the next avalible group id
     * 
     * @return      the next unused group_id
    */
    public int nextGroupID(){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select max(group_id) from user_group");
            if(results.next())
                return results.getInt(1) + 1;
            else
                return 1;
        }
        catch (Exception e){
            System.out.println("GetNextGroupID Error:\n    "+e);
        }
        return -1;
    }
    /*
     * Creates a new group in the database
     * 
     * @param groupID       the groupID of the new group to be created
     * @param name          the name of the group
     * @param creatorID     the id of the user who made the group
     */
    public void CreateGroup(int groupID, String name, int creatorID){
        try{
            PCDatabase.createStatement().execute("insert into user_group (group_id, group_name, group_creator) values ("+groupID+", \'"+name+"\', "+creatorID+")");
            PCDatabase.createStatement().execute("insert into group_members (group_id, user_id) values ("+groupID+", "+creatorID+")");
        }
        catch (Exception e){
            System.out.println("CreateGroup Error:\n    "+e);
        }
    }
    /*
     * Adds a user to a group within the database
     * 
     * @param groupID   the id of the group that will receive the new member
     * @param userID    the id of the user that will be added to the group
     */
    public void addGroupMember(int groupID, int userID){
        try{
            PCDatabase.createStatement().execute("insert into group_members (user_id, group_id) values ("+userID+", "+groupID+")");
        }
        catch (Exception e){
            System.out.println("CreateGroup Error:\n    "+e);
        }
    }
    /*
     * Returns an int[] of the groups a given user is in
     * 
     * @param user_id   the user to querry the database for
     * @return          the list of group ids the user is a member of
     */
    public int[] getGroups(int user_id){
        try{
            ResultSet len = PCDatabase.createStatement().executeQuery("select count(group_id) from group_members where user_id="+user_id);
            int group_values[];
            if(len.next()){
                group_values = new int[len.getInt(1)];
            }
            else{
                return null;
            }
            ResultSet groups = PCDatabase.createStatement().executeQuery("select group_id from group_members where user_id="+user_id);
            int i =0;
            while(groups.next()){
                group_values[i]=groups.getInt("group_id");
                i++;
            }
            return group_values;
        }
        catch(Exception e){
            System.out.println("getGroups Error:\n    "+e);
        }
        return null;
    }
    /*
     * Returns an int[] of the users that are a memeber of a given group
     * 
     * @param group_id  the group id to querry the database for
     * @return          the list of user ids that are a member of the group
     */
    public int[] getGroupMembers(int group_id){
        try{
            ResultSet len = PCDatabase.createStatement().executeQuery("select count(user_id) from group_members where group_id="+group_id);
            int members[];
            if(len.next()){
                members = new int[len.getInt(1)];
            }
            else{
                return null;
            }
            ResultSet Members = PCDatabase.createStatement().executeQuery("select user_id from group_members where group_id="+group_id);
            int i =0;
            while(Members.next()){
                members[i]=Members.getInt("user_id");
                i++;
            }
            return members;
        }
        catch(Exception e){
            System.out.println("getGroupMembers Error:\n    "+e);
        }
        return null;
    }
    /*
     * Returns an int that is the next avalible meeting id in the database
     * 
     * @return      the next avalible id for the meeting table
     */
    public int getNextMeetingID(){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select max(meeting_id) from group_meetings");
            if(results.next())
                return results.getInt(1) + 1;
            else
                return 1;
        }
        catch(Exception e){
            System.out.println("getNextMeetingID Error:\n    "+e);
        }
        return -1;
    }
    /*
     * Creates a new meeting with in the database
     * 
     * @param meeting_id        the unique id of the meeting
     * @param group_id          the id of the group having the meeting
     * @param meeting_name      the name of the meeting
     * @param dateTime          the date and time the meeting took place formated as "yyyy-mm-dd hh:mm:ss"
     * @param shareable         a boolean determining if a meeting can be shared beyond the group
     */
    public void CreateMeeting(int meeting_id, int group_id, String meeting_name, String dateTime, boolean shareable){
        try{
            PCDatabase.createStatement().execute("insert into group_meetings (group_id, meeting_id, meeting_name, meeting_date, shareable) values ("+group_id+", "+meeting_id+", \'"+meeting_name+"\', \'"+dateTime+"\', "+shareable+")");
        }
        catch(Exception e){
            System.out.println("CreateMeeting Error:\n    "+e);
        }
    }
    /*
     * Returns an int[] of the meetings a group has held
     * 
     * @param group_id      the id of the group to querry the database for
     * @return              the list of meeting ids that have been held by the given group
     */
    public int[] getGroupMeetings(int group_id){
        try{
            ResultSet len = PCDatabase.createStatement().executeQuery("select count(meeting_id) from group_meetings where group_id="+group_id);
            int meetings[];
            if(len.next()){
                meetings = new int[len.getInt(1)];
            }
            else{
                return null;
            }
            ResultSet Meetings = PCDatabase.createStatement().executeQuery("select meeting_id from group_meetings where group_id="+group_id);
            int i =0;
            while(Meetings.next()){
                meetings[i]=Meetings.getInt("meeting_id");
                i++;
            }
            return meetings;
        }
        catch(Exception e){
            System.out.println("getGroupMembers Error:\n    "+e);
        }
        return null;

    }
    /*
     * Returns an int for the next message id avalible within a meeting
     * 
     * @param meeting_id    the id of the meeting this message id will be a part of
     * @return              the next avalible id for a message
     */
    public int getNextMessageID(int meeting_id){
        try{
            ResultSet results = PCDatabase.createStatement().executeQuery("select max(message_id) from messages where meeting_id="+meeting_id);
            if(results.next())
                return results.getInt(1) + 1;
            else
                return 1;
        }
        catch(Exception e){
            System.out.println("getNextMessageID Error:\n    "+e);
        }
        return -1;
    }
    /*
     * Creates a message within the database
     * 
     * @param user_id       the id of the user who created the message
     * @param meeting_id    the id of the meeting this message is a part of
     * @param message_id    the id of the message (note: within the database a message must have a unique pair of meeting id and message id)
     * @param message       the contents of the message
     * @param dateTime      the date and time the message was made formated as "yyyy-mm-dd hh:mm:ss"
     */
    public void createMessage(int user_id, int meeting_id, int message_id, String message, String dateTime, boolean pinned){
        try{
            PCDatabase.createStatement().execute("insert into messages (user_id, meeting_id, message_id, body, created_at, pinned) values ("+user_id+", "+meeting_id+", "+message_id+", \'"+message+"\', \'"+dateTime+"\', "+pinned+")");
        }
        catch(Exception e){
            System.out.println("CreateMessage Error:\n    "+e);
        }
    }
}
