package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ClienteContacto;
import crm.services.sei.ClienteContactoManagerSEI;

public class ClienteContactoManager extends CongressCRMService implements ClienteContactoManagerSEI  {
	private ClienteContactoManagerSEI stub;
	
	private ClienteContactoManager() throws WSIFException{
		super("ClienteContactoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		try {
			addType("ClienteContacto", Class.forName("crm.libraries.abm.entities.ClienteContacto"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (ClienteContactoManagerSEI)service.getStub(ClienteContactoManagerSEI.class);
	}


	public ClienteContacto getClienteContactoById(String codigo) throws RemoteException {
		return stub.getClienteContactoById(codigo);
	}


	public ClienteContacto[] getAllClienteContactos() throws RemoteException {
		return stub.getAllClienteContactos();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public String update(ClienteContacto clienteC) throws RemoteException {
		return stub.update(clienteC);
	}


	public ClienteContacto[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	public ClienteContacto[] findByClientAndField(String client, String field, String value) throws RemoteException {
		return stub.findByClientAndField(client,field,value);
	}
	
	private static ClienteContactoManager instance;

	public static ClienteContactoManager instance() {
		try {
			if (instance == null) {
				instance = new ClienteContactoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	/**
	 * Trae la lista de clientes del cache. 
	 * Este metodo no se comunica con el webservice, quien lo hace es la clase
	 * ClientSynchronizer
	 */
	public Object[] getClienteContactosReport() throws RemoteException {
		return stub.getClienteContactosReport();
	}

	public int getCantidadClienteContactos() throws RemoteException {
		return stub.getCantidadClienteContactos();
	}

	public Object[] getClienteContactosReportLimited(int firstResult, int maxResults) throws RemoteException {
		return stub.getClienteContactosReportLimited(firstResult, maxResults);
	}
	
	public Object[] getClienteContactoByClienteCodeReport(String codCliente) throws RemoteException{
		return stub.getClienteContactoByClienteCodeReport(codCliente);
	}




}
