package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Sucursal;
import crm.libraries.abm.entities.Vendedor;
import crm.services.sei.SucursalManagerSEI;

public class SucursalManager extends CongressCRMService implements SucursalManagerSEI {
	private static SucursalManager instance;
	
	private SucursalManagerSEI stub;
	
	public SucursalManager() throws WSIFException{
		super("SucursalManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Sucursal", Class.forName("crm.libraries.abm.entities.Sucursal"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (SucursalManagerSEI)service.getStub(SucursalManagerSEI.class);
	}

	public static SucursalManager instance() {
		try {
			if (instance == null) {
				instance = new SucursalManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public String getSucursalNameByCodigo(String codigo) {
		try {
			return stub.getSucursalNameByCodigo(codigo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	

	public Sucursal getSucursalById(String codigo) throws RemoteException {
		return stub.getSucursalById(codigo);
	}


	public Sucursal[] getAllSucursales() throws RemoteException {
		return stub.getAllSucursales();
	}

	public Sucursal[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Sucursal sucursal) throws RemoteException {
		stub.update(sucursal);
	}


	public Sucursal getSucursalByDescripcion(String descripcion) throws RemoteException {
		return stub.getSucursalByDescripcion(descripcion);
	}

}
