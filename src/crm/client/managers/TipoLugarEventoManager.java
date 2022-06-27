package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoLugarEvento;
import crm.services.sei.TipoLugarEventoManagerSEI;

public class TipoLugarEventoManager extends CongressCRMService implements TipoLugarEventoManagerSEI {
	private TipoLugarEventoManagerSEI stub;
	
	public TipoLugarEventoManager() throws WSIFException{
		super("TipoLugarEventoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoLugarEvento", Class.forName("crm.libraries.abm.entities.TipoLugarEvento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoLugarEventoManagerSEI)service.getStub(TipoLugarEventoManagerSEI.class);
	}


	public TipoLugarEvento getTipoLugarEventoById(String codigo) throws RemoteException {
		return stub.getTipoLugarEventoById(codigo);
	}


	public TipoLugarEvento[] getAllTipoLugarEventos() throws RemoteException {
		return stub.getAllTipoLugarEventos();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoLugarEvento tipoLugarEvento) throws RemoteException {
		stub.update(tipoLugarEvento);
	}


	public TipoLugarEvento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoLugarEventoManager instance;

	public static TipoLugarEventoManager instance() {
		try {
			if (instance == null) {
				instance = new TipoLugarEventoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getTipoLugarEventosReport() throws RemoteException {
		return stub.getTipoLugarEventosReport();
	}


	public TipoLugarEvento getTipoLugarEventoByDescripcion(String descripcion) throws RemoteException {
		return stub.getTipoLugarEventoByDescripcion(descripcion);
	}
}
