package com.company.services;

import com.company.dao.ModelsDAO;
import com.company.dao.QueueDAO;
import com.company.enitities.*;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;

import java.security.SecureRandom;

public class QueueService {
    QueueDAO dao;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static final int SIZE_FILE_ID = 50;
    private static String generateFilename() {
//        if (SIZE_SESSION_ID < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(SIZE_FILE_ID);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < SIZE_FILE_ID; i++) {

            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);
            sb.append(rndChar);

        }

        return sb.toString();

    }

    public QueueService(QueueDAO dao)
    {
        this.dao = dao;
    }

    public QueueTaskUserEntity[] getAllQueueTaskUserByUserId(int id) throws SelectException{
        return this.dao.getAllQueueTaskUserByUserId(id);
    }
//    public QueueTaskUserEntity getCurrentTaskUserByUserId(int id) throws SelectException{
//        return this.dao.getCurrentTaskUserByUserId(id);
//    }
    public QueueMergeTaskEntity[] getAllQueueMergeTasks() throws SelectException{
        return this.dao.getAllQueueMergeTasks();
    }
//    public QueueMergeTaskEntity getCurrentMergeTask() throws SelectException{
//        return this.dao.getCurrentMergeTask();
//    }
    public QueueCheckDataset[] getAllQueueCheckTasks() throws SelectException{
        return this.dao.getAllQueueCheckTasks();
    }
//    public QueueCheckDataset getCurrentCheckTask() throws SelectException{
//        return this.dao.getCurrentCheckTask();
//    }
    public QueueTaskAdminEntity[] getAllQueueTaskAdmin() throws SelectException{
        return this.dao.getAllQueueTaskAdmin();
    }
//    public QueueTaskAdminEntity getCurrentTaskAdmin() throws SelectException{
//        return this.dao.getCurrentTaskAdmin();
//    }
    public QueueTaskUserEntity[] getAllQueueTaskUser() throws SelectException{
        return this.dao.getAllQueueTaskUser();
    }
//    public QueueTaskUserEntity getCurrentTaskUser() throws SelectException {
//        return this.dao.getCurrentTaskUser();
//    }
    public QueueTaskMlEntity[] getAllMLTask() throws SelectException{
        return this.dao.getAllMLTask();
    }
//    public QueueTaskMlEntity getCurrentMLTask() throws SelectException{
//        return this.dao.getCurrentMLTask();
//    }

    public void AddAdminTextTask() throws InsertException {
        this.dao.ge
    }
    public void AddUserTextTask() throws InsertException {

    }

    public QueueTaskAdminEntity getByIdAdminTask(int id) throws SelectException {
        this.dao.getAdminTaskById(id);
    }

    public QueueTaskUserEntity getByIdUserTask(int id){
        this.dao.getUserTaskById(id);
    }

}
