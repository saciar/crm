/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.libraries.abm.entities.Equipos;
import crm.services.wsdl2.sei.EquiposManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author saciar
 */
public class EquiposManager extends CongressCRMServiceWSDL2 implements EquiposManagerSEI{
    private EquiposManagerSEI stub;
    private static EquiposManager instance;

    public static EquiposManager instance() {
		try {
			if (instance == null) {
				instance = new EquiposManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public EquiposManager() throws WSIFException{
        super("EquiposManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("Equipos", Class.forName("crm.libraries.abm.entities.Equipos"));            
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(EquiposManagerSEI)service.getStub(EquiposManagerSEI.class);
    }

    public Equipos getById(String codigo) throws RemoteException {
        return stub.getById(codigo);
    }

    public Equipos[] getAll() throws RemoteException {
        return stub.getAll();
    }

    public Equipos[] findByField(String field,String value) throws RemoteException {
        return stub.findByField(field, value);
    }

    public void remove(String codigo) throws RemoteException {
        stub.remove(codigo);
    }

    public Equipos update(Equipos equipo) throws RemoteException{
        return stub.update(equipo);
    }

    public Equipos[] findByFields(Object[] field,Object[] value) throws RemoteException{
        return stub.findByFields(field, value);
    }
    public Equipos[] findByFieldExactly(String field,String value) throws RemoteException{
        return stub.findByFieldExactly(field, value);
    }
}
