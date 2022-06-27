package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.LugarEventoContacto;
import crm.services.sei.LugarEventoContactoManagerSEI;

public class LugarEventoContactoManager extends CongressCRMService implements LugarEventoContactoManagerSEI {
	private static LugarEventoContactoManager instance;
	private LugarEventoContactoManagerSEI stub;
	
	private LugarEventoContactoManager() throws WSIFException{
		super("LugarEventoContactoManagerSEI");
	}
	
	public static LugarEventoContactoManager instance() {
		try {
			if (instance == null) {
				instance = new LugarEventoContactoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("LugarEventoContacto", Class.forName("crm.libraries.abm.entities.LugarEventoContacto"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		stub = (LugarEventoContactoManagerSEI)service.getStub(LugarEventoContactoManagerSEI.class);
	}

	public LugarEventoContacto getLugarEventoContactoById(String codigo) throws RemoteException {
		return stub.getLugarEventoContactoById(codigo);
	}

	public LugarEventoContacto[] getAllLugarEventoContactos() throws RemoteException {
		return stub.getAllLugarEventoContactos();
	}

	public LugarEventoContacto[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field, value);
	}

	public LugarEventoContacto[] findByLugarAndField(String codLugar, String field, String value) throws RemoteException {
		return stub.findByLugarAndField(codLugar, field, value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public String update(LugarEventoContacto lugarEventoContacto) throws RemoteException {
		return stub.update(lugarEventoContacto);
	}
	
	public Object[] getLugarContactoByClienteCodeReport(String codLugar) throws RemoteException{
		return stub.getLugarContactoByClienteCodeReport(codLugar);
	}
	
}
