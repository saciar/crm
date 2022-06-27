package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoPeriodo;
import crm.services.wsdl2.sei.PrtPptoPeriodoManagerSEI;

public class PrtPptoPeriodoManager extends CongressCRMServiceWSDL2 implements PrtPptoPeriodoManagerSEI {
	private static PrtPptoPeriodoManager instance;
	
	private PrtPptoPeriodoManagerSEI stub;	

	public static PrtPptoPeriodoManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoPeriodoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoPeriodoManager() throws WSIFException{
		super("PrtPptoPeriodoManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoPeriodo", Class.forName("crm.libraries.abm.entities.PrtPptoPeriodo"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoPeriodoManagerSEI)service.getStub(PrtPptoPeriodoManagerSEI.class);
	}

	public PrtPptoPeriodo getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoPeriodo[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoPeriodo model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoPeriodo[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

}
