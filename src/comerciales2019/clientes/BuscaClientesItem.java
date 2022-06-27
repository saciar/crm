package comerciales2019.clientes;

import java.io.Serializable;

public class BuscaClientesItem implements Serializable, Cloneable {
	
	private String codigo;
	
	private String razonSocial;
	
	private String nombreFantasia;
	
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public BuscaClientesItem(){
		
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String nombreLugar) {
		this.razonSocial = nombreLugar;
	}

}
