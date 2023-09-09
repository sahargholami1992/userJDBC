package org.example;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

public class ApplicationConnection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver"); // this loads postgres driver class for JDK8
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres"); //STEP1
        //Statement statement = connection.createStatement(); //ignore this line
//        String createTableUsers = "CREATE TABLE Users(" +    //STEP 2
//                "id serial," +
//                "userName VARCHAR(50)," +
//                "password VARCHAR(50)," +
//                "signUp VARCHAR(10)"+
//                ")";
//        String sample = """
//                CREATE TABLE sample(
//                id serial,
//                age int
//                )
//                """;
//        PreparedStatement preparedStatement = connection.prepareStatement(createTableUsers); //STEP3
//        preparedStatement.execute(); //STEP4

        //statement.execute(createTableUsers); // ignore this line
        UserRepository userRepository = new UserRepository(connection);
//        User user = new User("samaneh","123","12-2-10");
//        userRepository.save(user);
        //User user2 = new User("sahar","123","12-3-10");
        //userRepository.save(user2);
      //  User user = userRepository.load(3);
       // System.out.println(user);
       // User[] users=userRepository.loadAll();
        //System.out.println(Arrays.toString(users));
        User[] user = new User[3];
        for (int i = 0; i < 3; i++) {
            user[i]=new User("aaa","123","12-22-10");
        }
        userRepository.saveAll(user);




    }

}
