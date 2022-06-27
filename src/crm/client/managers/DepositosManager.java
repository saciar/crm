/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.Depositos;
import crm.services.wsdl2.sei.DepositosManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author saciar
 */
public class DepositosManager extends CongressCRMServiceWSDL2 implements DepositosManagerSEI{
    private DepositosManagerSEI stub;
    private static DepositosManager instance;

    public static DepositosManager instance() {
		try {
			if (instance == null) {
				instance = new DepositosManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public DepositosManager() throws WSIFException{
        super("DepositosManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("Depositos", Class.forName("crm.libraries.abm.entities.Depositos"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(DepositosManagerSEI)service.getStub(DepositosManagerSEI.class);
    }

    public Depositos getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public Depositos[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public Depositos[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(Depositos equipo) throws RemoteException{
        stub.update(equipo);
    }

}
