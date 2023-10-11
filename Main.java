package com.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ReflectiveOperationException {

        UserDAO myDao = new UserDAO();

        //User myUser = new User("abdou","bkh", "mymail@gmail.com");
        //myDao.connectDB();
        //myDao.addUser(myUser);
        myDao.updateUser("jdid", "sadiki", "jdidbrk@gmail.com",1);
        myDao.deleteUser(1);
        User isUser = new User("yes","two","yeshe");
        //myDao.addUser(isUser);
        User lUser = new User("ee","teewo","yeeeshe");
        //myDao.addUser(lUser);
        //myDao.getAllUsers();
        myDao.getUsersByFirstName("yes");
        //System.out.println(myUser.getEmail());
        
        

       
    }
}