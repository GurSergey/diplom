package com.company.repositories;

import com.company.enitities.VariantEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;

public interface VariantsRepository {
    VariantEntity[] getAllVariantsByQuestionId(int pollId) throws SelectException;
    void saveVariant(VariantEntity question) throws InsertException;
    void updateVariant(VariantEntity question) throws UpdateException;
    void deleteVariant(VariantEntity question) throws DeleteException;
}
