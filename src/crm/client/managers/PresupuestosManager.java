package crm.client.managers;

import java.rmi.RemoteException;
import java.util.Date;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.helper.PresupuestoHelper;
import crm.services.sei.PresupuestosManagerSEI;
import crm.services.transaction.ManagerService;
import crm.services.wsdl2.manager.WSDL2Service;

public class PresupuestosManager extends CongressCRMService implements PresupuestosManagerSEI {
	private static PresupuestosManager instance;

	private PresupuestosManagerSEI stub;

	private PresupuestosManager() throws WSIFException {
		super("PresupuestosManagerSEI");
	}

	protected void registerTypes() throws WSIFException {
		// map types
		try {
                addType("ArrayOfEquipos", Class.forName("crm.client.deserializer.ArrayOfEquipos"));
			addType("anyType", Class.forName("java.lang.Object"));
			addType("Presupuesto", Class.forName("crm.libraries.abm.entities.Presupuesto"));
            addType("Acceso", Class.forName("crm.libraries.abm.entities.Acceso"));
            addType("Ppto_Sala_Servicio_Desc_Detallada", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada"));
			addType("Ppto_Sala_Servicio", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Servicio"));
			addType("Ppto_Sala_Agregado", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Agregado"));			
			addType("Ppto_Sala", Class.forName("crm.libraries.abm.entities.Ppto_Sala"));
			addType("Ppto_Sala_Horario", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Horario"));
			addType("PptoCambioEstado", Class.forName("crm.libraries.abm.entities.PptoCambioEstado"));
			addType("Ppto_Facturas", Class.forName("crm.libraries.abm.entities.Ppto_Facturas"));
			addType("EstadoEvento", Class.forName("crm.libraries.abm.entities.EstadoEvento"));
			addType("Usuario", Class.forName("crm.libraries.abm.entities.Usuario"));

			// addType("PresupuestoAbstracto",
			// Class.forName("crm.libraries.presupuestos.PresupuestoAbstracto"));
			addType("SalaHelper", Class.forName("crm.libraries.abm.helper.SalaHelper"));
			addType("PresupuestoHelper", Class.forName("crm.libraries.abm.helper.PresupuestoHelper"));
			addType("ServicioHelper", Class.forName("crm.libraries.abm.helper.ServicioHelper"));
			addType("HorariosHelper", Class.forName("crm.libraries.abm.helper.HorariosHelper"));
			addType("EstadoActualHelper", Class.forName("crm.libraries.abm.helper.EstadoActualHelper"));
			addType("ContactoHelper", Class.forName("crm.libraries.abm.helper.ContactoHelper"));
			addType("ContactoLugarHelper", Class.forName("crm.libraries.abm.helper.ContactoLugarHelper"));
			addType("FacturacionHelper", Class.forName("crm.libraries.abm.helper.FacturacionHelper"));
			addType("RentabilidadHelper", Class.forName("crm.libraries.abm.helper.RentabilidadHelper"));
			addType("SeguimientoHelper", Class.forName("crm.libraries.abm.helper.SeguimientoHelper"));
			addType("AgregadoHelper", Class.forName("crm.libraries.abm.helper.AgregadoHelper"));
			addType("AdelantoHelper", Class.forName("crm.libraries.abm.helper.AdelantoHelper"));
			addType("AgregadoSalaHelper", Class.forName("crm.libraries.abm.helper.AgregadoSalaHelper"));
			addType("DescDetalladaServicioHelper", Class.forName("crm.libraries.abm.helper.DescDetalladaServicioHelper"));
			addType("PagoHelper", Class.forName("crm.libraries.abm.helper.PagoHelper"));
			addType("ArrayOfSalaHelper", Class.forName("crm.client.deserializer.ArrayOfSalaHelper"));
			addType("ArrayOfServicioHelper", Class.forName("crm.client.deserializer.ArrayOfServicioHelper"));
			addType("ArrayOfHorariosHelper", Class.forName("crm.client.deserializer.ArrayOfHorariosHelper"));
			addType("ArrayOfAgregadoSalaHelper", Class.forName("crm.client.deserializer.ArrayOfAgregadoSalaHelper"));
			addType("ArrayOfDescDetalladaHelper", Class.forName("crm.client.deserializer.ArrayOfDescDetalladaHelper"));
			addType("ArrayOfString", Class.forName("crm.client.deserializer.ArrayOfString"));
			addType("collection", Class.forName("crm.client.deserializer.ArrayOfObject"));
			addType("TipoEvento", Class.forName("crm.libraries.abm.entities.TipoEvento"));
			addType("TipoLugarEvento", Class.forName("crm.libraries.abm.entities.TipoLugarEvento"));
			addType("TipoUniforme", Class.forName("crm.libraries.abm.entities.TipoUniforme"));
			addType("Cliente", Class.forName("crm.libraries.abm.entities.Cliente"));
			/*
			 * addType("Referencia", Class .forName("crm.libraries.abm.entities.Referencia"));
			 */
			addType("Ppto_Sala", Class.forName("crm.libraries.abm.entities.Ppto_Sala"));
			addType("TipoArmado", Class.forName("crm.libraries.abm.entities.TipoArmado"));
			addType("Pptp_Sala_Servicio", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Servicio"));
			addType("Ppto_Sala_Agregado", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Agregado"));			
			addType("Pptp_Sala_Servicio_Desc_Detallada", Class.forName("crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada"));
			addType("LugarEvento", Class.forName("crm.libraries.abm.entities.LugarEvento"));
			addType("Servicio", Class.forName("crm.libraries.abm.entities.Servicio"));
			addType("SalaLugar", Class.forName("crm.libraries.abm.entities.SalaLugar"));
			addType("VendedorPpto", Class.forName("crm.libraries.abm.entities.VendedorPpto"));
			addType("Vendedor", Class.forName("crm.libraries.abm.entities.Vendedor"));
			addType("PrtPptoHeader", Class.forName("crm.libraries.abm.entities.PrtPptoHeader"));
			addType("PrtPptoFPago", Class.forName("crm.libraries.abm.entities.PrtPptoFPago"));
			addType("PrtPptoValidez", Class.forName("crm.libraries.abm.entities.PrtPptoValidez"));
			addType("PrtPptoCondPago", Class.forName("crm.libraries.abm.entities.PrtPptoCondPago"));
			addType("PrtPptoCancelacion", Class.forName("crm.libraries.abm.entities.PrtPptoCancelacion"));
			addType("PrtPptoTipoPresupuesto", Class.forName("crm.libraries.abm.entities.PrtPptoTipoPresupuesto"));
			addType("PrtPptoPeriodo", Class.forName("crm.libraries.abm.entities.PrtPptoPeriodo"));
			addType("PptoEstadoActual", Class.forName("crm.libraries.abm.entities.PptoEstadoActual"));
			addType("MonedaExtranjera", Class.forName("crm.libraries.abm.entities.MonedaExtranjera"));
			addType("Ppto_Contacto", Class.forName("crm.libraries.abm.entities.Ppto_Contacto"));
			addType("Ppto_Contacto_Lugar", Class.forName("crm.libraries.abm.entities.Ppto_Contacto_Lugar"));
			addType("Ppto_Facturacion", Class.forName("crm.libraries.abm.entities.Ppto_Facturacion"));
			addType("Ppto_Seguimiento", Class.forName("crm.libraries.abm.entities.Ppto_Seguimiento"));
			addType("Ppto_Agregado", Class.forName("crm.libraries.abm.entities.Ppto_Agregado"));
			addType("Ppto_Adelanto", Class.forName("crm.libraries.abm.entities.Ppto_Adelanto"));
			addType("Ppto_Rentabilidad", Class.forName("crm.libraries.abm.entities.Ppto_Rentabilidad"));
			addType("Ppto_Pago", Class.forName("crm.libraries.abm.entities.Ppto_Pago"));
			addType("ModalidadContrat", Class.forName("crm.libraries.abm.entities.ModalidadContrat"));

			addType("Ppto_GastoSC", Class.forName("crm.libraries.abm.entities.Ppto_GastoSC"));
			addType("GastoContratHelper", Class.forName("crm.libraries.abm.helper.GastoContratHelper"));
			addType("ArrayOfGastoContratHelper", Class.forName("crm.client.deserializer.ArrayOfGastoContratHelper"));
			addType("Ppto_GastoOperador", Class.forName("crm.libraries.abm.entities.Ppto_GastoOperador"));
			addType("Ppto_GastoRepresentacion", Class.forName("crm.libraries.abm.entities.Ppto_GastoRepresentacion"));
			addType("Ppto_GastoAsistentes", Class.forName("crm.libraries.abm.entities.Ppto_GastoAsistentes"));
			addType("Ppto_GastoHoteleria", Class.forName("crm.libraries.abm.entities.Ppto_GastoHoteleria"));
			addType("Ppto_GastoVarios", Class.forName("crm.libraries.abm.entities.Ppto_GastoVarios"));
			addType("Ppto_GastoViaticos", Class.forName("crm.libraries.abm.entities.Ppto_GastoViaticos"));

                        addType("Equipos", Class.forName("crm.libraries.abm.entities.Equipos"));

			addType("ArrayOfPpto_GastoOperador", Class.forName("crm.client.deserializer.ArrayOfPpto_GastoOperador"));
			addType("ArrayOfPpto_GastoRepresentacion", Class.forName("crm.client.deserializer.ArrayOfPpto_GastoRepresentacion"));
			addType("ArrayOfPpto_GastoAsistentes", Class.forName("crm.client.deserializer.ArrayOfPpto_GastoAsistentes"));
			addType("ArrayOfPpto_GastoHoteleria", Class.forName("crm.client.deserializer.ArrayOfPpto_GastoHoteleria"));
			addType("ArrayOfPpto_GastoVarios", Class.forName("crm.client.deserializer.ArrayOfPpto_GastoVarios"));
			addType("ArrayOfPpto_GastoViaticos", Class.forName("crm.client.deserializer.ArrayOfPpto_GastoViaticos"));			

			addType("FamiliaServ", Class.forName("crm.libraries.abm.entities.FamiliaServ"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// throw new WSIFException(e);
		}

		stub = (PresupuestosManagerSEI) service.getStub(PresupuestosManagerSEI.class);
	}

	public static PresupuestosManager instance() {
		try {
			if (instance == null) {
				instance = new PresupuestosManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
/**********************************************************************************************/	

	/*public Object[] buscarPorCliente(long codCliente) throws RemoteException {
		return stub.buscarPorCliente(codCliente);
	}
	
	public Object[] buscarPorClienteYFecha(long codCliente, String fecha)throws RemoteException{
		return stub.buscarPorClienteYFecha(codCliente, fecha);		
	}
	
	public Object[] buscarPorEstado(String codEstado) throws RemoteException {
		return stub.buscarPorEstado(codEstado);
	}

	public Object[] buscarPorFechaInicio(String fechaInicio) throws RemoteException {
		return stub.buscarPorFechaInicio(fechaInicio);
	}

	public Object[] buscarPorLugar(long codigoLugar) throws RemoteException {
		return stub.buscarPorLugar(codigoLugar);
	}
	
	public Object[] buscarPorLugarYFecha(long codLugar, String fecha) throws RemoteException{
		return stub.buscarPorLugarYFecha(codLugar, fecha);
	}*/

	public Presupuesto buscarPresupuesto(long numPpto) throws RemoteException {
		return stub.buscarPresupuesto(numPpto);
	}

	/*public Object[] buscarPorVendedor(long codVendedor) throws RemoteException {
		return stub.buscarPorVendedor(codVendedor);
	}*/
	
	public Object[] buscarPorNumero(long nro) throws RemoteException{
		return stub.buscarPorNumero(nro);
	}
	
	public Object[] buscarEstadoActual(long nroPpto) throws RemoteException{
		return stub.buscarEstadoActual(nroPpto);
	}
	/*public Object[] buscarPorClienteFac(long codCliente) throws RemoteException{
		return stub.buscarPorClienteFac(codCliente);
	}
	
	public Object[] buscarPorTipoEvt(long nro) throws RemoteException{
		return stub.buscarPorTipoEvt(nro);
	}*/
/*********************************************************************************************/
	
	public long nuevoPresupuesto(PresupuestoHelper pa) throws RemoteException {
		return stub.nuevoPresupuesto(pa);
	}

	public long actualizarPresupuesto(PresupuestoHelper p) throws RemoteException {
		return stub.actualizarPresupuesto(p);
	}
	
	public boolean canEdit(long nroPpto,String loggedUser)throws RemoteException{
		return stub.canEdit(nroPpto,loggedUser);
	}
/**********************************************************************************************/
	
	public Object[] buscarPendientesPorVendedor(String codigoVendedor) throws RemoteException {
		return stub.buscarPendientesPorVendedor(codigoVendedor);
	}

	public Object[] buscarDeHoyPorVendedor(String codigoVendedor) throws RemoteException {
		return stub.buscarDeHoyPorVendedor(codigoVendedor);
	}

	public Object[] buscarConfirmadosPorVendedor(String codigoVendedor) throws RemoteException {
		return stub.buscarConfirmadosPorVendedor(codigoVendedor);
	}
	
	public Object[] buscarAVencerPorVendedor(String codigoVendedor) throws RemoteException {
		return stub.buscarAVencerPorVendedor(codigoVendedor);
	}

	public Object[] buscarPendientesPorUC(String codigoVendedor) throws RemoteException  {
		return stub.buscarPendientesPorUC(codigoVendedor);
	}

	public Object[] buscarAVencerPorUC(String codigoVendedor) throws RemoteException  {
		return stub.buscarAVencerPorUC(codigoVendedor);
	}

	public Object[] buscarDeHoyPorUC(String codigoVendedor) throws RemoteException {
		return stub.buscarDeHoyPorUC(codigoVendedor);
	}

	public Object[] buscarConfirmadosPorUC(String codigoVendedor)  throws RemoteException {
		return stub.buscarConfirmadosPorUC(codigoVendedor);
	}
	
	public double getFacturacionByPPto(long nroPpto)throws RemoteException{
		return stub.getFacturacionByPPto(nroPpto);
	}
	
	public String getFechaByNroPptoAndStateAndUser(long nroPpto, int codEstado) throws RemoteException {
		return stub.getFechaByNroPptoAndStateAndUser(nroPpto, codEstado);
	}
/**********************************************************************************************/
	
	public void setAsCobrado(String numeroPresupuesto,String usuario) throws RemoteException {
		stub.setAsCobrado(numeroPresupuesto,usuario);
	}
	
	public Object[] findOFNoFacturados() throws RemoteException {
		return stub.findOFNoFacturados();
	}

	public void setAsFacturado(String numeroPresupuesto,String usuario, String nrofactura) throws RemoteException {
		stub.setAsFacturado(numeroPresupuesto, usuario, nrofactura);
	}
	
	public Object[] getPresupuestoByFactura(String nrofactura) throws RemoteException{
		return stub.getPresupuestoByFactura(nrofactura);
	}
	
	public Object[] getFacturasByNroPpto(long nroppto) throws RemoteException{
		return stub.getFacturasByNroPpto(nroppto);
	}
	
	public String getFacturaByNroPpto(long nroppto) throws RemoteException{
		return stub.getFacturaByNroPpto(nroppto);
	}
	
/*********************************************************************************************/

	public boolean isPptoActualizado(long nroPpto) throws RemoteException{
		return stub.isPptoActualizado(nroPpto);
	}
	
	public boolean isPptoCancelado(long nroPpto) throws RemoteException{
		return stub.isPptoCancelado(nroPpto);
	}
	
	public String getMaxFechaByNroPptoAndState(long nroPpto, int codEstado)throws RemoteException{
		return stub.getMaxFechaByNroPptoAndState(nroPpto, codEstado);
	}
	
//	public void modificarActivo(long nroPpto, String activo) throws RemoteException{
//		stub.modificarActivo(nroPpto, activo);
//	}
	
        public void modificarActivo(long nroPpto, String activo, int codUsuario, String ip, String mac) throws RemoteException{
		stub.modificarActivo(nroPpto, activo,codUsuario,ip,mac);
	}
        
	public int getCantPresupuestosByClientes(long codCliente) throws RemoteException{
		return stub.getCantPresupuestosByClientes(codCliente);
	}

	/*public void setAsAdelantado(String numeroPresupuesto, String usuarioId) throws RemoteException {
		stub.setAsAdelantado(numeroPresupuesto, usuarioId);		
	}*/
	public void setAsAdelantado(String numeroPresupuesto, String usuarioId, String nrofactura)  throws RemoteException {
		stub.setAsAdelantado(numeroPresupuesto, usuarioId, nrofactura);		
	}
	
	public Object[] findOFNoFacturadosByUnidadAdm(String codUnidad) throws RemoteException{
		return stub.findOFNoFacturadosByUnidadAdm(codUnidad);
	}
	
	public Object[] findFacturadosNoCobradosByUnidadAdm(String codUnidad) throws RemoteException{
		return stub.findFacturadosNoCobradosByUnidadAdm(codUnidad);
	}
	
	public Object[] buscarPresupuestosAbiertosVendedor(long codVendedor) throws RemoteException{
		return stub.buscarPresupuestosAbiertosVendedor(codVendedor);
	}

	public void setAsCobradoAConfirmar(String numeroPresupuesto, String usuarioId) throws RemoteException {
		stub.setAsCobradoAConfirmar(numeroPresupuesto, usuarioId);
		
	}

	public void setAnticipoAConfirmar(String numeroPresupuesto, String usuarioId) throws RemoteException {
		stub.setAnticipoAConfirmar(numeroPresupuesto, usuarioId);
		
	}

	public void setAnticipoCobrado(String numeroPresupuesto, String usuarioId) throws RemoteException {
		stub.setAnticipoCobrado(numeroPresupuesto,usuarioId);
		
	}

	public Object[] findFacturadosNoCobradosByUnidadAdmNroPpto(String codUnidad, long nroppto) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findFacturadosNoCobradosByUnidadAdmNroPpto(codUnidad,nroppto);
	}

	public void setAsAdicionalesFacturados(String numeroPresupuesto, String usuarioId, String nrofactura) throws RemoteException {
		// TODO Auto-generated method stub
		stub.setAsAdicionalesFacturados(numeroPresupuesto,usuarioId,nrofactura);
	}

	public Object[] findCobradosByUnidadAdmNroPpto(String codUnidad, long nroppto) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findCobradosByUnidadAdmNroPpto(codUnidad,nroppto);
	}
	
	public Object[] buscar(String clienteEvt, String clienteFact, String fechaDesde, String fechaHasta, String lugar,
			String vendedor, String uc, String estado, String tipoEvt, String nombreEvt) throws RemoteException{
		return stub.buscar(clienteEvt, clienteFact, fechaDesde, fechaHasta, lugar,vendedor, uc, estado, tipoEvt, nombreEvt);
	}
	
	public boolean setUnidadAdministrativaByNroPpto(String nroppto, String codUnidad) throws RemoteException{
		return stub.setUnidadAdministrativaByNroPpto(nroppto, codUnidad);
	}
	
	public Object[] findOFNoFacturadosByUnidadAdm2(String codUnidad) throws RemoteException{
		return stub.findOFNoFacturadosByUnidadAdm2(codUnidad);
	}
	
	public Object[] buscarParaReportes(String clienteEvt, String clienteFact, String fechaDesde, String fechaHasta, String lugar,
			String uc, String estado, String tipoEvt, String nombreEvt, String codFacturacion) throws RemoteException{
		return stub.buscarParaReportes(clienteEvt, clienteFact, fechaDesde, fechaHasta, lugar, uc, estado, tipoEvt, nombreEvt, codFacturacion);
	}
	
	public Object[] buscarParaReportesDeServicios(String clienteEvt,String fechaDesde, String fechaHasta, String lugar,
			String condpago, String estado, boolean subcontratado, String codServ, String nombreSub) throws RemoteException{
		return stub.buscarParaReportesDeServicios(clienteEvt, fechaDesde, fechaHasta, lugar, condpago, estado, subcontratado, codServ, nombreSub);
	}
	
	public Object[] buscarParaReportesFacturacion(long nroPpto, String nroFactura, String codUnidad) throws RemoteException{
		return stub.buscarParaReportesFacturacion(nroPpto, nroFactura, codUnidad);
	}
	
	public void setFactura(long id, String nroFactura) throws RemoteException {
		stub.setFactura(id, nroFactura);
	}
	
	public Object[] buscarParaReportesRentabilidad(String clienteEvt, String clienteFact, String fechaDesde, String fechaHasta, String lugar,
			String condpago, String estado, String tipoEvt, String nombreEvt, String vendedores) throws RemoteException{
		return stub.buscarParaReportesRentabilidad(clienteEvt, clienteFact, fechaDesde, fechaHasta, lugar, condpago, estado, tipoEvt, nombreEvt,vendedores);
	}
	
	public void setDesConfirmado(String numeroPresupuesto, String usuarioId) throws RemoteException {
		stub.setDesConfirmado(numeroPresupuesto, usuarioId);
	}
	
	public Object[] buscarParaReportesRentabilidadCostos(String nroppto) throws RemoteException{
		return stub.buscarParaReportesRentabilidadCostos(nroppto);
	}
	
	public Object[] buscarParaReportesDeFamServicios(String clienteEvt,String fechaDesde, String fechaHasta, String lugar,
			String condpago, String estado, boolean subcontratado, String codFam) throws RemoteException{
		return stub.buscarParaReportesDeFamServicios(clienteEvt, fechaDesde, fechaHasta, lugar, condpago, estado, subcontratado, codFam);
	}
	
	public Object[] buscarHorariosCostos(String codSala) throws RemoteException{
		return stub.buscarHorariosCostos(codSala);
	}
                
        public Object[] buscarParaReportesDeServiciosSubc(String fechaDesde, String fechaHasta, int codEstado) throws RemoteException{
                return stub.buscarParaReportesDeServiciosSubc(fechaDesde, fechaHasta, codEstado);
        }

    public Object[] buscarParaReportesComercial(String clienteEvt, String fechaDesde, String fechaHasta, String lugar, String estado, String vendedor) throws RemoteException {
        return stub.buscarParaReportesComercial(clienteEvt, fechaDesde, fechaHasta, lugar, estado, vendedor);
    }

    @Override
	public Object[] buscarParaReportesGerencia(String clienteEvt, String fechaDesde, String fechaHasta, String lugar,
			String estado, String tipoEvt, String vend) throws RemoteException {
		
		return stub.buscarParaReportesGerencia(clienteEvt, fechaDesde, fechaHasta, lugar, estado, tipoEvt,vend);
	}
	
	public Object[] buscarParaReportesGerenciaVendedores(String clienteEvt,String fechaDesde, String fechaHasta, String lugar,
			String estado, String tipoEvt, String vend) throws RemoteException{
		return stub.buscarParaReportesGerenciaVendedores(clienteEvt, fechaDesde, fechaHasta, lugar, estado, tipoEvt, vend);
	}

	@Override
	public Object[] buscarComparacionesGerenciaAnio(String vendedores, String anios, String estado, String tipos, int mes)
			throws RemoteException {
		// TODO Auto-generated method stub
		return stub.buscarComparacionesGerenciaAnio(vendedores, anios, estado,tipos, mes);
	}
	
	public Object[] buscarComparacionesGerenciaMeses(String vendedores, String meses, String estado, String tipos, int anio) throws RemoteException{
		return stub.buscarComparacionesGerenciaMeses(vendedores, meses, estado, tipos, anio);
	}
	
	public Object[] buscarPptosParaOperadores(String fechaDesde, String fechaHasta) throws RemoteException{
		return stub.buscarPptosParaOperadores(fechaDesde, fechaHasta);
	}
	
	public Object[] buscarOperadoresPorPpto(int nroPpto, String fecha) throws RemoteException{
		return stub.buscarOperadoresPorPpto(nroPpto, fecha);
	}
}