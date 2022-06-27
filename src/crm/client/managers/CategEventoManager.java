package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CategEvento;
import crm.services.sei.CategEventoManagerSEI;

public class CategEventoManager extends CongressCRMService implements CategEventoManagerSEI {
	private CategEventoManagerSEI stub;
	
	private CategEventoManager() throws WSIFException{
		super("CategEventoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("CategEvento", Class.forName("crm.libraries.abm.entities.CategEvento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (CategEventoManagerSEI)service.getStub(CategEventoManagerSEI.class);
	}
	
	private static CategEventoManager instance;

	public static CategEventoManager instance() {
		try {
			if (instance == null) {
				instance = new CategEventoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getCategEventosReport() throws RemoteException {
		return stub.getCategEventosReport();
	}


	public CategEvento getCategEventoById(String codigo) throws RemoteException {
		return stub.getCategEventoById(codigo);
	}


	public CategEvento getCategEventoByDescripcion(String descripcion) throws RemoteException {
		return stub.getCategEventoByDescripcion(descripcion);
	}


	public CategEvento[] getAllCategEventos() throws RemoteException {
		return stub.getAllCategEventos();
	}


	public CategEvento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);		
	}


	public void update(CategEvento categEvento) throws RemoteException {
		stub.update(categEvento);
	}
}
