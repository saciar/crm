package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Perfil;
import crm.services.sei.PerfilManagerSEI;

public class PerfilManager extends CongressCRMService implements PerfilManagerSEI {
	private PerfilManagerSEI stub;
	
	public PerfilManager() throws WSIFException{
		super("PerfilManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Perfil", Class.forName("crm.libraries.abm.entities.Perfil"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (PerfilManagerSEI)service.getStub(PerfilManagerSEI.class);
	}


	public Perfil getPerfilById(String codigo) throws RemoteException {
		return stub.getPerfilById(codigo);
	}


	public Perfil[] getAllPerfiles() throws RemoteException {
		return stub.getAllPerfiles();
	}

	public Perfil[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Perfil perfil) throws RemoteException {
		stub.update(perfil);
	}
	
	private static PerfilManager instance;

	public static PerfilManager instance() {
		try {
			if (instance == null) {
				instance = new PerfilManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Perfil getPerfilByDescripcion(String descripcion) throws RemoteException {
		return stub.getPerfilByDescripcion(descripcion);
	}
}
