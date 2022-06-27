package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.services.sei.VendedorUsuarioManagerSEI;

public class VendedorUsuarioManager extends CongressCRMService implements VendedorUsuarioManagerSEI {
	private static VendedorUsuarioManager instance;
	
	private VendedorUsuarioManagerSEI stub;
	
	public VendedorUsuarioManager() throws WSIFException{
		super("VendedorUsuarioManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("VendedorUsuario", Class.forName("crm.libraries.abm.entities.VendedorUsuario"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (VendedorUsuarioManagerSEI)service.getStub(VendedorUsuarioManagerSEI.class);
	}

	public static VendedorUsuarioManager instance() {
		try {
			if (instance == null) {
				instance = new VendedorUsuarioManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public String getCodigoUsuario(String codigoVendedor) throws RemoteException{
		return stub.getCodigoUsuario(codigoVendedor);
	}
	
	public String getCodigoVendedor(String codigoUsuario)throws RemoteException {
		return stub.getCodigoVendedor(codigoUsuario);
	}
	
	public boolean isVendedor(String codigoUsuario)throws RemoteException{
		return stub.isVendedor(codigoUsuario);
	}

}
