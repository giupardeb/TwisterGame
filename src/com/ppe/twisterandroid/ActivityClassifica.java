package com.ppe.twisterandroid;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityClassifica extends Activity {
	private MyDatabase db;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_classifica);
		Intent intent = getIntent(); //richiamo l'intent dove ci sarà il dato che mi servirà..il numero dei giocatori
		String pkg = getPackageName();
		db = (MyDatabase)intent.getSerializableExtra(pkg+".myDb"); // recupero il dato che mi serve

		ListView playersLv = (ListView)findViewById(R.id.playersLv);

		db.open();  //apriamo il db


		if(db.fetchPlayers().getCount()==0){ //controlla se ci sono elementi nel DB

			Toast toastExample;
			toastExample = Toast.makeText(getApplicationContext(),getResources().getString(R.string.DbEmpty), Toast.LENGTH_LONG);
			toastExample.show();
		}
		else{
			
			Cursor c = db.fetchPlayers(); // query
			startManagingCursor(c);

			SimpleCursorAdapter adapter = new SimpleCursorAdapter( //semplice adapter per i cursor
					this, 
					R.layout.player, //il layout di ogni riga/prodotto 
					c, 
					new String[]{MyDatabase.PlayersMetaData.PLAYER_NAME_KEY,MyDatabase.PlayersMetaData.PLAYER_POINT_KEY},//queste colonne 
					new int[]{R.id.nameTv,R.id.pointTv});//in queste views


			playersLv.setAdapter(adapter); //la listview ha questo adapter


			/*
			//qui vediamo invece come reperire i dati e usarli, in questo caso li stampiamo in una textview

			int nameCol = c.getColumnIndex(MyDatabase.PlayersMetaData.PLAYER_NAME_KEY);  //indici delle colonne
			int priceCol = c.getColumnIndex(MyDatabase.PlayersMetaData.PLAYER_POINT_KEY);       

			if(c.moveToFirst()){  //se va alla prima entry, il cursore non è vuoto
				do {
					playersLv.append("PLayer Name: "+c.getString(nameCol)+", Points: "+c.getInt(priceCol)+"\n"); //estrazione dei dati dalla entry del cursor

				} while (c.moveToNext());//iteriamo al prossimo elemento
			}
			 */
		}
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_classifica, menu);
		return true;
	}

}
