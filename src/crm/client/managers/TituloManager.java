package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Titulo;
import crm.services.sei.TituloManagerSEI;

public class TituloManager extends CongressCRMService implements TituloManagerSEI {
	private TituloManagerSEI stub;
	
	public TituloManager() throws WSIFException{
		super("TituloManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Titulo", Class.forName("crm.libraries.abm.entities.Titulo"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TituloManagerSEI)service.getStub(TituloManagerSEI.class);
	}


	public Titulo getTituloById(String codigo) throws RemoteException {
		return stub.getTituloById(codigo);
	}


	public Titulo[] getAllTitulos() throws RemoteException {
		return stub.getAllTitulos();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Titulo titulo) throws RemoteException {
		stub.update(titulo);
	}


	public Titulo[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static TituloManager instance;

	public static TituloManager instance() {
		try {
			if (instance == null) {
				instance = new TituloManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Titulo getTituloByDescripcion(String descripcion) throws RemoteException {
		return stub.getTituloByDescripcion(descripcion);
	}
}
