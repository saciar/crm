package comerciales2019.lugar;

import java.io.Serializable;

public class BuscaLugaresItem implements Serializable, Cloneable {
	
	private String codigo;
	
	private String nombreLugar;

	public BuscaLugaresItem(){
		
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreLugar() {
		return nombreLugar;
	}

	public void setNombreLugar(String nombreLugar) {
		this.nombreLugar = nombreLugar;
	}

}
