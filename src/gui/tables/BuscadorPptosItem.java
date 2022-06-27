/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import java.io.Serializable;

/**
 *
 * @author sergio
 */
public class BuscadorPptosItem implements Serializable, Cloneable {
	private Long numeroPpto;
	private String estado;
	private String vendedor;
	private String cliente;
	private String nombreEvento;
	private String fechaInicio;
	private String unidadAdm;
	
	public String getUnidadAdm() {
		return unidadAdm;
	}

	public void setUnidadAdm(String unidadAdm) {
		this.unidadAdm = unidadAdm;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String activo) {
		this.fechaInicio = activo;
	}

	public BuscadorPptosItem(){
		
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public Long getNumeroPpto() {
		return numeroPpto;
	}

	public void setNumeroPpto(Long numeroPpto) {
		this.numeroPpto = numeroPpto;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
    
}
