package com.labs.android.laba1;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends FragmentActivity {

    private HashMap<String, String> questionAnswerList = new HashMap<String, String>();
    QuestionsFragment qFragment;
    FragmentTransaction fTrans;

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

    public void showAllQuestions(View view) {
        for (Fragment fragment: getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        if(questionAnswerList == null || questionAnswerList.size() == 0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Input at least one question? please :)", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        qFragment = QuestionsFragment.newInstance(questionAnswerList);

        Fragment old = getSupportFragmentManager().findFragmentByTag("fragment_questions");
        if(old != null)
            getSupportFragmentManager().beginTransaction().remove(old).commit();

        fTrans = getFragmentManager().beginTransaction();

        fTrans.add(R.id.fragment_questions, qFragment);
        fTrans.commit();
    }


}
