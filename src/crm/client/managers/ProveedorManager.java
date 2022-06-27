package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Proveedor;
import crm.services.sei.ProveedorManagerSEI;

public class ProveedorManager extends CongressCRMService implements ProveedorManagerSEI {
	private ProveedorManagerSEI stub;
	
	public ProveedorManager() throws WSIFException{
		super("ProveedorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Proveedor", Class.forName("crm.libraries.abm.entities.Proveedor"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ProveedorManagerSEI)service.getStub(ProveedorManagerSEI.class);
	}


	public Proveedor getProveedorById(String codigo) throws RemoteException {
		return stub.getProveedorById(codigo);
	}


	public Proveedor[] getAllProveedores() throws RemoteException {
		return stub.getAllProveedores();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Proveedor proveedor) throws RemoteException {
		stub.update(proveedor);
	}


	public Proveedor[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static ProveedorManager instance;

	public static ProveedorManager instance() {
		try {
			if (instance == null) {
				instance = new ProveedorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Proveedor getProveedorByNombre(String nombre) throws RemoteException {
		return stub.getProveedorByNombre(nombre);
	}


	public String getDescrpcion(String codigo) throws RemoteException {
		return stub.getDescrpcion(codigo);
	}
}
