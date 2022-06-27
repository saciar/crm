/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.managers;

import crm.services.wsdl2.sei.SmsManagerSEI;
import java.rmi.RemoteException;
import org.apache.wsif.WSIFException;

/**
 *
 * @author sergio
 */
public class SmsManager extends CongressCRMServiceWSDL2 implements SmsManagerSEI {
	private static SmsManager instance;
	
	private SmsManagerSEI stub;
	
	public SmsManager() throws WSIFException{
		super("SmsManagerSEI");
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Long", Class.forName("java.lang.Long"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (SmsManagerSEI)service.getStub(SmsManagerSEI.class);
	}

	public static SmsManager instance() {
		try {
			if (instance == null) {
				instance = new SmsManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public Object[] buscarSmsParaLiqOperador(String fechaInicial, String fechaFinal, long codOp) throws RemoteException {
        return stub.buscarSmsParaLiqOperador(fechaInicial, fechaFinal, codOp);
    }

    public Object[] buscarSmsDetalleTodos(String fechaInicial, String fechaFinal) throws RemoteException {
        return stub.buscarSmsDetalleTodos(fechaInicial, fechaFinal);
    }

    public Object[] buscarSmsDetalleOperador(String fechaInicial, String fechaFinal, long codOp) throws RemoteException {
        return stub.buscarSmsDetalleOperador(fechaInicial, fechaFinal, codOp);
    }

    public Object[] buscarSmsParaLiqTodos(String fechaInicial, String fechaFinal) throws RemoteException {
        return stub.buscarSmsParaLiqTodos(fechaInicial, fechaFinal);
    }
    
    public Object[] buscarSmsPorNroPptoDetalleOperador(long nroppto, long codOp) throws RemoteException{
        return stub.buscarSmsPorNroPptoDetalleOperador(nroppto,codOp);
    }
    
    public Object[] buscarSmsPorNroPptoDetalleTodos(long nroppto) throws RemoteException{
        return stub.buscarSmsPorNroPptoDetalleTodos(nroppto);
    }
    
    public Object[] buscarSmsPorNroPptoLiqTodos(long nroppto) throws RemoteException{
        return stub.buscarSmsPorNroPptoLiqTodos(nroppto);
    }
    
    public Object[] buscarSmsPorNroPptoLiqOperador(long nroppto, long codOp) throws RemoteException{
        return stub.buscarSmsPorNroPptoLiqOperador(nroppto, codOp);
    }
}
