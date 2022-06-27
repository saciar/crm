package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.VariacionFecha;
import crm.services.wsdl2.sei.VariacionFechaManagerSEI;

public class VariacionFechaManager extends CongressCRMServiceWSDL2 implements VariacionFechaManagerSEI{

	private static VariacionFechaManager instance;
	
	private VariacionFechaManagerSEI stub;	

	public static VariacionFechaManager instance() {
		try {
			if (instance == null) {
				instance = new VariacionFechaManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public VariacionFechaManager() throws WSIFException{
		super("VariacionFechaManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("VariacionFecha", Class.forName("crm.libraries.abm.entities.VariacionFecha"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (VariacionFechaManagerSEI)service.getStub(VariacionFechaManagerSEI.class);
	}


	public int getVariacionFecha(String fecha) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getVariacionFecha(fecha);
	}
	
	public VariacionFecha[] getVariacionesFecha(String fecha) throws RemoteException {
		return stub.getVariacionesFecha(fecha);
	}

}
