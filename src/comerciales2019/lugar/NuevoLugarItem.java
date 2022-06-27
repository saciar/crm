package comerciales2019.lugar;

import java.io.Serializable;

public class NuevoLugarItem implements Serializable, Cloneable {
	private int numeroSala;
	private String nombreSala;
	private String codSala;
	
	/**
	 * @return Returns the codSala.
	 */
	public String getCodSala() {
		return codSala;
	}
	/**
	 * @param codSala The codSala to set.
	 */
	public void setCodSala(String codSala) {
		this.codSala = codSala;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public int getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

}
