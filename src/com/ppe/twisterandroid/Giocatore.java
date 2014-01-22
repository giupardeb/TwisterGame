package com.ppe.twisterandroid;

public class Giocatore {

	static final int ARTI = 4;
	static final int COLORI = 4;
	private boolean MDX;
	private boolean MSX;
	private boolean PDX;
	private boolean PSX;
	private int punteggio;

	private String nome;
	
	public Giocatore(String nome){
		this.nome = nome;
		this.MDX = false;
		this.MSX = false;
		this.PDX = false;
		this.PSX = false;
		this.setPunteggio(0);
	}
	
	public void SetMDX(boolean b){
		this.MDX = b;
	}
	public void SetMSX(boolean b){
		this.MSX = b;
	}
	public void SetPDX(boolean b){
		this.PDX = b;
	}
	public void SetPSX(boolean b){
		this.PSX = b;
	}
	
	public boolean GetMDX(){
		return this.MDX;
	}
	public boolean GetMSX(){
		return this.MSX;
	}
	public boolean GetPDX(){
		return this.PDX;
	}
	public boolean GetPSX(){
		return this.PSX;
	}
	
	public String GetStringMDX(){
		return "manodestra";
	}
	public String GetStringMSX(){
		return "manosinistra";
	}
	public String GetStringPDX(){
		return "piededestro";
	}
	public String GetStringPSX(){
		return "piedesinistro";
	}
	
	public String GetNome(){
		return nome;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
}
