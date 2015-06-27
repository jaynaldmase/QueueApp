package com.example.matthew.mrtstories;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class HomePage extends ActionBarActivity {

    ArrayList<Story> story_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        updateList();

        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        ListView list = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        list.setAdapter(adapter);

        for(Story story : story_list){
            adapter.add(story.title);
        }

        final HomePage hp = this;

        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(hp)
                        .setTitle(story_list.get(position).title)
                        .setMessage(story_list.get(position).body)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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

    @Override
    public void onResume(){
        super.onResume();
        updateList();
    }

    public void newStory(View view){
        Intent intent = new Intent(this, NewStory.class);
        startActivity(intent);
    }

    public void updateList(){
        story_list = new ArrayList<>();

        StoryReaderDbHelper srdbhelper = new StoryReaderDbHelper(this);
        SQLiteDatabase db = srdbhelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + StoryReaderContract.StoryEntry.TABLE_NAME, null);

        if(c.getCount()>0) {
            c.moveToFirst();
            while (!c.isLast()) {
                story_list.add(new Story(c.getString(1), c.getString(2)));
                c.moveToNext();
            }
            story_list.add(new Story(c.getString(1), c.getString(2)));
        }
    }

    private class Story{
        String title;
        String body;

        public Story(String t, String b){
            this.title = t;
            this.body = b;
        }
    }
}
