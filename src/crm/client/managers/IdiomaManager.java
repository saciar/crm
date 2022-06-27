package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Idioma;
import crm.services.sei.IdiomaManagerSEI;

public class IdiomaManager extends CongressCRMService implements IdiomaManagerSEI {
	private IdiomaManagerSEI stub;
	
	public IdiomaManager() throws WSIFException{
		super("IdiomaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Idioma", Class.forName("crm.libraries.abm.entities.Idioma"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (IdiomaManagerSEI)service.getStub(IdiomaManagerSEI.class);
	}


	public Idioma getIdiomaById(String codigo) throws RemoteException {
		return stub.getIdiomaById(codigo);
	}


	public Idioma[] getAllIdiomas() throws RemoteException {
		return stub.getAllIdiomas();
	}


	public Idioma[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Idioma idioma) throws RemoteException {
		stub.update(idioma);
	}
	
	private static IdiomaManager instance;

	public static IdiomaManager instance() {
		try {
			if (instance == null) {
				instance = new IdiomaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Idioma getIdiomaByDescripcion(String descripcion) throws RemoteException {
		return stub.getIdiomaByDescripcion(descripcion);
	}
}
