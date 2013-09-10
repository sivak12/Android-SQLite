package com.example.moneytrack2;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	public DBController(Context applicationcontext) {
        super(applicationcontext, "androidsqlite.db", null, 1);
        Log.d(LOGCAT,"Created");
    }
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		
		//query = "DROP TABLE IF EXISTS moneytrans";
		//database.execSQL(query);
        //onCreate(database);
		
		query = "CREATE TABLE moneytrans ( transId INTEGER PRIMARY KEY, reason TEXT, amount INTEGER, trans_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        database.execSQL(query);
        Log.d(LOGCAT,"moneytrans Created");
	}
	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS moneytrans";
		database.execSQL(query);
        onCreate(database);
	}
	
	/*
	public void insertAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("animalName", queryValues.get("animalName"));
		database.insert("animals", null, values);
		database.close();
	}
	*/
	
	public void insertTrans(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("reason", queryValues.get("reason"));
		values.put("amount", queryValues.get("amount"));
		database.insert("moneytrans", null, values);
		database.close();
	}
	
	
	public int updateAnimal(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("animalName", queryValues.get("animalName"));
	    return database.update("animals", values, "animalId" + " = ?", new String[] { queryValues.get("animalId") });
	    //String updateQuery = "Update  words set txtWord='"+word+"' where txtWord='"+ oldWord +"'";
	    //Log.d(LOGCAT,updateQuery);
	    //database.rawQuery(updateQuery, null);
	    //return database.update("words", values, "txtWord  = ?", new String[] { word });
	}
	
	public void deleteAnimal(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  animals where animalId='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	
	public ArrayList<HashMap<String, String>> getAllTrans() {
		
		ArrayList<HashMap<String, String>> transList;
		transList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT  reason,amount, datetime(trans_time,'localtime') loc_time FROM moneytrans order by loc_time desc";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("reason", cursor.getString(0));
	        	map.put("amount", cursor.getString(1));
	        	map.put("trans_time", cursor.getString(2));
                transList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return transList;
		
		
		
		
		
		/*
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM animals";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("animalId", cursor.getString(0));
	        	map.put("animalName", cursor.getString(1));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return wordList;
	    */
	}
	
	public HashMap<String, String> getAnimalInfo(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM animals where animalId='"+id+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {
					//HashMap<String, String> map = new HashMap<String, String>();
	        	wordList.put("animalName", cursor.getString(1));
				   //wordList.add(map);
	        } while (cursor.moveToNext());
	    }				    
	return wordList;
	}	
}

