package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ComisionSupervisor;
import crm.services.sei.ComisionSupervisorManagerSEI;

public class ComisionSupervisorManager extends CongressCRMService implements ComisionSupervisorManagerSEI {
	private ComisionSupervisorManagerSEI stub;
	
	public ComisionSupervisorManager() throws WSIFException{
		super("ComisionSupervisorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		try {
			addType("ComisionSupervisor", Class.forName("crm.libraries.abm.entities.ComisionSupervisor"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (ComisionSupervisorManagerSEI)service.getStub(ComisionSupervisorManagerSEI.class);
	}


	
	private static ComisionSupervisorManager instance;

	public static ComisionSupervisorManager instance() {
		try {
			if (instance == null) {
				instance = new ComisionSupervisorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public ComisionSupervisor[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field, value);
	}


	public ComisionSupervisor[] getAllComisionesSupervisores() throws RemoteException {
		return stub.getAllComisionesSupervisores();
	}


	public ComisionSupervisor getComisionSupervisorById(String codigo) throws RemoteException {
		return stub.getComisionSupervisorById(codigo);
	}


	public ComisionSupervisor getComisionSupervisorByVendedor(String vendedor) throws RemoteException {
		return stub.getComisionSupervisorByVendedor(vendedor);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void removeByVendedor(String codigoVendedor) throws RemoteException {
		stub.removeByVendedor(codigoVendedor);
	}


	public void update(ComisionSupervisor comisionSupervisor) throws RemoteException {
		stub.update(comisionSupervisor);
	}

	
}
