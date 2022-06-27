/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.gastos;

import crm.client.managers.FeriadosManager;
import crm.client.managers.OperadorManager;
import crm.client.managers.SmsManager;
import crm.client.util.DateConverter;
import crm.client.util.Utils;
import crm.libraries.abm.entities.Operador;
import gui.tables.ReporteHorasTableItem;
import gui.tables.ReporteHorasTableModel;

import java.io.File;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author sergio
 */
public class RepoteHorasOperadores extends javax.swing.JDialog {

    private double totalComun=0.0;
    private double totalExtra=0.0;
    private double totalExtra100=0.0;
    
    /**
     * Creates new form ResmsoteHorasOsmserafechaEntradaores
     */
    public RepoteHorasOperadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadOperadores();
    }

    private void loadOperadores(){
        try {
            Operador[] list = OperadorManager.instance().getAllOperadores();
            for (Operador p : list) {
                jComboBox1.addItem(p);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This methofechaEntrada is callefechaEntrada from within the constructor to initialize the form.
     * WARNING: Do NOT mofechaEntradaify this cofechaEntradae. The content of this methofechaEntrada is always
 regeneratefechaEntrada by the Form EfechaEntradaitor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        totalHsComunes = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totaHsExtras = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        operadorRd = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        todosRd = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        LiqRd = new javax.swing.JRadioButton();
        detalleRd = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        totaHsExtras100 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jDateChooser1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser2 = new org.jdesktop.swingx.JXDatePicker();
        fechaRd = new javax.swing.JRadioButton();
        osRd = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Visor Mensajes de Operadores");

        jTable1.setModel(new ReporteHorasTableModel());
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Total Horas Normales");

        totalHsComunes.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        totalHsComunes.setText("0.0");
        totalHsComunes.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Total Horas Extras al 50%");

        totaHsExtras.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        totaHsExtras.setText("0.0");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crm/gui/imagenes/cross.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crm/gui/imagenes/page_white_excel.png"))); // NOI18N
        jButton2.setText("Exportar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/imagenes/magnifier.png"))); // NOI18N
        jButton4.setText("Buscar");
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Para quienes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        buttonGroup1.add(operadorRd);
        operadorRd.setSelected(true);
        operadorRd.setText("Por operador");

        buttonGroup1.add(todosRd);
        todosRd.setText("Todos los operadores");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operadorRd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(todosRd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operadorRd)
                    .addComponent(todosRd))
                .addGap(28, 28, 28))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Que desea ver", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        buttonGroup2.add(LiqRd);
        LiqRd.setSelected(true);
        LiqRd.setText("Liquidacion de horas extras");

        buttonGroup2.add(detalleRd);
        detalleRd.setText("Detalles de sms");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LiqRd)
                .addGap(57, 57, 57)
                .addComponent(detalleRd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LiqRd)
                    .addComponent(detalleRd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Total Horas Extras al 100%");

        totaHsExtras100.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        totaHsExtras100.setText("0.0");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecciones el rango de fechas a buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jDateChooser1.setDate(new Date());

        jLabel4.setText("Hasta");

        jDateChooser2.setDate(new Date());

        buttonGroup3.add(fechaRd);
        fechaRd.setSelected(true);
        fechaRd.setText("Por fecha desde");
        fechaRd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fechaRdItemStateChanged(evt);
            }
        });

        buttonGroup3.add(osRd);
        osRd.setText("Por orden de servicio");
        osRd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                osRdItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fechaRd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(osRd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaRd)
                    .addComponent(osRd)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(300, 300, 300))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(totaHsExtras))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(totalHsComunes))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(totaHsExtras100))))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(totalHsComunes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(totaHsExtras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(totaHsExtras100))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(55, 55, 55))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    final double MILLSECS_PER_HOUR = 60 * 60 * 1000;
    
    public double redondear(double numero) {       
        return Math.rint(numero * 10) / 10;
    }
    
    public String darFormatoHs(double numero){
        double entero=(int)numero;
        double decimal = redondear(numero - entero);
        String result = String.valueOf((int)entero)+":"+String.valueOf((int)(decimal *60));
        return result;
    }
    
    private void nuevaBusqueda(){
        totalComun=0.0;
        totalExtra=0.0;
        totalExtra100=0.0;
        ReporteHorasTableModel model = (ReporteHorasTableModel) jTable1.getModel();
        model.clear();
        jTable1.updateUI();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(JOptionPane.showConfirmDialog(RepoteHorasOperadores.this, "Seguro que desea salir del visor de horas de los operadores?","Salir",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String desde = dateformat.format(jDateChooser1.getDate());
        String hasta = dateformat.format(jDateChooser2.getDate());
        if(operadorRd.isSelected()){
            setComentariosXLS(((Operador)jComboBox1.getSelectedItem()).getApellidoYNombre()+" - desde "+desde+" hasta "+hasta);
        }
        else{
            setComentariosXLS("Todos los operadores - desde "+desde+" hasta "+hasta);
        }
        openSavePopUp();
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean isFeriado(Date fecha){
        String f = DateConverter.convertDateToString(fecha, "yyyy-MM-dd");
        boolean resp = false;
        try {
            String id = FeriadosManager.instance().getIdPorFecha(f);
            if (id !=null){
                return true;
            }
            else
                return false;
        } catch (RemoteException ex) {
            Logger.getLogger(RepoteHorasOperadores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /*private double getHoraExtraAl100(Date fechaEntrada, Date fechaSalida){
        double result=0.0;
        
        Calendar entrada = Calendar.getInstance();
        entrada.setTime(fechaEntrada);
        Calendar salida = Calendar.getInstance();
        salida.setTime(fechaSalida);
        
        //LA ENTRADA ES FERIADO
        if(isFeriado(fechaEntrada)){
            System.out.println("ES FERIADO");
            Calendar finFeriado = Calendar.getInstance();
            finFeriado.setTime(fechaEntrada);
            finFeriado.set(Calendar.HOUR_OF_DAY, 23);
            finFeriado.set(Calendar.MINUTE, 59);
            finFeriado.set(Calendar.SECOND, 59);
            if(salida.after(finFeriado)){
                result =(finFeriado.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                result = (salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        else{
        //LA ENTRADA ES SABADO
        if(entrada.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaEntrada);
            //sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 0);
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
            if(entrada.before(sabadoMediodia)){
                if(salida.after(sabadoMediodia)){
                    if(salida.before(domingoNoche)){
                        //CASO 2 --------|--(--*---)-|---------|-------
                        result=(salida.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
                    }
                    else{
                        //CASO 6  --------|--(--*----|---------|--)----
                        result=(domingoNoche.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
                    }
                }
                else{
                    //CASO 1  --------|-(--)-*----|---------|-------
                    result=0;
                }                
            }
            else{
                if(salida.before(domingoNoche)){
                    //CASO 3  --------|----*-(--)-|---------|-------
                    result=(salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
                }
                else{
                    //CASO 5  --------|----*---(-|---------|----)---
                    result=(domingoNoche.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
                }
            }
        }
        //LA ENTRADA ES DOMINGO
        else if(entrada.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaEntrada);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            domingoNoche.set(Calendar.SECOND, 59);
            if(salida.before(domingoNoche)){
                //CASO 1 --------|----*----|---(---)---|-------
                result=(salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                //CASO 2 --------|----*----|---(------|---)----
                result=(domingoNoche.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES SABADO
        else if(entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaSalida);
            //sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 0);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            if(salida.after(sabadoMediodia)){
                //CASO 1 -----(---|----*--)--|---------|-------
                result=(salida.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES DOMINGO
        else if(entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
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
            if(salida.before(domingoNoche)){
                //CASO 2 -----(---|----*----|----)-----|-------
                result=(sabadoMediodia.getTimeInMillis()-salida.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                //CASO 3 -----(---|----*----|---------|----)---
                result=(domingoNoche.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        }
        return result;
    }*/
    
    private double getHoraExtraAl100(Date fechaEntrada, Date fechaSalida){
        double result=0.0;
        
        Calendar entrada = Calendar.getInstance();
        entrada.setTime(fechaEntrada);
        Calendar salida = Calendar.getInstance();
        salida.setTime(fechaSalida);
        
        //LA ENTRADA ES FERIADO
        if(isFeriado(fechaEntrada)){
            System.out.println("ES FERIADO");
            Calendar finFeriado = Calendar.getInstance();
            finFeriado.setTime(fechaEntrada);
            finFeriado.set(Calendar.HOUR_OF_DAY, 23);
            finFeriado.set(Calendar.MINUTE, 59);
            finFeriado.set(Calendar.SECOND, 59);
            if(salida.after(finFeriado)){
                result =(finFeriado.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                result = (salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        else{
        //LA ENTRADA ES DOMINGO
        	if(entrada.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
            Calendar domingoNoche = Calendar.getInstance();
            domingoNoche.setTime(fechaEntrada);
            domingoNoche.set(Calendar.HOUR_OF_DAY, 23);
            domingoNoche.set(Calendar.MINUTE, 59);
            domingoNoche.set(Calendar.SECOND, 59);
            if(salida.before(domingoNoche)){
                //CASO 1 --------|----*----|---(---)---|-------
                result=(salida.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                //CASO 2 --------|----*----|---(------|---)----
                result=(domingoNoche.getTimeInMillis()-entrada.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES SABADO
        else if(entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
            Calendar sabadoMediodia = Calendar.getInstance();
            sabadoMediodia.setTime(fechaSalida);
            //sabadoMediodia.set(Calendar.HOUR_OF_DAY, 13);
            sabadoMediodia.set(Calendar.HOUR_OF_DAY, 0);
            sabadoMediodia.set(Calendar.MINUTE, 0);
            sabadoMediodia.set(Calendar.SECOND, 0);
            if(salida.after(sabadoMediodia)){
                //CASO 1 -----(---|----*--)--|---------|-------
                result=(salida.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        // LA ENTRADA NO ES SABADO NI DOMINGO PERO LA SALIDA ES DOMINGO
        else if(entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && entrada.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY && salida.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
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
            if(salida.before(domingoNoche)){
                //CASO 2 -----(---|----*----|----)-----|-------
                result=(sabadoMediodia.getTimeInMillis()-salida.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
            else{
                //CASO 3 -----(---|----*----|---------|----)---
                result=(domingoNoche.getTimeInMillis()-sabadoMediodia.getTimeInMillis())/MILLSECS_PER_HOUR;
            }
        }
        }
        return result;
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ReporteHorasTableModel model = (ReporteHorasTableModel) jTable1.getModel();
        nuevaBusqueda();
        try {
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatoVisible = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            String desde = dateformat.format(jDateChooser1.getDate());  
            Calendar c = Calendar.getInstance();//obtengo las fechas seleccionadas
            c.setTime(jDateChooser2.getDate());
            c.add(Calendar.DAY_OF_YEAR, 1);
            String hasta = dateformat.format(c.getTime());
            
            Object[] result = null;
            
//            if(osRd.isSelected()){
//                result = SmsManager.instance().buscarSmsPorNroPpto(Long.parseLong(jTextField1.getText()));
//            }
//            else{
//            if (operadorRd.isSelected()) {                                                           //busco por operador o por fechas
//                Long codOp = Long.parseLong(((Operador) jComboBox1.getSelectedItem()).getCodigo());
//                if(LiqRd.isSelected()){
//                    result = SmsManager.instance().buscarSmsParaLiqOperador(desde, hasta, codOp);
//                }
//                else 
//                    result = SmsManager.instance().buscarSmsDetalleOperador(desde, hasta, codOp);
//            } else {
//                if(LiqRd.isSelected())
//                    result = SmsManager.instance().buscarSmsParaLiqTodos(desde, hasta);
//                else
//                    result = SmsManager.instance().buscarSmsDetalleTodos(desde, hasta);
//            }  
//            }  
            Long codOp = Long.parseLong(((Operador) jComboBox1.getSelectedItem()).getCodigo());
            
            if(fechaRd.isSelected()){
                if(LiqRd.isSelected()){
                    if(operadorRd.isSelected()){                        
                        result = SmsManager.instance().buscarSmsParaLiqOperador(desde, hasta, codOp);
                    }
                    else{
                        result = SmsManager.instance().buscarSmsParaLiqTodos(desde, hasta);
                    }
                }
                else{
                    if(operadorRd.isSelected()){                        
                        result = SmsManager.instance().buscarSmsDetalleOperador(desde, hasta, codOp);
                    }
                    else{
                        result = SmsManager.instance().buscarSmsDetalleTodos(desde, hasta);
                    }
                }
            }
            else {
                Long nroppto;
                if (jTextField1.getText() != null) {
                    nroppto = Long.parseLong(jTextField1.getText());
                    if (LiqRd.isSelected()) {
                        if (operadorRd.isSelected()) {
                            result = SmsManager.instance().buscarSmsPorNroPptoLiqOperador(nroppto, codOp);
                        } else {
                            result = SmsManager.instance().buscarSmsPorNroPptoLiqTodos(nroppto);
                        }
                    } else if (operadorRd.isSelected()) {
                        result = SmsManager.instance().buscarSmsPorNroPptoDetalleOperador(nroppto, codOp);
                    } else {
                        result = SmsManager.instance().buscarSmsPorNroPptoDetalleTodos(nroppto);
                    }
                }
                else{
                    Util.alertMsg(this, "Debe ingresar un numero valido de Orden de servicio");
                }
            }
            for (int i = 0; i < result.length; i++) {
                Object[] sms = (Object[]) result[i];
                ReporteHorasTableItem item = new ReporteHorasTableItem();
                if (sms[0].getClass().equals(Long.class)) {
                    if ((Long) sms[0] == 1) {
                        item.setOs((String) sms[2]);
                    } else {
                        item.setOs(((Long) sms[0]).toString());
                    }
                } else {
                    item.setOs((String) sms[0]);
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
                    if(extra100>0){
                        item.setHsExtras100(darFormatoHs(redondear(extra100)));
                        item.setHsExtrasDecimales100(redondear(extra100));
                        item.setHsExtras(darFormatoHs(redondear(df - extra100)));
                        item.setHsExtrasDecimales(redondear(df - extra100));
                    }
                    if (df <= 9) {
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
                model.addRow(item);
                //}
            }
            totalHsComunes.setText(darFormatoHs(totalComun));
            totaHsExtras.setText(darFormatoHs(totalExtra));
            totaHsExtras100.setText(darFormatoHs(totalExtra100));
            jTable1.setModel(model);
            jTable1.updateUI();
        } catch (RemoteException ex) {
            Logger.getLogger(RepoteHorasOperadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void fechaRdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fechaRdItemStateChanged
        // TODO add your handling code here:
        if(fechaRd.isSelected()){
            jTextField1.setEnabled(false);
            jDateChooser1.setEnabled(true);
            jDateChooser2.setEnabled(true);
        }
            
    }//GEN-LAST:event_fechaRdItemStateChanged

    private void osRdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_osRdItemStateChanged
        // TODO add your handling code here:
        if(osRd.isSelected()){
            jDateChooser1.setEnabled(false);
            jDateChooser2.setEnabled(false);
            jTextField1.setEnabled(true);
        }
    }//GEN-LAST:event_osRdItemStateChanged

    private JFileChooser filechooser;
    public static final int TYPE_STRING = 0;
    public static final int TYPE_LONG = 1;
    public static final int TYPE_INTEGER = 2;
    public static final int TYPE_DOUBLE = 3;
    public static final int TYPE_BOOLEAN = 4;
    public static final int TYPE_EVENTO = 5;
    public static final int TYPE_VENDEDOR = 6;
    private String comentariosXLS = "";

    public void openSavePopUp() {
        filechooser = new JFileChooser();
        XlsFilter fil = new XlsFilter();
        filechooser.addChoosableFileFilter(fil);
        filechooser.setFileFilter(fil);
        filechooser.setAcceptAllFileFilterUsed(false);
        if (filechooser.showSaveDialog(RepoteHorasOperadores.this) == JFileChooser.APPROVE_OPTION) {

            File file = filechooser.getSelectedFile();
            File f2 = new File(file.getParentFile().getPath() + "\\" + filechooser.getSelectedFile().getName() + ".xls");

            String PATH = file.getAbsolutePath();

            if (!(PATH.endsWith(".xls"))) {
                File temp = new File(PATH + ".xls");
                file.renameTo(temp);//renombramos el archivo
            }

            if (filechooser.getFileFilter().accept(file)) {
                exportXLS(file);
            } else {
                exportXLS(f2);
            }

        }
    }

    public void exportXLS(File file) {
        int tipo = 0;

        WritableWorkbook workbook;
        try {
            workbook = Workbook.createWorkbook(file);

            WritableSheet sheet = workbook.createSheet("Operadores", 0);
            for (int j = 0; j < jTable1.getModel().getColumnCount(); j++) {
                Label label = new Label(j, 0, jTable1.getModel().getColumnName(j));
                sheet.addCell(label);
                if (jTable1.getModel().getColumnClass(j).equals(String.class)) {
                    tipo = TYPE_STRING;
                } else if (jTable1.getModel().getColumnClass(j).equals(Long.class)) {
                    tipo = TYPE_LONG;
                } else if (jTable1.getModel().getColumnClass(j).equals(Integer.class)) {
                    tipo = TYPE_INTEGER;
                } else if (jTable1.getModel().getColumnClass(j).equals(Double.class)) {
                    tipo = TYPE_DOUBLE;
                } else if (jTable1.getModel().getColumnClass(j).equals(java.lang.Boolean.class)) {
                    tipo = TYPE_BOOLEAN;
                }
                for (int i = 1; i <= jTable1.getModel().getRowCount(); i++) {
                    if (tipo == TYPE_STRING) {
                        Label labelData = new Label(j, i, (String) jTable1.getModel().getValueAt(i - 1, j));
                        sheet.addCell(labelData);
                    } else if (tipo == TYPE_LONG) {
                        jxl.write.Number temp = new jxl.write.Number(j, i, (Long) jTable1.getModel().getValueAt(i - 1, j));
                        sheet.addCell(temp);
                    } else if (tipo == TYPE_INTEGER) {
                        jxl.write.Number temp = new jxl.write.Number(j, i, (Integer) jTable1.getModel().getValueAt(i - 1, j));
                        sheet.addCell(temp);
                    } else if (tipo == TYPE_DOUBLE) {
						//String d = (String)table.getModel().getValueAt(i-1,j);
                        //Double d2 = Double.valueOf(d.substring(1,d.length()));

                        Double d2 = (Double) jTable1.getModel().getValueAt(i - 1, j);

                        jxl.write.Number temp = new jxl.write.Number(j, i, d2);
                        sheet.addCell(temp);
                    } else if (tipo == TYPE_BOOLEAN) {
                        jxl.write.Boolean temp = new jxl.write.Boolean(j, i, (java.lang.Boolean) jTable1.getModel().getValueAt(i - 1, j));
                        sheet.addCell(temp);
                    }
                }
            }
            WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);
            WritableCellFormat arial10format = new WritableCellFormat(arial10font);
            Label labelData = new Label(0, jTable1.getModel().getRowCount() + 2, "Reporte buscado para: " + comentariosXLS, arial10format);

            sheet.addCell(labelData);
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setComentariosXLS(String comentariosXLS) {
		this.comentariosXLS = comentariosXLS;
	}
    
    /**
     * @param args the commanfechaEntrada line arguments
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
            java.util.logging.Logger.getLogger(RepoteHorasOperadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RepoteHorasOperadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RepoteHorasOperadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RepoteHorasOperadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RepoteHorasOperadores dialog = new RepoteHorasOperadores(new javax.swing.JFrame(), true);
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
    
    public class XlsFilter extends FileFilter {

	    //Accept all directories and all xls files.
	    public boolean accept(File f) {
	        if (f.isDirectory()) {
	            return true;
	        }

	        String extension = Utils.getExtension(f);
	        if (extension != null) {
	            if (extension.equals(Utils.xls)) {
	                    return true;
	            } else {
	                return false;
	            }
	        }

	        return false;
	    }

	    //The description of this filter
	    public String getDescription() {
	        return "Excel (*.xls)";
	    }
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton LiqRd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton detalleRd;
    private javax.swing.JRadioButton fechaRd;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private org.jdesktop.swingx.JXDatePicker jDateChooser1;
    private org.jdesktop.swingx.JXDatePicker jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton operadorRd;
    private javax.swing.JRadioButton osRd;
    private javax.swing.JRadioButton todosRd;
    private javax.swing.JLabel totaHsExtras;
    private javax.swing.JLabel totaHsExtras100;
    private javax.swing.JLabel totalHsComunes;
    // End of variables declaration//GEN-END:variables
}
