package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.TipoContacto;
import crm.services.sei.TipoContactoManagerSEI;

public class TipoContactoManager extends CongressCRMService implements TipoContactoManagerSEI {
	private TipoContactoManagerSEI stub;
	
	public TipoContactoManager() throws WSIFException{
		super("TipoContactoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TipoContacto", Class.forName("crm.libraries.abm.entities.TipoContacto"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TipoContactoManagerSEI)service.getStub(TipoContactoManagerSEI.class);
	}


	public TipoContacto getTipoContactoById(String codigo) throws RemoteException {
		return stub.getTipoContactoById(codigo);
	}


	public TipoContacto[] getAllTipoContactos() throws RemoteException {
		return stub.getAllTipoContactos();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(TipoContacto tipoContacto) throws RemoteException {
		stub.update(tipoContacto);
	}


	public TipoContacto[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TipoContactoManager instance;

	public static TipoContactoManager instance() {
		try {
			if (instance == null) {
				instance = new TipoContactoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
