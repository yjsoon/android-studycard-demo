package com.ridiculousapps.studycards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    StudyCard[] studyCards;
    ListView questionsList;
    int lastQuestionSelected;
    int currentScore = 0;
    int numQuestions = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studyCards = new StudyCard[numQuestions];

        studyCards[0] = new StudyCard("What is Newton's First Law?",
                new String[]{"An object continues in blah blah",
                            "A hawker centre",
                            "Every action has an equal and opposite reaction"},
                0);

        studyCards[1] = new StudyCard("What is Newton's Second Law?",
                new String[]{"Another hawker centre",
                        "F = ma",
                        "Every action has an equal and opposite reaction"},
                1);

        studyCards[2] = new StudyCard("What is Newton's Third Law?",
                new String[]{"Every action has an unequal and opposite reaction",
                        "Still a hawker centre because I'm hungry",
                        "Every action has an equal and opposite reaction"},
                2);

        studyCards[3] = new StudyCard("In the following figures, the uniform metre rule " +
                "is pivoted at the middle O. A fixed weight W is loaded on one side and variable " +
                "weights V1 or V2 or V3 is loaded on the other side in order to balance the rule " +
                "horizontally in each case. Arrange the magnitude of the reaction force at the " +
                "support, O, in ascending order.",
                new String[]{"1, 2, 3",
                        "2, 3, 1",
                        "2, 1, 3"},
                2, R.drawable.diagram);

        questionsList = (ListView) findViewById(R.id.questions_list);

        ArrayAdapter<StudyCard> adapter = new ArrayAdapter<StudyCard>(this,
                android.R.layout.simple_list_item_checked, studyCards);

        questionsList.setAdapter(adapter);

        questionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TJC", "I've clicked on item " + position);
                Intent intent = new Intent(MainActivity.this, StudyCardActivity.class);
                intent.putExtra("study card", studyCards[position]);
                intent.putExtra("image", studyCards[position].imageResourceID);
                questionsList.setItemChecked(position, false);
                lastQuestionSelected = position;
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            boolean answerCorrect = data.getBooleanExtra("answerCorrect", false);
            questionsList.setItemChecked(lastQuestionSelected, answerCorrect);

            if (answerCorrect == true) {
                currentScore++;
            }
            TextView resultsText = (TextView) findViewById(R.id.results_text);
            resultsText.setText("Score: " + currentScore + " of " + numQuestions);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
