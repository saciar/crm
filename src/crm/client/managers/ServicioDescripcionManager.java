package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ServicioDescripcion;
import crm.services.sei.ServicioDescripcionManagerSEI;

public class ServicioDescripcionManager extends CongressCRMService implements ServicioDescripcionManagerSEI {	
	private static ServicioDescripcionManager instance;
	private ServicioDescripcionManagerSEI stub;

	public ServicioDescripcionManager() throws WSIFException {
		super("ServicioDescripcionManagerSEI");
	}

	public static ServicioDescripcionManager instance() {
		try {
			if (instance == null) {
				instance = new ServicioDescripcionManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}	

	protected void registerTypes() throws WSIFException {
		try {
			addType("ServicioDescripcion", Class.forName("crm.libraries.abm.entities.ServicioDescripcion"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		stub = (ServicioDescripcionManagerSEI) service.getStub(ServicioDescripcionManagerSEI.class);
	}

	public ServicioDescripcion[] findByServicio(String codServicio, String codIdioma) throws RemoteException {
		return stub.findByServicio(codServicio, codIdioma);
	}

	public void saveDescripcion(String codServicio, String codIdioma, String descripcion) throws RemoteException {
		stub.saveDescripcion(codServicio, codIdioma, descripcion);
	}

	public void removeByServicio(String codServicio, String codIdioma) throws RemoteException {
		stub.removeByServicio(codServicio, codIdioma);
	}


}
