package com.company.enitities;

public class QuestionStatisticsEntity implements Entity {
    public int getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }

    public QuestionStatisticsEntity(int countAnswers) {
        this.countAnswers = countAnswers;
    }

    public int countAnswers;
}
