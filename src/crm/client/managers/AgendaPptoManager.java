package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.AgendaPpto;
import crm.services.wsdl2.sei.AgendaPptoManagerSEI;

public class AgendaPptoManager extends CongressCRMServiceWSDL2 implements AgendaPptoManagerSEI{

	private AgendaPptoManagerSEI stub;
	private static AgendaPptoManager instance;
	
	public static AgendaPptoManager instance(){
		try {
			if (instance == null) {
				instance = new AgendaPptoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public AgendaPptoManager() throws WSIFException {
		super("AgendaPptoManagerSEI");
	}	

	public String update(AgendaPpto cobrador) throws RemoteException{
		return stub.update(cobrador);
	}

	@Override
	protected void registerTypes() throws WSIFException {
		try {
			addType("AgendaPpto", Class.forName("crm.libraries.abm.entities.AgendaPpto"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (AgendaPptoManagerSEI)service.getStub(AgendaPptoManagerSEI.class);
		
	}

	public AgendaPpto[] findByField(String field, String value) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.findByField(field,value);
	}

	public AgendaPpto getDataById(String codigo) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getDataById(codigo);
	}
	

}
