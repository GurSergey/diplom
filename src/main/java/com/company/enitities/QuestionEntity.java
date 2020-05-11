package com.company.enitities;

import java.sql.Date;
import java.util.ArrayList;

public class QuestionEntity implements Entity {
    int id;
    int pollId;
    PollEntity poll;


    public QuestionEntity(int id, int pollId, PollEntity poll, String question, Date createdDate) {
        this.id = id;
        this.pollId = pollId;
        this.poll = poll;
        this.question = question;
        this.createdDate = createdDate;
    }

    public QuestionEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public PollEntity getPoll() {
        return poll;
    }

    public void setPoll(PollEntity poll) {
        this.poll = poll;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    String question;
    Date createdDate;

    public QuestionStatisticsEntity getQuestionsStatistics() {
        return questionsStatistics;
    }

    public void setQuestionsStatistics(QuestionStatisticsEntity questions) {
        this.questionsStatistics = questions;
    }

    public ArrayList<VariantEntity> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<VariantEntity> variants) {
        this.variants = variants;
    }

    ArrayList<VariantEntity> variants = new ArrayList<>();

    QuestionStatisticsEntity questionsStatistics;


}
