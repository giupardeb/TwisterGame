package com.ppe.twisterandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;

public class InsertPlayersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_players);
		
		Intent intent = getIntent(); //richiamo l'intent dove ci sarà il dato che mi servirà..il numero dei giocatori
		String pkg = getPackageName();
		
		int players = intent.getIntExtra(pkg+".myInt",-1); // recupero il dato che mi serve
		EditText txt[] = new EditText[players];
		LinearLayout ll = (LinearLayout)findViewById(R.id.linearlayout);
		for(int i=0; i<players;i++){
			txt[i] = new EditText(this);
			ll.addView(txt[i]);
			txt[i].setText("Nome");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert_players, menu);
		return true;
	}

}
