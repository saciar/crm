package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.SalaLugar;
import crm.services.sei.SalaLugarManagerSEI;

public class SalaLugarManager extends CongressCRMService implements SalaLugarManagerSEI {
	private SalaLugarManagerSEI stub;
	
	private SalaLugarManager() throws WSIFException{
		super("SalaLugarManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("SalaLugar", Class.forName("crm.libraries.abm.entities.SalaLugar"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (SalaLugarManagerSEI)service.getStub(SalaLugarManagerSEI.class);
	}


	public SalaLugar getSalaLugarById(String codigo) throws RemoteException {
		return stub.getSalaLugarById(codigo);
	}


	public SalaLugar[] getAllSalaLugares() throws RemoteException {
		return stub.getAllSalaLugares();
	}

	public SalaLugar[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public void update(SalaLugar salaLugar) throws RemoteException {
		stub.update(salaLugar);
	}
	
	private static SalaLugarManager instance;

	public static SalaLugarManager instance() {
		try {
			if (instance == null) {
				instance = new SalaLugarManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Object[] getSalaLugarReport() throws RemoteException {
		return stub.getSalaLugarReport();
	}


	public Object[] getSalaLugarReportByLugar(String codigoLugar) throws RemoteException {
		return stub.getSalaLugarReportByLugar(codigoLugar);
	}


	public SalaLugar getSalaLugarByCodSala(String codigoSala) throws RemoteException {		
		return stub.getSalaLugarByCodSala(codigoSala);
	}
	
	public SalaLugar getSalaLugarByCodSalaAndLugar(String codigoSala, String codigoLugar) throws RemoteException {
		return stub.getSalaLugarByCodSalaAndLugar(codigoSala,codigoLugar);
	}
}
