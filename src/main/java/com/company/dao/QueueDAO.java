package com.company.dao;


import com.company.db.DBConnection;
import com.company.enitities.*;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface QueueDAO {
    public QueueTaskUserEntity[] getAllQueueTaskUserByUserId(int id) throws SelectException;
    public QueueTaskUserEntity getCurrentTaskUserByUserId(int id) throws SelectException;
    public QueueMergeTaskEntity[] getAllQueueMergeTasks() throws SelectException;
    public QueueMergeTaskEntity getCurrentMergeTask() throws SelectException;
    public QueueCheckDataset[] getAllQueueCheckTasks() throws SelectException ;
    public QueueCheckDataset getCurrentCheckTask() throws SelectException;
    public QueueTaskAdminEntity[] getAllQueueTaskAdmin() throws SelectException;
    public QueueTaskAdminEntity getCurrentTaskAdmin() throws SelectException;
    public QueueTaskUserEntity[] getAllQueueTaskUser() throws SelectException ;
    public QueueTaskUserEntity getCurrentTaskUser() throws SelectException ;
    public QueueTaskMlEntity[] getAllMLTask() throws SelectException;
    public QueueTaskMlEntity getCurrentMLTask() throws SelectException;

}
