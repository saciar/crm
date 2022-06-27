/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.EgresoEquipo;
import crm.services.wsdl2.sei.EgresoEquipoManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author saciar
 */
public class EgresoEquipoManager extends CongressCRMServiceWSDL2 implements EgresoEquipoManagerSEI{

    private EgresoEquipoManagerSEI stub;
    private static EgresoEquipoManager instance;

    public static EgresoEquipoManager instance() {
		try {
			if (instance == null) {
				instance = new EgresoEquipoManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EgresoEquipoManager() throws WSIFException{
        super("EgresoEquipoManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("EgresoEquipo", Class.forName("crm.libraries.abm.entities.EgresoEquipo"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EgresoEquipoManagerSEI)service.getStub(EgresoEquipoManagerSEI.class);
    }

    public EgresoEquipo getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public EgresoEquipo[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public EgresoEquipo[] findByField(String field, String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(EgresoEquipo model) throws RemoteException {
        stub.update(model);
    }

    public EgresoEquipo[] findByFields(Object[] field, Object[] value) throws RemoteException {
        return stub.findByFields(field, value);
    }

    public EgresoEquipo[] findByFieldExactly(String field, String value) throws RemoteException {
        return stub.findByFieldExactly(field, value);
    }

}
