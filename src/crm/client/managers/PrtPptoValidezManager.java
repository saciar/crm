package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoValidez;
import crm.services.wsdl2.sei.PrtPptoValidezManagerSEI;

public class PrtPptoValidezManager extends CongressCRMServiceWSDL2 implements PrtPptoValidezManagerSEI {
	private static PrtPptoValidezManager instance;
	
	private PrtPptoValidezManagerSEI stub;	

	public static PrtPptoValidezManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoValidezManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoValidezManager() throws WSIFException{
		super("PrtPptoValidezManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoValidez", Class.forName("crm.libraries.abm.entities.PrtPptoValidez"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoValidezManagerSEI)service.getStub(PrtPptoValidezManagerSEI.class);
	}

	public PrtPptoValidez getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoValidez[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoValidez model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoValidez[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
