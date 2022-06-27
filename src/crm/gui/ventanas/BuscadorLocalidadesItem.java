package crm.gui.ventanas;

import java.io.Serializable;

public class BuscadorLocalidadesItem implements Serializable, Cloneable {
	private String pais;
	private String provincia;
	private String partido;
	private String localidad;
	private String idLocalidad;
	private String idPartido;
	private String idProvincia;
	private String idPais;
	private String codigo;

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BuscadorLocalidadesItem(){
		
	}

	/**
	 * @return Returns the localidad.
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @param localidad The localidad to set.
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * @return Returns the pais.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais The pais to set.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return Returns the partido.
	 */
	public String getPartido() {
		return partido;
	}

	/**
	 * @param partido The partido to set.
	 */
	public void setPartido(String partido) {
		this.partido = partido;
	}

	/**
	 * @return Returns the provincia.
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia The provincia to set.
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return Returns the idLocalidad.
	 */
	public String getIdLocalidad() {
		return idLocalidad;
	}

	/**
	 * @param idLocalidad The idLocalidad to set.
	 */
	public void setIdLocalidad(String idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	/**
	 * @return Returns the idPais.
	 */
	public String getIdPais() {
		return idPais;
	}

	/**
	 * @param idPais The idPais to set.
	 */
	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	/**
	 * @return Returns the idPartido.
	 */
	public String getIdPartido() {
		return idPartido;
	}

	/**
	 * @param idPartido The idPartido to set.
	 */
	public void setIdPartido(String idPartido) {
		this.idPartido = idPartido;
	}

	/**
	 * @return Returns the idProvincia.
	 */
	public String getIdProvincia() {
		return idProvincia;
	}

	/**
	 * @param idProvincia The idProvincia to set.
	 */
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

}
