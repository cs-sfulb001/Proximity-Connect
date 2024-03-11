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
    static DatabaseQueries Object=new DatabaseQueries();
    String username[], password[], email[], phonenumber[], date_created[], group_name[], dateTime[], meeting_name[], message[];
    int user_ids[];
    @Before public void init(){
        username = new String[] {"test", "testUserName", "Pineapple"};
        password = new String[] {"password","1234", "OneTwoThree"};
        email = new String[]{"Testemail@gmail.com", "Fakeemail@hotmail.com", "Someonesemail@yahoo.com"};
        phonenumber = new String[]{"(757) 213 3521", "(123) 456 7890", "(964) 456 1348"};
        date_created = new String[] {"2024-02-11","2024-02-20","2024-03-09"};
        group_name = new String[] {"testGroup", "GroupName", "The Best Group"};
        dateTime = new String[] {"2024-03-03 12:47:32","2024-03-03 13:48:05", "2024-03-09 02:53:53"};
        meeting_name = new String[] {"Test Meeting", "The Next Meeting", "The Worst Meeting"};
        message = new String[] {"This is a test message that should not be bound by length in theory. Lets test that theory and see if this will fit.", "Simple test message", "Test123"};
        user_ids = new int[] {email[0].hashCode(), email[1].hashCode(), email[2].hashCode()};
    }
    /*
     * ---------------------------------------------------------------------------
     *                               User Tests
     * ---------------------------------------------------------------------------
     */
    //Depericated
    @Test public void testNextUserID(){
        /*
        //Gathering ID's
        assertThat(Object.getNextUserID(), is(not(equalTo(-1))));
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        Object.AddUser(email[1], phonenumber[1], username[1], password[1], date_created[1]);
        Object.AddUser(email[2], phonenumber[2], username[2], password[2], date_created[2]);
        int nextID = Object.getNextUserID();
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_id);
        }
        catch(Exception e){
            System.out.println("testNextUserID Error:\n    "+e);
        }
        assertEquals(user_id+1, nextID);
        */
        assert(true);
    }
    //Insure the user is created correctly
    @Test public void testAddUser(){
        /*
         * Setup
         */
        //Create User
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        /*
         * Verification
         */
        int testID = -1;
        String testUN = "";
        String testPhoneNumber = "";
        String testPW = "";
        String testDate = "";
        String defaultFName="";
        String defaultLName="";
        String defaultUIColor="";
        String defaultFont="";
        String defaultPP="";
        String defaultPrimaryLanguage="";
        try{
            ResultSet newUser = Object.PCDatabase.createStatement().executeQuery("select * from users where email=\'"+email[0]+"\'");
            newUser.next();
            testUN = newUser.getString("username");
            testPW = newUser.getString("user_password");
            testDate = newUser.getString("created_at");
            testID = newUser.getInt("user_id");
            testPhoneNumber = newUser.getString("phonenumber");
            ResultSet defaultSettings = Object.PCDatabase.createStatement().executeQuery("select * from user_settings where user_id="+testID);
            defaultSettings.next();
            defaultFName = defaultSettings.getString("first_name");
            defaultLName = defaultSettings.getString("last_name");
            defaultUIColor = defaultSettings.getString("UI_Color");
            defaultFont = defaultSettings.getString("UI_Font");
            defaultPP = defaultSettings.getString("profile_picture");
            defaultPrimaryLanguage = defaultSettings.getString("primary_language");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testAddUser Error:\n    "+e);
        }
        //Test for expected information with the userid (Would fail to create the user if the user_id is taken)
        assertEquals(username[0], testUN);
        assertEquals(password[0], testPW);
        assertEquals(date_created[0], testDate);
        assertEquals(phonenumber[0], testPhoneNumber);
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
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        String newpassword = "1234";
        Object.updatePassword(user_ids[0], password[0], password[1]);
        int TestID = -1;
        String testUN = "";
        String testPW = "";
        String testDate = "";
        try{
            ResultSet newUser = Object.PCDatabase.createStatement().executeQuery("select * from users where user_id="+user_ids[0]);
            newUser.next();
            testUN = newUser.getString("username");
            testPW = newUser.getString("user_password");
            testDate = newUser.getString("created_at");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testUpdatePassword Error:\n    "+e);
        }
        assertEquals(username[0], testUN);
        assertEquals(password[1], testPW);
        assertEquals(date_created[0], testDate);
    }
    @Test public void testUpdateUserSettings(){
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        // No future tests depend on settings thus only needed here
        String newFName="Pine";
        String newLName="Apple";
        String newUIColor="(0, 255, 0)";
        String newFont="Comic Sans";
        String newPrimaryLanguage="Spanish";
        Object.updateUserSettings(user_ids[0], newUIColor, newFont, newFName, newLName, newPrimaryLanguage);
        String storedFName="";
        String storedLName="";
        String storedUIColor="";
        String storedFont="";
        String storedPP="";
        String storedPrimaryLanguage="";
        try{
            ResultSet Settings = Object.PCDatabase.createStatement().executeQuery("select * from user_settings where user_id="+user_ids[0]);
            Settings.next();
            storedFName = Settings.getString("first_name");
            storedLName = Settings.getString("last_name");
            storedUIColor = Settings.getString("UI_Color");
            storedFont = Settings.getString("UI_Font");
            storedPP = Settings.getString("profile_picture");
            storedPrimaryLanguage = Settings.getString("primary_language");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
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
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        int loginID = Object.Login(email[0], password[0]);
        String testUN = "";
        String testPW = "";
        String testDate = "";
        try{
            ResultSet newUser = Object.PCDatabase.createStatement().executeQuery("select * from users where user_id="+loginID);
            newUser.next();
            testUN = newUser.getString("username");
            testPW = newUser.getString("user_password");
            testDate = newUser.getString("created_at");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testLogin Error:\n    "+e);
        }
        assertEquals(user_ids[0], loginID);
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
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        int nextID = Object.nextGroupID();
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testNextUserID Error:\n    "+e);
        }
        assertEquals(group_id+1, nextID);
    }
    
    @Test public void testCreateGroup(){
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        String Storedgroup_name = "";
        int group_Creator = -1;
        try{
            ResultSet GroupQuery = Object.PCDatabase.createStatement().executeQuery("select * from user_group where group_id="+group_id);
            GroupQuery.next();
            Storedgroup_name=GroupQuery.getString("group_name");
            group_Creator=GroupQuery.getInt("group_creator");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testNextUserID Error:\n    "+e);
        }
        assertEquals(group_name[0], Storedgroup_name);
        assertEquals(user_ids[0], group_Creator);
    }
    
    @Test public void testAddGroupMember(){
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        Object.AddUser(email[1], phonenumber[1], username[1], password[1], date_created[1]);
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        Object.addGroupMember(group_id, user_ids[1]);
        int storedGroup=-1;
        int storedMember1=-1;
        int storedMember2=-1;
        try{
            ResultSet Groups = Object.PCDatabase.createStatement().executeQuery("select * from user_group where group_creator="+user_ids[0]);
            Groups.next();
            storedGroup=Groups.getInt("group_id");
            ResultSet Members = Object.PCDatabase.createStatement().executeQuery("select * from group_members where group_id="+group_id);
            Members.next();
            storedMember1=Members.getInt("user_id");
            Members.next();
            storedMember2=Members.getInt("user_id");
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[1]);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(group_id, storedGroup);
        assertEquals(user_ids[0], storedMember1);
        assertEquals(user_ids[1], storedMember2);
    }
    @Test public void testGetGroups(){
        /*
         * Setup
         */
        //Create Users
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        Object.AddUser(email[1], phonenumber[1], username[1], password[1], date_created[1]);
        Object.AddUser(email[2], phonenumber[2], username[2], password[2], date_created[2]);
        int group_id = Object.nextGroupID();
        //Create Groups
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        int group_id2 = Object.nextGroupID();
        Object.CreateGroup(group_id2, group_name[1], user_ids[1]);
        //Add members to groups
        Object.addGroupMember(group_id, user_ids[2]);
        Object.addGroupMember(group_id2, user_ids[2]);
        /*
         * Verification
         */
        int[] groups=Object.getGroups(user_ids[2]);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[1]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[2]);
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
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        Object.AddUser(email[1], phonenumber[1], username[1], password[1], date_created[1]);
        Object.AddUser(email[2], phonenumber[2], username[2], password[2], date_created[2]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        //Add Members
        Object.addGroupMember(group_id, user_ids[1]);
        Object.addGroupMember(group_id, user_ids[2]);
        /*
         * Verification
         */
        int[] members=Object.getGroupMembers(group_id);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[1]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[2]);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if( user_ids[i]==members[j]){
                    break;
                }
                else if(j==2){
                    assert(false);
                }
            }
        }
    }
    @Test public void testRemoveUserFromGroup(){
        
        /*
         * Setup
         */
        //Create Users
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        Object.AddUser(email[1], phonenumber[1], username[1], password[1], date_created[1]);
        Object.AddUser(email[2], phonenumber[2], username[2], password[2], date_created[2]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        //Add Members
        Object.addGroupMember(group_id, user_ids[1]);
        Object.addGroupMember(group_id, user_ids[2]);
        /*
         * Verification
         */
        Object.removeUserFromGroup(group_id, user_ids[0]);
        Object.removeUserFromGroup(group_id, user_ids[1]);
        int[] members = Object.getGroupMembers(group_id);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[1]);
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[2]);
        }
        catch(Exception e){
            System.out.println("testRemoveGroupMember Error:\n    "+e);
        }
        if((user_ids[0]==members[0]||user_ids[0]==members[1])&&(user_ids[2]==members[0]||user_ids[2]==members[1]))
            assert(true);
        else
            assert(false);
    }
    @Test public void testGetGroupName(){
        /*
         * Setup
         */
        //Create Users
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        /*
         * Verification
         */
        String storedName = Object.getGroupName(group_id);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testGetGroupName Error:\n    "+e);
        }
        assertEquals(group_name[0], storedName);
    }
    @Test public void testGetGroupCreator(){
        /*
         * Setup
         */
        //Create Users
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        /*
         * Verification
         */
        int group_creator = Object.getGroupCreator(group_id);
        try{
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testGetGroupName Error:\n    "+e);
        }
        assertEquals(user_ids[0], group_creator);
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
        //Create Users
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
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
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(group_id, storedGroup);
        assertEquals(meeting_name[0], storedMeeting_name);
        assertEquals(dateTime[0], storedMeeting_datetime);
        assertEquals(shareable, storedShareable);
    }
    @Test public void testGetGroupMeetings(){
        /*
         * Setup
         */
        //Create User
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
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
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
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
        Object.AddUser(email[0], phonenumber[0], username[0], password[0], date_created[0]);
        //Create Group
        int group_id = Object.nextGroupID();
        Object.CreateGroup(group_id, group_name[0], user_ids[0]);
        //Create Meeting
        boolean shareable = false;
        int meeting_id = Object.getNextMeetingID();
        Object.CreateMeeting(meeting_id, group_id, meeting_name[0], dateTime[0], shareable);
        //Create Message
        int message_id = Object.getNextMessageID(meeting_id);
        boolean pinned = false;
        Object.createMessage(user_ids[0], meeting_id, message_id, message[0], dateTime[0], pinned);
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
            Object.PCDatabase.createStatement().execute("delete from users where user_id="+user_ids[0]);
        }
        catch(Exception e){
            System.out.println("testAddGroupMember Error:\n    "+e);
        }
        assertEquals(user_ids[0], storedUser);
        assertEquals(message[0], storedMessage);
        assertEquals(dateTime[0], stored_datetime);
        assertEquals(pinned, stored_pinned);
    }
}
