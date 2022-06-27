package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.EventoUniforme;
import crm.services.sei.EventoUniformeManagerSEI;

public class EventoUniformeManager extends CongressCRMService implements EventoUniformeManagerSEI {
	private EventoUniformeManagerSEI stub;
	
	public EventoUniformeManager() throws WSIFException{
		super("EventoUniformeManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("EventoUniforme", Class.forName("crm.libraries.abm.entities.EventoUniforme"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (EventoUniformeManagerSEI)service.getStub(EventoUniformeManagerSEI.class);
	}


	public EventoUniforme getEventoUniformeById(String codigo) throws RemoteException {
		return stub.getEventoUniformeById(codigo);
	}


	public EventoUniforme[] getAllEventoUniformes() throws RemoteException {
		return stub.getAllEventoUniformes();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(EventoUniforme eventoUniforme) throws RemoteException {
		stub.update(eventoUniforme);
	}


	public EventoUniforme[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static EventoUniformeManager instance;

	public static EventoUniformeManager instance() {
		try {
			if (instance == null) {
				instance = new EventoUniformeManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public EventoUniforme getEventoUniformeByDescripcion(String descripcion) throws RemoteException {
		return stub.getEventoUniformeByDescripcion(descripcion);
	}
}
