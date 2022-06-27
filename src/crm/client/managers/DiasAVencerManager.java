package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.DiasAVencer;
import crm.services.sei.DiasAVencerManagerSEI;

public class DiasAVencerManager extends CongressCRMService implements DiasAVencerManagerSEI {
	private DiasAVencerManagerSEI stub;
	
	public DiasAVencerManager() throws WSIFException{
		super("DiasAVencerManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		try {
			addType("DiasAVencer", Class.forName("crm.libraries.abm.entities.DiasAVencer"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (DiasAVencerManagerSEI)service.getStub(DiasAVencerManagerSEI.class);
	}


	public DiasAVencer getDiasAVenver() throws RemoteException {
		return stub.getDiasAVenver();
	}


	public void update(DiasAVencer diasAVencer) throws RemoteException {
		stub.update(diasAVencer);
	}
	
	private static DiasAVencerManager instance;

	public static DiasAVencerManager instance() {
		try {
			if (instance == null) {
				instance = new DiasAVencerManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


}
