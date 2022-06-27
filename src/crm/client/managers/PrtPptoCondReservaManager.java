package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoCondReserva;
import crm.services.wsdl2.sei.PrtPptoCondReservaManagerSEI;

public class PrtPptoCondReservaManager extends CongressCRMServiceWSDL2 implements PrtPptoCondReservaManagerSEI {
	private static PrtPptoCondReservaManager instance;
	
	private PrtPptoCondReservaManagerSEI stub;	

	public static PrtPptoCondReservaManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoCondReservaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoCondReservaManager() throws WSIFException{
		super("PrtPptoCondReservaManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoCondReserva", Class.forName("crm.libraries.abm.entities.PrtPptoCondReserva"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoCondReservaManagerSEI)service.getStub(PrtPptoCondReservaManagerSEI.class);
	}

	public PrtPptoCondReserva getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoCondReserva[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoCondReserva model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoCondReserva[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

}
