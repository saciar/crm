package crm.client.helpers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import crm.client.util.DateConverter;
import crm.libraries.abm.entities.Ppto_GastoAsistentes;
import crm.libraries.abm.entities.Ppto_GastoHoteleria;
import crm.libraries.abm.entities.Ppto_GastoOperador;
import crm.libraries.abm.entities.Ppto_GastoRepresentacion;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_GastoVarios;
import crm.libraries.abm.entities.Ppto_GastoViaticos;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Horario;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.helper.AdelantoHelper;
import crm.libraries.abm.helper.AgregadoHelper;
import crm.libraries.abm.helper.ContactoHelper;
import crm.libraries.abm.helper.ContactoLugarHelper;
import crm.libraries.abm.helper.DescDetalladaServicioHelper;
import crm.libraries.abm.helper.EstadoActualHelper;
import crm.libraries.abm.helper.FacturacionHelper;
import crm.libraries.abm.helper.HorariosHelper;
import crm.libraries.abm.helper.PagoHelper;
import crm.libraries.abm.helper.PresupuestoHelper;
import crm.libraries.abm.helper.RentabilidadHelper;
import crm.libraries.abm.helper.SalaHelper;
import crm.libraries.abm.helper.ServicioHelper;
import gui.gastos.ImputacionGastosServicios;
import gui.tables.ServiciosSubcontratadosItem;
import gui.tables.ServiciosSubcontratadosTableModel;

public class GastosBuilder {
	
	private static final String PRESUPUESTO_INACTIVO = "N";

	private PresupuestoHelper p;

	private Presupuesto pptoAnterior;
        private ImputacionGastosServicios pantallaImputacion;
	
	public GastosBuilder(Presupuesto presupAnterior,ImputacionGastosServicios igs){
		pptoAnterior = presupAnterior;
                pantallaImputacion = igs;
	}
	
	public void run() {
		buildPresupuesto();
	}
	
	private void buildPresupuesto(){
		p = new PresupuestoHelper();
		buildSubcontratados();
		buildGastosAsistentes();
		buildGastosHoteleria();
		buildGastosVarios();
		buildGastosViaticos();
		buildGastosRepresentacion();
		
		buildCliente();
		
		buildMainPanel();
		buildEvento();
		buildLugar();
		buildReportes();
		
		p.setActivo(PRESUPUESTO_INACTIVO);			
		
		p.setEstado(getEstado());
		p.setContacto(getContacto());
		p.setContactoLugar(getContactoLugar());
		p.setSalas(buildSalas2());
		
		p.setAgregado(getAgregados());
		//----FACTURACION----------------------------------------------------------------------
		p.setFacturacion(getFacturacion());
		p.setPago(buildPago());
		//----RENTABILIDAD----------------------------------------------------------------------
		p.setRentabilidad(getRentabilidad());
		//----ADELANTO------------------------------------------------------------------------
		p.setAdelanto(getAdelanto());
		
	}
	
