package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoCondPago;
import crm.services.wsdl2.sei.PrtPptoCondPagoManagerSEI;

public class PrtPptoCondPagoManager extends CongressCRMServiceWSDL2 implements PrtPptoCondPagoManagerSEI {
	private static PrtPptoCondPagoManager instance;
	
	private PrtPptoCondPagoManagerSEI stub;	

	public static PrtPptoCondPagoManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoCondPagoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoCondPagoManager() throws WSIFException{
		super("PrtPptoCondPagoManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoCondPago", Class.forName("crm.libraries.abm.entities.PrtPptoCondPago"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoCondPagoManagerSEI)service.getStub(PrtPptoCondPagoManagerSEI.class);
	}

	public PrtPptoCondPago getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoCondPago[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoCondPago model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoCondPago[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

}
