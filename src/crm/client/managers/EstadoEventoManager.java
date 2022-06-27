package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.EstadoEvento;
import crm.services.sei.EstadoEventoManagerSEI;

public class EstadoEventoManager extends CongressCRMService implements EstadoEventoManagerSEI {
	private EstadoEventoManagerSEI stub;
	
	public EstadoEventoManager() throws WSIFException{
		super("EstadoEventoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("EstadoEvento", Class.forName("crm.libraries.abm.entities.EstadoEvento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (EstadoEventoManagerSEI)service.getStub(EstadoEventoManagerSEI.class);
	}


	public EstadoEvento getEstadoEventoById(String codigo) throws RemoteException {
		return stub.getEstadoEventoById(codigo);
	}


	public EstadoEvento[] getAllEstadoEventos() throws RemoteException {
		return stub.getAllEstadoEventos();
	}


	public EstadoEvento[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(EstadoEvento estadoEvento) throws RemoteException {
		stub.update(estadoEvento);
	}
	
	private static EstadoEventoManager instance;

	public static EstadoEventoManager instance() {
		try {
			if (instance == null) {
				instance = new EstadoEventoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public EstadoEvento getEstadoEventoByDescripcion(String descripcion) throws RemoteException {
		return stub.getEstadoEventoByDescripcion(descripcion);
	}
}
