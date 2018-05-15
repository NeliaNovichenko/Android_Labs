package com.labs.android.laba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

public class QuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        HashMap<String, String> questionAnswerList = (HashMap<String, String>) intent.getSerializableExtra("questionAnswerList");
        InitializeTable(questionAnswerList);
    }


    private void InitializeTable(HashMap<String, String> hashMap) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        int count = tableLayout.getChildCount();
        for (int i = 1; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        for (String key: hashMap.keySet())
        {
            TableRow tableRow = new TableRow(this);

            TextView question = new TextView(this);
            question.setText(key);
            TextView answer = new TextView(this);
            answer.setText(hashMap.get(key));
            tableRow.addView(question);
            tableRow.addView(answer);

            tableLayout.addView(tableRow);
        }
    }
}
