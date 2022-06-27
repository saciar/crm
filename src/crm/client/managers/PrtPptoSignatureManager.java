package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoSignature;
import crm.services.wsdl2.sei.PrtPptoSignatureManagerSEI;

public class PrtPptoSignatureManager extends CongressCRMServiceWSDL2 implements PrtPptoSignatureManagerSEI {
	private static PrtPptoSignatureManager instance;
	
	private PrtPptoSignatureManagerSEI stub;	

	public static PrtPptoSignatureManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoSignatureManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoSignatureManager() throws WSIFException{
		super("PrtPptoSignatureManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoSignature", Class.forName("crm.libraries.abm.entities.PrtPptoSignature"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoSignatureManagerSEI)service.getStub(PrtPptoSignatureManagerSEI.class);
	}

	public PrtPptoSignature getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoSignature[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoSignature model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoSignature[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
