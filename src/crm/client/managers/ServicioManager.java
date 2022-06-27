package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Servicio;
import crm.services.sei.ServicioManagerSEI;

public class ServicioManager extends CongressCRMService implements ServicioManagerSEI {
	private ServicioManagerSEI stub;

	public ServicioManager() throws WSIFException {
		super("ServicioManagerSEI");
	}

	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Servicio", Class
					.forName("crm.libraries.abm.entities.Servicio"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// throw new WSIFException(e);
		}

		stub = (ServicioManagerSEI) service.getStub(ServicioManagerSEI.class);
	}

	public Servicio getServicioById(String codigo) throws RemoteException {
		return stub.getServicioById(codigo);
	}

	public Servicio[] getAllServicios() throws RemoteException {
		return stub.getAllServicios();
	}

	public Servicio[] findByField(String field, String value)
			throws RemoteException {
		return stub.findByField(field, value);
	}
	
	public Servicio[] findByFieldExactly(String field, String value)
			throws RemoteException {
		return stub.findByFieldExactly(field, value);
	}
	
	public Servicio[] getAllServiciosTranslated(String lang)
			throws RemoteException {
		return getAllServiciosTranslated(lang);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public int getUnidadDeNegocio(String codigo) throws RemoteException{
		return stub.getUnidadDeNegocio(codigo);
	}
	
	public String update(Servicio servicio, String descDetEspaniol, String descDetIngles) throws RemoteException {
		return stub.update(servicio,descDetEspaniol,descDetIngles);
	}

	private static ServicioManager instance;

	public static ServicioManager instance() {
		try {
			if (instance == null) {
				instance = new ServicioManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public Object[] getAllServiciosReport() throws RemoteException {
		return stub.getAllServiciosReport();
	}
	
	public Object[] getServiciosByFamiliaAndPlaceReport(String familia) throws RemoteException {
		return stub.getServiciosByFamiliaAndPlaceReport(familia);
	}
	
	public double getPrecioVtaById(String cod) throws RemoteException{
		return stub.getPrecioVtaById(cod);
	}
	
	public double getDescuentoByServicioAndTechoDias(String codServ, int cantDias) throws RemoteException{
		return stub.getDescuentoByServicioAndTechoDias(codServ, cantDias);
	}
	
	public String admiteAccesorioSegunCodServicio(String codServicio)throws RemoteException {
		return stub.admiteAccesorioSegunCodServicio(codServicio);
	}

	public String buscarDescripcionEspaniol(String codigo)throws RemoteException {
		return stub.buscarDescripcionEspaniol(codigo);
	}

	public String buscarDescripcionIngles(String codigo)throws RemoteException  {
		return stub.buscarDescripcionIngles(codigo);
	}

	public String buscarDescripcion(String codServicio, String idioma)throws RemoteException  {
		return stub.buscarDescripcion(codServicio,idioma);
	}

	public void setDescripcion(String servicioId, String idiomaId, String descripcion) throws RemoteException {
		this.stub.setDescripcion(servicioId,idiomaId,descripcion);
	}
	
	public String getDescripcion(String servicioId, String idiomaId) throws RemoteException {
		return this.stub.getDescripcion(servicioId,idiomaId);
	}
	
	public String admiteDescuentoSegunCodServicio(String codServicio)throws RemoteException{
		return stub.admiteDescuentoSegunCodServicio(codServicio);
	}
}
