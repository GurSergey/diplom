package com.company.db;

import com.company.enitities.*;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.dao.QueueDAO;
import com.company.exceptions.SelectException;

import java.sql.*;
import java.util.ArrayList;

public class QueueDAODB implements QueueDAO {

    public QueueDAODB()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public QueueMergeTaskEntity[] getAllQueueMergeTasks() throws SelectException {
        ArrayList<QueueMergeTaskEntity> tasks = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT merge_dataset.id, dataset.title, completed_task, in_work," +
                            " source_datasets, merge_dataset.created_date FROM merge_dataset " +
                            "JOIN dataset ON dataset.id = dataset_id "
            );
            while (resultSet.next()) {
                DatasetEntity dataset = new DatasetEntity();
                dataset.setTitle(resultSet.getString(2));
                tasks.add(new QueueMergeTaskEntity(resultSet.getInt(1), resultSet.getBoolean(3),
                        dataset,
                        resultSet.getBoolean(4), resultSet.getString(5),
                        resultSet.getTimestamp(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return tasks.toArray(new QueueMergeTaskEntity[tasks.size()]);
    }

    @Override
    public QueueCheckDataset[] getAllQueueCheckTasks() throws SelectException {
        ArrayList<QueueCheckDataset> tasks = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT check_dataset.id, dataset.title, completed_task, normalize , in_work," +
                            " dataset.is_correct, check_dataset.created_date FROM check_dataset " +
                            "JOIN dataset ON dataset.id = dataset_id "
            );
            while (resultSet.next()) {
                DatasetEntity dataset = new DatasetEntity();
                dataset.setTitle(resultSet.getString(2));
                tasks.add(new QueueCheckDataset(resultSet.getInt(1), resultSet.getBoolean(3),
                        resultSet.getBoolean(4),
                        resultSet.getBoolean(5),
                        resultSet.getBoolean(6),
                        dataset,
                        resultSet.getTimestamp(7)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return tasks.toArray(new QueueCheckDataset[tasks.size()]);
    }



    @Override
    public QueueTaskAdminEntity[] getAllQueueTaskAdmin() throws SelectException {
        ArrayList<QueueTaskAdminEntity> tasks = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT queue_task_admin_file.id, queue_task_admin_file.title," +
                            " completed_task, progress, in_work, model.title," +
                            " queue_task_admin_file.created_date FROM queue_task_admin_file " +
                            "JOIN model ON model.id = model_id "
            );
            while (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                tasks.add(new QueueTaskAdminEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return tasks.toArray(new QueueTaskAdminEntity[tasks.size()]);
    }

//    @Override
//    public QueueTaskAdminEntity[] getAllQueueTaskAdmin() throws SelectException {
//        ArrayList<QueueTaskAdminEntity> tasks = new ArrayList<>();
//        try (Connection connection = DBConnection.getConnection()) {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "SELECT id, completed_task, progress, in_work, model.title," +
//                            " created_date FROM queue_task_admin_file " +
//                            "JOIN model ON model.id = model_id "
//            );
//            while (resultSet.next()) {
//                ModelEntity model = new ModelEntity();
//                model.setTitle(resultSet.getString(6));
//                tasks.add(new QueueTaskAdminEntity(resultSet.getInt(1), resultSet.getString(2),
//                        resultSet.getBoolean(3),
//                        resultSet.getInt(4),
//                        resultSet.getBoolean(5),
//                        model,
//                        resultSet.getTimestamp(7)));
//            }
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            throw new SelectException();
//        }
//        return tasks.toArray(new QueueTaskAdminEntity[tasks.size()]);
//    }
//
    @Override
    public QueueTaskAdminEntity getAdminTaskById(int id) throws SelectException{
        QueueTaskAdminEntity task = new QueueTaskAdminEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT queue_task_admin_file.id, queue_task_admin_file.title, completed_task, progress, in_work, model.title," +
                            " queue_task_admin_file.created_date FROM queue_task_admin_file " +
                            "JOIN model ON model.id = model_id WHERE queue_task_admin_file.id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                task =(new QueueTaskAdminEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }
    @Override
    public void AddAdminTextTask(QueueTaskAdminEntity task) throws InsertException{
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO queue_task_admin_file(model_id, title, filename) VALUES (?, ?, ?)"
            );
            preparedStatement.setInt(1, task.getModel().getId());
            preparedStatement.setString(2, task.getTitle());
            preparedStatement.setString(3, task.getFilename());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }
    @Override
    public void AddUserTextTask(QueueTaskUserEntity task) throws InsertException{
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO queue_task_user_file(model_id, title, filename, user_id) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, task.getModel().getId());
            preparedStatement.setString(2, task.getTitle());
            preparedStatement.setString(3, task.getFilename());
            preparedStatement.setInt(4, task.getUser().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }
    @Override
    public void UpdateUserTextTask(QueueTaskUserEntity task) throws InsertException{
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE queue_task_user_file SET title = ? WHERE id = ?"
            );
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setInt(2, task.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }
    @Override
    public void UpdateAdminTextTask(QueueTaskAdminEntity task) throws InsertException{
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE queue_task_admin_file SET title = ? WHERE id = ?"
            );
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setInt(2, task.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new InsertException();
        }
    }
    @Override
    public void DeleteAdminTextTask(QueueTaskAdminEntity task) throws DeleteException{
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM queue_task_admin_file WHERE id = ?"
            );
            preparedStatement.setInt(1, task.getModel().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }
    @Override
    public void DeleteUserTextTask(QueueTaskUserEntity task) throws DeleteException{
        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM queue_task_user_file WHERE id = ?"
            );
            preparedStatement.setInt(1, task.getModel().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DeleteException();
        }
    }


    @Override
    public QueueTaskUserEntity[] getAllQueueTaskUser() throws SelectException {
        ArrayList<QueueTaskUserEntity> tasks = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT queue_task_user_file.id, queue_task_user_file.title, completed_task, progress, in_work, model.title," +
                            " queue_task_user_file.created_date, \"user\".login FROM queue_task_user_file " +
                            "JOIN model ON model.id = model_id JOIN \"user\" ON \"user\".id = user_id"
            );
            while (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                UserEntity user = new UserEntity();
                user.setLogin(resultSet.getString(7));
                tasks.add(new QueueTaskUserEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7),
                        user));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return tasks.toArray(new QueueTaskUserEntity[tasks.size()]);
    }

    @Override
    public QueueTaskUserEntity[] getAllQueueTaskUserByUserId(int id) throws SelectException {
        ArrayList<QueueTaskUserEntity> tasks = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery(
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT queue_task_user_file.id, queue_task_user_file.title, completed_task, progress, in_work, model.title," +
                            " queue_task_user_file.created_date, user.login FROM queue_task_user_file " +
                            "JOIN model ON model.id = model_id JOIN user ON user.id = user_id " +
                            "WHERE user.id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                UserEntity user = new UserEntity();
                user.setLogin(resultSet.getString(7));
                tasks.add(new QueueTaskUserEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7),
                        user));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return tasks.toArray(new QueueTaskUserEntity[tasks.size()]);
    }

    @Override
    public QueueTaskUserEntity getUserTaskById(int id) throws SelectException {
        QueueTaskUserEntity task = new QueueTaskUserEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery(
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT queue_task_user_file.id, title, completed_task, progress, in_work, model.title," +
                            " queue_task_user_file.created_date, user.login FROM queue_task_user_file " +
                            "JOIN model ON model.id = model_id JOIN user ON user.id = user_id " +
                            "WHERE queue_task_user_file.id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                UserEntity user = new UserEntity();
                user.setLogin(resultSet.getString(7));
                task = (new QueueTaskUserEntity(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7),
                        user));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }


    @Override
    public QueueTaskMlEntity[] getAllMLTask() throws SelectException {
        ArrayList<QueueTaskMlEntity> tasks = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT queue_task_ml.id,  completed_task, progress, n_workers, in_work, model.title," +
                            " queue_task_ml.created_date FROM queue_task_ml " +
                            "JOIN model ON model.id = model_id"
            );
            while (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));

