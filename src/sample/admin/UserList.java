package sample.admin;

import java.util.ArrayList;
import java.util.List;

/**
 * UserList class for storing userList information
 */
public class UserList {
    /**
     * userList of user list
     */
    private List<User> userList;

    /**
     * This method forms userList
     */
    public UserList(){ userList = new ArrayList<>(); }

    /**
     * This method to add users to the user list
     * @param user from User
     */
    public void addUserList(User user){ userList.add(user);}

    /**
     * Get userList
     * @return userList
     */
    public List<User> getUserList(){ return userList; }
}
