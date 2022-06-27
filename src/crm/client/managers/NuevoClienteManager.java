package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.NuevoCliente;
import crm.services.sei.NuevoClienteManagerSEI;

public class NuevoClienteManager extends CongressCRMService implements NuevoClienteManagerSEI{
	private NuevoClienteManagerSEI stub;
	
	private NuevoClienteManager() throws WSIFException{
		super("NuevoClienteManagerSEI");
	}

	protected void registerTypes() throws WSIFException {
		try {
			addType("NuevoCliente", Class.forName("crm.libraries.abm.entities.NuevoCliente"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (NuevoClienteManagerSEI)service.getStub(NuevoClienteManagerSEI.class);		
	}

	public NuevoCliente getNuevoClienteById(String codigoCliente) throws RemoteException {
		return stub.getNuevoClienteById(codigoCliente);
	}

	public Object[] getNuevoClienteByClienteCodeReport(String codCliente) throws RemoteException {
		return stub.getNuevoClienteByClienteCodeReport(codCliente);
	}

	public String update(NuevoCliente nuevoCliente) throws RemoteException {
		return stub.update(nuevoCliente);
	}

	public boolean isNuevoContacto(String codCliente) throws RemoteException {
		return stub.isNuevoContacto(codCliente);
	}
	
	private static NuevoClienteManager instance;

	public static NuevoClienteManager instance() {
		try {
			if (instance == null) {
				instance = new NuevoClienteManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

}
