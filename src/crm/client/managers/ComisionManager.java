package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Comision;
import crm.services.sei.ComisionManagerSEI;

public class ComisionManager extends CongressCRMService implements ComisionManagerSEI {
	private ComisionManagerSEI stub;
	
	public ComisionManager() throws WSIFException{
		super("ComisionManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Comision", Class.forName("crm.libraries.abm.entities.Comision"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ComisionManagerSEI)service.getStub(ComisionManagerSEI.class);
	}


	public Comision getComisionById(String codigo) throws RemoteException {
		return stub.getComisionById(codigo);
	}


	public Comision[] getAllComisiones() throws RemoteException {
		return stub.getAllComisiones();
	}

	public Comision[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(Comision comision) throws RemoteException {
		stub.update(comision);
	}
	
	private static ComisionManager instance;

	public static ComisionManager instance() {
		try {
			if (instance == null) {
				instance = new ComisionManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Comision getComisionByVendedor(String vendedor) throws RemoteException {
		return stub.getComisionByVendedor(vendedor);
	}


	public void removeByVendedor(String codigoVendedor) throws RemoteException {
		stub.removeByVendedor(codigoVendedor);
	}
	
	public String getMarcoLiquidacionByCodVendedor(String codVendedor) throws RemoteException{
		return stub.getMarcoLiquidacionByCodVendedor(codVendedor);
	}
	
	public String getPorcentajeByCodVendedor(String codVendedor) throws RemoteException{
		return stub.getPorcentajeByCodVendedor(codVendedor);
	}
	
	public boolean isLugarComisionable(String codLugar) throws RemoteException{
		return stub.isLugarComisionable(codLugar);
	}
}
