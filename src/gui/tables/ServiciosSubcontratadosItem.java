/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Proveedor;

/**
 *
 * @author saciar
 */
public class ServiciosSubcontratadosItem {

    private String cantidad;
    private String nombre;
    private String dias;
    private String descuento;
    private Double precio_vta;
    private Double costo;
    private Proveedor proveedor;
    private Long codSubcontratado;
    private Long codProveedor;
    private Boolean isSubcontratado;
    private Long codServicioPpto;
    private String estado;
    private Integer codEstado;
    private String nombreSala;
    private String modalidad;

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }
    private String codigoServicio;
    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    
    public Integer getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Integer codEstado) {
        this.codEstado = codEstado;
    }
    private Long tableItemId;
    
//    private Ppto_Sala_Servicio pptoSalaServicio;
//
//    public Ppto_Sala_Servicio getPptoSalaServicio() {
//        return pptoSalaServicio;
//    }
//
//    public void setPptoSalaServicio(Ppto_Sala_Servicio pptoSalaServicio) {
//        this.pptoSalaServicio = pptoSalaServicio;
//    }   
   
    public Long getTableItemId() {
        return tableItemId;
    }

    public void setTableItemId(Long tableItemId) {
        this.tableItemId = tableItemId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Boolean isIsSubcontratado() {
        return isSubcontratado;
    }

    public void setIsSubcontratado(Boolean isSubcontratado) {
        this.isSubcontratado = isSubcontratado;
    }
    
    public Long getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(Long codProveedor) {
        this.codProveedor = codProveedor;
    }

    public Long getCodSubcontratado() {
        return codSubcontratado;
    }

    public void setCodSubcontratado(Long codSubcontratado) {
        this.codSubcontratado = codSubcontratado;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Long getCodServicioPpto() {
        return codServicioPpto;
    }

    public void setCodServicioPpto(Long codServicioPpto) {
        this.codServicioPpto = codServicioPpto;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio_vta() {
        return precio_vta;
    }

    public void setPrecio_vta(Double precio_vta) {
        this.precio_vta = precio_vta;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
