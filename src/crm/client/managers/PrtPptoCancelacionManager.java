package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoCancelacion;
import crm.services.wsdl2.sei.PrtPptoCancelacionManagerSEI;

public class PrtPptoCancelacionManager extends CongressCRMServiceWSDL2 implements PrtPptoCancelacionManagerSEI {
	private static PrtPptoCancelacionManager instance;
	
	private PrtPptoCancelacionManagerSEI stub;	

	public static PrtPptoCancelacionManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoCancelacionManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoCancelacionManager() throws WSIFException{
		super("PrtPptoCancelacionManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoCancelacion", Class.forName("crm.libraries.abm.entities.PrtPptoCancelacion"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoCancelacionManagerSEI)service.getStub(PrtPptoCancelacionManagerSEI.class);
	}

	public PrtPptoCancelacion getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoCancelacion[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoCancelacion model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoCancelacion[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
