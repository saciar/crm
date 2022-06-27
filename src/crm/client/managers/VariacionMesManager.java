package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.VariacionMes;
import crm.services.wsdl2.sei.VariacionMesManagerSEI;

public class VariacionMesManager extends CongressCRMServiceWSDL2 implements VariacionMesManagerSEI{
	
private static VariacionMesManager instance;
	
	private VariacionMesManagerSEI stub;	

	public static VariacionMesManager instance() {
		try {
			if (instance == null) {
				instance = new VariacionMesManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public VariacionMesManager() throws WSIFException{
		super("VariacionMesManagerSEI");
	}
		
	protected void registerTypes() throws WSIFException {
		try {
			addType("VariacionMes", Class.forName("crm.libraries.abm.entities.VariacionMes"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (VariacionMesManagerSEI)service.getStub(VariacionMesManagerSEI.class);
	}

	public VariacionMes getById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getById(codigo);
	}

	public VariacionMes[] getAll() throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getAll();
	}

	public VariacionMes[] findByField(String field, String value) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByField(field, value);
	}

	public String getVariacionById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getVariacionById(codigo);
	}

	public int getVariacionByMes(int mes) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getVariacionByMes(mes);
	}

}
