package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.ListaPrecioCustom;
import crm.services.sei.ListaPrecioCustomManagerSEI;

public class ListaPrecioCustomManager extends CongressCRMService implements ListaPrecioCustomManagerSEI {
	private ListaPrecioCustomManagerSEI stub;
	
	public ListaPrecioCustomManager() throws WSIFException{
		super("ListaPrecioCustomManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("ListaPrecioCustom", Class.forName("crm.libraries.abm.entities.ListaPrecioCustom"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ListaPrecioCustomManagerSEI)service.getStub(ListaPrecioCustomManagerSEI.class);
	}


	public ListaPrecioCustom getListaPrecioCustomById(String codigo) throws RemoteException {
		return stub.getListaPrecioCustomById(codigo);
	}


	public ListaPrecioCustom[] getAllListaPrecioCustoms() throws RemoteException {
		return stub.getAllListaPrecioCustoms();
	}

	public ListaPrecioCustom[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(ListaPrecioCustom listaPrecioCustom) throws RemoteException {
		stub.update(listaPrecioCustom);
	}
	
	private static ListaPrecioCustomManager instance;

	public static ListaPrecioCustomManager instance() {
		try {
			if (instance == null) {
				instance = new ListaPrecioCustomManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public ListaPrecioCustom getListaPrecioCustomByCodLugar(String codLugar) throws RemoteException {
		return stub.getListaPrecioCustomByCodLugar(codLugar);
	}
}
