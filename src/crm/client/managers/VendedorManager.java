package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Vendedor;
import crm.services.sei.VendedorManagerSEI;

public class VendedorManager extends CongressCRMService implements VendedorManagerSEI {
	private VendedorManagerSEI stub;
	
	public VendedorManager() throws WSIFException{
		super("VendedorManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Vendedor", Class.forName("crm.libraries.abm.entities.Vendedor"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (VendedorManagerSEI)service.getStub(VendedorManagerSEI.class);
	}


	
	public Vendedor getVendedorById(String codigo) throws RemoteException {
		return stub.getVendedorById(codigo);
	}


	public Vendedor[] getAllVendedores() throws RemoteException {
		return stub.getAllVendedores();
	}

	
	
	public Vendedor[] findByField(String field,String value) throws RemoteException{
		return stub.findByField(field,value);
	}
	
	public Vendedor[] findByCategoryIdAndField(String categoryId, String field, String value) throws RemoteException {
		return stub.findByCategoryIdAndField(categoryId,field,value);
	}
	
	public Object[] getVendedoresSinUnidadComercial(String categoria)throws RemoteException{
		return stub.getVendedoresSinUnidadComercial(categoria);
	}

	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public String update(Vendedor vendedor/*,String unidadComercial*/) throws RemoteException {
		return this.stub.update(vendedor/*,unidadComercial*/);
	}
	
	private static VendedorManager instance;

	public static VendedorManager instance() {
		try {
			if (instance == null) {
				instance = new VendedorManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}


	public Vendedor getVendedorByApYNom(String apYNom) throws RemoteException {
		return stub.getVendedorByApYNom(apYNom);
	}

	public Object[] getAllVendedoresNotInUnidadesVendedores(String categoria) throws RemoteException {
		return stub.getAllVendedoresNotInUnidadesVendedores(categoria);
	}

	public Object[] getAllVendedoresNotInUnidadesVendedoresByVendedores(String categoria,String vendedoresArray) throws RemoteException {
		return stub.getAllVendedoresNotInUnidadesVendedoresByVendedores(categoria,vendedoresArray);
	}
	
	public Object[] getAllVendedoresNotInUnidadesComerciales(String categoria) throws RemoteException{
		return stub.getAllVendedoresNotInUnidadesComerciales(categoria);
	}
	public Object[] getAllVendedoresNotInUnidadesComercialesByVendedores(String categoria,String vendedor) throws RemoteException{
		return stub.getAllVendedoresNotInUnidadesComercialesByVendedores(categoria,vendedor);
	}

	/*public String[] getVendedorReportByCodigoReport(String codigo, String categoria) throws RemoteException {
		return stub.getVendedorReportByCodigoReport(codigo, categoria);
	}*/
	
	public Object[] getVendedorReportByCodigoReport(String codigo) throws RemoteException {
		return stub.getVendedorReportByCodigoReport(codigo);
	}

	public Object[] getVendedoresByComercialUnit(String codigoUC) throws RemoteException{
		return stub.getVendedoresByComercialUnit(codigoUC);
	}
	
	public boolean isVendedorById(String codVendedor) throws RemoteException{
		return stub.isVendedorById(codVendedor);
	}
	
}
