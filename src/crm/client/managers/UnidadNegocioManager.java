package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.UnidadNegocio;
import crm.services.sei.UnidadNegocioManagerSEI;

public class UnidadNegocioManager extends CongressCRMService implements UnidadNegocioManagerSEI {
	private UnidadNegocioManagerSEI stub;
	
	public UnidadNegocioManager() throws WSIFException{
		super("UnidadNegocioManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("UnidadNegocio", Class.forName("crm.libraries.abm.entities.UnidadNegocio"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UnidadNegocioManagerSEI)service.getStub(UnidadNegocioManagerSEI.class);
	}


	public UnidadNegocio getUnidadNegocioById(String codigo) throws RemoteException {
		return stub.getUnidadNegocioById(codigo);
	}


	public UnidadNegocio[] getAllUnidadNegocios() throws RemoteException {
		return stub.getAllUnidadNegocios();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(UnidadNegocio unidadNegocio) throws RemoteException {
		stub.update(unidadNegocio);
	}


	public UnidadNegocio[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static UnidadNegocioManager instance;

	public static UnidadNegocioManager instance() {
		try {
			if (instance == null) {
				instance = new UnidadNegocioManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public UnidadNegocio getUnidadNegocioByDescripcion(String descripcion) throws RemoteException {
		return stub.getUnidadNegocioByDescripcion(descripcion);
	}
	
	
}
