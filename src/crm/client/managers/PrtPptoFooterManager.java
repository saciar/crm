package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoFooter;
import crm.services.wsdl2.sei.PrtPptoFooterManagerSEI;

public class PrtPptoFooterManager extends CongressCRMServiceWSDL2 implements PrtPptoFooterManagerSEI {
	private static PrtPptoFooterManager instance;
	
	private PrtPptoFooterManagerSEI stub;	

	public static PrtPptoFooterManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoFooterManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoFooterManager() throws WSIFException{
		super("PrtPptoFooterManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoFooter", Class.forName("crm.libraries.abm.entities.PrtPptoFooter"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoFooterManagerSEI)service.getStub(PrtPptoFooterManagerSEI.class);
	}

	public PrtPptoFooter getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoFooter[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoFooter model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoFooter[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
