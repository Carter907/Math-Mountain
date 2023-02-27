package org.example.trigquizzer;

import com.google.gson.GsonBuilder;

public class Question {
    private long id;
    private String text;
    private String answer;

    public Question() {

    }
    public Question(long id, String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
