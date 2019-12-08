package com.example.planningpoker.Objects;

import java.util.ArrayList;

public class Question {
    private String question,questionID,alpha,omega;
    private ArrayList<Result> results;

    public Question() {
    }

    public Question(String question,String questionID) {
        this.question = question;
        this.questionID = questionID;
        results = new ArrayList<>();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results.add(results);
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getOmega() {
        return omega;
    }

    public void setOmega(String omega) {
        this.omega = omega;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", questionID='" + questionID + '\'' +
                ", results=" + results +
                '}';
    }
}
