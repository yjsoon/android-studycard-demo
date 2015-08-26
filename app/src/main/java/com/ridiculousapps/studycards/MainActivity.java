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


public class MainActivity extends ActionBarActivity {

    StudyCard[] studyCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studyCards = new StudyCard[4];

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

        final ListView questionsList = (ListView) findViewById(R.id.questions_list);

        ArrayAdapter<StudyCard> adapter = new ArrayAdapter<StudyCard>(this,
                android.R.layout.simple_list_item_checked, studyCards);

        questionsList.setAdapter(adapter);
        questionsList.setItemChecked(0, true);

        questionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TJC", "I've clicked on item " + position);
                Intent intent = new Intent(MainActivity.this, StudyCardActivity.class);
                intent.putExtra("study card", studyCards[position]);
                intent.putExtra("image", studyCards[position].imageResourceID);
                questionsList.setItemChecked(position, false);
                startActivity(intent);
            }
        });

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
