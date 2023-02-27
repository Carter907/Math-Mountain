package org.example.trigquizzer.model;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnswerParser {

    public String replaceAllPIs(TextField answerInput) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile("pi", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(answerInput.getText());


        while(matcher.find()) {
            matcher.appendReplacement(builder, "π");
        }
        matcher.appendTail(builder);
        answerInput.setText(builder.toString());

        return builder.toString();
    }
}
