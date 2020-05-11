package com.company.enitities;

public class VariantStatisticsEntity implements Entity {
    public VariantStatisticsEntity(int countAnswers) {
        this.countAnswers = countAnswers;
    }

    public int getCountAnswers() {
        return countAnswers;
    }

    public void setCountAnswers(int countAnswers) {
        this.countAnswers = countAnswers;
    }

    private int countAnswers;

}
