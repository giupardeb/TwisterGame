package com.ppe.twisterandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class InsertPlayersActivity extends Activity {

	EditText txt[];
	Button gioca;
	int players;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_players);

		Intent intent = getIntent(); //richiamo l'intent dove ci sarà il dato che mi servirà..il numero dei giocatori
		String pkg = getPackageName();


		players = intent.getIntExtra(pkg+".myInt",-1); // recupero il dato che mi serve
		txt = new EditText[players];
		LinearLayout ll = (LinearLayout)findViewById(R.id.linearlayout);
		for(int i=0; i<players;i++){
			txt[i] = new EditText(this);
			ll.addView(txt[i]);
			txt[i].setText("Nome");
		}

		gioca = (Button)findViewById(R.id.btnGioca);

		gioca.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// ERRORE..NON RICHIAMA IL METODO SOTTOSTANTE 
				if(CheckEmptyEditText(txt,players)){
					//fai apparire un toast che dice che c'è un edit text vuoto
					Toast toastExample;
					toastExample = Toast.makeText(getApplicationContext(),getResources().getString(R.string.Empty), Toast.LENGTH_LONG);
					toastExample.show();
				}
				else{
					// apri activity di gioco..oltre a creare oggetti giocatori e inserirli nel DB
				}
			}


		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert_players, menu);
		return true;
	}

	//Tale metodo è essenziale per controllare se tutte le Edittext sono riempite con il nome
	private boolean CheckEmptyEditText(EditText[] txt,int players) {
		boolean vuoto = false;
		
		for(int i=0; i<players && vuoto == false;i++)
			if(txt[i].getText().equals(""))
				vuoto = true;

		return vuoto;
	}

}
