package com.labs.android.laba1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, String> questionAnswerList = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner dropdown = findViewById(R.id.answer_spinner);
        String[] answerOptions = new String[]{"Так", "Ні"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, answerOptions);
        dropdown.setAdapter(adapter);
    }

    public void clear(View view) {
        EditText question_editText = findViewById(R.id.question_editText);
        question_editText.setText("");
        TextView result_textView = findViewById(R.id.result_textView);
        result_textView.setText("");
    }

    public void outputAnswer(View view) {
        EditText question_editText = findViewById(R.id.question_editText);
        Spinner answer_spinner = findViewById(R.id.answer_spinner);
        TextView result_textView = findViewById(R.id.result_textView);

        String question = question_editText.getText().toString();
        String answer = answer_spinner.getSelectedItem().toString();
        if(question.charAt(question.length() - 1) != '?')
            question = question.concat("?");

        if(Add(question, answer) == false){
            result_textView.setText("Такий запис вже існує.");
            result_textView.setTextColor(Color.rgb(200,0,0));
            return;
        }
        else{
            result_textView.setText(question + " - " + answer);
            result_textView.setTextColor(Color.rgb(0,200,0));
        }

    }

    public boolean Add(String question, String answer){
        if(questionAnswerList.containsKey(question))
            return false;
        questionAnswerList.put(question, answer);
        return true;
    }






    /*public void openQuestionList(View view) {
        EditText question_editText = (EditText)findViewById(R.id.question_editText);
        Spinner answer_spinner = (Spinner)findViewById(R.id.answer_spinner);
        TextView result_textView = (TextView)findViewById(R.id.result_textView);
        QuestionsActivity questionsActivity = new QuestionsActivity();

        String question = question_editText.getText().toString();
        String answer = answer_spinner.getSelectedItem().toString();
        if(questionsActivity.Add(question, answer)){
            result_textView.setText("Такий запис вже існує");
            return;
        }
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/
}
