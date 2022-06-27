package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.UnidadVendedor;
import crm.services.sei.UnidadVendedorManagerSEI;

public class UnidadVendedorManager extends CongressCRMService implements UnidadVendedorManagerSEI {
	private static UnidadVendedorManager instance;
	
	private UnidadVendedorManagerSEI stub;
	
	public UnidadVendedorManager() throws WSIFException{
		super("UnidadVendedorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("UnidadVendedor", Class.forName("crm.libraries.abm.entities.UnidadVendedor"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UnidadVendedorManagerSEI)service.getStub(UnidadVendedorManagerSEI.class);
	}

	public UnidadVendedor[] getUnidadByCodigoUnidad(String codigoUnidad) throws RemoteException {
		return stub.getUnidadByCodigoUnidad(codigoUnidad);
	}
	
	public void removeByCodigoUnidad(String codigoUnidad)throws RemoteException  {
		stub.removeByCodigoUnidad(codigoUnidad);
	}
	
	public void update(UnidadVendedor unidadVendedor) throws RemoteException {
		stub.update(unidadVendedor);
	}
	
	public static UnidadVendedorManager instance() {
		try {
			if (instance == null) {
				instance = new UnidadVendedorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public String getCodigoUnidad(String codigoVendedor) {
		try {
			return stub.getCodigoUnidad(codigoVendedor);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}





}
