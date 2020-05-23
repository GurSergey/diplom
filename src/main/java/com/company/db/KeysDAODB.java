package com.company.db;

import com.company.dao.KeysDAO;
import com.company.enitities.*;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeysDAODB implements KeysDAO {

    public KeysDAODB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public KeyEntity[] getAllKeys() throws SelectException {
        ArrayList<KeyEntity> keys = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, name, key_str, created_date FROM key "
            );
            while (resultSet.next()) {
                keys.add(new KeyEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getTimestamp(4)));
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO key(name, " +
                    "key_str " +
                    " " +
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
            preparedStatement.setInt(2, key.getId());
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

//    @Override
//    public DatasetEntity[] getAllOpenPolls() throws SelectException {
//        ArrayList<DatasetEntity> polls = new ArrayList<>();
//        try (Connection connection = DBConnection.getConnection()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "SELECT poll.id, title, date_to, " +
//                            "start_date , COUNT(DISTINCT voter_id) AS count_voters, " +
//                            "COUNT(DISTINCT answer.id) AS count_answers " +
//                            "FROM poll LEFT JOIN question " +
//                            "ON question.poll_id = poll.id LEFT JOIN " +
//                            "variants ON " +
//                            "variants.question_id = question.id LEFT JOIN " +
//                            "answer ON answer.variant_id = variants.id " +
//                            "WHERE poll.visible = true AND current_date <= poll.date_to " +
//                            "AND current_date >= poll.start_date " +
//                            "GROUP BY poll.id; "
//            );
//            while (resultSet.next()) {
//                DatasetEntity pollEntity = new DatasetEntity();
//                pollEntity.setId(resultSet.getInt(1));
//                pollEntity.setTitle(resultSet.getString(2));
//                pollEntity.setDateTo(resultSet.getDate(3));
//                pollEntity.setStartDate(resultSet.getDate(4));
//                pollEntity.setStatistics(new ModelEntity(resultSet.getInt(5),
//                        resultSet.getInt(6)));
//                polls.add(pollEntity);
//            }
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            throw new SelectException();
//        }
//        return polls.toArray(new DatasetEntity[polls.size()]);
//    }
//
//    @Override
//    public DatasetEntity[] getAllEndedPolls() throws SelectException {
//        ArrayList<DatasetEntity> polls = new ArrayList<>();
//        try (Connection connection = DBConnection.getConnection()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "SELECT poll.id, title, date_to, " +
//                            "start_date , COUNT(DISTINCT voter_id) AS count_voters, " +
//                            "COUNT(DISTINCT answer.id) AS count_answers " +
//                            "FROM poll LEFT JOIN question " +
//                            "ON question.poll_id = poll.id LEFT JOIN " +
//                            "variants ON " +
//                            "variants.question_id = question.id LEFT JOIN " +
//                            "answer ON answer.variant_id = variants.id " +
//                            "WHERE poll.visible = true " +
//                            "AND current_date >= poll.date_to " +
//                            "GROUP BY poll.id; "
//            );
//            while (resultSet.next()) {
//                DatasetEntity pollEntity = new DatasetEntity();
//                pollEntity.setId(resultSet.getInt(1));
//                pollEntity.setTitle(resultSet.getString(2));
//                pollEntity.setDateTo(resultSet.getDate(3));
//                pollEntity.setStartDate(resultSet.getDate(4));
//                pollEntity.setStatistics(new ModelEntity(resultSet.getInt(5),
//                        resultSet.getInt(6)));
//                polls.add(pollEntity);
//            }
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            throw new SelectException();
//        }
//        return polls.toArray(new DatasetEntity[polls.size()]);
//    }
//
//    public DatasetEntity getPollResult(int id) throws SelectException {
//        DatasetEntity pollEntity = new DatasetEntity();
//        try (Connection connection = DBConnection.getConnection()) {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT title FROM poll WHERE id = ?  ");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            pollEntity.setTitle(resultSet.getString(1));
//            preparedStatement.close();
//
//            preparedStatement = connection.prepareStatement(
//                    "SELECT question.id, question, COUNT(answer.id) " +
//                    "FROM question LEFT JOIN variants ON " +
//                    "question.id = variants.question_id " +
//                    "LEFT JOIN answer ON " +
//                    "variants.id = answer.variant_id " +
//                    "WHERE poll_id = ? GROUP BY question.id " );
//            preparedStatement.setInt(1, id);
//            resultSet = preparedStatement.executeQuery();
//
//            HashMap<Integer, QuestionEntity> questionEntityHashMap= new HashMap<Integer, QuestionEntity>();
//            while (resultSet.next()) {
//                QuestionEntity questionEntity = new QuestionEntity();
//                questionEntity.setId(resultSet.getInt(1));
//                questionEntity.setQuestion(resultSet.getString(2));
//                questionEntity.setQuestionsStatistics(new QuestionStatisticsEntity(resultSet.getInt(3)));
//                questionEntityHashMap.put(questionEntity.getId(), questionEntity);
//            }
//            preparedStatement.close();
//            if(questionEntityHashMap.keySet().size()!=0) {
//                String in = Arrays.toString(questionEntityHashMap.keySet().toArray()).
//                        replaceAll("[\\[\\]]", "");
//                Statement statement = connection.createStatement();
//                resultSet = statement.executeQuery(
//                        "SELECT variants.id, variants.text, variants.question_id, " +
//                        "COUNT(answer.id) AS count_answers_variant " +
//                        "FROM variants LEFT JOIN answer ON " +
//                        "variants.id = answer.variant_id WHERE question_id IN ("+in+") GROUP BY variants.id");
//
//                while (resultSet.next()) {
//                    KeyEntity variantEntity = new KeyEntity();
//                    variantEntity.setId(resultSet.getInt(1));
//                    variantEntity.setText(resultSet.getString(2));
//                    variantEntity.setQuestionId(resultSet.getInt(3));
//                    variantEntity.setVariantStatistics(new VariantStatisticsEntity(resultSet.getInt(4)));
//                    questionEntityHashMap.get(variantEntity.getQuestionId()).getVariants().add(variantEntity);
//            }
//            preparedStatement.close();
//            }
//            pollEntity.setQuestions(new ArrayList<QuestionEntity>(questionEntityHashMap.values()));
//
//        } catch (SQLException e) {
//            throw new SelectException();
//        }
//        return pollEntity;
//    }
//
//    public DatasetEntity getPollWithAnswersUser(int pollId, int userId) throws SelectException {
//        DatasetEntity pollEntity = new DatasetEntity();
//        try (Connection connection = DBConnection.getConnection()) {
//
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT title FROM poll WHERE id = ?  ");
//            preparedStatement.setInt(1, pollId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            pollEntity.setTitle(resultSet.getString(1));
//            preparedStatement.close();
//
//            preparedStatement = connection.prepareStatement(
//                    "SELECT question.id, question, variant_id, " +
//                    "variants.text, answer.answer_date " +
//                    "FROM question LEFT JOIN variants ON " +
//                    "question.id = variants.question_id " +
//                    "LEFT JOIN answer ON " +
//                    "variants.id = answer.variant_id " +
//                    "WHERE poll_id = ? AND  answer.voter_id = ? ");
//            preparedStatement.setInt(1, pollId);
//            preparedStatement.setInt(2, userId);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                QuestionEntity questionEntity = new QuestionEntity();
//                questionEntity.setId(resultSet.getInt(1));
//                questionEntity.setQuestion(resultSet.getString(2));
//
//                KeyEntity variantEntity = new KeyEntity();
//                variantEntity.setId(resultSet.getInt(3));
//                variantEntity.setText(resultSet.getString(4));
//
//                AnswerEntity answerEntity = new AnswerEntity();
//                answerEntity.setAnswerDate(resultSet.getDate(5));
//
//                questionEntity.getVariants().add(variantEntity);
//                variantEntity.setAnswer(answerEntity);
//
//                pollEntity.getQuestions().add(questionEntity);
//            }
//
//        } catch (SQLException e){
//            throw new SelectException();
//        }
//        return pollEntity;
//    }
//
//    public DatasetEntity[] getPollsByUser(int userId) throws SelectException {
//        ArrayList<DatasetEntity> polls = new ArrayList<>();
//        try (Connection connection = DBConnection.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT DISTINCT poll.id, poll.title, poll.visible, poll.date_to, " +
//                            "poll.start_date, poll.create_date  " +
//                            "FROM poll JOIN question ON " +
//                            "question.poll_id = poll.id JOIN variants ON " +
//                            "question.id = variants.question_id " +
//                            "JOIN answer ON " +
//                            "variants.id = answer.variant_id WHERE " +
//                            "answer.voter_id = ? ");
//            preparedStatement.setInt(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                polls.add(new DatasetEntity(resultSet.getInt(1), resultSet.getString(2),
//                        resultSet.getBoolean(3), resultSet.getDate(4),
//                        resultSet.getDate(5), resultSet.getDate(6)));
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e){
//            throw new SelectException();
//        }
//        return polls.toArray(new DatasetEntity[polls.size()]);
//    }


}
