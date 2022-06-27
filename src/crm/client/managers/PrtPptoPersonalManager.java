package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoPersonal;
import crm.services.wsdl2.sei.PrtPptoPersonalManagerSEI;

public class PrtPptoPersonalManager extends CongressCRMServiceWSDL2 implements PrtPptoPersonalManagerSEI {
	private static PrtPptoPersonalManager instance;
	
	private PrtPptoPersonalManagerSEI stub;	

	public static PrtPptoPersonalManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoPersonalManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoPersonalManager() throws WSIFException{
		super("PrtPptoPersonalManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoPersonal", Class.forName("crm.libraries.abm.entities.PrtPptoPersonal"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoPersonalManagerSEI)service.getStub(PrtPptoPersonalManagerSEI.class);
	}

	public PrtPptoPersonal getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoPersonal[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoPersonal model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoPersonal[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

}