                tasks.add(new QueueTaskMlEntity(resultSet.getInt(1), resultSet.getBoolean(2),
                        model,
                        resultSet.getTimestamp(7),
                        resultSet.getBoolean(5),
                        resultSet.getInt(4),
                        resultSet.getInt(3)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return tasks.toArray(new QueueTaskMlEntity[tasks.size()]);
    }



}
/*

@Override
    public QueueTaskMlEntity getCurrentMLTask() throws SelectException {
        QueueTaskMlEntity task = new QueueTaskMlEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id,  completed_task, progress, n_workers, in_work, model.title," +
                            " created_date FROM queue_task_ml " +
                            "JOIN model ON model.id = model_id " +
                            "WHERE in_work = TRUE"
            );
            if (resultSet.next()) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(5));
                UserEntity user = new UserEntity();
                user.setLogin(resultSet.getString(7));
                task = new QueueTaskMlEntity(resultSet.getInt(1), resultSet.getBoolean(2),
                        model,
                        resultSet.getTimestamp(7),
                        resultSet.getBoolean(5),
                        resultSet.getInt(4),
                        resultSet.getInt(3));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }

    @Override
    public QueueTaskUserEntity getCurrentTaskUserByUserId(int id) throws SelectException {
        QueueTaskUserEntity task = new QueueTaskUserEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, title completed_task, progress, in_work, model.title," +
                            " created_date, user.login FROM queue_task_user_file " +
                            "JOIN model ON model.id = model_id JOIN user ON user.id = user_id " +
                            "WHERE in_work = TRUE WHERE user.id = ?"
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() ) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                UserEntity user = new UserEntity();
                user.setLogin(resultSet.getString(7));
                task = new QueueTaskUserEntity(resultSet.getInt(1), resultSet.getString(2) ,resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7),
                        user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }


    @Override
    public QueueTaskUserEntity getCurrentTaskUser() throws SelectException {
        QueueTaskUserEntity task = new QueueTaskUserEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, title completed_task, progress, in_work, model.title," +
                            " created_date, user.login FROM queue_task_user_file " +
                            "JOIN model ON model.id = model_id JOIN user ON user.id = user_id " +
                            "WHERE in_work = TRUE"
            );
            if (resultSet.next() ) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                UserEntity user = new UserEntity();
                user.setLogin(resultSet.getString(7));
                task = new QueueTaskUserEntity(resultSet.getInt(1), resultSet.getString(2) ,resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7),
                        user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }

        @Override
    public QueueTaskAdminEntity getCurrentTaskAdmin() throws SelectException {
        QueueTaskAdminEntity task = new QueueTaskAdminEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, title , completed_task, progress, in_work, model.title," +
                            " created_date FROM queue_task_admin_file " +
                            "JOIN model ON model.id = model_id WHERE in_work = TRUE"
            );
            if (resultSet.next() ) {
                ModelEntity model = new ModelEntity();
                model.setTitle(resultSet.getString(6));
                task = (new QueueTaskAdminEntity(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3),
                        resultSet.getInt(4),
                        resultSet.getBoolean(5),
                        model,
                        resultSet.getTimestamp(7)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }

        @Override
    public QueueCheckDataset getCurrentCheckTask() throws SelectException {
        QueueCheckDataset task = new QueueCheckDataset();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, dataset.title, completed_task, normalize , in_work," +
                            " is_correct, created_date FROM check_dataset " +
                            "JOIN dataset ON dataset.id = dataset_id WHERE in_work = TRUE "
            );
            if (resultSet.next() ) {
                DatasetEntity dataset = new DatasetEntity();
                dataset.setTitle(resultSet.getString(2));
                task = new QueueCheckDataset(resultSet.getInt(1), resultSet.getBoolean(3),
                        resultSet.getBoolean(4),
                        resultSet.getBoolean(5),
                        resultSet.getBoolean(6),
                        dataset,
                        resultSet.getTimestamp(6));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }
        @Override
    public QueueMergeTaskEntity getCurrentMergeTask() throws SelectException {
        QueueMergeTaskEntity task = new QueueMergeTaskEntity();
        try (Connection connection = DBConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, dataset.title, completed_task, in_work," +
                            " source_datasets, created_date FROM merge_dataset " +
                            "JOIN dataset ON dataset.id = dataset_id  WHERE in_work = TRUE"
            );
            if (resultSet.next() ) {
                DatasetEntity dataset = new DatasetEntity();
                dataset.setTitle(resultSet.getString(2));
                task = (new QueueMergeTaskEntity(resultSet.getInt(1), resultSet.getBoolean(3),
                        dataset,
                        resultSet.getBoolean(4), resultSet.getString(5),
                        resultSet.getTimestamp(6)));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new SelectException();
        }
        return task;
    }
 */