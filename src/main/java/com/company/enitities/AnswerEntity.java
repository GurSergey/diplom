package com.company.enitities;

import java.sql.Date;

public class AnswerEntity implements Entity {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public int id;
    public int voterId;
    public int variantId;
    public Date answerDate;
    public String text;
}
