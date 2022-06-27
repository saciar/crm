package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.DescPrecioDias;
import crm.services.sei.DescPrecioDiasManagerSEI;

public class DescPrecioDiasManager extends CongressCRMService implements DescPrecioDiasManagerSEI {
	private DescPrecioDiasManagerSEI stub;
	
	public DescPrecioDiasManager() throws WSIFException{
		super("DescPrecioDiasManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("DescPrecioDias", Class.forName("crm.libraries.abm.entities.DescPrecioDias"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (DescPrecioDiasManagerSEI)service.getStub(DescPrecioDiasManagerSEI.class);
	}


	public DescPrecioDias getDescPrecioDiasById(String codigo) throws RemoteException {
		return stub.getDescPrecioDiasById(codigo);
	}


	public DescPrecioDias[] getAllDescPrecioDias() throws RemoteException {
		return stub.getAllDescPrecioDias();
	}

	public DescPrecioDias[] findByField(String field,String value) throws RemoteException {
		return stub.findByField(field,value);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(DescPrecioDias descPrecioDias) throws RemoteException {
		stub.update(descPrecioDias);
	}
	
	private static DescPrecioDiasManager instance;

	public static DescPrecioDiasManager instance() {
		try {
			if (instance == null) {
				instance = new DescPrecioDiasManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public DescPrecioDias[] getDescPrecioDiasByServicio(String codigoServicio) throws RemoteException {
		return stub.getDescPrecioDiasByServicio(codigoServicio);
	}
}
