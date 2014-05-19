package com.jcarter62.numbergamex3.ngx3;

/**
 * Created by jim on 5/18/14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.UUID;

/**
 * Created by jim on 4/20/14.
 */
public class DatabaseConnector {
    private static final String DATABASE_NAME = "ScoresDB";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;
    private final String TAG = "dbConnector";

    private void setAllToBlank() {
        String execstr;
        execstr = "update scores set highscore = score ;";
        database.execSQL(execstr);
    }

    private int determineHighScore() {
        int hiscore = 0;

        Cursor c = database.query("scores",new String[]{"_id","score"},null, null, null, null, null);

        Log.d(TAG, "Cursor open, records = " + int2str(c.getCount()));

        if ( c.getCount() > 0) {
            int ci;
            ci = c.getColumnIndex("score");

            c.moveToFirst();
            while ( ! c.isAfterLast()) {
                Log.d(TAG, "Check to see if " + int2str(c.getInt(ci)) + " > " + int2str(hiscore) );
                if ( c.getInt(ci) > hiscore )
                    hiscore = c.getInt(ci);
                c.moveToNext();
            }
        }
        c = null;

        Log.d(TAG, "high score = " + int2str(hiscore) );

        return hiscore;
    }

    private String int2str(int x) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(x);
        String r = sb.toString();
        return r;
    }

    private void setHighScoreField(int highs) {
        String execstr;
        execstr = "update scores set highscore = '" + int2str(highs) + " <<-- High Score ! ' " +
                  " where score = " + int2str(highs) + ";";
        Log.d(TAG, execstr);
        database.execSQL(execstr);
    }

    public void setHighScore() {
        int highscore;

        Log.d(TAG, "setAllToBlank");
        setAllToBlank();
        Log.d(TAG, "determineHighScore");
        highscore = determineHighScore();
        Log.d(TAG, "high score = " + int2str(highscore) );
        Log.d(TAG, "setHighScoreField");
        setHighScoreField(highscore);
    }

    public void close() {
        if (database != null)
            database.close();
        Log.d(TAG, "close");
    }

    public void addScore(int score) {
        Log.d(TAG, "Insert Score of " + score );
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        ContentValues nc = new ContentValues();
        nc.put("score", score);
        nc.put("guid", randomUUIDString );
        open();
        database.insert("scores", null, nc);
        close();
    }

    public void deleteRecord(String id) {
        Log.d(TAG, "deleteRecord: id=" + id );
        open();
        database.delete("scores", "guid=" + id, null);
        close();
    }

    private void dumpData() {
        Cursor c = database.query("scores",new String[]{"_id","guid","score","highscore"},null, null, null, null, null);

        Log.d(TAG, "dumpdata: Start");
        if ( c.getCount() > 0) {
            c.moveToFirst();
            while ( ! c.isAfterLast()) {
                String s;
                s = "dumpdata: " ;
                s = s + "guid = " + c.getString(c.getColumnIndex("guid")) ;
                s = s + ", score = " + c.getInt(c.getColumnIndex("score")) ;
                s = s + ", highscore = " + c.getString(c.getColumnIndex("highscore")) ;
                Log.d(TAG, s);
                c.moveToNext();
            }
        }
        c = null;
        Log.d(TAG, "dumpdata: End");
    }

    public Cursor getRecent() {
        Log.d(TAG, "getRecent() - setHighScore()");
        setHighScore();
        Log.d(TAG, "getRecent() - return dataset");
        dumpData();
        return database.query("scores",new String[]{"_id","highscore"},null, null, null, null, null);
    }

    public Cursor getRecord(String id) {
        Log.d(TAG, "get record, id=" + id );
        return database.query("scores", null, "guid=" + id, null, null, null, null);
    }

    public void open() throws SQLException {
        database = databaseOpenHelper.getWritableDatabase();
        Log.d(TAG, "Open " + database.getPath());
    }

    public void updateRecord(String id, int score ) {
        Log.d(TAG, "Update Record");
        ContentValues c = new ContentValues();
        c.put("score", score);
        open();
        database.update("scores", c, "guid=" + id, null);
        close();
    }

    public DatabaseConnector(Context context) {
        databaseOpenHelper = new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper {
        public DatabaseOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createQuery =
                    "CREATE TABLE scores" +
                    "( _id integer primary key autoincrement, " +
                    "  guid TEXT," +
                    "  score INTEGER, " +
                    "  timestamp TEXT, " +
                    "  highscore TEXT  " +
                    ");";

            Log.d(TAG, "Create DB");
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}