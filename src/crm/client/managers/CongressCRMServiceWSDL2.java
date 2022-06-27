package crm.client.managers;

public abstract class CongressCRMServiceWSDL2 extends BaseManager {

	protected static final String congressCrmService = "CongressCRMServiceWSDL2";
	
	public CongressCRMServiceWSDL2(String portType) {
		super(congressCrmService, portType);
	}
}
