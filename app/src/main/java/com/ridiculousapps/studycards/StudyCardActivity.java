package com.ridiculousapps.studycards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class StudyCardActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_card);

        TextView questionText = (TextView) findViewById(R.id.question_text);
        Button choiceA = (Button) findViewById(R.id.choice_a_btn);
        Button choiceB = (Button) findViewById(R.id.choice_b_btn);
        Button choiceC = (Button) findViewById(R.id.choice_c_btn);

        Intent i = getIntent();
        final StudyCard s = i.getParcelableExtra("study card");

        // get the image resource ID, which we sent over separately
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        int imageResourceId = i.getIntExtra("image", -1);
        // check if there's actually an image before we set it:
        if (imageResourceId != 0 && imageResourceId != -1) {
            imageView.setImageResource(imageResourceId);
        }

        questionText.setText(s.question);
        choiceA.setText(s.choices[0]);
        choiceB.setText(s.choices[1]);
        choiceC.setText(s.choices[2]);

        choiceA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s.answerIndex == 0) {
                    correctAnswer();
                } else {
                    wrongAnswer();
                }
            }
        });

        choiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s.answerIndex == 1) {
                    correctAnswer();
                } else {
                    wrongAnswer();
                }
            }
        });

        choiceC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (s.answerIndex == 2) {
                    correctAnswer();
                } else {
                    wrongAnswer();
                }
            }
        });

    }

    private void correctAnswer() {
        Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        Intent data = new Intent();
        data.putExtra("answerCorrect", true);
        setResult(RESULT_OK, data);
        finish();
    }

    private void wrongAnswer() {
        Toast.makeText(getApplicationContext(), "Wrong :(", Toast.LENGTH_SHORT).show();
        Intent data = new Intent();
        data.putExtra("answerCorrect", false);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
