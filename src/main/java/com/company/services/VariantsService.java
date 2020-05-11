package com.company.services;

import com.company.enitities.VariantEntity;
import com.company.exceptions.DeleteException;
import com.company.exceptions.InsertException;
import com.company.exceptions.SelectException;
import com.company.exceptions.UpdateException;
import com.company.repositories.VariantsRepository;

public class VariantsService {
    private VariantsRepository repository;

    public VariantsService(VariantsRepository variantsRepository) {
        this.repository = variantsRepository;
    }

    public VariantEntity[] getVariantsByIdQuestion(int id) throws SelectException {
        return this.repository.getAllVariantsByQuestionId(id);
    }

    public void saveVariant(VariantEntity question) throws InsertException {
        this.repository.saveVariant(question);
    }

    public void updateVariant(VariantEntity question) throws UpdateException {
        this.repository.updateVariant(question);
    }

    public void deleteVariant(VariantEntity question) throws DeleteException {
        this.repository.deleteVariant(question);
    }
}