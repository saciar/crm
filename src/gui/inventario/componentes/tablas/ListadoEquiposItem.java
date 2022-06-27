package gui.inventario.componentes.tablas;

import crm.libraries.abm.entities.Equipamientos;

public class ListadoEquiposItem {
	private int codigo;
    private int deposito;
    private int subfamilia;
    private int marca;
    private String nroSerie;
    private String activo;
    private String observaciones;
    private int estado;
    private int codigoBarras;
    private String modelo;
    private int alto;
    private int ancho;
    private int largo;
    private int peso;
    private String nombreFamilia;
    private String nombreSubfamilia;
    private String nombreMarca;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getDeposito() {
		return deposito;
	}
	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}
	public int getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(int subfamilia) {
		this.subfamilia = subfamilia;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public String getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getLargo() {
		return largo;
	}
	public void setLargo(int largo) {
		this.largo = largo;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
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
		eq.setActivo(activo);
		eq.setAlto(alto);
		eq.setAncho(ancho);
		eq.setCodigo(String.valueOf(codigo));
		eq.setCodigoBarras(String.valueOf(codigoBarras));
		eq.setDeposito(String.valueOf(deposito));
		eq.setEstado(String.valueOf(estado));
		eq.setLargo(largo);
		eq.setMarca(String.valueOf(marca));
		eq.setModelo(modelo);
		eq.setNroSerie(nroSerie);
		eq.setObservaciones(observaciones);
		eq.setPeso(peso);
		eq.setSubfamilia(String.valueOf(subfamilia));
		return eq;
	}
}
