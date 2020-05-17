package com.company.db;


import com.company.dao.ModelsDAO;
import com.company.enitities.DatasetEntity;
import com.company.enitities.ModelEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

import java.sql.*;
import java.util.ArrayList;

public class ModelsDAODB implements ModelsDAO {

    public ModelsDAODB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void saveQueue(QueueEntity queueEntity) throws InsertException {
//        try (Connection connection = DBConnection.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO queue_task(id, " +
//                    "completed_learn, " +
//                    "model_id) " +
//                    " VALUES (?, ?, ?)");
//            preparedStatement.setInt(1, queueEntity.getId());
//            preparedStatement.setBoolean(2, false);
//            preparedStatement.setInt(3, queueEntity.getModel().getId());
//            preparedStatement.execute();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            throw new InsertException();
//        }
//    }

    @Override
    public ModelEntity[] getAllModels() throws SelectException {
        ArrayList<ModelEntity> models = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT model.id, model.title, queue_task.completed_learn, dataset.title, model.created_date, " +
                            "queue_task.progress " +
                            "FROM model JOIN dataset ON dataset_id = dataset.id JOIN queue_task " +
                            "ON queue_task.model_id = model.id"
            );
            while (resultSet.next()) {
                models.add(new ModelEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return models.toArray(new ModelEntity[models.size()]);
    }

    @Override
    public ModelEntity[] getAllCompletedModels() throws SelectException {
        ArrayList<ModelEntity> models = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT model.id, model.title, queue_task.completed_learn, dataset.title, model.created_date, " +
                            "queue_task.progress " +
                            "FROM model JOIN dataset ON dataset_id = dataset.id JOIN queue_task " +
                            "ON queue_task.model_id = model.id WHERE queue_task.completed_learn = true"
            );
            while (resultSet.next()) {
                models.add(new ModelEntity(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getString(4),
                        resultSet.getDate(5),
                        resultSet.getInt(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return models.toArray(new ModelEntity[models.size()]);
    }

    @Override
    public void saveModel(ModelEntity model, DatasetEntity dataset) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO model(title, " +
                    "dataset_id) " +
                    "VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, model.getTitle());
            preparedStatement.setInt(2, dataset.getId());
//            preparedStatement.execute();
//            preparedStatement.close();
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }
            int modelId;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    modelId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException();
                }
            }
            preparedStatement.close();
            preparedStatement = connection.prepareStatement("INSERT INTO queue_task(completed_learn, model_id) " +
                    "VALUES (?, ?)");
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt( 2, modelId);
            preparedStatement.execute();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateModel(ModelEntity model) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE models SET  " +
                    "title = ?  WHERE id = ?");
            preparedStatement.setString(1, model.getTitle());
            preparedStatement.setInt(2, model.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteModel(ModelEntity model) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM models WHERE  " +
                    "id = ? ");
            preparedStatement.setInt(1, model.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }
}