package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.CategReferencia;
import crm.libraries.abm.entities.TxSeguimiento;
import crm.services.sei.TxSeguimientoManagerSEI;

public class TxSeguimientoManager extends CongressCRMService implements TxSeguimientoManagerSEI {
	private TxSeguimientoManagerSEI stub;
	
	private TxSeguimientoManager() throws WSIFException{
		super("TxSeguimientoManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("TxSeguimiento", Class.forName("crm.libraries.abm.entities.TxSeguimiento"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (TxSeguimientoManagerSEI)service.getStub(TxSeguimientoManagerSEI.class);
	}
	
	private static TxSeguimientoManager instance;

	public static TxSeguimientoManager instance() {
		try {
			if (instance == null) {
				instance = new TxSeguimientoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public TxSeguimiento[] getAllTxSeguimientos() throws RemoteException {
		return stub.getAllTxSeguimientos();
	}
	
	public void update(TxSeguimiento txSeguimiento) throws RemoteException {
		stub.update(txSeguimiento);
	}
	
	public TxSeguimiento[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field, value);
	}
	
	public Object[] getSeguimientosByNroPpto(long nroPpto) throws RemoteException{
		return stub.getSeguimientosByNroPpto(nroPpto);
	}
}
