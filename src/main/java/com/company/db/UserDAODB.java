package com.company.db;

import com.company.enitities.UserEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.dao.UserDAO;

import java.sql.*;
import java.util.ArrayList;

public class UserDAODB implements UserDAO {

    public UserDAODB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserEntity[] getAllUsers() throws SelectException{
        ArrayList<UserEntity> users = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, login, registration_date, password " +
                            "FROM \"user\" ");
            while (resultSet.next()) {
                users.add(new UserEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4)
                ));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return users.toArray(new UserEntity[users.size()]);
    }


    public UserEntity getUserByLoginPassword(String login, String password) throws SelectException {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, login, registration_date,  name, phone, password" +
                            " FROM \"user\" WHERE login = ? and password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ) {
                return new UserEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e){
            throw new SelectException();
        }
        return null;
    }

    public void createUser(UserEntity user) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"user\" ( " +
                    "login, " +
                    "password) " +
            " VALUES ( ?, ?)");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    public void deleteUser(UserEntity user) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"user\" WHERE id = ? " +
                    "");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }

//    public UserEntity getVoterById(int id) throws SelectException {
//        try (Connection connection = DBConnection.getConnection()) {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT id, login, registration_date, name, phone, password" +
//                            " FROM voter WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next() ) {
//                return new UserEntity(
//                        resultSet.getInt(1),
//                        resultSet.getString(2),
//                        resultSet.getDate(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6)
//                );
//            }
//        } catch (SQLException e){
//            throw new SelectException();
//        }
//        return null;
//    }
//
//    public UserEntity updateVoter(UserEntity voter) throws UpdateException {
//        try (Connection connection = DBConnection.getConnection()) {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "UPDATE voter SET name = ?, phone = ?, password = ?" +
//                            " WHERE id = ?");
//            preparedStatement.setString(1, voter.getName());
//            preparedStatement.setString(2, voter.getPhone());
//            preparedStatement.setString(3, voter.getPassword());
//            preparedStatement.setInt(4, voter.getId());
//            preparedStatement.execute();
//
//        } catch (SQLException e){
//            throw new UpdateException();
//        }
//        return null;
//    }
}
