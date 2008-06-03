package ipc.db;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public abstract class DAOFactory {
	public static final Class HIBERNATE = ipc.db.IpcDAOFactory.class;
	
	//private static final SessionFactory sessionFactory;
	
    /**
     * Factory method for instantiation of concrete factories.
     */
    public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory)factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }
    
    // DAO interfaces
    public abstract CorsoDAO getCorsoDAO();
    public abstract AccountDAO getAccountDAO();
    public abstract EsameDAO getEsameDAO();
    public abstract IscrizioneCorsoDAO getIscrizioneCorsoDAO();
    public abstract PrenotazioneEsameDAO getPrenotazioneEsameDAO();
    public abstract ProgettoDAO getProgettoDAO();
    public abstract GruppoDAO getGruppoDAO();

}
