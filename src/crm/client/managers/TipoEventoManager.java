package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoEvento;
import crm.services.sei.TipoEventoManagerSEI;

public class TipoEventoManager extends CongressCRMService implements TipoEventoManagerSEI {
	private TipoEventoManagerSEI stub;
	
	public TipoEventoManager() throws WSIFException{
		super("TipoEventoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoEvento", Class.forName("crm.libraries.abm.entities.TipoEvento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoEventoManagerSEI)service.getStub(TipoEventoManagerSEI.class);
	}


	public TipoEvento getTipoEventoById(String codigo) throws RemoteException {
		return stub.getTipoEventoById(codigo);
	}


	public TipoEvento[] getAllTipoEventos() throws RemoteException {
		return stub.getAllTipoEventos();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoEvento tipoEvento) throws RemoteException {
		stub.update(tipoEvento);
	}


	public TipoEvento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoEventoManager instance;

	public static TipoEventoManager instance() {
		try {
			if (instance == null) {
				instance = new TipoEventoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTipoEventosReport() throws RemoteException {
		return stub.getTipoEventosReport();
	}


	public TipoEvento getTipoEventoByDescripcion(String descripcion) throws RemoteException {
		return stub.getTipoEventoByDescripcion(descripcion);
	}
}
