/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package database;

import java.sql.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class TestDatabaseQueries {
    static DatabaseQueries Object;
    String username[], password[], datecreated[], group_name[], dateTime[], meeting_name[], message[];
    @Before public void init(){
        Object = new DatabaseQueries();
        username = new String[] {"test", "testUserName", "Pineapple"};
        password = new String[] {"password","1234", "OneTwoThree"};
        date_created = new String[] {"2024-02-11","2024-02-20","2024-03-09"};
        group_name = new String[] {"testGroup", "GroupName", "The Best Group"};
        dateTime = new String[] {"2024-03-03 12:47:32","2024-03-03 13:48:05", "2024-03-09 02:53:53"};
        meeting_name = new String[] {"Test Meeting", "The Next Meeting", "The Worst Meeting"};
        message = new String[] {"This is a test message that should not be bound by length in theory. Lets test that theory and see if this will fit.", "Simple test message", "Test123"};
    }
    /*
     * ---------------------------------------------------------------------------
     *                               User Tests
     * ---------------------------------------------------------------------------
     */
    @Test public void testNextUserID(){
        //Object.fillSampleData(); 
        /*
         * Setup
         */
        //Gathering ID's
        assertThat(Object.getNextUserID(), is(not(equalTo(-1))));
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]); //To have getNextUserID() to generate a new id
        int nextID = Object.getNextUserID();
        /*
         * Verification
         */
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testNextUserID Error:\n    "+e);
        }
        assertEquals(user_id+1, nextID);
    }
    //Insure the user is created correctly
    @Test public void testAddUser(){
        /*
         * Setup
         */
        //Create User
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id[0], username[0], password[0], datecreated[0]);
        /*
         * Verification
         */
        int TestID = -1;
        String testUN = "";
        String testPW = "";
        String testDate = "";
        String defaultFName="";
        String defaultLName="";
        String defaultUIColor="";
        String defaultFont="";
        String defaultPP="";
        String defaultPrimaryLanguage="";
        try{
            ResultSet newUser = Object.PCDatabase.createStatement().executeQuery("select * from users where user_id="+user_id);
            newUser.next();
            testUN = newUser.getString("username");
            testPW = newUser.getString("user_password");
            testDate = newUser.getString("created_at");
            ResultSet defaultSettings = Object.PCDatabase.createStatement().executeQuery("select * from user_settings where user_id="+user_id);
            defaultSettings.next();
            defaultFName = defaultSettings.getString("first_name");
            defaultLName = defaultSettings.getString("last_name");
            defaultUIColor = defaultSettings.getString("UI_Color");
            defaultFont = defaultSettings.getString("UI_Font");
            defaultPP = defaultSettings.getString("profile_picture");
            defaultPrimaryLanguage = defaultSettings.getString("primary_language");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testAddUser Error:\n    "+e);
        }
        //Test for expected information with the userid (Would fail to create the user if the user_id is taken)
        assertEquals(username, testUN);
        assertEquals(password, testPW);
        assertEquals(datecreated, testDate);
        //Checking Default settings being created
        assertEquals("FirstName", defaultFName);
        assertEquals( "LastName", defaultLName);
        assertEquals(null, defaultPP);
        assertEquals("English", defaultPrimaryLanguage);
        assertEquals("Times New Roman", defaultFont);
        assertEquals("(255, 255, 255)", defaultUIColor);
    }
    /*
     *                User Functions
     */
    @Test public void testUpdatePassword(){
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        String newpassword = "1234";
        Object.updatePassword(user_id, password[0], password[1]);
        int TestID = -1;
        String testUN = "";
        String testPW = "";
        String testDate = "";
        try{
            ResultSet newUser = Object.PCDatabase.createStatement().executeQuery("select * from users where user_id="+user_id);
            newUser.next();
            TestID = newUser.getInt("user_id");
            testUN = newUser.getString("username");
            testPW = newUser.getString("user_password");
            testDate = newUser.getString("created_at");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testUpdatePassword Error:\n    "+e);
        }
        assertEquals(user_id, user_id);
        assertEquals(username[0], testUN);
        assertEquals(password[1], testPW);
        assertEquals(date_created[0], testDate);
    }
    @Test public void testUpdateUserSettings(){
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        // No future tests depend on settings thus only needed here
        String newFName="Pine";
        String newLName="Apple";
        String newUIColor="(0, 255, 0)";
        String newFont="Comic Sans";
        String newPrimaryLanguage="Spanish";
        Object.updateUserSettings(user_id, newUIColor, newFont, newFName, newLName, newPrimaryLanguage);
        String storedFName="";
        String storedLName="";
        String storedUIColor="";
        String storedFont="";
        String storedPP="";
        String storedPrimaryLanguage="";
        try{
            ResultSet Settings = Object.PCDatabase.createStatement().executeQuery("select * from user_settings where user_id="+user_id);
            Settings.next();
            storedFName = Settings.getString("first_name");
            storedLName = Settings.getString("last_name");
            storedUIColor = Settings.getString("UI_Color");
            storedFont = Settings.getString("UI_Font");
            storedPP = Settings.getString("profile_picture");
            storedPrimaryLanguage = Settings.getString("primary_language");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testUpdateUserSettings Error:\n    "+e);
        }
        assertEquals(newFName, storedFName);
        assertEquals( newLName, storedLName);
        assertEquals(null, storedPP);
        assertEquals(newPrimaryLanguage, storedPrimaryLanguage);
        assertEquals(newFont, storedFont);
        assertEquals(newUIColor, storedUIColor);
    }
    //Make sure Usernames are unique?
    @Test public void testLogin(){
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        int loginID = Object.Login(user_id, password[0]);
        String testUN = "";
        String testPW = "";
        String testDate = "";
        try{
            ResultSet newUser = Object.PCDatabase.createStatement().executeQuery("select * from users where user_id="+loginID);
            newUser.next();
            testUN = newUser.getString("username");
            testPW = newUser.getString("user_password");
            testDate = newUser.getString("created_at");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testLogin Error:\n    "+e);
        }
        assertEquals(user_id, loginID);
        assertEquals(username[0], testUN);
        assertEquals(password[0], testPW);
        assertEquals(date_created[0], testDate);
    }
    /*
     * ---------------------------------------------------------------------------
     *                               Group Tests
     * ---------------------------------------------------------------------------
     */
    @Test public void testNextGroupID(){
        int group_id = Object.nextGroupID();
        assertThat(group_id, is(not(equalTo(-1))));
        int user_id = Object.nextGroupID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        Object.CreateGroup(group_id, group_name[0], user_id);
        int nextID = Object.nextGroupID();
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testNextUserID Error:\n    "+e);
        }
        assertEquals(group_id+1, nextID);
    }
    
    @Test public void testCreateGroup(){
        int group_id = Object.nextGroupID();
        assertThat(group_id, is(not(equalTo(-1))));
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        Object.CreateGroup(group_id, group_name[0], user_id);
        String Storedgroup_name = "";
        int group_Creator = -1;
        try{
            ResultSet GroupQuery = Object.PCDatabase.createStatement().executeQuery("select * from user_group where group_id="+group_id);
            GroupQuery.next();
            Storedgroup_name=GroupQuery.getString("group_name");
            group_Creator=GroupQuery.getInt("group_creator");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testNextUserID Error:\n    "+e);
        }
        assertEquals(group_name[0], Storedgroup_name);
        assertEquals(user_id, group_Creator);
    }
    
    @Test public void testAddGroupMember(){
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], datecreated[0]);
        int user_id2 = Object.getNextUserID();
        Object.AddUser(user_id2, username[1], password[1], datecreated[1]);
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_id);
        Object.addGroupMember(group_id, user_id2);
        int storedGroup=-1;
        int storedMember1=-1;
        int storedMember2=-1;
        try{
            ResultSet Groups = Object.PCDatabase.createStatement().executeQuery("select * from user_group where group_creator="+user_id);
            Groups.next();
            storedGroup=Groups.getInt("group_id");
            ResultSet Members = Object.PCDatabase.createStatement().executeQuery("select * from group_members where group_id="+group_id);
            Members.next();
            storedMember1=Members.getInt("user_id");
            Members.next();
            storedMember2=Members.getInt("user_id");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id2);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(group_id, storedGroup);
        assertEquals(user_id, storedMember1);
        assertEquals(user_id2, storedMember2);
    }
    @Test public void testGetGroups(){
        /*
         * Setup
         */
        //Create Users
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        int user_id2 = Object.getNextUserID();
        Object.AddUser(user_id2, username[1], password[1], date_created[1]);
        int user_id3 = Object.getNextUserID();
        Object.AddUser(user_id3, username[2], password[2], date_created[2]);
        int group_id = Object.nextGroupID();
        //Create Groups
        Object.CreateGroup(group_id, group_name[0], user_id);
        int group_id2 = Object.nextGroupID();
        Object.CreateGroup(group_id2, group_name[1], user_id2);
        //Add members to groups
        Object.addGroupMember(group_id, user_id3);
        Object.addGroupMember(group_id2, user_id3);
        /*
         * Verification
         */
        int[] groups=Object.getGroups(user_id3);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id2);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id3);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(group_id, groups[0]);
        assertEquals(group_id2, groups[1]);
    }
    @Test public void testGetGroupMembers(){
        /*
         * Setup
         */
        //Create Users
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        int user_id2 = Object.getNextUserID();
        Object.AddUser(user_id2, username[1], password[1], date_created[1]);
        int user_id3 = Object.getNextUserID();
        Object.AddUser(user_id3, username[2], password[2], date_created[2]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_id);
        //Add Members
        Object.addGroupMember(group_id, user_id2);
        Object.addGroupMember(group_id, user_id3);
        /*
         * Verification
         */
        int[] members=Object.getGroupMembers(group_id);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id2);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id3);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(user_id, members[0]);
        assertEquals(user_id2, members[1]);
        assertEquals(user_id3, members[2]);
    }
    
    /*
     * ---------------------------------------------------------------------------
     *                               Meeting Tests
     * ---------------------------------------------------------------------------
     */
    @Test public void testCreateMeeting(){
        /*
         * Setup
         */
        //Create Group
        int group_id = Object.nextGroupID();
        assertThat(group_id, is(not(equalTo(-1))));
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        //Create Group
        Object.CreateGroup(group_id, group_name[0], user_id);
        //Create Meeting
        int meeting_id = Object.getNextMeetingID();
        boolean shareable = false;
        Object.CreateMeeting(meeting_id, group_id, meeting_name[0], dateTime[0], shareable);
        /*
         * Verification
         */
        int storedGroup = -1;
        String storedMeeting_name = "";
        String storedMeeting_datetime = "";
        boolean storedShareable = true;
        try{
            ResultSet meeting = Object.PCDatabase.createStatement().executeQuery("select * from group_meetings where meeting_id="+meeting_id);
            meeting.next();
            storedGroup = meeting.getInt("group_id");
            storedMeeting_name = meeting.getString("meeting_name");
            storedMeeting_datetime = meeting.getString("meeting_date");
            storedShareable = meeting.getBoolean("shareable");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(group_id, storedGroup);
        assertEquals(meeting_name, storedMeeting_name);
        assertEquals(meeting_datetime, storedMeeting_datetime);
        assertEquals(shareable, storedShareable);
    }
    @Test public void testGetGroupMeetings(){
        /*
         * Setup
         */
        //Create User
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username[0], password[0], date_created[0]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name, user_id);
        //Create Meetings
        boolean shareable = false;
        int meeting_id = Object.getNextMeetingID();
        Object.CreateMeeting(meeting_id, group_id, meeting_name[0], dateTime[0], shareable);
        int meeting_id2 = Object.getNextMeetingID();
        Object.CreateMeeting(meeting_id2, group_id, meeting_name[1], dateTime[1], shareable);
        /*
         * Verification
         */
        int[] storedMeetings = Object.getGroupMeetings(group_id);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(meeting_id, storedMeetings[0]);
        assertEquals(meeting_id2, storedMeetings[1]);
    }
    /*
     * ---------------------------------------------------------------------------
     *                               Message Tests
     * ---------------------------------------------------------------------------
     */
    @Test public void testCreateMessage(){
        /*
         * Setup
         */
        //Create User
        int user_id = Object.getNextUserID();
        Object.AddUser(user_id, username, password, datecreated);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name, user_id);
        //Create Meeting
        boolean shareable = false;
        int meeting_id = Object.getNextMeetingID();
        Object.CreateMeeting(meeting_id, group_id, meeting_name[0], dateTime[0], shareable);
        //Create Message
        int message_id = Object.getNextMessageID(meeting_id);
        boolean pinned = false;
        Object.createMessage(user_id, meeting_id, message_id, message[0], dateTime[0], pinned);
        /*
         * Verification
         */
        int storedUser=-1;
        String storedMessage ="";
        String stored_datetime="";
        boolean stored_pinned = true;
        try{
            ResultSet results=Object.PCDatabase.createStatement().executeQuery("select * from messages where message_id="+message_id+" and meeting_id="+meeting_id);
            results.next();
            storedUser=results.getInt("user_id");
            storedMessage = results.getString("Body");
            stored_datetime = results.getString("created_at");
            stored_pinned = results.getBoolean("pinned");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(user_id, storedUser);
        assertEquals(message, storedMessage);
        assertEquals(message_datetime, stored_datetime);
        assertEquals(pinned, stored_pinned);
    }
}