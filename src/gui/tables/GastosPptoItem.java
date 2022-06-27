/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

/**
 *
 * @author saciar
 */
public class GastosPptoItem {
    private String nroPpto;
    private String nombreEvento;

    private String cliente;
    private String vendedor;
    private String fechaDesde;
    private String fechaHasta;
    private String subcontratados;
    private Long codigoSala;

    public Long getCodigoSala() {
        return codigoSala;
    }

    public void setCodigoSala(Long codigoSala) {
        this.codigoSala = codigoSala;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNroPpto() {
        return nroPpto;
    }

    public void setNroPpto(String nroPpto) {
        this.nroPpto = nroPpto;
    }

    public String getSubcontratados() {
        return subcontratados;
    }

    public void setSubcontratados(String subcontratados) {
        this.subcontratados = subcontratados;
    }

}
