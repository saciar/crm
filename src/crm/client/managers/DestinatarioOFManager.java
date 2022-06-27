package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.DestinatarioOF;
import crm.services.wsdl2.sei.DestinatarioOFManagerSEI;

public class DestinatarioOFManager extends CongressCRMServiceWSDL2 implements DestinatarioOFManagerSEI{
	private DestinatarioOFManagerSEI stub;
	
	public DestinatarioOFManager() throws WSIFException{
		super("DestinatarioOFManagerSEI");
	}
	
	@Override
	protected void registerTypes() throws WSIFException {
		try{
			addType("DestinatarioOF", Class.forName("crm.libraries.abm.entities.DestinatarioOF"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		stub=(DestinatarioOFManagerSEI)service.getStub(DestinatarioOFManagerSEI.class);
	}

	public DestinatarioOF[] getAll() throws RemoteException {

		return stub.getAll();
	}

	public DestinatarioOF getById(String id) throws RemoteException {		
		return stub.getById(id);
	}

	private static DestinatarioOFManager instance;
	
	public static DestinatarioOFManager instance(){
		try{
			if(instance == null){
				instance = new DestinatarioOFManager();
			}
		}
		catch(WSIFException e){
			e.printStackTrace();
		}
		return instance;
	}

	public String update(DestinatarioOF destinatario) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.update(destinatario);
	}
	
}
