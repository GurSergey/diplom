package com.company.db;

import com.company.enitities.PollEntity;
import com.company.enitities.QuestionEntity;
import com.company.enitities.VariantEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.QuestionsRepository;

import java.sql.*;
import java.util.ArrayList;

public class QuestionsRepositoryDB implements QuestionsRepository {

    public QuestionsRepositoryDB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public QuestionEntity[] getAllQuestionsByPollId(int pollId) throws SelectException {
        ArrayList<QuestionEntity> questions = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, poll_id, question, created_date " +
                            "FROM question WHERE poll_id = ? "
            );
            preparedStatement.setInt(1, pollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                questions.add(new QuestionEntity(resultSet.getInt(1),
                        resultSet.getInt(2), null,
                        resultSet.getString(3), resultSet.getDate(4)));
            }
            preparedStatement.close();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return questions.toArray(new QuestionEntity[questions.size()]);
    }

    @Override
    public void saveQuestion(QuestionEntity question) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO question(poll_id, " +
                    "question, " +
                    "created_date) " +
                   " VALUES (?, ?, ?)");
            preparedStatement.setInt(1, question.getPollId());
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.setDate(3, question.getCreatedDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updateQuestion(QuestionEntity question) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE question SET question=?, " +
                    "created_date = ? " +
                    "WHERE id = ? ");
            preparedStatement.setString(1, question.getQuestion());
            preparedStatement.setDate(2, question.getCreatedDate());
            preparedStatement.setInt(3, question.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deleteQuestion(QuestionEntity question) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM question WHERE id = ?");
            preparedStatement.setInt(1, question.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }

    public QuestionEntity getQuestionForUser(int pollId, int userId) throws SelectException{
         try (Connection connection = DBConnection.getConnection()){
             PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT DISTINCT poll.id, poll.title, question.question, question.id FROM poll " +
             "LEFT JOIN question ON poll.id=question.poll_id " +
             "LEFT JOIN variants ON " +
             "question.id = variants.question_id LEFT JOIN answer " +
             "ON answer.variant_id = variants.id WHERE poll.id=? AND question.id NOT IN " +
             "(SELECT question.id FROM question JOIN variants ON variants.question_id = question.id " +
             "JOIN answer ON variants.id=answer.variant_id  WHERE answer.voter_id=?) LIMIT 1");
             preparedStatement.setInt(1, pollId);
             preparedStatement.setInt(2, userId);
             ResultSet resultSet = preparedStatement.executeQuery();
             if( resultSet.next()) {
                 QuestionEntity question = new QuestionEntity();
                 PollEntity poll = new PollEntity();
                 poll.setId(resultSet.getInt(1));
                 poll.setTitle(resultSet.getString(2));
                 question.setQuestion(resultSet.getString(3));
                 question.setId(resultSet.getInt(4));
                 question.setPoll(poll);
                 ArrayList<VariantEntity> variants = new ArrayList<>();

                 preparedStatement = connection.prepareStatement(
                         "SELECT id, question_id, text " +
                                 "FROM variants WHERE question_id = ? "
                 );
                 preparedStatement.setInt(1, question.getId());
                 resultSet = preparedStatement.executeQuery();
                 while (resultSet.next()) {
                     variants.add(new VariantEntity(resultSet.getInt(1), resultSet.getInt(2), null,
                             resultSet.getString(3)));
                 }
                 question.setVariants(variants);
                 preparedStatement.close();
                 resultSet.close();
                return  question;
             }
         } catch (SQLException e) {
             throw new SelectException();
         }
         return null;
    }


}
