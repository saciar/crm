/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.rentabilidad;

import crm.client.managers.PresupuestosManager;
import crm.client.managers.SmsManager;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import gui.gastos.Util;
import gui.tables.RentabilidadTableItem;
import gui.tables.RentabilidadTableModel;
import gui.tables.ReporteHorasTableItem;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Evento
 */
public class RentabilidadPrincipal extends javax.swing.JDialog {

    /**
     * Creates new form RentabilidadPrincipal
     */
    public RentabilidadPrincipal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private double calcularCOReal(String nroppto) {
        try {
            StringBuffer detalle = new StringBuffer();

            Object[] presupuestos = null;

            presupuestos = PresupuestosManager.instance().buscarParaReportesRentabilidadCostos(nroppto);

            double armado = 0.0;
            double operacion = 0.0;
            double cantPersonasArm = 0.0;
            double cantPersonasOp = 0.0;
            double totalCostoEquipo = 0.0;

            double hsExtras = 0;
            double hsExtrasAl50 = 0;
            double hsNormales = 0;
            long hsPruebas = 0;

            long codServicio = 0;
            int cantidad = 0;
            String fechaDesde = "";
            String fechaHasta = "";
            double armadoXServ = 0d;
            double costoHsHombreXServ = 0d;
            double operacionXServ = 0d;
            double costoUnitXServ = 0d;
            double duracionXServ = 0d;
            double horasFijas = 0d;
            double costoHsHombreNormal = 0d;
            double costoHsHombreExtra = 0d;
            double costoHsHombreExtraAl50 = 0d;
            double horasPruebasXDefault = 0d;
            String fechaPrueba = "";
            String fechaDesarme = "";

            if (presupuestos != null) {
                //System.out.println("-----------------------------------PPTO "+nroppto+" REAL--------------------------------------------------------------------------");
                detalle.append("------------------------Presupuesto " + nroppto + " REAL------------------------------\n");
                detalle.append("-----------------------------------------------------------------------------\n");
                for (int i = 0; i < presupuestos.length; i++) {
                    Object[] p = (Object[]) presupuestos;
                    Object[] presupuestoDato = (Object[]) p[i];

                    codServicio = (Long) presupuestoDato[0];
                    cantidad = (Integer) presupuestoDato[2];
                    fechaDesde = (String) presupuestoDato[3];
                    fechaHasta = (String) presupuestoDato[4];
                    armadoXServ = (Double) presupuestoDato[5];
                    costoHsHombreXServ = (Double) presupuestoDato[6];
                    operacionXServ = (Double) presupuestoDato[7];
                    costoUnitXServ = (Double) presupuestoDato[8];
                    duracionXServ = (Double) presupuestoDato[9];
                    horasFijas = (Double) presupuestoDato[10]; //     horas de carga de camion, transalado, etc = 5.83
                    costoHsHombreNormal = (Double) presupuestoDato[11]; //    valor de la hora normal trabajada = 54.55
                    costoHsHombreExtra = (Double) presupuestoDato[12]; //    valor de la hora extra al 100 trabajada = 178.95
                    costoHsHombreExtraAl50 = 134.21d;					// valor de la hora extra al 50 trabajada= 134.21
                    horasPruebasXDefault = (Double) presupuestoDato[13]; //  cant de horas de pruebas si no tiene cargada horas de pruebas
                    fechaPrueba = (String) presupuestoDato[14];
                    fechaDesarme = (String) presupuestoDato[15];

                    //ARMADO
                    //System.out.println("Servicio cod. "+codServicio+"------------------------------");
                    detalle.append("------------------------Servicio cod. " + codServicio + "------------------------------\n");
                    //System.out.println("-----------------------------------------------------------");
                    //System.out.println("Cant.item: "+cantidad+"\n");
                    detalle.append("Cant.item: " + cantidad + "\n");
                    //System.out.println("Campo Armado: "+armadoXServ);
                    detalle.append("Campo Armado: " + armadoXServ + "\n");
                    double personaNecesarias = cantidad * armadoXServ;
                    //System.out.println("personas necesarias armado: "+personaNecesarias);
                    detalle.append("personas necesarias armado: " + personaNecesarias + "\n");
                    cantPersonasArm += personaNecesarias;

                    //OPERACION
                    //System.out.println("Campo operacion: "+operacionXServ);
                    detalle.append("Campo operacion: " + operacionXServ + "\n");
                    double personasNecesariasOp = cantidad * operacionXServ;
                    //System.out.println("personas necesarias Operacion: "+personasNecesariasOp);
                    detalle.append("personas necesarias Operacion: " + personasNecesariasOp + "\n");
                    cantPersonasOp += personasNecesariasOp;

                    //COSTO EQUIPO
                    hsPruebas = DateDiff.calcularHorasTotales(fechaPrueba, fechaDesde);
                    if (hsPruebas <= 0) {
                        hsPruebas = ((Double) horasPruebasXDefault).longValue();
                    }

                    long cantHorasTotales = DateDiff.calcularHorasTotales(fechaDesde, fechaHasta);
                    //System.out.println("Cant. de horas totales de equipo funcionando: "+cantHorasTotales);
                    detalle.append("Cant. de horas totales de equipo funcionando: " + cantHorasTotales + "\n");
                    double valCURedondeado = 0;
                    if (duracionXServ != 0) {
                        //System.out.println("Costo unitario x hora: "+(Double)presupuestoDato[8] / (Double)presupuestoDato[9]);
                        valCURedondeado = (costoUnitXServ / duracionXServ) * (cantHorasTotales + horasFijas + hsPruebas);
                    } else {
                        //System.out.println("Costo unitario x hora: 0.0");
                        valCURedondeado = 0;
                    }
                    //System.out.println("Costo unitario: "+round(valCURedondeado,2));
                    detalle.append("Costo unitario: " + round(valCURedondeado, 2) + "\n\n");
                    totalCostoEquipo += round(valCURedondeado, 2);

                }

                double[] hs = horasReales(Long.valueOf(nroppto));

                double cantHorasNormalesOp = +hs[0];
                double cantHorasExtrasOpAl50 = +hs[1];
                double cantHorasExtrasOp = +hs[2];

                //System.out.println("---------------------------------------------------------------------------------------");
                detalle.append("---------------------------------------------------------------------------------------\n");
                //System.out.println("Cant. de horas de operacion normales totales: "+cantHorasNormalesOp);
                detalle.append("Cant. de horas de operacion normales totales: " + cantHorasNormalesOp + "\n");
                //System.out.println("Cant. de horas de operacion extras al 50% totales: "+cantHorasExtrasOpAl50);
                detalle.append("Cant. de horas de operacion extras al 50% totales: " + cantHorasExtrasOpAl50 + "\n");
                //System.out.println("Cant. de horas de operacion extras al 100% totales: "+cantHorasExtrasOp);
                detalle.append("Cant. de horas de operacion extras al 100% totales: " + cantHorasExtrasOp + "\n");

                //System.out.println("-----------------------------------------------------------");
                //System.out.println("-----------------------------------------------------------");
                armado = round(cantPersonasArm, 0) * costoHsHombreNormal * horasFijas;

                hsNormales = round(cantHorasNormalesOp * costoHsHombreNormal, 2);
                hsExtras = round(cantHorasExtrasOp * costoHsHombreExtra, 2);
                //hsExtrasAl50 = round(cantHorasExtrasOpAl50 * (costoHsHombreNormal * 1.5),2);
                hsExtrasAl50 = round(cantHorasExtrasOpAl50 * costoHsHombreExtraAl50, 2);

                operacion = round(hsExtras + hsExtrasAl50 + hsNormales, 2);

                //System.out.println("Cant de $ de hs extras: $"+hsExtras);
                detalle.append("Cant de $ de hs extras: $" + hsExtras + "\n");
                totalRealHsExtra100 += hsExtras;
                //System.out.println("Cant de $ de hs extras al 50%: $"+hsExtrasAl50);
                detalle.append("Cant de $ de hs extras al 50%: $" + hsExtrasAl50 + "\n");
                totalRealHsExtra50 += hsExtrasAl50;
                //System.out.println("Cant de $ de hs normales: $"+hsNormales+"\n");
                detalle.append("Cant de $ de hs normales: $" + hsNormales + "\n\n");
                totalRealHs += hsNormales;
                //System.out.println("---------------------------------------------------------------------------------------");
                detalle.append("---------------------------------------------------------------------------------------\n");
                //System.out.println("-------------------------------------TOTALES-------------------------------------------");
                detalle.append("-------------------------------------TOTALES-------------------------------------------\n");

                /*System.out.println("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0));
				detalle.append("PERSONAS NECESARIAS ARMADO: "+round(cantPersonasArm,0)+"\n");
				System.out.println("PERSONAS NECESARIAS OP: "+cantPersonasOp);
				detalle.append("PERSONAS NECESARIAS OP: "+cantPersonasOp+"\n");*/
                //System.out.println("TOTAL ARMADO EVENTO: "+armado);
                detalle.append("TOTAL ARMADO EVENTO: " + armado + "\n");
                //System.out.println("TOTAL OPERACION EVENTO: "+operacion);
                detalle.append("TOTAL OPERACION EVENTO: " + operacion + "\n");
                //System.out.println("TOTAL COSTO EQUIPOS: "+totalCostoEquipo);
                detalle.append("TOTAL COSTO EQUIPOS: " + totalCostoEquipo + "\n");

                //System.out.println("-------------------------------------------------------------------------------------");
                detalle.append("-----------------------------------------------------------------------------------------\n");

                double total = armado + operacion + totalCostoEquipo;
                //System.out.println("TOTAL --------> "+total);
                detalle.append("TOTAL --------> " + total + "\n");

                //todosDetallesReal.put(Long.valueOf(nroppto), detalle);
                return total;
            } else {
                return -1.0d;
            }
        } catch (RemoteException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return -1.0d;
        } catch (Exception exc) {
            // TODO Auto-generated catch block
            //System.out.println("numero de presupuesto: "+nroppto);
            exc.printStackTrace();
            return -1.0d;
        }
    }

