package com.ppe.twisterandroid;

import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase implements Serializable{  

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197667667965365342L;
	SQLiteDatabase mDb;
	DbHelper mDbHelper;
	Context mContext;
	private static final String DB_NAME = "TwisterGame";//nome del db
	private static final int DB_VERSION = 1; //numero di versione del nostro db
	private static final String PLAYERS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "  //codice sql di creazione della tabella
			+ PlayersMetaData.PLAYERS_TABLE + " (" 
			+ PlayersMetaData.ID + " integer primary key autoincrement, "
			+ PlayersMetaData.PLAYER_NAME_KEY + " text not null, "
			+ PlayersMetaData.PLAYER_POINT_KEY + " integer not null);";
	
	
	public MyDatabase(Context ctx){
		mContext=ctx;
		mDbHelper=new DbHelper(ctx, DB_NAME, null, DB_VERSION);	  //quando istanziamo questa classe, istanziamo anche l'helper (vedi sotto)	
	}

	public void open(){  //il database su cui agiamo è leggibile/scrivibile
		mDb = mDbHelper.getWritableDatabase();

	}

	public void close(){ //chiudiamo il database su cui agiamo
		mDb.close();
	}


	//i seguenti 2 metodi servono per la lettura/scrittura del db. aggiungete e modificate a discrezione

	public void insertPlayer(String name,int points){ //metodo per inserire i dati
		ContentValues cv = new ContentValues();
		cv.put(PlayersMetaData.PLAYER_NAME_KEY, name);
		cv.put(PlayersMetaData.PLAYER_POINT_KEY, points);
		mDb.insert(PlayersMetaData.PLAYERS_TABLE, null, cv);
	}

	public Cursor fetchPlayers(){ //metodo per fare la query di tutti i dati
		return mDb.query(PlayersMetaData.PLAYERS_TABLE, null,null,null,null,null,null);		
	}

	static class PlayersMetaData {  // i metadati della tabella, accessibili ovunque
		static final String PLAYERS_TABLE = "products";
		static final String ID = "_id";
		static final String PLAYER_NAME_KEY = "name";
		static final String PLAYER_POINT_KEY = "points";
	}

	
	private class DbHelper extends SQLiteOpenHelper { //classe che ci aiuta nella creazione del db

		public DbHelper(Context context, String name, CursorFactory factory,int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) { //solo quando il db viene creato, creiamo la tabella
			_db.execSQL(PLAYERS_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			//qui mettiamo eventuali modifiche al db, se nella nostra nuova versione della app, il db cambia numero di versione

		}

	}
}
