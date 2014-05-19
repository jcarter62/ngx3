package com.jcarter62.numbergamex3.ngx3;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ScoreList extends ListActivity {
    private ListView scoreListView;
    private CursorAdapter cAdptr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoreListView = getListView();

//        scoreListView.setOnItemClickListener(viewContactListener);

        String[] from = new String[]{"highscore"};
        int[] to = new int[]{R.id.scoreTextView};
        cAdptr = new SimpleCursorAdapter( ScoreList.this,
                R.layout.score_list_item, null, from, to);
        setListAdapter(cAdptr);

//        setContentView(R.layout.activity_score_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.score_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetScoresTask().execute((Object[]) null);
    }

    private class GetScoresTask extends AsyncTask<Object, Object, Cursor> {

        DatabaseConnector databaseConnector = new DatabaseConnector(ScoreList.this);

        @Override
        protected Cursor doInBackground(Object... params) {
            databaseConnector.open();
            return databaseConnector.getRecent();
        }

        @Override
        protected void onPostExecute(Cursor result) {
            try {
                cAdptr.changeCursor(result);
            } catch (Exception ex) {
                Log.d("Scores", ex.toString());
            }
            databaseConnector.close();
        }
    }

}
