/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.EstadoEquipos;
import crm.services.wsdl2.sei.EstadoEquiposManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author saciar
 */
public class EstadoEquiposManager extends CongressCRMServiceWSDL2 implements EstadoEquiposManagerSEI{
    private EstadoEquiposManagerSEI stub;
    private static EstadoEquiposManager instance;

    public static EstadoEquiposManager instance() {
		try {
			if (instance == null) {
				instance = new EstadoEquiposManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EstadoEquiposManager() throws WSIFException{
        super("EstadoEquiposManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("EstadoEquipos", Class.forName("crm.libraries.abm.entities.EstadoEquipos"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EstadoEquiposManagerSEI)service.getStub(EstadoEquiposManagerSEI.class);
    }

    public EstadoEquipos getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public EstadoEquipos[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public EstadoEquipos[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public void update(EstadoEquipos equipo) throws RemoteException{
        stub.update(equipo);
    }

    public EstadoEquipos[] findByFields(Object[] field,Object[] value) throws RemoteException{
        return stub.findByFields(field, value);
    }

}
