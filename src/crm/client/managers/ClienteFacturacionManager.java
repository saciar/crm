package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ClienteFacturacion;
import crm.services.sei.ClienteFacturacionManagerSEI;

public class ClienteFacturacionManager extends CongressCRMService implements ClienteFacturacionManagerSEI {
	private ClienteFacturacionManagerSEI stub;
	
	public ClienteFacturacionManager() throws WSIFException{
		super("ClienteFacturacionManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ClienteFacturacion", Class.forName("crm.libraries.abm.entities.ClienteFacturacion"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ClienteFacturacionManagerSEI)service.getStub(ClienteFacturacionManagerSEI.class);
	}


	public ClienteFacturacion getClienteFacturacionById(String codigo) throws RemoteException {
		return stub.getClienteFacturacionById(codigo);
	}


	public ClienteFacturacion[] getAllClienteFacturaciones() throws RemoteException {
		return stub.getAllClienteFacturaciones();
	}

	public ClienteFacturacion[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(ClienteFacturacion clienteFacturacion) throws RemoteException {
		stub.update(clienteFacturacion);
	}
	
	private static ClienteFacturacionManager instance;

	public static ClienteFacturacionManager instance() {
		try {
			if (instance == null) {
				instance = new ClienteFacturacionManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
