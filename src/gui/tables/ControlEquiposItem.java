/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

/**
 *
 * @author saciar
 */
public class ControlEquiposItem {
    private String codigoEquipo;
    private String codigoBarras;
    private String nombre;
    private String noSerie;
    private String idEgreso;

    public String getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(String idEgreso) {
        this.idEgreso = idEgreso;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(String codigo) {
        this.codigoEquipo = codigo;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
