package com.labs.android.laba1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.HashMap;
import java.util.Map;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity {

    private Map<String, String> questionAnswerList = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        TableRow tr_head = new TableRow(this);
        //tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        TextView label_question = new TextView(this);
        //label_question.setId(20);
        label_question.setText("Питання");
        label_question.setTextColor(Color.WHITE);
        label_question.setPadding(5, 5, 5, 5);
        tr_head.addView(label_question);// add the column to the table row here

        TextView label_answer = new TextView(this);
        //label_answer.setId(21);// define id that must be unique
        label_answer.setText("Wt(Kg.)"); // set the text for the header
        label_answer.setTextColor(Color.WHITE); // set the color
        label_answer.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_answer); // add the column to the table row here

        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));


        for (String key : questionAnswerList.keySet()) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

            //Create two columns to add as table data
            // Create a TextView to add date
            TextView labelDATE = new TextView(this);
            //labelDATE.setId(200+count);
            labelDATE.setText(key);
            labelDATE.setPadding(2, 0, 5, 0);
            labelDATE.setTextColor(Color.WHITE);
            tr.addView(labelDATE);
            TextView labelWEIGHT = new TextView(this);
            //labelWEIGHT.setId(200+count);
            labelWEIGHT.setText(questionAnswerList.get(key));
            labelWEIGHT.setTextColor(Color.WHITE);
            tr.addView(labelWEIGHT);

            // finally add this to the table row
            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }

    }

    public boolean Add(String question, String answer){
        if(questionAnswerList.containsKey(question))
            return false;
        questionAnswerList.put(question, answer);
        return true;
    }
}
