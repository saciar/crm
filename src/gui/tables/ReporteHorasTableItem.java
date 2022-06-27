/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class ReporteHorasTableItem {
    private String os;
    private String desde;
    private String hasta;
    private String hsNormales;
    private String hsExtras;
    private double hsNormalesDecimales;
    private double hsExtrasDecimales;
    private String hsExtras100;
    private double hsExtrasDecimales100;
    private String nombre;

    public String getHsExtras100() {
        return hsExtras100;
    }

    public void setHsExtras100(String hsExtras50) {
        this.hsExtras100 = hsExtras50;
    }

    public double getHsExtrasDecimales100() {
        return hsExtrasDecimales100;
    }

    public void setHsExtrasDecimales100(double hsExtrasDecimales50) {
        this.hsExtrasDecimales100 = hsExtrasDecimales50;
    }

    public double getHsNormalesDecimales() {
        return hsNormalesDecimales;
    }

    public void setHsNormalesDecimales(double hsNormalesDecimales) {
        this.hsNormalesDecimales = hsNormalesDecimales;
    }

    public double getHsExtrasDecimales() {
        return hsExtrasDecimales;
    }

    public void setHsExtrasDecimales(double hsExtrasDecimales) {
        this.hsExtrasDecimales = hsExtrasDecimales;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDesde() {
        return desde;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getHsNormales() {
        return hsNormales;
    }

    public void setHsNormales(String hsNormales) {
        this.hsNormales = hsNormales;
    }

    public String getHsExtras() {
        return hsExtras;
    }

    public void setHsExtras(String hsExtras) {
        this.hsExtras = hsExtras;
    }
    
    
}
