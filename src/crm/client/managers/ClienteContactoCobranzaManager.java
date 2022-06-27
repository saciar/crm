package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ClienteContactoCobranza;
import crm.services.wsdl2.sei.ClienteContactoCobranzaManagerSEI;


public class ClienteContactoCobranzaManager extends CongressCRMServiceWSDL2 implements ClienteContactoCobranzaManagerSEI  {
	private ClienteContactoCobranzaManagerSEI stub;
	
	private ClienteContactoCobranzaManager() throws WSIFException{
		super("ClienteContactoCobranzaManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		try {
			addType("ClienteContactoCobranza", Class.forName("crm.libraries.abm.entities.ClienteContactoCobranza"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (ClienteContactoCobranzaManagerSEI)service.getStub(ClienteContactoCobranzaManagerSEI.class);
	}


	public ClienteContactoCobranza getClienteContactoById(String codigo) throws RemoteException {
		return stub.getClienteContactoById(codigo);
	}


	public ClienteContactoCobranza[] getAllClienteContactos() throws RemoteException {
		return stub.getAllClienteContactos();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public String update(ClienteContactoCobranza clienteC) throws RemoteException {
		return stub.update(clienteC);
	}


	public ClienteContactoCobranza[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	public ClienteContactoCobranza[] findByClientAndField(String client, String field, String value) throws RemoteException {
		return stub.findByClientAndField(client,field,value);
	}
	
	private static ClienteContactoCobranzaManager instance;

	public static ClienteContactoCobranzaManager instance() {
		try {
			if (instance == null) {
				instance = new ClienteContactoCobranzaManager();
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
