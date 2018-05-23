package com.labs.android.laba1;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends FragmentActivity {

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
        question = question.replace('?', ' ');

        try {
            AddToDB(question, answer);
            result_textView.setText("Запис додано.");
            result_textView.setTextColor(Color.rgb(0,200,0));
        }
        catch (Exception e){
            Log.e("Exception", e.getMessage());
            result_textView.setText("При записі до бд сталася помилка...");
            result_textView.setTextColor(Color.rgb(200,0,0));
        }
    }

    public void showAllQuestions(View view) {
        HashMap<String, String> questionAnswerList = null;
        try{
            questionAnswerList = ReadFromDB();
        }
        catch (Exception e){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "An error occurred while reading data from db...", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        if(questionAnswerList == null || questionAnswerList.size() == 0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Input at least one question? please :)", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
        intent.putExtra("questionAnswerList", questionAnswerList);
        startActivity(intent);
    }

    private HashMap<String, String> ReadFromDB(){
        HashMap<String, String> tmp =  new HashMap<String, String>();

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS questions (queation TEXT, answer TEXT)");


        Cursor query = db.rawQuery("SELECT * FROM questions;", null);
        if(query.moveToFirst()){
            do{
                String question = query.getString(0);
                if(question.charAt(question.length() - 1) != '?')
                    question = question.concat("?");
                String answer = query.getString(1);
                tmp.put(question, answer);
                Log.d("DB", "Q: " + question + " A: " + answer + "");
            }
            while(query.moveToNext());
        }
        query.close();
        db.close();

        return tmp;
    }

    private void AddToDB(String q, String a){
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS questions (queation TEXT, answer TEXT)");
        db.execSQL("INSERT INTO questions VALUES ('" + q + "', '" + a + "');");
    }
}
