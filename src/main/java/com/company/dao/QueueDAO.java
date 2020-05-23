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

     QueueMergeTaskEntity[] getAllQueueMergeTasks() throws SelectException;
     QueueMergeTaskEntity getCurrentMergeTask() throws SelectException;
     QueueCheckDataset[] getAllQueueMergeTasks() throws SelectException;
     QueueCheckDataset getCurrentMergeTask() throws SelectException;
    public QueueTaskAdminEntity[] getAllQueueTaskAdmin() throws SelectException

}
