package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CondicionPago;
import crm.services.sei.CondicionPagoManagerSEI;

public class CondicionPagoManager extends CongressCRMService implements CondicionPagoManagerSEI {
	private CondicionPagoManagerSEI stub;
	
	private CondicionPagoManager() throws WSIFException{
		super("CondicionPagoManagerSEI");
	}
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("CondicionPago", Class.forName("crm.libraries.abm.entities.CondicionPago"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (CondicionPagoManagerSEI)service.getStub(CondicionPagoManagerSEI.class);
	}
	
	private static CondicionPagoManager instance;

	public static CondicionPagoManager instance() {
		try {
			if (instance == null) {
				instance = new CondicionPagoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getCondicionPagosReport() throws RemoteException {
		return stub.getCondicionPagosReport();
	}

	public CondicionPago getCondicionPagoById(String codigo) throws RemoteException {
		return stub.getCondicionPagoById(codigo);
	}

	public CondicionPago getCondicionPagoByDescripcion(String descripcion) throws RemoteException {
		return stub.getCondicionPagoByDescripcion(descripcion);
	}

	public CondicionPago[] getAllCondicionPagos() throws RemoteException {
		return stub.getAllCondicionPagos();
	}

	public CondicionPago[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);		
	}

	public void update(CondicionPago condicionPago) throws RemoteException {	
		stub.update(condicionPago);
	}
	
	
	
}
