package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoHeader;
import crm.services.wsdl2.sei.PrtPptoHeaderManagerSEI;

public class PrtPptoHeaderManager extends CongressCRMServiceWSDL2 implements PrtPptoHeaderManagerSEI {
	private static PrtPptoHeaderManager instance;
	
	private PrtPptoHeaderManagerSEI stub;	

	public static PrtPptoHeaderManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoHeaderManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoHeaderManager() throws WSIFException{
		super("PrtPptoHeaderManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoHeader", Class.forName("crm.libraries.abm.entities.PrtPptoHeader"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoHeaderManagerSEI)service.getStub(PrtPptoHeaderManagerSEI.class);
	}

	public PrtPptoHeader getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoHeader[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoHeader model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoHeader[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
