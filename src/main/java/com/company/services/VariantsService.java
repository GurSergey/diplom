package com.company.services;

import com.company.enitities.KeyEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.dao.VariantsDAO;

public class VariantsService {
    private VariantsDAO repository;

    public VariantsService(VariantsDAO variantsDAO) {
        this.repository = variantsDAO;
    }

    public KeyEntity[] getVariantsByIdQuestion(int id) throws SelectException {
        return this.repository.getAllVariantsByQuestionId(id);
    }

    public void saveVariant(KeyEntity question) throws InsertException {
        this.repository.saveVariant(question);
    }

    public void updateVariant(KeyEntity question) throws UpdateException {
        this.repository.updateVariant(question);
    }

    public void deleteVariant(KeyEntity question) throws DeleteException {
        this.repository.deleteVariant(question);
    }
}