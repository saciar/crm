package gui.inventario.componentes.listas;

public class SalaListItem {
	
	private String nombreSala;
	private String codigoSala;
	private String orden;
	
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public String getCodigoSala() {
		return codigoSala;
	}
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	public String toString(){
		return nombreSala;
	}
}
