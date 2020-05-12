package com.company.dao;

import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface VariantsDAO {
    KeyEntity[] getAllVariantsByQuestionId(int pollId) throws SelectException;
    void saveVariant(KeyEntity question) throws InsertException;
    void updateVariant(KeyEntity question) throws UpdateException;
    void deleteVariant(KeyEntity question) throws DeleteException;
}
