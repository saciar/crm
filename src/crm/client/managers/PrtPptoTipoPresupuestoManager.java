package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.PrtPptoTipoPresupuesto;
import crm.services.wsdl2.sei.PrtPptoTipoPresupuestoManagerSEI;;

public class PrtPptoTipoPresupuestoManager extends CongressCRMServiceWSDL2 implements PrtPptoTipoPresupuestoManagerSEI {
	private static PrtPptoTipoPresupuestoManager instance;
	
	private PrtPptoTipoPresupuestoManagerSEI stub;	

	public static PrtPptoTipoPresupuestoManager instance() {
		try {
			if (instance == null) {
				instance = new PrtPptoTipoPresupuestoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public PrtPptoTipoPresupuestoManager() throws WSIFException{
		super("PrtPptoTipoPresupuestoManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("PrtPptoTipoPresupuesto", Class.forName("crm.libraries.abm.entities.PrtPptoTipoPresupuesto"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (PrtPptoTipoPresupuestoManagerSEI)service.getStub(PrtPptoTipoPresupuestoManagerSEI.class);
	}

	public PrtPptoTipoPresupuesto getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public PrtPptoTipoPresupuesto[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(PrtPptoTipoPresupuesto model) throws RemoteException {
		stub.update(model);
	}

	public PrtPptoTipoPresupuesto[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}

}
