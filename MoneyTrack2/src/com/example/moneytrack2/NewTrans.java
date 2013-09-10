package com.example.moneytrack2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;



public class NewTrans extends Activity{
	EditText reason,amount;
	DBController controller = new DBController(this);
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_new_trans);
	        reason = (EditText) findViewById(R.id.reason);
	        amount = (EditText) findViewById(R.id.amount);
	    }
	public void addNewTrans(View view) {
		HashMap<String, String> queryValues =  new  HashMap<String, String>();
		queryValues.put("reason", reason.getText().toString());
		queryValues.put("amount", amount.getText().toString());
		controller.insertTrans(queryValues);
		this.callHomeActivity(view);
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}	
}
