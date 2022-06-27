package comerciales2019.salas.servicios;

public class SalaServicioItem {
	private int cantidad;
	private String familia;
	private String servicio;
	private String familiaCodigo;
	private String servicioCodigo;
	private int dias;
	private int descuento;
	private Boolean subContratado;
	private Boolean opcional;
	private Boolean unicoDia;
	private double total;
	private double precioLista;	
	private String fechaAlta;
	private String editar;
	private String eliminar;

	public String getEliminar() {
		return eliminar;
	}
	public void setEliminar(String eliminar) {
		this.eliminar = eliminar;
	}
	public String getEditar() {
		return editar;
	}
	public void setEditar(String editar) {
		this.editar = editar;
	}
	/**
	 * @return Returns the cantidad.
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad The cantidad to set.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return Returns the dias.
	 */
	public int getDias() {
		return dias;
	}
	/**
	 * @param dias The dias to set.
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}
	/**
	 * @return Returns the familia.
	 */
	public String getFamilia() {
		return familia;
	}
	/**
	 * @param familia The familia to set.
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	/**
	 * @return Returns the familiaCodigo.
	 */
	public String getFamiliaCodigo() {
		return familiaCodigo;
	}
	/**
	 * @param familiaCodigo The familiaCodigo to set.
	 */
	public void setFamiliaCodigo(String familiaCodigo) {
		this.familiaCodigo = familiaCodigo;
	}
	/**
	 * @return Returns the servicio.
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @param servicio The servicio to set.
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return Returns the servicioCodigo.
	 */
	public String getServicioCodigo() {
		return servicioCodigo;
	}
	/**
	 * @param servicioCodigo The servicioCodigo to set.
	 */
	public void setServicioCodigo(String servicioCodigo) {
		this.servicioCodigo = servicioCodigo;
	}
	/**
	 * @return Returns the totalSinDescuento.
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param totalSinDescuento The totalSinDescuento to set.
	 */
	public void setTotal(double totalSinDescuento) {
		this.total = totalSinDescuento;
	}

	/*public String getSubContratado() {
		return subContratado;
	}
	public void setSubContratado(String subContratado) {
		this.subContratado = subContratado;
	}
	public String getOpcional() {
		return opcional;
	}
	public void setOpcional(String opcional) {
		this.opcional = opcional;
	}
	public String getUnicoDia() {
		return unicoDia;
	}
	public void setUnicoDia(String unicoDia) {
		this.unicoDia = unicoDia;
	}*/
	
	public Object clone(){
		SalaServicioItem clon = new SalaServicioItem();
		clon.cantidad = cantidad;
		clon.familia = familia;
		clon.servicio = servicio;
		clon.familiaCodigo = familiaCodigo;
		clon.servicioCodigo = servicioCodigo;
		clon.dias = dias;
		clon.subContratado = subContratado;
		clon.opcional = opcional;
		clon.total = total;
		clon.precioLista = precioLista;
		clon.descuento = descuento;
		return clon;
	}
	//-----------------------------------------------------------------------------------------------------------------------

	public Boolean getSubContratado() {
		return subContratado;
	}
	public void setSubContratado(Boolean subContratado) {
		this.subContratado = subContratado;
	}
	public Boolean getOpcional() {
		return opcional;
	}
	public void setOpcional(Boolean opcional) {
		this.opcional = opcional;
	}
	public Boolean getUnicoDia() {
		return unicoDia;
	}
	public void setUnicoDia(Boolean unicoDia) {
		this.unicoDia = unicoDia;
	}
	public double getPrecioLista() {
		return precioLista;
	}
	
	public void setPrecioLista(double precioLista) {
		this.precioLista = precioLista;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