	private void buildSubcontratados(){
            if (pantallaImputacion != null) {
                //p.setGastosSubcontratados(pantallaImputacion.getGastos());
                p.setGastosContratHelper(pantallaImputacion.getGastos2());
            } else if (pptoAnterior.getNumeroDePresupuesto() > 0) {
                Set gastos = pptoAnterior.getGastosSC();
                Ppto_GastoSC[] gastosArray = new Ppto_GastoSC[gastos.size()];
                if (gastos != null) {
                    Iterator it = gastos.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        Ppto_GastoSC gasto = (Ppto_GastoSC) it.next();
                        gastosArray[i].setDetalle(gasto.getDetalle());
                        gastosArray[i].setPrecio(gasto.getPrecio());
                        gastosArray[i].setProveedor(gasto.getProveedor());
                        gastosArray[i].setCosto(gasto.getCosto());
                        gastosArray[i].setSala(gasto.getSala());
                        gastosArray[i].setCantidad(gasto.getCantidad());
                        gastosArray[i].setEstado(gasto.getEstado());
                        i++;
                    }
                }
                p.setGastosSubcontratados(gastosArray);
            }
	}
	
	public void buildGastosOperadores(){
            if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosOperador();
			Ppto_GastoOperador[] gastosArray = new Ppto_GastoOperador[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoOperador gasto = (Ppto_GastoOperador) it.next();					
					gastosArray[i].setOperador(gasto.getOperador());
					gastosArray[i].setCargo(gasto.getCargo());
					gastosArray[i].setHorario(gasto.getHorario());
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosOperador(gastosArray);
		}
	}
	
	public void buildGastosRepresentacion(){
            if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosRepresentacion();
			Ppto_GastoRepresentacion[] gastosArray = new Ppto_GastoRepresentacion[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoRepresentacion gasto = (Ppto_GastoRepresentacion) it.next();
                                        gastosArray[i]=new Ppto_GastoRepresentacion();
                                        if(gasto.getDetalle()!=null)
                                            gastosArray[i].setDetalle(gasto.getDetalle());
                                        if(gasto.getCosto()!=null)
                                            gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosRepresentacion(gastosArray);
		}
	}
	
	public void buildGastosAsistentes(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosAsistentes();
			Ppto_GastoAsistentes[] gastosArray = new Ppto_GastoAsistentes[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoAsistentes gasto = (Ppto_GastoAsistentes) it.next();
					gastosArray[i].setAsistente(gasto.getAsistente());
					gastosArray[i].setCargo(gasto.getCargo());
					gastosArray[i].setJornada(gasto.getJornada());
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosAsistentes(gastosArray);
		}
	}
	
	public void buildGastosViaticos(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosViaticos();
			Ppto_GastoViaticos[] gastosArray = new Ppto_GastoViaticos[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoViaticos gasto = (Ppto_GastoViaticos) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());					
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosViaticos(gastosArray);
		}
	}
	
	public void buildGastosHoteleria(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosHoteleria();
			Ppto_GastoHoteleria[] gastosArray = new Ppto_GastoHoteleria[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoHoteleria gasto = (Ppto_GastoHoteleria) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());					
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosHoteleria(gastosArray);
		}
	}
	
	public void buildGastosVarios(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosVarios();
			Ppto_GastoVarios[] gastosArray = new Ppto_GastoVarios[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoVarios gasto = (Ppto_GastoVarios) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());					
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosVarios(gastosArray);
		}
	}
	
	public void buildReportes(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			if(pptoAnterior.getCotizacion()!= null)
				p.setCotizacion(Double.valueOf(pptoAnterior.getCotizacion()));
			else 
				p.setCotizacion(1.00d);
			if(pptoAnterior.getEncabezadoPpto() != null)
				p.setCodigoEncabezado(pptoAnterior.getEncabezadoPpto().getCodigo());
			if(pptoAnterior.getFormaPagoPpto() != null)
				p.setCodigoFormaPago(pptoAnterior.getFormaPagoPpto().getCodigo());
			if(pptoAnterior.getPeriodoPpto() != null)
				p.setCodigoPeriodo(pptoAnterior.getPeriodoPpto().getCodigo());
			if(pptoAnterior.getTipoPpto() != null)
				p.setCodigoTipoPpto(pptoAnterior.getTipoPpto().getCodigo());
			if(pptoAnterior.getValidezPpto() != null)
				p.setCodigoValidez(pptoAnterior.getValidezPpto().getCodigo());
			if(pptoAnterior.getMoneda() != null)
				p.setCodigoMoneda(pptoAnterior.getMoneda().getCodigo());
			else
				p.setCodigoMoneda("2");
			if(pptoAnterior.getCancelacionPpto() != null)
				p.setCodigoCancelacion(pptoAnterior.getCancelacionPpto().getCodigo());
			if(pptoAnterior.getCondPagoPpto() != null)
				p.setCodigoCondPago(pptoAnterior.getCondPagoPpto().getCodigo());
		}
	}
	
	public void buildLugar(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			p.setCodigoLugarDelEvento(pptoAnterior.getLugarDelEvento().getCodigo());
		}
	}
	
	public void buildMainPanel(){
		
		p.setNumeroDePresupuesto(pptoAnterior.getNumeroDePresupuesto());
		p.setCodigoVendedor(pptoAnterior.getVendedor().getVendedor().getCodigo());
                p.setCodigoUsuario("29");
		p.setFechaDeInicio(pptoAnterior.getFechaDeInicio());
		p.setFechaDeFinalizacion(pptoAnterior.getFechaDeFinalizacion());
		p.setCodigoReferencia(pptoAnterior.getCodigoReferencia().toString());	
	}
	
	public void buildCliente(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			p.setCodigoCliente(pptoAnterior.getCliente().getCodigo());
			p.setObservacionesDelCliente(pptoAnterior.getObservacionesDelCliente());				
			p.setResponsableEvento(pptoAnterior.getResponsableEvento());
			p.setResponsableEmail(pptoAnterior.getResponsableEmail());
			p.setResponsableTel(pptoAnterior.getResponsableTel());
			if(pptoAnterior.getResponsableNextelFlota() != null)
				p.setResponsableNextelFlota(pptoAnterior.getResponsableNextelFlota());
			else p.setResponsableNextelFlota(null);
			if(pptoAnterior.getResponsableNextelId() != null)
				p.setResponsableNextelId(pptoAnterior.getResponsableNextelId());
			else p.setResponsableNextelId(null);
		}
	}
	
	public void buildEvento(){
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			p.setFechaDeInstalacion(pptoAnterior.getFechaDeInstalacion());
			if(!pptoAnterior.getTotalDePersonas().equals(""))
				p.setTotalDePersonas(pptoAnterior.getTotalDePersonas());
			else p.setTotalDePersonas(null);
			p.setNombreDelEvento(pptoAnterior.getNombreDelEvento());
			p.setObservacionesDelEvento(pptoAnterior.getObservacionesDelEvento());
			if(pptoAnterior.getTipoDeEvento() != null)
				p.setCodigoTipoDeEvento(pptoAnterior.getTipoDeEvento().getCodigo());
			if(pptoAnterior.getTipoDeLugarDelEvento() != null)
				p.setCodigoTipoDeLugarDelEvento(pptoAnterior.getTipoDeLugarDelEvento().getCodigo());
			if(pptoAnterior.getUniforme() != null)
				p.setCodigoUniforme(pptoAnterior.getUniforme().getCodigo());
		}
	}
	
	private ContactoHelper getContacto(){
		ContactoHelper contacto = new ContactoHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			contacto.setCodContacto(Integer.parseInt(pptoAnterior.getContacto().getCodContacto()));
		}
		
		return contacto;
	}
	
	private AgregadoHelper getAgregados(){
		AgregadoHelper agreg = new AgregadoHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			if (pptoAnterior.getAgregado() != null){
				if (pptoAnterior.getAgregado().getModoIngreso() != null)
					agreg.setModoIngreso(Integer.valueOf(pptoAnterior.getAgregado().getModoIngreso()));
				if (pptoAnterior.getAgregado().getSeguridadIngreso() != null)
					agreg.setSeguridadIngreso(Integer.valueOf(pptoAnterior.getAgregado().getSeguridadIngreso()));
				if(pptoAnterior.getAgregado().getCategoriaEvento() != null)
					agreg.setCategoriaEvento(Integer.valueOf(pptoAnterior.getAgregado().getCategoriaEvento()));
			}
		}
		return agreg;
	}
	
	private AdelantoHelper getAdelanto(){
		AdelantoHelper adelanto = new AdelantoHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			if (pptoAnterior.getAdelanto().getValor() != null)
				adelanto.setValor(Double.valueOf(pptoAnterior.getAdelanto().getValor()));
			if (pptoAnterior.getAdelanto().getPorcentaje() != null)
				adelanto.setPorcentaje(Integer.valueOf(pptoAnterior.getAdelanto().getPorcentaje()));
		}
		return adelanto;
	}
	
	private ContactoLugarHelper getContactoLugar(){
		ContactoLugarHelper contacto = new ContactoLugarHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			contacto.setCodContacto(Integer.parseInt(pptoAnterior.getContactoLugar().getCodContacto()));
		}
		
		return contacto;
	}
	
	private FacturacionHelper getFacturacion(){
		FacturacionHelper fact = new FacturacionHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			if (pptoAnterior.getFacturacion().getCodCliente()!= null)
				fact.setCodCliente(Integer.parseInt(pptoAnterior.getFacturacion().getCodCliente()));
		}
		
		return fact;
	}
	
	private PagoHelper buildPago(){
		PagoHelper pago = new PagoHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			if(pptoAnterior.getPago().getCodMedioPago()!=null)
				pago.setCodMedioPago(Integer.parseInt(pptoAnterior.getPago().getCodMedioPago()));
			if(pptoAnterior.getPago().getCodCondicionPago()!=null)
				pago.setCodCondicionPago(Integer.parseInt(pptoAnterior.getPago().getCodCondicionPago()));
		}
		return pago;
	}
	
	private RentabilidadHelper getRentabilidad(){
		RentabilidadHelper rent = new RentabilidadHelper();
		if(pptoAnterior.getNumeroDePresupuesto()>0){
			rent.setFacturacionOriginal(Double.parseDouble(pptoAnterior.getRentabilidad().getFacturacionOriginal()));
			rent.setFacturacionExtra(Double.parseDouble(pptoAnterior.getRentabilidad().getFacturacionExtra()));
			rent.setCostoOperativo(Double.parseDouble(pptoAnterior.getRentabilidad().getCostoOperativo()));
			rent.setGastosAsistentes(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosAsistentes()));
			rent.setGastosContrataciones(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosContrataciones()));
			rent.setGastosOperadores(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosOperadores()));
			rent.setGastosOtros(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosOtros()));
			rent.setComisionesLugar(Double.parseDouble(pptoAnterior.getRentabilidad().getComisionesLugar()));
			rent.setComisionesTerceros(Double.parseDouble(pptoAnterior.getRentabilidad().getComisionesTerceros()));
		}

		return rent;
	}
	
	/**
	 * Crea un objeto representando el estado de la orden.
	 * @return
	 */
	private EstadoActualHelper getEstado(){
		EstadoActualHelper estado = new EstadoActualHelper();
		
		estado.setCancelado(pptoAnterior.getEstado().getCancelado());
		estado.setCobrado(pptoAnterior.getEstado().getCobrado());
		estado.setConfirmado(pptoAnterior.getEstado().getConfirmado());
		estado.setFacturado(pptoAnterior.getEstado().getFacturado());
		estado.setOrdenDeCompra(pptoAnterior.getEstado().getOc());
		estado.setOrdenDeFacturacion(pptoAnterior.getEstado().getOf());
		estado.setOrdenDeServicio(pptoAnterior.getEstado().getOs());
		estado.setRechazado(pptoAnterior.getEstado().getRechazado());

			estado.setAcobrar(pptoAnterior.getEstado().getAcobrar());
			estado.setAdelantoacobrar(pptoAnterior.getEstado().getAdelantoacobrar());
			estado.setAdelantocobrado(pptoAnterior.getEstado().getAdelantocobrado());
			estado.setAdicionalesFacturados(pptoAnterior.getEstado().getAdicionalesFacturados());

		if(pptoAnterior.getNumeroDePresupuesto()>0){
			estado.setAdelanto(pptoAnterior.getEstadoActual().getAdelanto());
			estado.setAdelantado(pptoAnterior.getEstadoActual().getAdelantado());
		}
				
		return estado;
	}
	
	private SalaHelper[] buildSalas2() {
            SalaHelper[] salashelpers = new SalaHelper[pptoAnterior.getSalas().size()];
            int j = 0;

            if (pptoAnterior.getSalas() != null) {
                Object[] salas = pptoAnterior.getSalasArray();
                for (int i = 0; i < salas.length; i++) {
                    Ppto_Sala pptosala = (Ppto_Sala) salas[i];

                    SalaHelper sh = new SalaHelper();
                    sh.setCodigoSalaLugar(pptosala.getSala().getCodigo());

                    sh.setFechaDeFinalizacion(pptosala.getFechaDeFinalizacion());
                    sh.setFechaDeInicio(pptosala.getFechaDeInicio());
                    sh.setFechaDeInstalacion(pptosala.getFechaDeInstalacion());
                    sh.setNombreSalaUnico(pptosala.getNombreSalaUnico());
                    sh.setObservaciones(pptosala.getObservaciones());
                    if (pptosala.getFechaDesarme() != null) {
                        sh.setFechaDesarme(pptosala.getFechaDesarme());
                    }
                    if (pptosala.getFechaPrueba() != null) {
                        sh.setFechaPrueba(pptosala.getFechaPrueba());
                    }
                    if (pptosala.getOrden() != null) {
                        sh.setOrden(Integer.parseInt(pptosala.getOrden()));
                    }
                    if (pptosala.getTipoArmado() != null) {
                        sh.setTipoArmado(pptosala.getTipoArmado().getCodigo());
                    }
                    if (!pptosala.getTotalDePersonas().equals("")) {
                        sh.setTotalDePersonas(pptosala.getTotalDePersonas());
                    }
//                    if (pantallaImputacion.isSalaPptoByName(pptosala.getSala().getDescripcion())) {
//                        sh.setServicios(buildServiciosFromPantallaImputacion(pptosala.getServicios()));
//                    } else {
//                        sh.setServicios(buildServiciosFromPpto(pptosala.getServicios()));
//                    }
                    sh.setServicios(buildServiciosFromPantallaImputacion(pptosala.getServicios()));
                    sh.setHorarios(buildHorariosFromPpto(pptosala.getHorario()));

                    salashelpers[j++] = sh;
                }
        }

        return salashelpers;
    }     
                
	private ServicioHelper[] buildServiciosFromPantallaImputacion(Set <Ppto_Sala_Servicio> servicios) {

		ServicioHelper[] servicioshelpers = new ServicioHelper[servicios.size()];
		int i = 0;
		for (Ppto_Sala_Servicio servicio : servicios) {

			ServicioHelper sh = new ServicioHelper();
                      
                        sh.setTableItemId(pantallaImputacion.getTableItemIdFromModel(servicio.getId()));
			sh.setOrden(Integer.parseInt(servicio.getOrden()));
			sh.setCantidad(Integer.parseInt(servicio.getCantidad()));
			sh.setCodigoServicio(Integer.parseInt(servicio.getServicio().getCodigo()));
			
			sh.setFechaAlta(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sh.setPrecioDeLista(Double.parseDouble(servicio.getPrecioDeLista()));
			
			sh.setPrecioDescuento(Double.parseDouble(servicio.getPrecioDeLista())+(Double.parseDouble(servicio.getPrecioDeLista())*Double.parseDouble(servicio.getDescuento())/100));
			
//                        if (pantallaImputacion.getModalidadContratacion(servicio) && (servicio.getModalidad().getCodigo()!="3" || servicio.getModalidad().getCodigo()!="4")){
//				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_EXTERNA);
//				sh.setDetalle(servicio.getDetalle());
//				sh.setDescDetallada(buildDescripcionesDetalladasFromPpto(servicio));
//			}
//			else if(!pantallaImputacion.getModalidadContratacion(servicio) && (servicio.getModalidad().getCodigo()!="3" || servicio.getModalidad().getCodigo()!="4")){
//				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_INTERNA);
//				sh.setDetalle(null);
//				sh.setDescDetallada(null);
//			}
//			else if(pantallaImputacion.getModalidadContratacion(servicio) && (servicio.getModalidad().getCodigo()=="3" || servicio.getModalidad().getCodigo()=="4")){
//				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL);
//				sh.setDetalle(servicio.getDetalle());
//				sh.setDescDetallada(buildDescripcionesDetalladasFromPpto(servicio));
//			}
//			else if(!pantallaImputacion.getModalidadContratacion(servicio) && (servicio.getModalidad().getCodigo()=="3" || servicio.getModalidad().getCodigo()=="4")){
//				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_INTERNA_OPCIONAL);
//				sh.setDetalle(null);
//				sh.setDescDetallada(null);
//			}

                        sh.setCodigoModalidadContratacion(Integer.parseInt(servicio.getModalidad().getCodigo()));
                        sh.setDetalle(servicio.getDetalle());
                        sh.setDescDetallada(buildDescripcionesDetalladasFromPpto(servicio));
			sh.setDias(Integer.parseInt(servicio.getDias()));
			sh.setDescuento(Integer.parseInt(servicio.getDescuento()));
			
			sh.setModificado("S");
		
			servicioshelpers[i++] = sh;
		}
		
		return servicioshelpers;
	}
        
        private ServicioHelper[] buildServiciosFromPpto(Set <Ppto_Sala_Servicio> servicios) {

		ServicioHelper[] servicioshelpers = new ServicioHelper[servicios.size()];
		int i = 0;
		for (Ppto_Sala_Servicio servicio : servicios) {

			ServicioHelper sh = new ServicioHelper();
			//le mando -1 para que el presupuesto util se de cuenta q usa el id q tenia en el preupuesto original
			sh.setTableItemId(-1);

			sh.setOrden(Integer.parseInt(servicio.getOrden()));
			sh.setCantidad(Integer.parseInt(servicio.getCantidad()));
			sh.setCodigoServicio(Integer.parseInt(servicio.getServicio().getCodigo()));
			
				sh.setFechaAlta(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sh.setPrecioDeLista(Double.parseDouble(servicio.getPrecioDeLista()));

			sh.setPrecioDescuento(Double.parseDouble(servicio.getPrecioDeLista())+(Double.parseDouble(servicio.getPrecioDeLista())*Double.parseDouble(servicio.getDescuento())/100));
			
			sh.setCodigoModalidadContratacion(Integer.parseInt(servicio.getModalidad().getCodigo()));
			sh.setDetalle(servicio.getDetalle());
			sh.setDescDetallada(buildDescripcionesDetalladasFromPpto(servicio));
			
			
			sh.setDias(Integer.parseInt(servicio.getDias()));
			sh.setDescuento(Integer.parseInt(servicio.getDescuento()));
			
			sh.setModificado("N");
			//sh.setAccesorios(buildAccesorios(servicio));
						
			servicioshelpers[i++] = sh;
		}
		
		return servicioshelpers;
	}
	
	private DescDetalladaServicioHelper[] buildDescripcionesDetalladasFromPpto(Ppto_Sala_Servicio servicio){
		Set descripciones = servicio.getDescripcionDetallada();
		DescDetalladaServicioHelper[] descripcioneshelpers = null;
		int i=0;
		if(descripciones != null){
			descripcioneshelpers = new DescDetalladaServicioHelper[descripciones.size()];
			Iterator it = descripciones.iterator();
			while (it.hasNext()) {

				DescDetalladaServicioHelper ah = new DescDetalladaServicioHelper();
				Ppto_Sala_Servicio_Desc_Detallada d =(Ppto_Sala_Servicio_Desc_Detallada)it.next();
				ah.setDescripcion(d.getDescripcion());
				
				descripcioneshelpers[i++] = ah;		
			
			}
		}
		return descripcioneshelpers;
	}
        
        private HorariosHelper[] buildHorariosFromPpto(Set <Ppto_Sala_Horario> horarios){
		HorariosHelper[] helper = new HorariosHelper[horarios.size()];
		int i = 0;
		for(Ppto_Sala_Horario horario:horarios){
			HorariosHelper hh = new HorariosHelper();
			hh.setFecha(horario.getFecha());
			hh.setHoraDesde(horario.getHoraDesde());
			hh.setHoraHasta(horario.getHoraHasta());
			
			helper[i++]=hh;
		}
		return helper;
	}
	
	public boolean isValid(){
		return true;
	}
	
	public PresupuestoHelper getPresupuesto(){
		return p;
	}
}
