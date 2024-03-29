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

	private EditText txt[];
	private Button gioca;
	private int numero_players;
	private Giocatore players[];
	private MyDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_players);

		Intent intent = getIntent(); //richiamo l'intent dove ci sarà il dato che mi servirà..il numero dei giocatori
		String pkg = getPackageName();

		numero_players = intent.getIntExtra(pkg+".myInt",-1); // recupero il dato che mi serve
		db = (MyDatabase)intent.getSerializableExtra(pkg+".myDb"); // recupero il dato che mi serve
		
		players = new Giocatore[numero_players];
		txt = new EditText[numero_players];
		LinearLayout ll = (LinearLayout)findViewById(R.id.linearlayout);
		for(int i=0; i<numero_players;i++){
			txt[i] = new EditText(this);
			txt[i].setHint("Inserisci il nome del Giocatore");
			txt[i].setSingleLine(); //la edit text avrà una singola linea, disattivando il tasto enter che può creare stringhe vuote non gestibili
			ll.addView(txt[i]);
		}

		gioca = (Button)findViewById(R.id.btnGioca);
		
		gioca.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(CheckEmptyEditText(txt,numero_players)){
					//fai apparire un toast che dice che c'è un edit text vuoto
					Toast toastExample;
					toastExample = Toast.makeText(getApplicationContext(),getResources().getString(R.string.Empty), Toast.LENGTH_LONG);
					toastExample.show();
				}
				else{
					// apri activity di gioco..oltre a creare oggetti giocatori e inserirli nel DB
					
					if (CheckGiocatoriInDb(txt)){ //controlla se ci sono giocatori con nome uguale nel DB
						Toast toastExample;
						toastExample = Toast.makeText(getApplicationContext(),getResources().getString(R.string.hello_world), Toast.LENGTH_LONG);
						toastExample.show();
					}
					else{
						CreaGiocatori(txt); //passo la EdiText, perché la lunghezza di tale array corrisponde al numero dei giocatori
						InsertPlayersDB(players);
						Toast toastExample;
						toastExample = Toast.makeText(getApplicationContext(),getResources().getString(R.string.hello_world), Toast.LENGTH_LONG);
						toastExample.show();
					}
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
			if(txt[i].getText().toString().equals("")) //devi mettere il tostring per convertire in stringa ciò che l'utente ha inserito
				vuoto = true;

		return vuoto;
	}
	
	private void CreaGiocatori(EditText txt[]) {
		for(int i=0; i<numero_players; i++)
			players[i] = new Giocatore(txt[i].getText().toString());
		
	}
	
	private void InsertPlayersDB(Giocatore[] players) {
		// TODO Auto-generated method stub
		db.open();
		for(int i=0;i<players.length;i++)
			db.insertPlayer(players[i].GetNome(), players[i].getPunteggio());
		db.close();
	}
	
	private boolean CheckGiocatoriInDb(EditText[] txt) {
		// TODO Auto-generated method stub
		
	}
}
