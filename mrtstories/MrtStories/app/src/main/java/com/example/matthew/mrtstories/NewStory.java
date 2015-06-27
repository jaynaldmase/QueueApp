package com.example.matthew.mrtstories;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class NewStory extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_story, menu);
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

    public void createStory(View view){
        EditText view_title = (EditText) findViewById(R.id.storyTitle);
        EditText view_body = (EditText) findViewById(R.id.storyBody);

        String title = view_title.getText().toString();
        String body = view_body.getText().toString();

        StoryReaderDbHelper srdbhelper = new StoryReaderDbHelper(this);

        SQLiteDatabase db = srdbhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StoryReaderContract.StoryEntry.COLUMN_NAME_TITLE, title);
        values.put(StoryReaderContract.StoryEntry.COLUMN_NAME_BODY, body);
        db.insert(StoryReaderContract.StoryEntry.TABLE_NAME, null, values);

        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

    }
}
