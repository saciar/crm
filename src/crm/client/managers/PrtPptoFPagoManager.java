package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoFPago;
import crm.services.wsdl2.sei.PrtPptoFPagoManagerSEI;

public class PrtPptoFPagoManager extends CongressCRMServiceWSDL2 implements PrtPptoFPagoManagerSEI {
	private static PrtPptoFPagoManager instance;
	
	private PrtPptoFPagoManagerSEI stub;	

	public static PrtPptoFPagoManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoFPagoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoFPagoManager() throws WSIFException{
		super("PrtPptoFPagoManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoFPago", Class.forName("crm.libraries.abm.entities.PrtPptoFPago"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoFPagoManagerSEI)service.getStub(PrtPptoFPagoManagerSEI.class);
	}

	public PrtPptoFPago getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoFPago[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoFPago model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoFPago[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
