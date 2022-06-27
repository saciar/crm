package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.FechasProceso;
import crm.services.sei.FechasProcesoManagerSEI;

public class FechasProcesoManager extends CongressCRMService implements FechasProcesoManagerSEI{
	
	private FechasProcesoManagerSEI stub;
	
	public FechasProcesoManager() throws WSIFException{
		super("FechasProcesoManagerSEI");
	}

	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("FechasProceso", Class.forName("crm.libraries.abm.entities.FechasProceso"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (FechasProcesoManagerSEI)service.getStub(FechasProcesoManagerSEI.class);
	}

	public void update(FechasProceso fecha) throws RemoteException {
		stub.update(fecha);
	}

	public String getFechaProcesoById(String cod) throws RemoteException {
		return stub.getFechaProcesoById(cod);
	}
	
	public String getMaxCodigo() throws RemoteException{
		return stub.getMaxCodigo();
	}
	
	private static FechasProcesoManager instance;

	public static FechasProcesoManager instance() {
		try {
			if (instance == null) {
				instance = new FechasProcesoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
