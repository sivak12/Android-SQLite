package com.example.moneytrack2;


import java.util.ArrayList;
import java.util.HashMap;

import com.example.moneytrack2.R;
import com.example.moneytrack2.DBController;
import com.example.moneytrack2.NewTrans;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {

	DBController controller = new DBController(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<HashMap<String, String>> transList =  controller.getAllTrans();
		if(transList.size()!=0) {
			ListView lv = getListView();
			 
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,transList, R.layout.view_trans_entry, new String[] { "reason","amount","trans_time"}, new int[] {R.id.reason, R.id.amount,R.id.trans_time}); 
			setListAdapter(adapter);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void showAddForm(View view) {
		Intent objIntent = new Intent(getApplicationContext(), NewTrans.class);
		startActivity(objIntent);
	}
    
}
