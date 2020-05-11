package com.company.db;

import com.company.enitities.*;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.PollsRepository;
import com.company.repositories.QuestionsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PollsRepositoryDB implements PollsRepository {

    public PollsRepositoryDB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PollEntity[] getAllPolls() throws SelectException {
        ArrayList<PollEntity> polls = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, title, visible, date_to, " +
                    "start_date, create_date FROM poll"
            );
            while (resultSet.next()) {
                polls.add(new PollEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getDate(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return polls.toArray(new PollEntity[polls.size()]);
    }

    @Override
    public void savePoll(PollEntity poll) throws InsertException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO poll(title, " +
                    "visible, " +
                    "date_to, " +
                    "start_date, " +
                    "create_date) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, poll.getTitle());
            preparedStatement.setBoolean(2, poll.isVisible());
            preparedStatement.setDate(3, poll.getDateTo());
            preparedStatement.setDate(4, poll.getStartDate());
            preparedStatement.setDate(5, poll.getCreateDate());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }

    @Override
    public void updatePoll(PollEntity poll) throws UpdateException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE poll SET title=?, " +
                    "visible = ?, " +
                    "date_to = ?, " +
                    "start_date = ?, " +
                    "create_date = ? WHERE id = ? ");
            preparedStatement.setString(1, poll.getTitle());
            preparedStatement.setBoolean(2, poll.isVisible());
            preparedStatement.setDate(3, poll.getDateTo());
            preparedStatement.setDate(4, poll.getStartDate());
            preparedStatement.setDate(5, poll.getCreateDate());
            preparedStatement.setInt(6, poll.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new UpdateException();
        }
    }

    @Override
    public void deletePoll(PollEntity poll) throws DeleteException {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM poll WHERE id = ?");
            preparedStatement.setInt(1, poll.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }

    @Override
    public PollEntity[] getAllOpenPolls() throws SelectException {
        ArrayList<PollEntity> polls = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT poll.id, title, date_to, " +
                            "start_date , COUNT(DISTINCT voter_id) AS count_voters, " +
                            "COUNT(DISTINCT answer.id) AS count_answers " +
                            "FROM poll LEFT JOIN question " +
                            "ON question.poll_id = poll.id LEFT JOIN " +
                            "variants ON " +
                            "variants.question_id = question.id LEFT JOIN " +
                            "answer ON answer.variant_id = variants.id " +
                            "WHERE poll.visible = true AND current_date <= poll.date_to " +
                            "AND current_date >= poll.start_date " +
                            "GROUP BY poll.id; "
            );
            while (resultSet.next()) {
                PollEntity pollEntity = new PollEntity();
                pollEntity.setId(resultSet.getInt(1));
                pollEntity.setTitle(resultSet.getString(2));
                pollEntity.setDateTo(resultSet.getDate(3));
                pollEntity.setStartDate(resultSet.getDate(4));
                pollEntity.setStatistics(new PollStatisticsEntity(resultSet.getInt(5),
                        resultSet.getInt(6)));
                polls.add(pollEntity);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return polls.toArray(new PollEntity[polls.size()]);
    }

    @Override
    public PollEntity[] getAllEndedPolls() throws SelectException {
        ArrayList<PollEntity> polls = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT poll.id, title, date_to, " +
                            "start_date , COUNT(DISTINCT voter_id) AS count_voters, " +
                            "COUNT(DISTINCT answer.id) AS count_answers " +
                            "FROM poll LEFT JOIN question " +
                            "ON question.poll_id = poll.id LEFT JOIN " +
                            "variants ON " +
                            "variants.question_id = question.id LEFT JOIN " +
                            "answer ON answer.variant_id = variants.id " +
                            "WHERE poll.visible = true " +
                            "AND current_date >= poll.date_to " +
                            "GROUP BY poll.id; "
            );
            while (resultSet.next()) {
                PollEntity pollEntity = new PollEntity();
                pollEntity.setId(resultSet.getInt(1));
                pollEntity.setTitle(resultSet.getString(2));
                pollEntity.setDateTo(resultSet.getDate(3));
                pollEntity.setStartDate(resultSet.getDate(4));
                pollEntity.setStatistics(new PollStatisticsEntity(resultSet.getInt(5),
                        resultSet.getInt(6)));
                polls.add(pollEntity);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return polls.toArray(new PollEntity[polls.size()]);
    }

    public PollEntity getPollResult(int id) throws SelectException {
        PollEntity pollEntity = new PollEntity();
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT title FROM poll WHERE id = ?  ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            pollEntity.setTitle(resultSet.getString(1));
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(
                    "SELECT question.id, question, COUNT(answer.id) " +
                    "FROM question LEFT JOIN variants ON " +
                    "question.id = variants.question_id " +
                    "LEFT JOIN answer ON " +
                    "variants.id = answer.variant_id " +
                    "WHERE poll_id = ? GROUP BY question.id " );
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            HashMap<Integer, QuestionEntity> questionEntityHashMap= new HashMap<Integer, QuestionEntity>();
            while (resultSet.next()) {
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setId(resultSet.getInt(1));
                questionEntity.setQuestion(resultSet.getString(2));
                questionEntity.setQuestionsStatistics(new QuestionStatisticsEntity(resultSet.getInt(3)));
                questionEntityHashMap.put(questionEntity.getId(), questionEntity);
            }
            preparedStatement.close();
            if(questionEntityHashMap.keySet().size()!=0) {
                String in = Arrays.toString(questionEntityHashMap.keySet().toArray()).
                        replaceAll("[\\[\\]]", "");
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(
                        "SELECT variants.id, variants.text, variants.question_id, " +
                        "COUNT(answer.id) AS count_answers_variant " +
                        "FROM variants LEFT JOIN answer ON " +
                        "variants.id = answer.variant_id WHERE question_id IN ("+in+") GROUP BY variants.id");

                while (resultSet.next()) {
                    VariantEntity variantEntity = new VariantEntity();
                    variantEntity.setId(resultSet.getInt(1));
                    variantEntity.setText(resultSet.getString(2));
                    variantEntity.setQuestionId(resultSet.getInt(3));
                    variantEntity.setVariantStatistics(new VariantStatisticsEntity(resultSet.getInt(4)));
                    questionEntityHashMap.get(variantEntity.getQuestionId()).getVariants().add(variantEntity);
            }
            preparedStatement.close();
            }
            pollEntity.setQuestions(new ArrayList<QuestionEntity>(questionEntityHashMap.values()));

        } catch (SQLException e) {
            throw new SelectException();
        }
        return pollEntity;
    }

    public PollEntity getPollWithAnswersUser(int pollId, int userId) throws SelectException {
        PollEntity pollEntity = new PollEntity();
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT title FROM poll WHERE id = ?  ");
            preparedStatement.setInt(1, pollId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            pollEntity.setTitle(resultSet.getString(1));
            preparedStatement.close();

            preparedStatement = connection.prepareStatement(
                    "SELECT question.id, question, variant_id, " +
                    "variants.text, answer.answer_date " +
                    "FROM question LEFT JOIN variants ON " +
                    "question.id = variants.question_id " +
                    "LEFT JOIN answer ON " +
                    "variants.id = answer.variant_id " +
                    "WHERE poll_id = ? AND  answer.voter_id = ? ");
            preparedStatement.setInt(1, pollId);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                QuestionEntity questionEntity = new QuestionEntity();
                questionEntity.setId(resultSet.getInt(1));
                questionEntity.setQuestion(resultSet.getString(2));

                VariantEntity variantEntity = new VariantEntity();
                variantEntity.setId(resultSet.getInt(3));
                variantEntity.setText(resultSet.getString(4));

                AnswerEntity answerEntity = new AnswerEntity();
                answerEntity.setAnswerDate(resultSet.getDate(5));

                questionEntity.getVariants().add(variantEntity);
                variantEntity.setAnswer(answerEntity);

                pollEntity.getQuestions().add(questionEntity);
            }

        } catch (SQLException e){
            throw new SelectException();
        }
        return pollEntity;
    }

    public PollEntity[] getPollsByUser(int userId) throws SelectException {
        ArrayList<PollEntity> polls = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT DISTINCT poll.id, poll.title, poll.visible, poll.date_to, " +
                            "poll.start_date, poll.create_date  " +
                            "FROM poll JOIN question ON " +
                            "question.poll_id = poll.id JOIN variants ON " +
                            "question.id = variants.question_id " +
                            "JOIN answer ON " +
                            "variants.id = answer.variant_id WHERE " +
                            "answer.voter_id = ? ");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                polls.add(new PollEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getDate(6)));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e){
            throw new SelectException();
        }
        return polls.toArray(new PollEntity[polls.size()]);
    }


}
