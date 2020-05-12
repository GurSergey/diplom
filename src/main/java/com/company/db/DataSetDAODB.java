package com.company.db;

import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.dao.DatasetDAO;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

import java.sql.*;
import java.util.ArrayList;

public class DataSetDAODB implements DatasetDAO {

    public DataSetDAODB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public KeyEntity[] getAllDataSet() throws SelectException {
        ArrayList<KeyEntity> keys = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, name, key_str, created_date FROM key "
            );
            while (resultSet.next()) {
                keys.add(new KeyEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return keys.toArray(new KeyEntity[keys.size()]);
    }

    @Override
    public void saveKey(KeyEntity key) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO poll(name, " +
                    "key_str " +
                    ") VALUES (?, ?)");
            preparedStatement.setString(1, key.getName());
            preparedStatement.setString(2, key.getKeyStr());
//            preparedStatement.setDate(3, key.getCreatedDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateKey(KeyEntity key) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE key SET name=? " +
                    "WHERE id = ? ");
            preparedStatement.setString(1, key.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteKey(KeyEntity poll) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM key WHERE id = ?");
            preparedStatement.setInt(1, poll.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }
}
