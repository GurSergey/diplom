package com.company.db;


import com.company.enitities.VariantEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.VariantsRepository;

import java.sql.*;
import java.util.ArrayList;

public class VariantsRepositoryDB implements VariantsRepository {

    public VariantsRepositoryDB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public VariantEntity[] getAllVariantsByQuestionId(int questionId) throws SelectException {
        ArrayList<VariantEntity> variants = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, question_id, text " +
                            "FROM variants WHERE question_id = ? "
            );
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                variants.add(new VariantEntity(resultSet.getInt(1), resultSet.getInt(2), null,
                        resultSet.getString(3)));
            }
            preparedStatement.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return variants.toArray(new VariantEntity[variants.size()]);
    }

    @Override
    public void saveVariant(VariantEntity variant) throws InsertException {
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
    public void updateVariant(VariantEntity variant) throws UpdateException {
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
    public void deleteVariant(VariantEntity variant) throws DeleteException {
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
