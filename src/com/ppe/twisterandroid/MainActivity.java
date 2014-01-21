package com.ppe.twisterandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private String m_Text = "";
	private Button SceltaGiocatori;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
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
        		        m_Text = input.getText().toString();
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
    
}