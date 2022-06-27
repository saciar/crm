package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.services.sei.UserAccessHistoryManagerSEI;

public class UserAccessHistoryManager extends CongressCRMService implements UserAccessHistoryManagerSEI {
	private static UserAccessHistoryManager instance;
	private UserAccessHistoryManagerSEI stub;
	
	private UserAccessHistoryManager() throws WSIFException{
		super("UserAccessHistoryManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		try {
			addType("UserAccessHistory", Class.forName("crm.libraries.abm.entities.UserAccessHistory"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		stub = (UserAccessHistoryManagerSEI)service.getStub(UserAccessHistoryManagerSEI.class);
	}

	public static UserAccessHistoryManager instance() {
		try {
			if (instance == null) {
				instance = new UserAccessHistoryManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public void saveHistory(String userId, String accessId, String type) throws RemoteException {
		this.stub.saveHistory(userId,accessId,type);
		
	}
	
	
	
}
