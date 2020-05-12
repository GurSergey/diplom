package com.company.db;


import com.company.enitities.KeyEntity;
import com.company.enitities.ModelEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.dao.VariantsDAO;

import java.sql.*;
import java.util.ArrayList;

public class ModelsDAODB implements VariantsDAO {

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
    public ModelEntity[] getAllModels(int questionId) throws SelectException {
        ArrayList<ModelEntity> variants = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title, dataset_id " +
                            "FROM model WHERE question_id = ? JOIN dataset ON dataset_id = dataset.id "
            );
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                variants.add(new KeyEntity(resultSet.getInt(1), resultSet.getInt(2), null,
                        resultSet.getString(3)));
            }
            preparedStatement.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return variants.toArray(new KeyEntity[variants.size()]);
    }

    @Override
    public void saveVariant(ModelEntity model) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO variants(question_id, " +
                    "text) " +
                    " VALUES (?, ?)");
            preparedStatement.setInt(1, variant.getQuestionId());
            preparedStatement.setString(2, variant.getText());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateModel(ModelEntity model) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE variants SET  " +
                    "text = ?  WHERE id = ?");
            preparedStatement.setString(1, variant.getText());
            preparedStatement.setInt(2, variant.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteModel(ModelEntity model) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM variants WHERE  " +
                    "id = ? ");
            preparedStatement.setInt(1, variant.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }
}
