package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.UnidadComercial;
import crm.services.sei.UnidadComercialManagerSEI;

public class UnidadComercialManager extends CongressCRMService implements UnidadComercialManagerSEI {
	private static UnidadComercialManager instance;
	
	private UnidadComercialManagerSEI stub;
	
	public UnidadComercialManager() throws WSIFException{
		super("UnidadComercialManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("UnidadComercial", Class.forName("crm.libraries.abm.entities.UnidadComercial"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UnidadComercialManagerSEI)service.getStub(UnidadComercialManagerSEI.class);
	}

	public static UnidadComercialManager instance() {
		try {
			if (instance == null) {
				instance = new UnidadComercialManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public UnidadComercial getUCDataByCodigoUsuario(String codigoUsuario) {
		try {
			return stub.getUCDataByCodigoUsuario(codigoUsuario);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public UnidadComercial getUCDataByCodigoUnidad(String codigoUnidad) {
		try {
			return stub.getUCDataByCodigoUnidad(codigoUnidad);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] getPptosOfAllUnidadComercial() throws RemoteException{
		return stub.getPptosOfAllUnidadComercial();
	}
	
	public UnidadComercial getUnidadComercialById(String codigo) throws RemoteException {
		return stub.getUnidadComercialById(codigo);
	}

	public UnidadComercial[] getUnidadComercialesBySupervisorOrDescripcion(String codigoSupervisor,String descripcion) throws RemoteException{
		return stub.getUnidadComercialesBySupervisorOrDescripcion(codigoSupervisor,descripcion);
	}
	
	public UnidadComercial[] getAllUnidadComerciales() throws RemoteException {
		return stub.getAllUnidadComerciales();
	}

	public UnidadComercial[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public String update(UnidadComercial unidadComercial) throws RemoteException {
		return stub.update(unidadComercial);
	}
	
	public UnidadComercial getUCDataByCodigoVendedor(String codigoVendedor) throws RemoteException{
		return stub.getUCDataByCodigoVendedor(codigoVendedor);
	}
}
