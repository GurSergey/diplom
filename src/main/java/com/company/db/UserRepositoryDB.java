package com.company.db;

import com.company.enitities.VoterEntity;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.UserRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryDB  implements UserRepository {

    public UserRepositoryDB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public VoterEntity[] getAllUsers() throws SelectException{
        ArrayList<VoterEntity> voters = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, login, registration_date, name, phone, password " +
                            "FROM voter");
            while (resultSet.next()) {
                voters.add(new VoterEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return voters.toArray(new VoterEntity[voters.size()]);
    }


    public VoterEntity getUserByLoginPassword(String login, String password) throws SelectException {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, login, registration_date,  name, phone, password" +
                            " FROM voter WHERE login = ? and password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ) {
                return new VoterEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        } catch (SQLException e){
            throw new SelectException();
        }
        return null;
    }

    public void createUser(VoterEntity user) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO voter(registration_date, " +
                    "name, " +
                    "password, " +
                    "phone, " +
                    "login) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setDate(1, user.getRegistrationDate());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    public VoterEntity getVoterById(int id) throws SelectException {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, login, registration_date, name, phone, password" +
                            " FROM voter WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ) {
                return new VoterEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }
        } catch (SQLException e){
            throw new SelectException();
        }
        return null;
    }

    public VoterEntity updateVoter(VoterEntity voter) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE voter SET name = ?, phone = ?, password = ?" +
                            " WHERE id = ?");
            preparedStatement.setString(1, voter.getName());
            preparedStatement.setString(2, voter.getPhone());
            preparedStatement.setString(3, voter.getPassword());
            preparedStatement.setInt(4, voter.getId());
            preparedStatement.execute();

        } catch (SQLException e){
            throw new UpdateException();
        }
        return null;
    }
}
