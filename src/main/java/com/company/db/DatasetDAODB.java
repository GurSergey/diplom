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
import java.util.StringJoiner;

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
                    "SELECT id, title, filename, created_date, checking, is_correct FROM dataset  "
            );
            while (resultSet.next()) {
                datasets.add(new DatasetEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getBoolean(5),
                        resultSet.getBoolean(6), resultSet.getTimestamp(4)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return datasets.toArray(new DatasetEntity[datasets.size()]);
    }

    @Override
    public DatasetEntity getById(int id) throws SelectException {
        ArrayList<DatasetEntity> datasets = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, filename, created_date, checking, is_correct FROM dataset WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                datasets.add(new DatasetEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),resultSet.getBoolean(5),
                        resultSet.getBoolean(6), resultSet.getTimestamp(4)));
            }
        } catch (SQLException e) {
            throw new SelectException();
        }
        return datasets.get(0);
    }

    @Override
    public void saveDataset(DatasetEntity dataset, boolean normalize) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO dataset (title, " +
                    "filename " +
                    ") VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dataset.getTitle());
            preparedStatement.setString(2, dataset.getFilename());
//            preparedStatement.setDate(3, key.getCreatedDate());
//            preparedStatement.execute();
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }
            int datasetId;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    datasetId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException();
                }
            }
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("INSERT INTO check_dataset(" +
                    " dataset_id, normalize) " +
                    "VALUES (?, ?)");
            preparedStatement.setInt( 1, datasetId);
            preparedStatement.setBoolean( 2, normalize);
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void mergeDataset(DatasetEntity dataset, int[] ids) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO dataset (title, " +
                            "filename " +
                            ") VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dataset.getTitle());
            preparedStatement.setString(2, dataset.getFilename());
//            preparedStatement.setDate(3, key.getCreatedDate());
//            preparedStatement.execute();
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }
            int datasetId;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    datasetId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException();
                }
            }
            String datasets = "";
            StringJoiner joiner = new StringJoiner("|");
            for (int i = 0; i < ids.length; i++)
                joiner.add(""+ids[i]);
            datasets = joiner.toString();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("INSERT INTO merge_dataset(" +
                    " dataset_id, source_datasets) " +
                    "VALUES (?, ?)");
            preparedStatement.setInt( 1, datasetId);
            preparedStatement.setString( 2, datasets);
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateDataset(DatasetEntity dataset) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE dataset SET title=? " +
                    "WHERE id = ? ");
            preparedStatement.setString(1, dataset.getTitle());
            preparedStatement.setInt(2, dataset.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteDataset(DatasetEntity dataset) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM dataset WHERE id = ?");
            preparedStatement.setInt(1, dataset.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }
}
