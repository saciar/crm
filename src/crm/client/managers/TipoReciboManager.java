package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoRecibo;
import crm.services.wsdl2.sei.TipoReciboManagerSEI;

public class TipoReciboManager extends CongressCRMServiceWSDL2 implements TipoReciboManagerSEI {
	private TipoReciboManagerSEI stub;
	
	public TipoReciboManager() throws WSIFException{
		super("TipoReciboManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoRecibo", Class.forName("crm.libraries.abm.entities.TipoRecibo"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoReciboManagerSEI)service.getStub(TipoReciboManagerSEI.class);
	}


	public TipoRecibo getTipoReciboById(String codigo) throws RemoteException {
		return stub.getTipoReciboById(codigo);
	}


	public TipoRecibo[] getAllTipoRecibos() throws RemoteException {
		return stub.getAllTipoRecibos();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoRecibo tipoEvento) throws RemoteException {
		stub.update(tipoEvento);
	}


	public TipoRecibo[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoReciboManager instance;

	public static TipoReciboManager instance() {
		try {
			if (instance == null) {
				instance = new TipoReciboManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTipoReciboReport() throws RemoteException {
		return stub.getTipoReciboReport();
	}


	public TipoRecibo getTipoReciboByDescripcion(String descripcion) throws RemoteException {
		return stub.getTipoReciboByDescripcion(descripcion);
	}


}
