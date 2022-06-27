package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CategVendedor;
import crm.services.sei.CategVendedorManagerSEI;

public class CategVendedorManager extends CongressCRMService implements CategVendedorManagerSEI {
	private CategVendedorManagerSEI stub;
	
	private CategVendedorManager() throws WSIFException{
		super("CategVendedorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("CategVendedor", Class.forName("crm.libraries.abm.entities.CategVendedor"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (CategVendedorManagerSEI)service.getStub(CategVendedorManagerSEI.class);
	}


	public CategVendedor getCategVendedorById(String codigo) throws RemoteException {
		return stub.getCategVendedorById(codigo);
	}


	public CategVendedor[] getAllCategVendedores() throws RemoteException {
		return stub.getAllCategVendedores();
	}

	public CategVendedor[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(CategVendedor categVendedor) throws RemoteException {
		stub.update(categVendedor);
	}
	
	private static CategVendedorManager instance;

	public static CategVendedorManager instance() {
		try {
			if (instance == null) {
				instance = new CategVendedorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public CategVendedor getCategVendedorByDescripcion(String descripcion) throws RemoteException {
		return stub.getCategVendedorByDescripcion(descripcion);
	}
}
