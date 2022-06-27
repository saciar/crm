/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tables;

/**
 *
 * @author Evento
 */
public class RentabilidadTableItem {
    private Long nroppto;
    private String vendedor;
    private String cliente;
    private Double facturado;
    private Double CO;
    private Double subcontrataciones;
    private Double comisionLugar;
    private Double comisionTercero;
    private Double comisionComercial;
    private Double rentabilidad;
    private Double porcentajeRent;

    public Long getNroppto() {
        return nroppto;
    }

    public void setNroppto(Long nroppto) {
        this.nroppto = nroppto;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getFacturado() {
        return facturado;
    }

    public void setFacturado(Double facturado) {
        this.facturado = facturado;
    }

    public Double getCO() {
        return CO;
    }

    public void setCO(Double CO) {
        this.CO = CO;
    }

    public Double getSubcontrataciones() {
        return subcontrataciones;
    }

    public void setSubcontrataciones(Double subcontrataciones) {
        this.subcontrataciones = subcontrataciones;
    }

    public Double getComisionLugar() {
        return comisionLugar;
    }

    public void setComisionLugar(Double comisionLugar) {
        this.comisionLugar = comisionLugar;
    }

    public Double getComisionTercero() {
        return comisionTercero;
    }

    public void setComisionTercero(Double comisionTercero) {
        this.comisionTercero = comisionTercero;
    }

    public Double getComisionComercial() {
        return comisionComercial;
    }

    public void setComisionComercial(Double comisionComercial) {
        this.comisionComercial = comisionComercial;
    }

    public Double getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(Double rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public Double getPorcentajeRent() {
        return porcentajeRent;
    }

    public void setPorcentajeRent(Double porcentajeRent) {
        this.porcentajeRent = porcentajeRent;
    }
    
    
}
