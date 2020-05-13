package com.company.db;

import com.company.enitities.DatasetEntity;
import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.dao.DatasetDAO;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class DatasetDAODB implements DatasetDAO {

    public DatasetDAODB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DatasetEntity[] getAllDataSet() throws SelectException {
        ArrayList<DatasetEntity> datasets = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, title, filename, created_date FROM dataset "
            );
            while (resultSet.next()) {
                datasets.add(new DatasetEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return datasets.toArray(new DatasetEntity[datasets.size()]);
    }

    @Override
    public void saveDataset(DatasetEntity dataset) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO dataset (title, " +
                    "filename " +
                    ") VALUES (?, ?)");
            preparedStatement.setString(1, dataset.getTitle());
            preparedStatement.setString(2, dataset.getFilename());
//            preparedStatement.setDate(3, key.getCreatedDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateDataset(DatasetEntity dataset) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE key SET title=? " +
                    "WHERE id = ? ");
            preparedStatement.setString(1, dataset.getTitle());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteDataset(DatasetEntity dataset) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM key WHERE id = ?");
            preparedStatement.setInt(1, dataset.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }
}
