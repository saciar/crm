package gui.inventario.componentes.tablas;

import crm.libraries.abm.entities.Equipamientos;

public class SalidasTableItem {
	private String codigo;
    private String codigoBarras;
    private String modelo;
    private String nombreFamilia;
    private String nombreSubfamilia;
    private String nombreMarca;
    
    private String codigoEgresoEquipo;
    private String fechaEgreso;
    private String codigoUsuarioEgreso;
    private String codigoTransporteEgreso;
    private String codigoChoferEgreso;
    
	public String getFechaEgreso() {
		return fechaEgreso;
	}
	public void setFechaEgreso(String fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	public String getCodigoUsuarioEgreso() {
		return codigoUsuarioEgreso;
	}
	public void setCodigoUsuarioEgreso(String codigoUsuarioEgreso) {
		this.codigoUsuarioEgreso = codigoUsuarioEgreso;
	}
	public String getCodigoTransporteEgreso() {
		return codigoTransporteEgreso;
	}
	public void setCodigoTransporteEgreso(String codigoTransporteEgreso) {
		this.codigoTransporteEgreso = codigoTransporteEgreso;
	}
	public String getCodigoChoferEgreso() {
		return codigoChoferEgreso;
	}
	public void setCodigoChoferEgreso(String codigoChoferEgreso) {
		this.codigoChoferEgreso = codigoChoferEgreso;
	}
	public String getCodigoEgresoEquipo() {
		return codigoEgresoEquipo;
	}
	public void setCodigoEgresoEquipo(String codigoEgresoEquipo) {
		this.codigoEgresoEquipo = codigoEgresoEquipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNombreFamilia() {
		return nombreFamilia;
	}
	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}
	public String getNombreSubfamilia() {
		return nombreSubfamilia;
	}
	public void setNombreSubfamilia(String nombreSubfamilia) {
		this.nombreSubfamilia = nombreSubfamilia;
	}
	public String getNombreMarca() {
		return nombreMarca;
	}
	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}
    
	public Equipamientos toEquipamientoEntity(){
		Equipamientos eq = new Equipamientos();
		eq.setCodigo(String.valueOf(codigo));
		eq.setCodigoBarras(String.valueOf(codigoBarras));
		eq.setModelo(modelo);
		return eq;
	}
}
