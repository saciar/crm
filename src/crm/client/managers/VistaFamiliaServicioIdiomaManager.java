package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.VistaFamiliaServicioIdioma;
import crm.services.sei.VistaFamiliaServicioIdiomaManagerSEI;

public class VistaFamiliaServicioIdiomaManager extends CongressCRMService implements VistaFamiliaServicioIdiomaManagerSEI{
private VistaFamiliaServicioIdiomaManagerSEI stub;
	
	public VistaFamiliaServicioIdiomaManager() throws WSIFException{
		super("VistaFamiliaServicioIdiomaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("VistaFamiliaServicioIdioma", Class.forName("crm.libraries.abm.entities.VistaFamiliaServicioIdioma"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (VistaFamiliaServicioIdiomaManagerSEI)service.getStub(VistaFamiliaServicioIdiomaManagerSEI.class);
	}


	public VistaFamiliaServicioIdioma getVistaFamiliaServicioIdiomaById(String codigo) throws RemoteException {
		return stub.getVistaFamiliaServicioIdiomaById(codigo);
	}


	public VistaFamiliaServicioIdioma[] getAllVistaFamiliaServicioIdioma() throws RemoteException {
		return stub.getAllVistaFamiliaServicioIdioma();
	}

	public VistaFamiliaServicioIdioma[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static VistaFamiliaServicioIdiomaManager instance;

	public static VistaFamiliaServicioIdiomaManager instance() {
		try {
			if (instance == null) {
				instance = new VistaFamiliaServicioIdiomaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public Object[] getDescripcionByFamiliaAndServicio
											(String codServ, String codFam) throws RemoteException {
		return stub.getDescripcionByFamiliaAndServicio(codServ, codFam);
	}
	
	public Object[] getDescripcionByServicio(String codServ) throws RemoteException {
		return stub.getDescripcionByServicio(codServ);
	}
}