    final double MILLSECS_PER_HOUR = 60 * 60 * 1000;

    private double getHoraExtraAl100(Date fechaEntrada, Date fechaSalida) {
        double result = 0.0;

        Calendar entrada = Calendar.getInstance();
        entrada.setTime(fechaEntrada);
        Calendar salida = Calendar.getInstance();
        salida.setTime(fechaSalida);
        //LA ENTRADA ES SABADO
        if (entrada.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaEntrada);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            sabadoMediodia.getTime();
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaEntrada);
            domingoNoche.add(Calendar.DAY_OF_MONTH, 1);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            domingoNoche.set(Calendar.SECOND, 59);
            domingoNoche.getTime();
            if (entrada.before(sabadoMediodia)) {
                if (salida.after(sabadoMediodia)) {
                    if (salida.before(domingoNoche)) {
                        //CASO 2 --------|--(--*---)-|---------|-------
                        result = (salida.getTimeInMillis() - sabadoMediodia.getTimeInMillis()) / MILLSECS_PER_HOUR;
                    } else {
                        //CASO 6  --------|--(--*----|---------|--)----
                        result = (domingoNoche.getTimeInMillis() - sabadoMediodia.getTimeInMillis()) / MILLSECS_PER_HOUR;
                    }
                } else {
                    //CASO 1  --------|-(--)-*----|---------|-------
                    result = 0;
                }
            } else if (salida.before(domingoNoche)) {
                //CASO 3  --------|----*-(--)-|---------|-------
                result = (salida.getTimeInMillis() - entrada.getTimeInMillis()) / MILLSECS_PER_HOUR;
            } else {
                //CASO 5  --------|----*---(-|---------|----)---
                result = (domingoNoche.getTimeInMillis() - entrada.getTimeInMillis()) / MILLSECS_PER_HOUR;
            }
        } //LA ENTRADA ES DOMINGO
        else if (entrada.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaEntrada);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            domingoNoche.set(Calendar.SECOND, 59);
            if (salida.before(domingoNoche)) {
                //CASO 1 --------|----*----|---(---)---|-------
                result = (salida.getTimeInMillis() - entrada.getTimeInMillis()) / MILLSECS_PER_HOUR;
            } else {
                //CASO 2 --------|----*----|---(------|---)----
                result = (domingoNoche.getTimeInMillis() - entrada.getTimeInMillis()) / MILLSECS_PER_HOUR;
            }
        } // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES SABADO
        else if (entrada.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaSalida);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            if (salida.after(sabadoMediodia)) {
                //CASO 1 -----(---|----*--)--|---------|-------
                result = (salida.getTimeInMillis() - sabadoMediodia.getTimeInMillis()) / MILLSECS_PER_HOUR;
            }
        } // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES DOMINGO
        else if (entrada.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaSalida);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);

            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaSalida);
            sabadoMediodia.add(Calendar.DAY_OF_MONTH, -1);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);

            domingoNoche.set(Calendar.SECOND, 59);
            if (salida.before(domingoNoche)) {
                //CASO 2 -----(---|----*----|----)-----|-------
                result = (sabadoMediodia.getTimeInMillis() - salida.getTimeInMillis()) / MILLSECS_PER_HOUR;
            } else {
                //CASO 3 -----(---|----*----|---------|----)---
                result = (domingoNoche.getTimeInMillis() - sabadoMediodia.getTimeInMillis()) / MILLSECS_PER_HOUR;
            }
        }

        return result;
    }

    private double[] horasReales(Long nroppto) {
        double totalComun = 0.0;
        double totalExtra = 0.0;
        double totalExtra100 = 0.0;

        double[] total = new double[4];
        SimpleDateFormat formatoVisible = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Object[] result = null;
        try {
            result = SmsManager.instance().buscarSmsPorNroPptoLiqTodos(nroppto);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < result.length; i++) {
            Object[] sms = (Object[]) result[i];
            ReporteHorasTableItem item = new ReporteHorasTableItem();
            if ((Long) sms[0] == 1) {
                item.setOs((String) sms[2]);
            } else {
                item.setOs(((Long) sms[0]).toString());
            }

            String fechaEntrada = formatoVisible.format(((GregorianCalendar) sms[1]).getTime());
            item.setDesde(fechaEntrada);
            item.setNombre((String) sms[5]);

            String fechaSalida = "";
            if ((GregorianCalendar) sms[6] != null) {
                fechaSalida = formatoVisible.format(((GregorianCalendar) sms[6]).getTime());
                item.setHasta(fechaSalida);
                double df = (double) ((double) (((GregorianCalendar) sms[6]).getTimeInMillis() - ((GregorianCalendar) sms[1]).getTimeInMillis()) / MILLSECS_PER_HOUR);
                double extra100 = getHoraExtraAl100(((GregorianCalendar) sms[1]).getTime(), ((GregorianCalendar) sms[6]).getTime());
                if (extra100 > 0) {
                    item.setHsExtras100(darFormatoHs(redondear(extra100)));
                    item.setHsExtrasDecimales100(redondear(extra100));
                    item.setHsExtras(darFormatoHs(redondear(df - extra100)));
                    item.setHsExtrasDecimales(redondear(df - extra100));
                } else if (df <= 9) {
                    item.setHsNormales(darFormatoHs(redondear(df)));
                    item.setHsNormalesDecimales(redondear(df));
                    item.setHsExtras("0:00");
                    item.setHsExtrasDecimales(0.00);
                } else {
                    item.setHsNormales("9:00");
                    item.setHsNormalesDecimales(9.00);
                    item.setHsExtras(darFormatoHs(redondear(df - 9)));
                    item.setHsExtrasDecimales(redondear(df - 9));
                }
            } else {
                item.setHasta("");
                double df = 9.0d;
                item.setHsNormales(darFormatoHs(redondear(df)));
                item.setHsNormalesDecimales(redondear(df));
                item.setHsExtras("0:00");
                item.setHsExtrasDecimales(0.00);

            }
            totalComun += redondear(item.getHsNormalesDecimales());
            totalExtra += redondear(item.getHsExtrasDecimales());
            totalExtra100 += redondear(item.getHsExtrasDecimales100());
            total[0] = totalComun;
            total[1] = totalExtra;
            total[2] = totalExtra100;
            //}
        }
        return total;
    }

    public double redondear(double numero) {
        return Math.rint(numero * 10) / 10;
    }

    public String darFormatoHs(double numero) {
        double entero = (int) numero;
        double decimal = redondear(numero - entero);
        String result = String.valueOf((int) entero) + ":" + String.valueOf((int) (decimal * 60));
        return result;
    }

    private double round(double value, int decimalDigits) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(decimalDigits, BigDecimal.ROUND_UP);
        return bd.doubleValue();
    }

    private void getData() {
        try {
            Object[] presupuestos = null;
            RentabilidadTableModel model = new RentabilidadTableModel();
            StringBuffer vendedores = new StringBuffer();
            //resetDetallesGrales();
            //setCriteria();
            if (codVendedores != null) {
                for (int j = 0; j < codVendedores.length; j++) {
                    vendedores.append(codVendedores[j]);
                    if (j < codVendedores.length - 1) {
                        vendedores.append(",");
                    }
                }
            }
            presupuestos = PresupuestosManager.instance().buscarParaReportesRentabilidad(codCliente, codClienteFact, fechaDesde, fechaHasta, codLugar, codCondPago, estado, codTipoEvento, codServicio, vendedores.toString());

            double tot = 0;
            int cant = 0;
            if (presupuestos != null) {
                for (int i = 0; i < presupuestos.length; i++) {
                    long l = System.currentTimeMillis();
                    RentabilidadTableItem item = new RentabilidadTableItem();

                    Object[] p = (Object[]) presupuestos;
                    Object[] presupuestoDato = (Object[]) p[i];

                    cant++;
                    item.setNroppto((Long) (presupuestoDato[0]));
                    //item.setCostoOperativo(calcularCO(String.valueOf(presupuestoDato[0])));
                    item.setCO(calcularCOReal(String.valueOf(presupuestoDato[0])));
                    if (presupuestoDato[5] != null) {
                        item.setSubcontrataciones(((Double) presupuestoDato[5]).doubleValue());
                    } else {
                        item.setSubcontrataciones(0d);
                    }
                    //item.setOtros(((Double) presupuestoDato[6]).doubleValue());
                    item.setComisionLugar(((Double) presupuestoDato[7]).doubleValue());
                    item.setComisionTercero(((Double) presupuestoDato[8]).doubleValue());
                    item.setFacturado(((Double) presupuestoDato[3]).doubleValue());
                    item.setComisionComercial(((Double) presupuestoDato[9]).doubleValue());
                    //item.setRegalias(((Double) presupuestoDato[10]).doubleValue());
                    //item.setNombreEvento((String) presupuestoDato[2]);
                    item.setVendedor((String) presupuestoDato[1]);
                    //double totalGastos = item.getGastosSubcontratados() + item.getOtros() + item.getCostoOperativo();
                    double totalGastosReal = item.getSubcontrataciones() + item.getCO();
                    double totalComisions = item.getComisionComercial() + item.getComisionLugar() + item.getComisionTercero();
                    //item.setRentabilidad(item.getTotalFacturado() - (totalGastos + totalComisions));
                    item.setRentabilidad(item.getFacturado() - (totalGastosReal + totalComisions));
                    if (item.getFacturado() != 0) {
                        //item.setMargen(round(item.getRentabilidad() / item.getFacturado(), 2));
                        item.setPorcentajeRent(round(item.getRentabilidad() / item.getFacturado(), 2));
                    } else {
                        //item.setMargen(-1000);
                        item.setPorcentajeRent(-1000d);
                    }
                    tot = tot + (Double) presupuestoDato[3];
                    model.addRow(item);
                    jLabel4.setText("Cant. registros: " + cant);
                    jLabel5.setText("Total: " + getTotalFormateado(tot));
                    System.out.println("Tiempo en ppto " + item.getNroppto()+ ":" + ((System.currentTimeMillis() - l) / 1000) + " seg.");

                }
//                detallesGral.append("TOTALES \n");
//                detallesGral.append("Real:\n");
//                detallesGral.append("	Total hs extras al 100%: " + totalRealHsExtra100 + "\n");
//                detallesGral.append("	Total hs extras al 50%: " + totalRealHsExtra50 + "\n");
//                detallesGral.append("	Total hs normales: " + totalRealHs + "\n\n");
//                detallesGral.append("Presupuestado:\n");
//                detallesGral.append("	Total hs extras al 100%: " + totalPresHsExtra100 + "\n");
//                detallesGral.append("	Total hs extras al 50%: " + totalPresHsExtra50 + "\n");
//                detallesGral.append("	Total hs normales: " + totalPresHs + "\n\n");

            }
            jTable1.setModel(model);
            jTable1.updateUI();
            //jTable1.getTable().setModel(model);
            //jTable1.refreshTable();
            if (model.getRowCount() <= 0) {
                Util.alertMsg(this, "No se encontraron presupuestos");
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getTotalFormateado(double tot) {
        return getCurrencyFormat().format(tot);
    }

    private NumberFormat getCurrencyFormat() {
        NumberFormat currencyFormat;
        Locale l = new Locale("es", "AR");
        currencyFormat = NumberFormat.getCurrencyInstance(l);
        return currencyFormat;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("RENTABILIDAD");

        jLabel2.setText("Seleccione la fecha desde");

        jLabel3.setText("hasta");

        jButton1.setText("Filtro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(372, 372, 372))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new RentabilidadTableModel());
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(88, 88, 88))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String codCliente;
    private String codClienteFact;
    private String fechaDesde;
    private String fechaHasta;
    private String codLugar;
    private String[] codVendedores;
    private String codCondPago;
    private String estado;
    private String codTipoEvento;
    private String codServicio;

    private void setCriteria(FiltrosRentabilidad owner) {

        fechaDesde = DateConverter.convertDateToString(jXDatePicker1.getDate(), "yyyy-MM-dd");
        fechaHasta = DateConverter.convertDateToString(jXDatePicker2.getDate(), "yyyy-MM-dd");

        //jTable1.setComentariosXLS("Fecha desde "+fechaDesde+" hasta "+fechaHasta);
//        if (owner.getTabCriterios().getCliente_check().isSelected() && owner.getTabCriterios().getClienteElegido() != null) {
//            codCliente = owner.getTabCriterios().getClienteElegido().getCodigo();
//
//            jTable1.setComentariosXLS(jTable1.getComentariosXLS() + " // Cliente: " + owner.getTabCriterios().getClienteElegido().getEmpresa());
//        } else {
//            codCliente = null;
//        }
//        if (owner.getTabCriterios().getLugar_check().isSelected() && owner.getTabCriterios().getLugarElegido() != null) {
//            codLugar = owner.getTabCriterios().getLugarElegido().getCodigo();
//
//            jTable1.setComentariosXLS(jTable1.getComentariosXLS() + " // Lugar: " + owner.getTabCriterios().getLugarElegido().getNombreLugar());
//        } else {
//            codLugar = null;
//        }
//        if (owner.getTabCriterios().getEstado_check().isSelected()) {
//            estado = (String) owner.getTabCriterios().getEstados().getSelectedItem();
//
//            jTable1.setComentariosXLS(jTable1.getComentariosXLS() + " // Estado: " + estado);
//        } else {
//            estado = null;
//        }
//        if (owner.getTabCriterios().getVendedores_check().isSelected()) {
//            codVendedores = owner.getTabCriterios().getListaVendedores().searchForeignsArray();
//
//            jTable1.setComentariosXLS(jTable1.getComentariosXLS() + " // Vendedores: ");
//            for (int i = 0; i < owner.getTabCriterios().getListaVendedores().getSelectedValues().length; i++) {
//                if (i != 0) {
//                    jTable1.setComentariosXLS(jTable1.getComentariosXLS() + ",");
//                }
//                jTable1.setComentariosXLS(jTable1.getComentariosXLS() + " " + (String) owner.getTabCriterios().getListaVendedores().getSelectedValues()[i]);
//            }
//        } else {
//            codVendedores = null;
//        }
//        if (owner.getTabCriterios().getCondiciones_check().isSelected()) {
//            codCondPago = owner.getTabCriterios().getCondicionesPago().searchForeign();
//
//            jTable1.setComentariosXLS(jTable1.getComentariosXLS() + " // Cond. de pago: " + (String) owner.getTabCriterios().getCondicionesPago().getSelectedItem());
//        } else {
//            codCondPago = null;
//        }
//        if (owner.getTabCriterios().getServicios_check().isSelected()) {
//            codServicio = owner.getTabCriterios().getServicioElegido();

            //jTable1.setComentariosXLS(jTable1.getComentariosXLS()+" // Servicio: "+jLabel7.getText());
//        } else {
//            codServicio = null;
//        }

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        getData();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FiltrosRentabilidad dialog = new FiltrosRentabilidad(new javax.swing.JFrame(), true);
                setCriteria(dialog);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RentabilidadPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RentabilidadPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RentabilidadPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RentabilidadPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RentabilidadPrincipal dialog = new RentabilidadPrincipal(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private double totalRealHs = 0.0;
    private double totalRealHsExtra100 = 0.0;
    private double totalRealHsExtra50 = 0.0;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables
}
