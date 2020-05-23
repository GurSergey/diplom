package com.company.services;

import com.company.dao.ModelsDAO;
import com.company.dao.QueueDAO;
import com.company.enitities.*;
import com.company.exceptions.SelectException;

public class QueueService {
    QueueDAO dao;

    public QueueService(QueueDAO dao)
    {
        this.dao = dao;
    }

    public QueueTaskUserEntity[] getAllQueueTaskUserByUserId(int id) throws SelectException{
        return this.dao.getAllQueueTaskUserByUserId(id);
    }
    public QueueTaskUserEntity getCurrentTaskUserByUserId(int id) throws SelectException{
        return this.dao.getCurrentTaskUserByUserId(id);
    }
    public QueueMergeTaskEntity[] getAllQueueMergeTasks() throws SelectException{
        return this.dao.getAllQueueMergeTasks();
    }
    public QueueMergeTaskEntity getCurrentMergeTask() throws SelectException{
        return this.dao.getCurrentMergeTask();
    }
    public QueueCheckDataset[] getAllQueueCheckTasks() throws SelectException{
        return this.dao.getAllQueueCheckTasks();
    }
    public QueueCheckDataset getCurrentCheckTask() throws SelectException{
        return this.dao.getCurrentCheckTask();
    }
    public QueueTaskAdminEntity[] getAllQueueTaskAdmin() throws SelectException{
        return this.dao.getAllQueueTaskAdmin();
    }
    public QueueTaskAdminEntity getCurrentTaskAdmin() throws SelectException{
        return this.dao.getCurrentTaskAdmin();
    }
    public QueueTaskUserEntity[] getAllQueueTaskUser() throws SelectException{
        return this.dao.getAllQueueTaskUser();
    }
    public QueueTaskUserEntity getCurrentTaskUser() throws SelectException {
        return this.dao.getCurrentTaskUser();
    }
    public QueueTaskMlEntity[] getAllMLTask() throws SelectException{
        return this.dao.getAllMLTask();
    }
    public QueueTaskMlEntity getCurrentMLTask() throws SelectException{
        return this.dao.getCurrentMLTask();
    }

}
