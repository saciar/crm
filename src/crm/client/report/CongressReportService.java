package crm.client.report;

import crm.client.managers.BaseManager;

public abstract class CongressReportService extends BaseManager {

	public static final String congressReportService = "CongressReportService";
	
	public CongressReportService(String portType) {
		super(congressReportService, portType);
	}
}
