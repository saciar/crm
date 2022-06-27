/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImputacionGastosServicios.java
 *
 * Created on 15/11/2013, 16:17:06
 */
package gui.gastos;

import crm.client.helpers.GastosBuilder;
import crm.client.managers.FamiliaServManager;
import crm.client.managers.GastosManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.ProveedorManager;
import crm.client.managers.ServicioIdiomaManager;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Subcontratado;
import crm.libraries.abm.entities.Proveedor;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.helper.GastoContratHelper;
import gui.tables.ServiciosSubcontratadosItem;
import gui.tables.ServiciosSubcontratadosTableModel;
import gui.tables.ServiciosSubcontratadosTableRender;
import gui.tables.ServiciosSubcontratadosTableRender2;

import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumn;
import org.apache.commons.lang.StringUtils;
 
/**
 *
 * @author saciar
 */
public class ImputacionGastosServicios extends javax.swing.JDialog {

    private Long nroPpto;
    private Presupuesto pptoElegido;    

    private void loadProveedores() {
        try {
            Proveedor[] list = ProveedorManager.instance().getAllProveedores();
            for (Proveedor p : list) {
                jComboBox1.addItem(p);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Proveedor getNombreProveedor(long cod) {
        try {
            Proveedor p = ProveedorManager.instance().getProveedorById(String.valueOf(cod));
            return p;
        } catch (RemoteException ex) {
            Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Ppto_Sala getSalaPptoByName(String index) {
        Ppto_Sala sala = null;
        if (pptoElegido.getSalas() != null) {
            Object[] salas = pptoElegido.getSalasArray();

            for (int i = 0; i < salas.length; i++) {
                Ppto_Sala pptosala = (Ppto_Sala) salas[i];
                if (pptosala.getSala().getDescripcion().equals(index)) {
                    sala = (Ppto_Sala) salas[i];
                    break;
                }
            }

        }
        return sala;
    }
    
    public boolean isSalaPptoByName(String index) {
        boolean result = false;

        if (jXHeader1.getTitle().equals(index)) {
            result = true;
        }
        
        return result;
    }

    private List<Ppto_GastoSC> getGastoSc(String nombreSala) {

        Set gastos = pptoElegido.getGastosSC();
        List<Ppto_GastoSC> result = new ArrayList<Ppto_GastoSC>();
        if (gastos != null) {
            Iterator it = gastos.iterator();
            while (it.hasNext()) {
                Ppto_GastoSC gasto = (Ppto_GastoSC) it.next();
                if (gasto.getSala().equals(nombreSala)) {
                    result.add(gasto);
                }

            }

        }
        return result;
    }
          
    public void loadSalas(Presupuesto p){
        //nroPpto = pptoElegido.getNumeroDePresupuesto();
        

        Worker w = new Worker(p);
        w.execute();
    }
    
    private class Worker extends SwingWorker<Double, String>{
        ServiciosSubcontratadosTableModel model;
        Presupuesto ppto;
        
        public Worker (Presupuesto p){
            ppto = p;
            model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
            model.clear();
        }
        
        private int cantServicosTotal (Object[] salas){
            int cant =0;
            for (int n = 0; n < salas.length; n++) {
                Ppto_Sala pptosala = (Ppto_Sala) salas[n];
                if (pptosala.getServicios() != null) {
                    cant += pptosala.getServiciosArray().length;
                }
            }
            return cant;
        }
        
        @Override
        protected Double doInBackground() throws Exception {
                                    
            int cantDeREg = 0;
            int contados = 0;
            long tableitem = 1;
            GastosManager gmanager = GastosManager.instance();
            Object[] salas = ppto.getSalasArray();
            cantDeREg = cantServicosTotal(salas);
            for (int n = 0; n < salas.length; n++) {
                Ppto_Sala pptosala = (Ppto_Sala) salas[n];

                if (pptosala.getServicios() != null) {
                    //obtengo los servicios de la sala
                    Object[] pptoservicios = pptosala.getServiciosArray();
                    String nombreSala = pptosala.getSala().getDescripcion();
                    ServicioIdiomaManager servicioManager = ServicioIdiomaManager.instance();
                    //recorro todos los servicios
                    for (int i = 0; i < pptoservicios.length; i++) {

                        Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) pptoservicios[i];
                                                
                        //creo un item de la grilla a llenar
                        ServiciosSubcontratadosItem item = new ServiciosSubcontratadosItem();
                        //le pongo un valor al Table Id                
                        item.setTableItemId(tableitem);
                        
                        item.setNombreSala(nombreSala);

                        item.setCodServicioPpto(serv.getId());
                        item.setCantidad(serv.getCantidad());
                        item.setDias(serv.getDias());
                        item.setDescuento(serv.getDescuento());
                        item.setPrecio_vta(Double.parseDouble(serv.getPrecioDescuento()));
                        item.setCodigoServicio(serv.getServicio().getCodigo());
                        item.setModalidad(serv.getModalidad().getCodigo());
                        if (serv.getDetalle() != null) {

                            item.setNombre(serv.getDetalle());

                        } else {
                            try {
                                String s = servicioManager.getDescripcionServicio(serv.getServicio().getCodigo(), "1");
                                item.setNombre(s);
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                        }
                        //si es subcontratado cargo los datos de subcontratacion
//                        if ((serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA))) || (serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL)))) {
                            Object[] resultado;
                            try {
                                resultado = gmanager.getSubcontratadoByServ(serv.getId());
                                for (int j = 0; j < resultado.length; j++) {
                                    Object[] gasto = (Object[]) resultado[j];
                                    if (gasto[1] != null) {
                                        item.setCosto((Double) gasto[1]);
                                        if ((Long) gasto[0] != null) {
                                            item.setCodProveedor((Long) gasto[0]);
                                            item.setProveedor(getNombreProveedor((Long) gasto[0]));
                                        }
                                        if ((Long) gasto[3] != null) {
                                            item.setEstado(getNombreEstado(((Long) gasto[3]).intValue()));
                                            item.setCodEstado(((Long) gasto[3]).intValue());
                                        }
                                        item.setIsSubcontratado(true);
                                    }

                                }
                            } catch (RemoteException ex) {
                                Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
                            }

//                            item.setIsSubcontratado(true);
//                        } else {
//                            item.setIsSubcontratado(false);
//                        }
                        model.addRow(item);
                        contados ++;
                        tableitem ++;
                        publish((contados)+" de "+cantDeREg);
                    }
                    jTable1.setModel(model);
                    jTable1.updateUI();
                }

            }
            return 100d;
        }
        
        @Override
        protected void done() {

            System.out.println("done() esta en el hilo "
                    + Thread.currentThread().getName());
            jProgressBar1.setString("Finalizado");
        }
        
        @Override
        protected void process(List<String> chunks) {
            System.out.println("process() esta en el hilo "
                    + Thread.currentThread().getName());
            jProgressBar1.setString(chunks.get(0));
        }
        
        
    }
    
    private void loadServicios(String nombreSala) {
        nroPpto = pptoElegido.getNumeroDePresupuesto();
        jXHeader1.setTitle(nombreSala);
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        model.clear();
        //Ppto_Sala pptosala = getSalaPptoByName(nombreSala);
        //List<Ppto_GastoSC> result = getGastoSc(nombreSala);
        GastosManager gmanager = GastosManager.instance();

        Object[] salas = pptoElegido.getSalasArray();

        for (int n = 0; n < salas.length; n++) {
            Ppto_Sala pptosala = (Ppto_Sala) salas[n];
            //if (pptosala.getSala().getDescripcion().equals(nombreSala)) {
                if (pptosala.getServicios() != null) {
                    //obtengo los servicios de la sala
                    Object[] pptoservicios = pptosala.getServiciosArray();

                    long time = System.currentTimeMillis();

                    ServicioIdiomaManager servicioManager = ServicioIdiomaManager.instance();
                    //recorro todos los servicios
                    for (int i = 0; i < pptoservicios.length; i++) {

                        Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) pptoservicios[i];
                        //si es la sasla seleccionada
                        if (pptosala.getSala().getDescripcion().equals(nombreSala)) {
                            //creo un item de la grilla a llenar
                            ServiciosSubcontratadosItem item = new ServiciosSubcontratadosItem();
                            //le pongo un valor al Table Id                
                            item.setTableItemId((long) i + 1);

                            item.setCodServicioPpto(serv.getId());
                            item.setCantidad(serv.getCantidad());
                            item.setDias(serv.getDias());
                            item.setDescuento(serv.getDescuento());
                            item.setPrecio_vta(Double.parseDouble(serv.getPrecioDescuento()));
                            if (serv.getDetalle() != null) {

                                item.setNombre(serv.getDetalle());
                                //if()
                            } else {
                                try {
                                    String s = servicioManager.getDescripcionServicio(serv.getServicio().getCodigo(), "1");
                                    item.setNombre(s);
                                } catch (RemoteException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            //si es subcontratado cargo los datos de subcontratacion
//                            if ((serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA))) || (serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL)))) {
                                Object[] resultado;
                                try {
                                    resultado = gmanager.getSubcontratadoByServ(serv.getId());
                                    for (int j = 0; j < resultado.length; j++) {
                                        Object[] gasto = (Object[]) resultado[j];
                                        if (gasto[1] != null) {
                                            item.setCosto((Double) gasto[1]);
                                            if ((Long) gasto[0] != null) {
                                                item.setCodProveedor((Long) gasto[0]);
                                                item.setProveedor(getNombreProveedor((Long) gasto[0]));
                                            }
                                            if ((Long) gasto[3] != null) {
                                                item.setEstado(String.valueOf((Long) gasto[3]));
                                                item.setCodEstado((Integer) gasto[3]);
                                            }
                                            item.setIsSubcontratado(true);
                                        }

                                    }
                                } catch (RemoteException ex) {
                                    Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
                                }

//                                item.setIsSubcontratado(true);
//                            } else {
//                                item.setIsSubcontratado(false);
//                            }
                            model.addRow(item);
                        }
                    }
                    jTable1.setModel(model);
                    jTable1.updateUI();
                    System.out.println("TIEMPO QUERY TOTAL " + (System.currentTimeMillis() - time) / 1000 + " seg.");

                }
            
        }

    }
    
     public ServiciosSubcontratadosTableModel getTableModel(){
         return (ServiciosSubcontratadosTableModel) jTable1.getModel();
     }
    
    private void loadTodosServicios(String nroppto) {

        nroPpto = pptoElegido.getNumeroDePresupuesto();
        jXHeader1.setTitle(nroppto);
        //obtengo el table model
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        model.clear();
        //obtengo la sala en el presupuesto por su nombre
        Ppto_Sala pptosala = getSalaPptoByName(nroppto);        
        GastosManager gmanager = GastosManager.instance();
        
        if (pptosala.getServicios() != null) {
            //obtengo los servicios de la sala
            Object[] pptoservicios = pptosala.getServiciosArray();

            long time = System.currentTimeMillis();
            String nombreSala = pptosala.getSala().getDescripcion();

            ServicioIdiomaManager servicioManager = ServicioIdiomaManager.instance();
            //recorro todos los servicios
            for (int i = 0; i < pptoservicios.length; i++) {

                Ppto_Sala_Servicio serv = (Ppto_Sala_Servicio) pptoservicios[i];
                //creo un item de la grilla a llenar
                ServiciosSubcontratadosItem item = new ServiciosSubcontratadosItem();
                //le pongo un valor al Table Id                
                item.setTableItemId((long)i+1);
                
                item.setNombreSala(nombreSala);
                item.setCodServicioPpto(serv.getId());
                item.setCantidad(serv.getCantidad());
                item.setDias(serv.getDias());
                item.setDescuento(serv.getDescuento());
                item.setPrecio_vta(Double.parseDouble(serv.getPrecioDescuento()));
                
                if (serv.getDetalle() != null) {
                    
                    item.setNombre(serv.getDetalle());
                    //if()
                } else {
                    try {
                        String s = servicioManager.getDescripcionServicio(serv.getServicio().getCodigo(), "1");
                        item.setNombre(s);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
                //si es subcontratado cargo los datos de subcontratacion
                if((serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA))) || (serv.getModalidad().getCodigo().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL)))){
                    Object[] resultado;
                    try {
                        resultado = gmanager.getSubcontratadoByServ(serv.getId());
                        for(int j =0; j<resultado.length;j++){
                            Object[] gasto = (Object[])resultado[j];
                            if (gasto[1] != null) {
                            item.setCosto((Double)gasto[1]);
                            if((Long)gasto[0] !=null){
                                item.setCodProveedor((Long)gasto[0]);
                                item.setProveedor(getNombreProveedor((Long)gasto[0]));                        
                            }
                            if((Long)gasto[3] !=null)
                                item.setEstado(String.valueOf((Long)gasto[3]));
                        }

                    }
                    } catch (RemoteException ex) {
                        Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
                    }             

                    item.setIsSubcontratado(true);
                }
                else
                    item.setIsSubcontratado(false);
                model.addRow(item);

            }
            jTable1.setModel(model);
            jTable1.updateUI();
            System.out.println("TIEMPO QUERY TOTAL " + (System.currentTimeMillis() - time) / 1000 + " seg.");

        }
    }

    /**
     * Creates new form ImputacionGastosServicios
     */
    public ImputacionGastosServicios(java.awt.Frame parent, boolean modal, Presupuesto ppto) {
        super(parent, modal);
        pptoElegido = ppto;
        initComponents();
        loadProveedores();
    }
    
    public ImputacionGastosServicios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        initComponents();
        loadProveedores();
    }
    
    public void setPptoElegido(Presupuesto ppto){
        pptoElegido = ppto;
        jXHeader1.setTitle("Ppto Nro :"+pptoElegido.getNumeroDePresupuesto().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jCheckBox1 = new javax.swing.JCheckBox();
        jProgressBar1 = new javax.swing.JProgressBar();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jTable1.setModel(new ServiciosSubcontratadosTableModel());
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setDefaultRenderer(Object.class, new ServiciosSubcontratadosTableRender());
        TableColumn col1 = jTable1.getColumn("Subc.");
        TableColumn col2 = jTable1.getColumn("Cant");
        TableColumn col3 = jTable1.getColumn("Dias");
        TableColumn col4 = jTable1.getColumn("Variacion");
        TableColumn col5 = jTable1.getColumn("opc");
        TableColumn col6 = jTable1.getColumn("ext");
        col1.setPreferredWidth(30);
        col2.setPreferredWidth(30);
        col3.setPreferredWidth(30);
        col4.setPreferredWidth(50);
        col5.setPreferredWidth(30);
        col6.setPreferredWidth(30);

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crm/gui/imagenes/disk.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crm/gui/imagenes/cross.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jXHeader1.setDescription("Imputacion de gastos");
        jXHeader1.setDescriptionFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jXHeader1.setTitle("Presupuesto:");

        jCheckBox1.setText("Subcontratar todo");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jProgressBar1.setStringPainted(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Servicios");

        jLabel2.setText("Seleccione un proveedor para el servicio seleccionado");

        jLabel3.setText("Valor de costo $");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel1.setText("Estado del servicio");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "en OS", "Solicitado al proveedor", "Confirmado por proveedor", "A pagar", "Pagado" }));

        jCheckBox2.setText("Dividir el total de costo por cada item");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/crm/gui/imagenes/table_edit.png"))); // NOI18N
        jButton1.setText("Imputar costo y proveedor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jCheckBox2)
                        .addComponent(jButton1))
                    .addContainerGap(314, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jCheckBox2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addComponent(jXHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(836, 562));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private int getIndexProveedorEnTabla(Proveedor p) {
        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            Proveedor pcb = (Proveedor) (jComboBox1.getModel().getElementAt(i));
            if (p.getCodigo().equals(pcb.getCodigo())) {
                return i;
            }
        }
        return -1;
    }

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        ServiciosSubcontratadosItem item = model.getRow(jTable1.getSelectedRow());
        if (item != null) {
            if (item.getCosto() != null) {
                jTextField1.setText(item.getCosto().toString());
            }
            if (item.getProveedor() != null) {
                int index = getIndexProveedorEnTabla(item.getProveedor());
                if (index > 0) {
                    jComboBox1.setSelectedIndex(index);
                }
            }
            if(item.getEstado() != null){
                jComboBox2.setSelectedIndex(item.getCodEstado()-1);
            }
        }

    }//GEN-LAST:event_jTable1MouseReleased
        
    private String getToolTipText(){
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        ServiciosSubcontratadosItem item = model.getRow(jTable1.getSelectedRow());
        if (item != null) {
            if (item.getCosto() != null) {
                return ((Double)(item.getPrecio_vta()-item.getCosto())).toString();
            }
            else return "-";
        }
        else return ""; 
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {            
            if (!jTable1.getSelectionModel().isSelectionEmpty()) {
                ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
                List<ServiciosSubcontratadosItem> itemsSeleccionados = model.getRows();
                boolean esDivisible = jCheckBox2.isSelected();
                for (int i = 0; i < itemsSeleccionados.size(); i++) {
                    ServiciosSubcontratadosItem itemSeleccionado = itemsSeleccionados.get(i);
                    if (jTable1.getSelectionModel().isSelectedIndex(i)) {
                        if (!esDivisible) {
                            model.setValueAt(Double.parseDouble(jTextField1.getText()), i, ServiciosSubcontratadosTableModel.COLUMNA_COSTO);
                        } else {
                            model.setValueAt(getCostoXItem(jTable1.getSelectedRowCount()), i, ServiciosSubcontratadosTableModel.COLUMNA_COSTO);
                        }
                        model.setValueAt((Proveedor) jComboBox1.getSelectedItem(), i, ServiciosSubcontratadosTableModel.COLUMNA_PROVEEDOR);
                        model.setValueAt(true, i, ServiciosSubcontratadosTableModel.COLUMNA_ES_SUBCONTRATADO);
                        itemSeleccionado.setCodProveedor(Long.valueOf(((Proveedor) jComboBox1.getSelectedItem()).getCodigo()));
                        itemSeleccionado.setCodEstado(getEstado((String) jComboBox2.getSelectedItem()));
                        itemSeleccionado.setEstado((String) jComboBox2.getSelectedItem());
                        itemSeleccionado.setIsSubcontratado(true);
                        jTable1.setModel(model);
                        jTable1.updateUI();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(ImputacionGastosServicios.this, "Debe seleccionar al menos un item de la grilla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ImputacionGastosServicios.this, "Debe ingresar un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("0.0");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (JOptionPane.showConfirmDialog(ImputacionGastosServicios.this, "Seguro que desea salir de la imputacion de gastos de la sala?", "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            cerrar();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cerrar() {
        try {
            PresupuestosManager.instance().modificarActivo(pptoElegido.getNumeroDePresupuesto(), "N",29,null,null);
            this.dispose();
        } catch (RemoteException ex) {
            Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(ImputacionGastosServicios.this, "Se produjo un error al cerrar el presupuesto. Comuniquese con soporte tecnico para informar el error", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private int getEstado(String est) {
        int result = 1;
        if (est != null) {
            if (est.equals("en OS")) {
                result = 1;
            } else if (est.equals("Solicitado al proveedor")) {
                result = 2;
            } else if (est.equals("Confirmado por proveedor")) {
                result = 3;
            } else if (est.equals("A pagar")) {
                result = 4;
            } else if (est.equals("Pagado")) {
                result = 5;
            }
        }
        return result;
    }

    public String getNombreEstado(int cod){
        String result ="";
        switch (cod){
                case 1: {
                    result = "en OS";
                    break;
                }
                case 2: {
                    result = "Solicitado al proveedor";
                    break;
                }
                case 3: {
                    result = "Confirmado por proveedor";
                    break;
                }
                case 4: {
                    result = "A pagar";
                    break;
                }
                case 5: {
                    result = "Pagado";
                    break;
                }
                        
        }
        return result;
        
    }
    
    public boolean getModalidadContratacion(Ppto_Sala_Servicio pptoServ){
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        List<ServiciosSubcontratadosItem> itemsGenerales = model.getRows(); 
        for (int i = 0; i < itemsGenerales.size(); i++) {
            ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem) itemsGenerales.get(i);
            if(item.getCodServicioPpto() == pptoServ.getId()){
                if(item.isIsSubcontratado() !=null)
                    return item.isIsSubcontratado(); 
                else
                    return false;
            }
        }
        return false;
    }
    
    public boolean cambioModalidad(Ppto_Sala_Servicio pptoServ){
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        List<ServiciosSubcontratadosItem> itemsGenerales = model.getRows(); 
        for (int i = 0; i < itemsGenerales.size(); i++) {
            ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem) itemsGenerales.get(i);
            if(item.getCodServicioPpto() == pptoServ.getId()){
                //if(pptoServ.getModalidad().getCodigo()==1)
                if(item.isIsSubcontratado() !=null)
                    return item.isIsSubcontratado(); 
                else
                    return false;
            }
        }
        return false;
    }
    
    private double getCostoXItem(int cantItem){
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        //List<ServiciosSubcontratadosItem> itemsGenerales = model.getRows();  
        double totalDividido = 0.0;
        if(StringUtils.isNotBlank(jTextField1.getText())){
            totalDividido = Math.rint(Double.parseDouble(jTextField1.getText()) / cantItem * 100) / 100;            
        }
        return totalDividido; 
    }
    
    private int getCountSubcontratados(){
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        List<ServiciosSubcontratadosItem> itemsGenerales = model.getRows();
        int count = 0;
        for (int i = 0; i < itemsGenerales.size(); i++) {
            ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem) itemsGenerales.get(i);
            if (item.isIsSubcontratado()!=null && item.isIsSubcontratado()) {
                count++;
            }
        }
        return count;
    }
    
    public long getTableItemIdFromModel(long id){
        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
        List<ServiciosSubcontratadosItem> itemsGenerales = model.getRows();
        long code =-1;
        for (int i = 0; i < itemsGenerales.size(); i++) {
            ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem) itemsGenerales.get(i);
            if (item.getCodServicioPpto() == id) {
                code = item.getTableItemId();
                break;
            }            
        }
        return code;
    }
    
    public GastoContratHelper[] getGastos2(){
		ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
                List<ServiciosSubcontratadosItem> itemsGenerales = model.getRows();

		GastoContratHelper[] gastoHelper = new GastoContratHelper[getCountSubcontratados()];
		int j =0;
		for(int i=0;i<itemsGenerales.size();i++){
			ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem) itemsGenerales.get(i);
                        if (item.isIsSubcontratado()!=null && item.isIsSubcontratado()) {
                            gastoHelper[j] = new GastoContratHelper();
                            gastoHelper[j].setSalaServicioTableItemId(item.getTableItemId());

                            Ppto_GastoSC  gasto = new Ppto_GastoSC();			
                            gasto.setDetalle(item.getNombre());
                            if(item.getCodProveedor() != null)
                                gasto.setProveedor(String.valueOf(item.getCodProveedor()));
                            if(item.getCosto()!=null)
                                gasto.setCosto(Double.toString(item.getCosto()));                            
                            gasto.setPrecio(Double.toString(item.getPrecio_vta()));
                            if(item.getEstado() != null)
                                gasto.setEstado(String.valueOf(item.getCodEstado()));
                            gasto.setSala(item.getNombreSala());
                            gastoHelper[j].setPpto_GastoSC(gasto);
                            j++;
                        }
		}				
             
		return gastoHelper;
	}
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            final GastosBuilder builder = new GastosBuilder(pptoElegido, this);
            builder.run();
            PresupuestosManager manager = PresupuestosManager.instance();
            manager.actualizarPresupuesto(builder.getPresupuesto());
            manager.modificarActivo(pptoElegido.getNumeroDePresupuesto(), "N",29,null,null);
            this.dispose();
        } catch (RemoteException ex) {
            Logger.getLogger(ImputacionGastosServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        char tecla = evt.getKeyChar();
        String cod = jTextField1.getText();
        int punto = cod.indexOf(".") + 1;
        if (punto == 0) {
            if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE && tecla != KeyEvent.VK_PERIOD) {
                evt.consume();
                getToolkit().beep();
            }
        } else if (!Character.isDigit(tecla) && tecla != KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            getToolkit().beep();
        }

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

            ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) jTable1.getModel();
            List<ServiciosSubcontratadosItem> itemsSeleccionados = model.getRows();
            Iterator it = itemsSeleccionados.iterator();
            while(it.hasNext()){
                ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem)it.next();
                if(jCheckBox1.isSelected()){
                    jTable1.getSelectionModel().setSelectionInterval(0, jTable1.getModel().getRowCount());
                }
                else
                    jTable1.getSelectionModel().clearSelection();
            }
            jTable1.setModel(model);
            jTable1.updateUI();

    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    // End of variables declaration//GEN-END:variables

}
