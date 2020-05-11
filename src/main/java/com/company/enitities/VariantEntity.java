package com.company.enitities;

public class VariantEntity implements Entity {
    int id;

    public VariantEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public VariantEntity(int id, int questionId, QuestionEntity question, String text) {
        this.id = id;
        this.questionId = questionId;
        this.question = question;
        this.text = text;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    int questionId;
    QuestionEntity question;

    String text;

    public VariantStatisticsEntity getVariantStatistics() {
        return variantStatistics;
    }

    public void setVariantStatistics(VariantStatisticsEntity variantStatistics) {
        this.variantStatistics = variantStatistics;
    }

    VariantStatisticsEntity variantStatistics;

    public AnswerEntity getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    AnswerEntity answer;
}
