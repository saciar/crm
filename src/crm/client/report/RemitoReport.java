/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.report;

import crm.libraries.report.Remito;
import crm.libraries.report.RemitoEquipos;
import crm.services.report.sei.RemitoReportSEI;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.wsif.WSIFException;

/**
 *
 * @author sergio
 */
public class RemitoReport extends CongressReportService implements RemitoReportSEI{
	private static final String REPORT_LP_NAME = "remito.jasper";
	private static final String REPORT_LP_SERVICIOS_NAME = "remito_equipos.jasper";
	
	private RemitoReportSEI stub;
	private static RemitoReport instance;
	
	public RemitoReport() throws WSIFException{
		super("RemitoReportSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Remito", Class.forName("crm.libraries.report.Remito"));	
                        addType("RemitoEquipos", Class.forName("crm.libraries.report.RemitoEquipos"));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (RemitoReportSEI)service.getStub(RemitoReportSEI.class);
	}

	public static RemitoReport instance() {
		try {
			if (instance == null) {
				instance = new RemitoReport();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public JasperPrint createListaPreciosReport(long fecha) throws RemoteException, JRException {

		Remito[] listas = findByNroPpto(fecha);
		String title = "Remito";
		
		return createReport(listas, title);
	}
        
//        public void prueba(){
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/crmvacio", "saciar", "SmA38378");
//                
//                JasperReport reporte = (JasperReport) JRLoader.loadObject("remito2.jasper");
//                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
//                
//                JRExporter exporter = new JRPdfExporter();
//                
//                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
//                
//                exporter.exportReport();
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(RemitoReport.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(RemitoReport.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (JRException ex) {
//                Logger.getLogger(RemitoReport.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
                
	
	@SuppressWarnings("unchecked")
	private JasperPrint createReport(Remito[] listas, String title) throws RemoteException, JRException {
  

		// 1- cargar los reporte desde los .jasper			
		JasperReport listaPreciosReport = (JasperReport)JRLoader.loadObject(REPORT_LP_NAME);
		JasperReport listaPreciosServiciosReport = (JasperReport)JRLoader.loadObject(REPORT_LP_SERVICIOS_NAME);		
		
		// 2- create a map of parameters to pass to the report.
		Map parameters = new HashMap();
		parameters.put("SUBREPORT_EQUIPOS",listaPreciosServiciosReport);
		parameters.put("VEHICULO","KVU 357");
                parameters.put("CHOFER","Pablo Panduro");
//		parameters.put("REPORT_TITLE",title);
//		parameters.put("REPORT_UPDATE",new Date());
		
		// 3- create JasperPrint using fillReport() method
		JasperPrint jasperPrint = JasperFillManager.fillReport(listaPreciosReport, parameters,toJRMap(listas));
		
		return jasperPrint;
	}

    public Remito[] findByNroPpto(long nroppto) throws RemoteException {
        return stub.findByNroPpto(nroppto);
    }

    public JRDataSource toJRMap(Remito[] listas){
		Object[] data = new Object[listas.length];
		
                for (int i=0;i<listas.length;i++) {
			Remito remito = listas[i];
			Map map = new HashMap();
			
			map.put("nombre_fantasia", remito.getEmpresa());	
			map.put("el_calle", remito.getCalle());
                        map.put("el_nro", remito.getNumero());
                        map.put("el_piso", remito.getPiso());
                        map.put("el_depto", "concha");
                        map.put("lc_descriplocalidad", "balin");
                        map.put("iv_descripcond", remito.getConIVA());
                        map.put("CUIT", remito.getCuit());
			map.put("data_source_equipos", toJRMap2(remito.getEquipos()));
			                                               
                        
			data[i] = map;
                }
		return new JRMapArrayDataSource(data);
	}
    
    private JRDataSource toJRMap2(RemitoEquipos[] familias){
		Object[] data = new Object[familias.length];
		
		for (int i=0;i<familias.length;i++) {
			RemitoEquipos familia = familias[i];
			Map map = new HashMap();
			
			map.put("eq_descripcion",familia.getDescripcion());
			map.put("codEquipo",familia.getCodigo());
			
			data[i] = map;
		}
		
		return new JRMapArrayDataSource(data);
	}
        
}

//class ListasPreciosDSBuilder{
//		
//	@SuppressWarnings("unchecked")
//	public static JRMapArrayDataSource toJRMap(Remito[] listas){
//		Object[] data = new Object[listas.length];
//		
//                for (int i=0;i<listas.length;i++) {
//			Remito remito = listas[i];
//			Map map = new HashMap();
//			
//			map.put("nombre_fantasia", remito.getEmpresa());	
//			map.put("el_calle", remito.getCalle());
//                        map.put("el_nro", remito.getNumero());
//                        map.put("el_piso", remito.getPiso());
//                        map.put("el_depto", "concha");
//                        map.put("lc_descriplocalidad", remito.getLocalidad());
//                        map.put("iv_descripcond", remito.getConIVA());
//                        map.put("CUIT", remito.getCuit());
//			map.put("data_source_equipos", toJRMap(remito.getEquipos()));
//			
//                                                
//                        
//			data[i] = map;
//                }
//		return new JRMapArrayDataSource(data);
//	}
//	
//	@SuppressWarnings("unchecked")
//	private static JRMapArrayDataSource toJRMap(RemitoEquipos[] familias){
//		Object[] data = new Object[familias.length];
//		
//		for (int i=0;i<familias.length;i++) {
//			RemitoEquipos familia = familias[i];
//			Map map = new HashMap();
//			
//			map.put("descripcion",familia.getDescripcion());
//			map.put("codigo",familia.getCodigo());
//			
//			data[i] = map;
//		}
//		
//		return new JRMapArrayDataSource(data);
//	}
//	
//    
//}
