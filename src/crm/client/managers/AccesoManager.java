package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Acceso;
import crm.services.sei.AccesoManagerSEI;

public class AccesoManager extends CongressCRMService implements AccesoManagerSEI {
	private AccesoManagerSEI stub;
	
	public AccesoManager() throws WSIFException{
		super("AccesoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Acceso", Class.forName("crm.libraries.abm.entities.Acceso"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (AccesoManagerSEI)service.getStub(AccesoManagerSEI.class);
	}


	public Acceso getAccesoById(String codigo) throws RemoteException {
		return stub.getAccesoById(codigo);
	}
	
	public Acceso getAccesoByDescripcion(String descripcion) throws RemoteException{
		return stub.getAccesoByDescripcion(descripcion);
	}


	public Acceso[] getAllAccesos() throws RemoteException {
		return stub.getAllAccesos();
	}

/*
	public Acceso[] getAllAccesosTranslated(String lang) throws RemoteException {
		return stub.getAllAccesosTranslated(lang);
	}*/
	public Acceso[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Acceso acceso) throws RemoteException {
		stub.update(acceso);
	}
	
	private static AccesoManager instance;

	public static AccesoManager instance() {
		try {
			if (instance == null) {
				instance = new AccesoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
