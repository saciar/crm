package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoInstalacion;
import crm.services.wsdl2.sei.PrtPptoInstalacionManagerSEI;

public class PrtPptoInstalacionManager extends CongressCRMServiceWSDL2 implements PrtPptoInstalacionManagerSEI {
	private static PrtPptoInstalacionManager instance;
	
	private PrtPptoInstalacionManagerSEI stub;	

	public static PrtPptoInstalacionManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoInstalacionManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoInstalacionManager() throws WSIFException{
		super("PrtPptoInstalacionManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoInstalacion", Class.forName("crm.libraries.abm.entities.PrtPptoInstalacion"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoInstalacionManagerSEI)service.getStub(PrtPptoInstalacionManagerSEI.class);
	}

	public PrtPptoInstalacion getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoInstalacion[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoInstalacion model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoInstalacion[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
