package com.ppe.twisterandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int players = 0;
	private Button SceltaGiocatori;
	private Button classifica;
	private MyDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new MyDatabase(getApplicationContext());

		
		classifica = (Button)findViewById(R.id.btnClassifica);
		classifica.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(MainActivity.this, ActivityClassifica.class);
				String pkg = getPackageName();//per rendere univoci i nomi delle chiavi passate
				//è consigliato (la doc dice 'must') aggiungere il nome del nostro package davanti al nome
				i.putExtra(pkg+".myDb", db); //inseriamo i dati nell'intent da passare
				
				startActivity(i);
			}
			
		});
		
		
		
		
		SceltaGiocatori = (Button)findViewById(R.id.buttonGiocatori);
		SceltaGiocatori.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Quanti Giocatori Siete? (max 6)");

				// Set up the input
				final EditText input = new EditText(MainActivity.this);
				// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
				input.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_SIGNED);
				builder.setView(input);

				// Set up the buttons
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
					@Override
					public void onClick(DialogInterface dialog, int which) {
						players = Integer.parseInt(input.getText().toString()); //acquisisco il dato inserito dall'utente
						if(players<=6){
							Intent i = new Intent(MainActivity.this, InsertPlayersActivity.class); //creo il ponte per aprire l'altra activity
							String pkg = getPackageName();//per rendere univoci i nomi delle chiavi passate
							//è consigliato (la doc dice 'must') aggiungere il nome del nostro package davanti al nome
							i.putExtra(pkg+".myDb", db);
							i.putExtra(pkg+".myInt", players); //inseriamo i dati nell'intent da passare
							startActivity(i);
						}
						else{
							Toast toastExample;
							toastExample = Toast.makeText(getApplicationContext(),getResources().getString(R.string.MaxPlayers), Toast.LENGTH_LONG);
							toastExample.show();
						}

					}

				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

				builder.show();

			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public int GetPlayers(){
		return players;
	}
}