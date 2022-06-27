package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.DestinatarioOS;
import crm.services.wsdl2.sei.DestinatarioOSManagerSEI;

public class DestinatarioOSManager extends CongressCRMServiceWSDL2 implements DestinatarioOSManagerSEI{
	private DestinatarioOSManagerSEI stub;
	
	public DestinatarioOSManager() throws WSIFException{
		super("DestinatarioOSManagerSEI");
	}
	
	@Override
	protected void registerTypes() throws WSIFException {
		try{
			addType("DestinatarioOS", Class.forName("crm.libraries.abm.entities.DestinatarioOS"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		stub=(DestinatarioOSManagerSEI)service.getStub(DestinatarioOSManagerSEI.class);
	}

	public DestinatarioOS[] getAll() throws RemoteException {

		return stub.getAll();
	}

	public DestinatarioOS getById(String id) throws RemoteException {		
		return stub.getById(id);
	}

	private static DestinatarioOSManager instance;
	
	public static DestinatarioOSManager instance(){
		try{
			if(instance == null){
				instance = new DestinatarioOSManager();
			}
		}
		catch(WSIFException e){
			e.printStackTrace();
		}
		return instance;
	}

	public String update(DestinatarioOS destinatario) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.update(destinatario);
	}
	
}
