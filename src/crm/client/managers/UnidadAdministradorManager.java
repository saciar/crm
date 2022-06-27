package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.UnidadAdministrador;
import crm.services.wsdl2.sei.UnidadAdministradorManagerSEI;

public class UnidadAdministradorManager extends CongressCRMServiceWSDL2 implements UnidadAdministradorManagerSEI {
	private static UnidadAdministradorManager instance;
	
	private UnidadAdministradorManagerSEI stub;
	
	public UnidadAdministradorManager() throws WSIFException{
		super("UnidadAdministradorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("UnidadAdministrador", Class.forName("crm.libraries.abm.entities.UnidadAdministrador"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (UnidadAdministradorManagerSEI)service.getStub(UnidadAdministradorManagerSEI.class);
	}

	public UnidadAdministrador[] getUnidadByCodigoUnidad(String codigoUnidad) throws RemoteException {
		return stub.getUnidadByCodigoUnidad(codigoUnidad);
	}
	
	public void removeByCodigoUnidad(String codigoUnidad)throws RemoteException  {
		stub.removeByCodigoUnidad(codigoUnidad);
	}
	
	public String update(UnidadAdministrador unidadVendedor) throws RemoteException {
		return stub.update(unidadVendedor);
	}
	
	public static UnidadAdministradorManager instance() {
		try {
			if (instance == null) {
				instance = new UnidadAdministradorManager();
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


	public Object[] getAdministradoresByUnidadAdministrativa(String codUnidad) throws RemoteException {
		return stub.getAdministradoresByUnidadAdministrativa(codUnidad);
	}

}
