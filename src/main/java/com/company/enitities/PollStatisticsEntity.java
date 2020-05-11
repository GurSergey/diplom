package com.company.enitities;

public class PollStatisticsEntity implements Entity {
    int countVoter;
    int countOpinions;

    public int getCountVoter() {
        return countVoter;
    }

    public PollStatisticsEntity(){}

    public PollStatisticsEntity(int countVoter, int countOpinions) {
        this.countVoter = countVoter;
        this.countOpinions = countOpinions;
    }

    public void setCountVoter(int countVoter) {
        this.countVoter = countVoter;
    }

    public int getCountOpinions() {
        return countOpinions;
    }

    public void setCountOpinions(int countOpinions) {
        this.countOpinions = countOpinions;
    }
}
