package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Pais;
import crm.services.sei.PaisManagerSEI;

public class PaisManager extends CongressCRMService implements PaisManagerSEI {
	private PaisManagerSEI stub;
	
	public PaisManager() throws WSIFException{
		super("PaisManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Pais", Class.forName("crm.libraries.abm.entities.Pais"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (PaisManagerSEI)service.getStub(PaisManagerSEI.class);
	}


	public Pais getPaisById(String codigo) throws RemoteException {
		return stub.getPaisById(codigo);
	}


	public Pais[] getAllPaises() throws RemoteException {
		return stub.getAllPaises();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Pais pais) throws RemoteException {
		stub.update(pais);
	}


	public Pais[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static PaisManager instance;

	public static PaisManager instance() {
		try {
			if (instance == null) {
				instance = new PaisManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public String getNombrePaisById(String codigo) throws RemoteException {
		return stub.getNombrePaisById(codigo);
	}


	public Pais getPaisByDescripcion(String descripcion) throws RemoteException {
		return stub.getPaisByDescripcion(descripcion);
	}
	
	
}
