package crm.client.managers;

public abstract class CongressCRMService extends BaseManager {

	public static final String congressCrmService = "CongressCRMService";
	
	public CongressCRMService(String portType) {
		super(congressCrmService, portType);
	}
}
