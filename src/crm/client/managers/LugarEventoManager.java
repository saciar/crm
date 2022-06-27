package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.LugarEvento;
import crm.services.sei.LugarEventoManagerSEI;

public class LugarEventoManager extends CongressCRMService implements LugarEventoManagerSEI {
	private LugarEventoManagerSEI stub;
	
	private LugarEventoManager() throws WSIFException{
		super("LugarEventoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("LugarEvento", Class.forName("crm.libraries.abm.entities.LugarEvento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (LugarEventoManagerSEI)service.getStub(LugarEventoManagerSEI.class);
	}


	public LugarEvento getLugarEventoById(String codigo) throws RemoteException {
		return stub.getLugarEventoById(codigo);
	}


	public LugarEvento[] getAllLugarEventos() throws RemoteException {
		return stub.getAllLugarEventos();
	}


	public LugarEvento[] getAllLugarEventosTranslated(String lang) throws RemoteException {
		return stub.getAllLugarEventosTranslated(lang);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public String update(LugarEvento lugarEvento) throws RemoteException {
		return stub.update(lugarEvento);
	}


	public LugarEvento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static LugarEventoManager instance;

	public static LugarEventoManager instance() {
		try {
			if (instance == null) {
				instance = new LugarEventoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getLugarEventosReport() throws RemoteException {
		return stub.getLugarEventosReport();
	}


	public LugarEvento getLugarEventoByNombre(String nombre) throws RemoteException {
		return stub.getLugarEventoByNombre(nombre);
	}
	
	public String getCodigoLugarComisionById(String codLugar) throws RemoteException{
		return stub.getCodigoLugarComisionById(codLugar);
	}
	
}
