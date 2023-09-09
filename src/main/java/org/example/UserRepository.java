package org.example;

import java.sql.*;

public class UserRepository {
    Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User save(User user) throws SQLException {
        String sql = "INSERT INTO users(userName, password, signUp) values (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,user.getUserName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3, user.getSingUp());
        preparedStatement.executeUpdate();
        if (preparedStatement.getGeneratedKeys().next()){
            user.setId(preparedStatement.getGeneratedKeys().getInt(1));
        }
        return user;
    }

    public void update(User user) throws SQLException {
        String sql = "UPDATE users set password=?, userName=? where id=? ";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,user.getPassword());
        preparedStatement.setString(2,user.getUserName());
        preparedStatement.setLong(3,user.getId());

        int result = preparedStatement.executeUpdate();
        if (result==1){
            System.out.println("information update");
        }

    }
    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM users where id=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,user.getId());
        int result = preparedStatement.executeUpdate();
        if (result==1){
            System.out.println("user deleted");
        }

    }
    public User load(long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        if (resultSet.next()){
            user.setId(resultSet.getLong(1));
            user.setUserName(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setSingUp(resultSet.getString(4));
        }
        return user;
    }
    public User[] loadAll() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
        //System.out.println(resultSet.last());
        User[] users = new User[resultSet.getRow()];
        resultSet.beforeFirst();
        int counter = 0;
        while (resultSet.next()){
            users[counter]= new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            counter++;
//            for (int i = 0; i < users.length; i++) {
//                users[i]=new User(
//                        resultSet.getLong(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4)
//                );
 //           }
        } //end while
        return users;

    }
    public void saveAll(User[]users) throws SQLException {
        String sql = "INSERT INTO users(userName, password, signUp) values(?,?,?)";
        for (int i = 0; i < users.length-1; i++) {
            sql = sql.concat(",(?,?,?)");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int i = 0;
        for (User u:users) {
            preparedStatement.setString(i+1,u.getUserName());
            preparedStatement.setString(i+2,u.getPassword());
            preparedStatement.setString(i+3, u.getSingUp());
            i+=3;
        }
        preparedStatement.executeUpdate();




    }
}
