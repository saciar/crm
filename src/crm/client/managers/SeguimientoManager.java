package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Seguimiento;
import crm.services.sei.SeguimientoManagerSEI;

public class SeguimientoManager extends CongressCRMService implements SeguimientoManagerSEI {
	private SeguimientoManagerSEI stub;
	
	public SeguimientoManager() throws WSIFException{
		super("SeguimientoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Seguimiento", Class.forName("crm.libraries.abm.entities.Seguimiento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (SeguimientoManagerSEI)service.getStub(SeguimientoManagerSEI.class);
	}


	public Seguimiento getSeguimientoById(String codigo) throws RemoteException {
		return stub.getSeguimientoById(codigo);
	}


	public Seguimiento[] getAllSeguimientos() throws RemoteException {
		return stub.getAllSeguimientos();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Seguimiento seguimiento) throws RemoteException {
		stub.update(seguimiento);
	}


	public Seguimiento[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static SeguimientoManager instance;

	public static SeguimientoManager instance() {
		try {
			if (instance == null) {
				instance = new SeguimientoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Seguimiento getSeguimientoByDescripcion(String descripcion) throws RemoteException {
		return stub.getSeguimientoByDescripcion(descripcion);
	}
	
	public Object[] getAccionesReport() throws RemoteException {
		return stub.getAccionesReport();
	}
}
