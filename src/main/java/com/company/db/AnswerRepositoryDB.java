package com.company.db;

import com.company.enitities.AnswerEntity;
import com.company.exceptions.InsertException;
import com.company.repositories.AnswerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerRepositoryDB implements AnswerRepository {
    public AnswerRepositoryDB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAnswer(AnswerEntity answer) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO answer(voter_id, " +
                    "variant_id, " +
                    "answer_date " +
                    ") VALUES (?, ?, ?)");
            preparedStatement.setInt(1, answer.getVoterId());
            preparedStatement.setInt(2, answer.getVariantId());
            preparedStatement.setDate(3, answer.getAnswerDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }
}
