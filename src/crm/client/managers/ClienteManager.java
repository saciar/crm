package crm.client.managers;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wsif.WSIFException;

import crm.client.util.SystemConfig;
import crm.libraries.abm.entities.Cliente;
import crm.services.sei.ClienteManagerSEI;

public class ClienteManager extends CongressCRMService implements ClienteManagerSEI {
	private ClienteManagerSEI stub;
	private Set clientes;
	
	private ClientSynchronizer clientSynchronizer;
	
	private ClienteManager() throws WSIFException{
		super("ClienteManagerSEI");
		
		if (SystemConfig.isPreloadClientesEnabled())
			startSynchronizer();
	}
	
	
	protected void registerTypes() throws WSIFException {
		// map types
		try {
			addType("Cliente", Class.forName("crm.libraries.abm.entities.Cliente"));
			addType("ArrayOfString", Class.forName("crm.client.deserializer.ArrayOfString"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new WSIFException(e);
		}
		
		stub = (ClienteManagerSEI)service.getStub(ClienteManagerSEI.class);
	}


	public Cliente getClienteById(String codigo) throws RemoteException {
		return stub.getClienteById(codigo);
	}

	public Object[] obtenerCodigoYNombreFantasia(String nombre) throws RemoteException{
		return stub.obtenerCodigoYNombreFantasia(nombre);
	}
	
	public Object[] buscarPorNombreFantasiaOEmpresa(String nombre) throws RemoteException{
		return stub.buscarPorNombreFantasiaOEmpresa(nombre);
	}
	
	public Cliente[] getAllClientes() throws RemoteException {
		return stub.getAllClientes();
	}


	public void remove(String codigo) throws RemoteException {
		stub.remove(codigo);
	}


	public String update(Cliente cliente) throws RemoteException {
		return stub.update(cliente);
	}


	public Cliente[] findByField(String field, String value) throws RemoteException {
		return stub.findByField(field,value);
	}
	
	private static ClienteManager instance;

	public static ClienteManager instance() {
		try {
			if (instance == null) {
				instance = new ClienteManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * Trae la lista de clientes del cache. 
	 * Este metodo no se comunica con el webservice, quien lo hace es la clase
	 * ClientSynchronizer
	 */
	public Cliente[] getClientesReport() throws RemoteException {
		/*
		if (SystemConfig.isPreloadClientesEnabled()){
			synchronized (ClienteManager.class){
				
				//if (clientSynchronizer.isWorking()){
				if (!clientsloaded){
					// TODO: usar notify
					long l = 0;
					while (true){
						//if (!clientSynchronizer.isWorking()){
						if (clientsloaded){
							break;
						}
						
						try {
							Thread.currentThread().wait(1000);
							
							if (++l>120000)// 2 minutos
								throw new RuntimeException("Espere demasiado!");
							
						} catch (InterruptedException e) {
							e.printStackTrace();
							//Util.errMsg();
							break;
						}
					}
				}
			}

		*/
		
		if (SystemConfig.isPreloadClientesEnabled()){
			if (clientSynchronizer != null && clientSynchronizer.isAlive()){
			    // Wait for the thread to finish but don't wait longer than a
			    // specified time
			    long delayMillis = 10000; // 5 seconds
			    try {
			    	clientSynchronizer.join(delayMillis);
			    
			        if (clientSynchronizer.isAlive()) {
			            System.out.println("**** thread time out *****");
			        }
			        
			    } catch (InterruptedException e) {
			        // Thread was interrupted
			    }
			}
		}
		else {
			clientSynchronizer = new ClientSynchronizer();
			clientSynchronizer.run();
		}
	
		//if (clientSynchronizer.getClientes().size() == 0)
		//	System.out.println("***************************");
		
		
		System.out.println("clientes:"+clientes.size());
		Cliente[] ac = (Cliente[])clientes.toArray(new Cliente[0]);
		
		return ac;
		
		//stub.getClientesReportLimited(0, 0);
		
		//return null;
		
	}

	public Cliente getClienteInfo(String codigo) throws RemoteException {
		return stub.getClienteById(codigo);
	}

	public Object[] getClientesModificadosReport(long fecha) throws RemoteException {
		return stub.getClientesModificadosReport(fecha);
	}
	
	public Object[] getClienteNoCobrado(String codigo) throws RemoteException{
		return stub.getClienteNoCobrado(codigo);
	}
	
	public static void startCaching(){
		// solo debo crear una instancia del manager.
		// el constructor llamara a startSynchronizer automaticamente.
		instance();
	}
	
	public void startSynchronizer(){
		if (clientSynchronizer == null){
			clientSynchronizer = new ClientSynchronizer();
		}
		
		if (!clientSynchronizer.isAlive())
			clientSynchronizer.start();
	}
	
	public int getCantidadClientes() throws RemoteException {
		return stub.getCantidadClientes();
	}


	public Object[] getClientesReportLimited(int firstResult, int maxResults) throws RemoteException {
		return stub.getClientesReportLimited(firstResult,maxResults);
	}
	
	private class ClientSynchronizer extends Thread implements Comparator {
		private final Log log = LogFactory.getLog(ClientSynchronizer.class);
		//private boolean working; 
		private static final long SYNCH_PERIOD = 30000; // 30 segs
		private static final int FETCH_SIZE = 1000;
		private long fulltime=0;
		private Date lastsync;
		

		/**
		 *  
		 * @param original array que contiene la lista cacheada de clientes
		 * @param cambios array que contiene la lista de clientes modificados
		 */
		ClientSynchronizer(){
			//working = true;
			clientes = Collections.synchronizedSortedSet(new TreeSet(this));
		}
		
		/**
		 * Ejecuta el thread, este proceso, traer� inicialmente la lista completa
		 * de clientes y luego sincronizar� cada SYNCH_PERIOD milisegundos.
		 */
		public void run(){
			try {
				//long time=0;
				if (log.isDebugEnabled()){
					fulltime = System.currentTimeMillis();
					log.debug("Trayendo lista completa de clientes");
				}
				
				int clientcount = stub.getCantidadClientes();
				
				if (log.isDebugEnabled()){
					log.debug(clientcount + " clientes disponibles, trayendo clientes en paginas de " + FETCH_SIZE);
					
				}
				
				int fetched = 0;
				while (fetched < clientcount){
					// lanzo la busqueda en threads paralelos
					loadfrom(fetched);
					fetched+=FETCH_SIZE;
				}
				
				/*
				if (SystemConfig.isPreloadClientesEnabled()){
					// me mantengo enlinea sincronizando cada SYNCH_PERIOD
					while (true){
						sleep(SYNCH_PERIOD);

						// sincronizo con el webservice
						//sincronizar();
					}
				}
				*/
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//working = false;
			}
		}

		/**
		 * Inserta el contenido del array de cambios en el array original, dependiendo de la
		 * accion necesaria.
		 * Si el cambio no existe en el array original, entonces lo agrega, si existe pero el
		 * cambio esta marcado como inactivo, entonces lo quita del array, y sino, actualiza
		 * los datos.
		 * 
		 * @return array con la lista de clientes sincronizada
		 * @throws RemoteException  
		 */
		private void sincronizar() throws RemoteException {
			long time=0;
			if (log.isDebugEnabled()){
				time = System.currentTimeMillis();
				log.debug("Sincronizando clientes");
			}
			
			//working = true;
			
			// traigo la lista de clientes modificados entre la ultima sincronizaci�n y ahora.
			Object[] cambios = stub.getClientesModificadosReport(lastsync.getTime());
			
			// asigno ahora, la fecha de la ultima sincronizaci�n
			lastsync = new Date();
			
			if (log.isDebugEnabled()){
				time = System.currentTimeMillis() - time;
				log.debug("Se encontraron " + cambios.length + " clientes modificados en "+time+"ms.");
				time = System.currentTimeMillis();
			}
			
			// recorro los resultados
			for (int i = 0; i < cambios.length; i++) {
				Object[] row = (Object[])cambios[i];
				Cliente c = new Cliente((String)row[0],(String)row[1],(String)row[2],(Character)row[3]);
				
				// elimino del set, el cliente siendo modificado 
				clientes.remove(c);
				
				// solo lo vuelvo a agregar al set, si no fue eliminado
				if (c.isActivo()){
					clientes.add(c);
				}
			}
			
			//working = false;
			
			if (log.isDebugEnabled()){
				time = System.currentTimeMillis() - time;
				log.debug("Lista de clientes sincronizada con exito en " + time +"ms. Ahora hay " + clientes.size() + " clientes en el cache");
			}
		}

		private void loadfrom(final int offset){
			threadcount++;
			if (log.isDebugEnabled()){
				log.debug("Lanzando thread para descargar "+FETCH_SIZE+" clientes a partir del " + offset);
			}
			
			// si levanto los threads se muere todo.. :-s
		/*	
			new Thread(){
				public void run() {*/
					try {
						long time = System.currentTimeMillis();
						
						// traigo la lista completa de clientes
						Object[] tmp = stub.getClientesReportLimited(offset, FETCH_SIZE);
	
						// asigno ahora, la fecha de la ultima sincronizaci�n
						lastsync = new Date();
					
						if (log.isDebugEnabled()){
							time = System.currentTimeMillis() - time;
							log.debug(tmp.length + " clientes traidos en " + time + "ms.");
						}
					
						// los agrego al set
						for (int i = 0; i < tmp.length; i++) {
							synchronized(clientes){
								Object[] row = (Object[])tmp[i];
								clientes.add(new Cliente((String)row[0],(String)row[1],(String)row[2]));
							}
						}
						
						threadFinished();
					}
					catch (Exception e){
						e.printStackTrace();
					}
				/*}
			}.start();*/
			
		}
		
		public int compare(Object o1, Object o2) {
			if (o1 == null)
				return -1;
			if (o2 == null)
				return 1;
			
			Cliente c1 = (Cliente)o1;
			Cliente c2 = (Cliente)o2;
			
			return c1.getEmpresa().compareToIgnoreCase(c2.getEmpresa());
		}
		
		private volatile int threadcount = 0;
		private synchronized void threadFinished(){
			threadcount--;
			if (threadcount == 0){
				if (log.isDebugEnabled()){
					fulltime = System.currentTimeMillis() - fulltime;
					log.debug(clientes.size() + " clientes cargados en un total de " + fulltime + "ms.");
				}
				notifyAll();
			}
		}
	}

	public void testStringArrayParam(String[] test) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	public void testClientArrayParam(Cliente[] test) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	public void testClientParam(Cliente test) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	public Object[] getClienteNoUsados(String date) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getClienteNoUsados(date);
	}


}
