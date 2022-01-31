package it.data;

public class ContoCorrente {
	
	private int idconto;
	private String intestatario;
	private float saldo;
	
	public ContoCorrente(int idconto, String intestatario, float saldo) {
		this.idconto = idconto;
		this.intestatario = intestatario;
		this.saldo = saldo;
	}

	public ContoCorrente() {}

	public int getIdconto() {
		return idconto;
	}

	public void setIdconto(int idconto) {
		this.idconto = idconto;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
}