package ipc.db;

import org.hibernate.Session;

public class IpcDAOFactory extends DAOFactory {
	
	public CorsoDAO getCorsoDAO() {
        return (CorsoDAO)instantiateDAO(CorsoDAOImpl.class);
    }
	
	public AccountDAO getAccountDAO() {
		return (AccountDAO)instantiateDAO(AccountDAOImpl.class);
	}
	
	public EsameDAO getEsameDAO() {
		return (EsameDAO)instantiateDAO(EsameDAOImpl.class);
	}
	
	public ProgettoDAO getProgettoDAO() {
		return (ProgettoDAO)instantiateDAO(ProgettoDAOImpl.class);
	}
	
	public IscrizioneCorsoDAO getIscrizioneCorsoDAO() {
		return (IscrizioneCorsoDAO)instantiateDAO(IscrizioneCorsoDAOImpl.class);
	}
	
	public PrenotazioneEsameDAO getPrenotazioneEsameDAO() {
		return (PrenotazioneEsameDAO)instantiateDAO(PrenotazioneEsameDAOImpl.class);
	}
	
	public GruppoDAO getGruppoDAO() {
		return (GruppoDAO)instantiateDAO(GruppoDAOImpl.class);
	}
	
    private GenericDAOImpl instantiateDAO(Class daoClass) {
        try {
        	GenericDAOImpl dao = (GenericDAOImpl)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

}
