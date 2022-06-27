package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ModoIngEquipos;
import crm.services.wsdl2.sei.ModoIngEquiposManagerSEI;

public class ModoIngEquiposManager extends CongressCRMServiceWSDL2 implements ModoIngEquiposManagerSEI {
	private static ModoIngEquiposManager instance;
	
	private ModoIngEquiposManagerSEI stub;	

	public static ModoIngEquiposManager instance() {
		try {
			if (instance == null) {
				instance = new ModoIngEquiposManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public ModoIngEquiposManager() throws WSIFException{
		super("ModoIngEquiposManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("ModoIngEquipos", Class.forName("crm.libraries.abm.entities.ModoIngEquipos"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (ModoIngEquiposManagerSEI)service.getStub(ModoIngEquiposManagerSEI.class);
	}

	public ModoIngEquipos getById(String codigo) throws RemoteException {
		return stub.getById(codigo);
	}

	public ModoIngEquipos[] getAll() throws RemoteException {
		return stub.getAll();
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}

	public void update(ModoIngEquipos model) throws RemoteException {
		stub.update(model);
	}

	public ModoIngEquipos[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	

}
