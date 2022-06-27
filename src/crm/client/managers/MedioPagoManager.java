package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.MedioPago;
import crm.services.sei.MedioPagoManagerSEI;

public class MedioPagoManager extends CongressCRMService implements MedioPagoManagerSEI {
	private MedioPagoManagerSEI stub;
	
	private MedioPagoManager() throws WSIFException{
		super("MedioPagoManagerSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("MedioPago", Class.forName("crm.libraries.abm.entities.MedioPago"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (MedioPagoManagerSEI)service.getStub(MedioPagoManagerSEI.class);
	}
	
	private static MedioPagoManager instance;

	public static MedioPagoManager instance() {
		try {
			if (instance == null) {
				instance = new MedioPagoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getMedioPagosReport() throws RemoteException {
		return stub.getMedioPagosReport();
	}

	public MedioPago getMedioPagoById(String codigo) throws RemoteException {
		return stub.getMedioPagoById(codigo);
	}

	public MedioPago getMedioPagoByDescripcion(String descripcion) throws RemoteException {
		return stub.getMedioPagoByDescripcion(descripcion);
	}

	public MedioPago[] getAllMedioPagos() throws RemoteException {
		return stub.getAllMedioPagos();
	}

	public MedioPago[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(MedioPago medioPago) throws RemoteException {
		stub.update(medioPago);
	}
	
	
}
