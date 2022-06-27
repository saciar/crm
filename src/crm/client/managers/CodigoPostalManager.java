package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CodigoPostal;
import crm.services.sei.CodigoPostalManagerSEI;

public class CodigoPostalManager extends CongressCRMService implements CodigoPostalManagerSEI {
	private CodigoPostalManagerSEI stub;
	
	public CodigoPostalManager() throws WSIFException{
		super("CodigoPostalManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("CodigoPostal", Class.forName("crm.libraries.abm.entities.CodigoPostal"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (CodigoPostalManagerSEI)service.getStub(CodigoPostalManagerSEI.class);
	}


	public CodigoPostal getCodigoPostalById(String codigo) throws RemoteException {
		return stub.getCodigoPostalById(codigo);
	}


	public CodigoPostal[] getAllCodigoPostales() throws RemoteException {
		return stub.getAllCodigoPostales();
	}

	public CodigoPostal[] findByLocalidadId(String value) throws RemoteException{
		return stub.findByLocalidadId(value);
	}
	
	public CodigoPostal[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(CodigoPostal codigoPostal) throws RemoteException {
		stub.update(codigoPostal);
	}
	
	public Object[] findNamesByLocalidadId(String value) throws RemoteException{
		return stub.findNamesByLocalidadId(value);
	}
	
	private static CodigoPostalManager instance;

	public static CodigoPostalManager instance() {
		try {
			if (instance == null) {
				instance = new CodigoPostalManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public CodigoPostal getCodigoPostalByCP(String cp) throws RemoteException {
		return stub.getCodigoPostalByCP(cp);
	}
}
