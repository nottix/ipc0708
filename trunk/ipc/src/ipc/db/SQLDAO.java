package ipc.db;

import java.util.Date;
import java.util.List;
import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

import ipc.entity.Account;
import ipc.entity.Corso;
import ipc.entity.Esame;
import ipc.entity.Gruppo;
import ipc.entity.IscrizioneCorso;
import ipc.entity.PrenotazioneEsame;
import ipc.entity.Progetto;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

public class SQLDAO {
	public Account getAccount(String email) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
		Account result = null;
        session.beginTransaction();
        Query q = session.createQuery("from Account a where a.email = :email");
        q.setParameter("email", email, Hibernate.STRING);
        System.out.println("query");
        if(q.uniqueResult()!=null) {
        	result = (Account) q.uniqueResult();
        }
        session.getTransaction().commit();
        /*Si deve controllare se result è null*/
        return result;
	}
	
	public Esame getEsame(Long id) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
		Esame result = null;
        session.beginTransaction();
        Query q = session.createQuery("from Esame a where a.id = :id");
        q.setParameter("id", id, Hibernate.LONG);
        System.out.println("query esame");
        if(q.uniqueResult()!=null) {
        	result = (Esame) q.uniqueResult();
        }
        session.getTransaction().commit();
        /*Si deve controllare se result è null*/
        return result;
	}
	
	public IscrizioneCorso getIscrizioneCorso(Long id) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
		IscrizioneCorso result = null;
        session.beginTransaction();
        Query q = session.createQuery("from IscrizioneCorso a where a.id = :id");
        q.setParameter("id", id, Hibernate.LONG);
        System.out.println("query iscrizioneCorso");
        if(q.uniqueResult()!=null) {
        	result = (IscrizioneCorso) q.uniqueResult();
        }
        session.getTransaction().commit();
        /*Si deve controllare se result è null*/
        return result;
	}
	
	public PrenotazioneEsame getPrenotazioneEsame(Long id) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
		PrenotazioneEsame result = null;
        session.beginTransaction();
        Query q = session.createQuery("from PrenotazioneEsame a where a.id = :id");
        q.setParameter("id", id, Hibernate.LONG);
        System.out.println("query PrenotazioneEsame");
        if(q.uniqueResult()!=null) {
        	result = (PrenotazioneEsame) q.uniqueResult();
        }
        session.getTransaction().commit();
        /*Si deve controllare se result è null*/
        return result;
	}
	
	public String creazioneProfessore(Hashtable data) throws Exception {
		Account test = null;
		String ret = "";
		test = this.getAccount((String) data.get("email"));
		ret = (test == null) ? this.createAndStoreAccount(data) : test.getEmail();
		return ret;
	}
	
	private Type getType(Object o) {
		Type type = null;
		if(o instanceof String)
			type = (Type) Hibernate.STRING;
		else if(o instanceof Boolean)
			type = (Type) Hibernate.BOOLEAN;
		else if(o instanceof Long)
			type = (Type) Hibernate.LONG;
		else if(o instanceof Integer)
			type = (Type) Hibernate.INTEGER;
		else if(o instanceof Date)
			type = (Type) Hibernate.DATE;
		else 
			type = (Type) Hibernate.OBJECT;
		return type;
	}
	
	public List queryWrapper(String table, String suffix, String []expLogic, Hashtable data) {
		Iterator keySet = data.keySet().iterator();
		Query q = null;
		int i = 0;
		String query = "from " + table + " " + suffix + " where ";
		while(keySet.hasNext())
		{
			String key = (String) keySet.next();
			query += suffix + "." + key + "= :" + key;
			if(expLogic != null && i < expLogic.length && expLogic[i] != null)
				query += " " + expLogic[i] + " ";
			i++;
		}
		keySet = data.keySet().iterator();
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
		session.beginTransaction();
        q = session.createQuery(query);
        while(keySet.hasNext()) {
        	String key = (String) keySet.next();
        	Object value = data.get(key);
        	q.setParameter(key, value, this.getType(value));
        }
        List result = q.list();
        session.getTransaction().commit();
        return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> listAccount() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Account> result = session.createQuery("from Account").list();
        session.getTransaction().commit();
        return result;
    }
    
	private Account hashToAccount(Account anAccount, Hashtable data) {
        if(anAccount == null)
        	anAccount = new Account();
	    if(data.get("nome") != null)
	        anAccount.setNome((String) data.get("nome"));
	    if(data.get("cognome") != null)
	        anAccount.setCognome((String) data.get("cognome"));
	    if(data.get("email") != null)
	        anAccount.setEmail((String) data.get("email"));
	    if(data.get("password") != null)
	        anAccount.setPassword((String) data.get("password"));
	    if(data.get("status") != null)
	        anAccount.setStatus((String) data.get("status"));
	    if(data.get("tipologia") != null)
	        anAccount.setTipologia((String) data.get("tipologia"));
	    if(data.get("isDirettore") != null)
	        anAccount.setIsDirettore((Boolean) data.get("isDirettore"));
	    if(data.get("matricola") != null)
	        anAccount.setMatricola((String) data.get("matricola"));
	    if(data.get("isGestore") != null)
	        anAccount.setIsGestore((Boolean) data.get("isGestore"));
	    if(data.get("canUpload") != null)
	        anAccount.setCanUpload((Boolean) data.get("canUpload"));
	    if(data.get("noteUpload") != null)
	        anAccount.setNoteUpload((String) data.get("noteUpload"));
	    if(data.get("noteStud") != null)
	        anAccount.setNoteStud((String) data.get("noteStud"));
	    if(data.get("noteProf") != null)
	        anAccount.setNoteProf((String) data.get("noteProf"));
        return anAccount;
	}
    
	public String createAndStoreAccount(Hashtable data) throws Exception {
		System.out.println("create account");
		Account anAccount = this.hashToAccount(null, data);
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(anAccount);
        session.getTransaction().commit();
        return anAccount.getEmail();
    }
	
	public boolean updateAccount(String email, Hashtable data) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Account anAccount = (Account) session.load(Account.class, email);
        anAccount = this.hashToAccount(anAccount, data);
        session.update(anAccount);
        tx.commit();
        return tx.wasCommitted();
    }
	
	@SuppressWarnings("unchecked")
	public List<Corso> listCorso() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction().begin();
        List<Corso> result = session.createQuery("from Corso").list();
        //session.getTransaction().commit();
        return result;
    }
	
	private Corso hashToCorso(Corso aCourse, Hashtable data) {
		if(aCourse == null)
			aCourse = new Corso();
		if(data.get("nome") != null)
			aCourse.setNome((String) data.get("nome"));
		if(data.get("acronimo") != null)		
			aCourse.setAcronimo((String) data.get("acronimo"));
		if(data.get("descrizione") != null)
		aCourse.setDescrizione((String) data.get("descrizione"));
		if(data.get("titolare1") != null)
			aCourse.setComunicazioni((String) data.get("comunicazioni"));
		if(data.get("dataApertura") != null)
			aCourse.setDataApertura((Date) data.get("dataApertura"));
		if(data.get("dataChiusura") != null)
			aCourse.setDataChiusura((Date) data.get("dataChiusura"));
		if(data.get("elencoCollaboratori") != null)
			aCourse.setElencoCollaboratori((Set) data.get("elencoCollaboratori"));
		if(data.get("elencoTitolari") != null)
			aCourse.setElencoTitolari((Set) data.get("elencoTitolari"));
		if(data.get("corsiPropedeutici") != null)
			aCourse.setCorsiPropedeutici((Set) data.get("corsiPropedeutici"));
		if(data.get("status") != null)
	        aCourse.setStatus((String) data.get("status"));
		return aCourse;
	}
	
	public Corso getCorso(String acronimo) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction().begin();
        Query q = session.createQuery("from Corso a where a.acronimo = :acronimo");
        q.setParameter("acronimo", acronimo, Hibernate.STRING);
        Corso result = null;
        if(q.uniqueResult()!=null)
        	result = (Corso) q.uniqueResult();
        session.getTransaction().commit();
        /*Si deve controllare se result è null*/
        return result;
    }
	
	public Long creazioneCorso(Hashtable data) throws Exception {
		Corso c = null;
		c = this.getCorso((String) data.get("acronimo"));
		return (c == null) ? this.createAndStoreCorso(data) : c.getId();
	}
	
	public Long createAndStoreCorso(Hashtable data) throws Exception {
		Corso aCourse = this.hashToCorso(null, data);
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(aCourse);
        session.getTransaction().commit();
        return aCourse.getId();
    }
	
	public boolean updateCorso(Long idCorso, Hashtable data) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Corso aCourse = (Corso) session.load(Corso.class, idCorso);
        aCourse = this.hashToCorso(aCourse, data);
        session.update(aCourse);
        tx.commit();
        return tx.wasCommitted();
    }
	
	@SuppressWarnings("unchecked")
	public List<Esame> listEsame() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Esame> result = session.createQuery("from Esame").list();
        session.getTransaction().commit();
        return result;
    }
	
	private Esame hashToEsame(Esame anExam, Hashtable data) {
		if(anExam == null)
			anExam = new Esame();
		if(data.get("idCorso") != null)
			anExam.setIdCorso((Long) data.get("idCorso"));
        if(data.get("dataInizioPeriodoPrenotazione") != null)
        	anExam.setDataInizioPeriodoPrenotazione((Date) data.get("dataInizioPeriodoPrenotazione"));
        if(data.get("dataFinePeriodoPrenotazione") != null)
        	anExam.setDataFinePeriodoPrenotazione((Date) data.get("dataFinePeriodoPrenotazione"));
        if(data.get("dataEsame") != null)
        	anExam.setDataEsame((Date) data.get("dataEsame"));
        if(data.get("auleEsame") != null)
	        anExam.setAuleEsame((String) data.get("auleEsame"));
        if(data.get("status") != null)
	        anExam.setStatus((String) data.get("status"));
	    return anExam;
	}
	
	public Long createAndStoreEsame(Hashtable data) throws Exception {
		Esame anExam = this.hashToEsame(null, data);
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(anExam);
        session.getTransaction().commit();
        return anExam.getId();
    }
	
	public boolean updateEsame(Long idEsame, Hashtable data) throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Esame anExam = (Esame) session.load(Esame.class, idEsame);
        anExam = this.hashToEsame(anExam, data);
        session.update(anExam);
        tx.commit();
        return tx.wasCommitted();
    }
	
	@SuppressWarnings("unchecked")
	public List<Gruppo> listGruppo() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Gruppo> result = session.createQuery("from Gruppo").list();
        session.getTransaction().commit();
        return result;
    }
	
	private Gruppo hashToGruppo(Gruppo aGroup, Hashtable data) {
		if(aGroup == null)
			aGroup = new Gruppo();
        if(data.get("maxStudenti") != null)
        	aGroup.setMaxStudenti((Integer) data.get("maxStudenti"));
        if(data.get("minStudenti") != null)
        	aGroup.setMinStudenti((Integer) data.get("minStudenti"));
        if(data.get("listaStudenti") != null)
        	aGroup.setListaStudenti((Set) data.get("listaStudenti"));
        if(data.get("status") != null)
	        aGroup.setStatus((String) data.get("status"));
	    return aGroup;
	}
	
	public Long createAndStoreGruppo(Hashtable data) throws Exception {
		Gruppo aGroup = this.hashToGruppo(null, data);
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(aGroup);
        session.getTransaction().commit();
        return aGroup.getId();
    }
	
	public boolean updateGruppo(Long idGruppo, Hashtable data) throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Gruppo aGroup = (Gruppo) session.load(Gruppo.class, idGruppo);
        aGroup = this.hashToGruppo(aGroup, data);
        session.update(aGroup);
        tx.commit();
        return tx.wasCommitted();
	}
	
	@SuppressWarnings("unchecked")
	public List<IscrizioneCorso> listIscrizioneCorso() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<IscrizioneCorso> result = session.createQuery("from IscrizioneCorso").list();
        session.getTransaction().commit();
        return result;
    }
	
	private IscrizioneCorso hashToIscrizioneCorso(IscrizioneCorso regCourse, Hashtable data) {
		if(regCourse == null)
			regCourse = new IscrizioneCorso();
        if(data.get("dataIscrizione") != null)
        	regCourse.setDataIscrizione((Date) data.get("dataIscrizione"));
        if(data.get("flag") != null)
        	regCourse.setFlag((String) data.get("flag"));
        if(data.get("idStudente") != null)
        	regCourse.setIdStudente((String) data.get("idStudente"));
        if(data.get("idCorso") != null)
        	regCourse.setIdCorso((Long) data.get("idCorso"));
        if(data.get("status") != null)
	        regCourse.setStatus((String) data.get("status"));
	    return regCourse;
	}
	
	public Long createAndStoreIscrizioneCorso(Hashtable data) throws Exception {
		IscrizioneCorso regCourse = this.hashToIscrizioneCorso(null, data);
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(regCourse);
        session.getTransaction().commit();
        return regCourse.getId();
    }
	
	public boolean updateIscrizioneCorso(Long idIscrizioneCorso, Hashtable data) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        IscrizioneCorso regCourse = (IscrizioneCorso) session.load(IscrizioneCorso.class, idIscrizioneCorso);
        regCourse = this.hashToIscrizioneCorso(regCourse, data);
        session.update(regCourse);
        tx.commit();
        return tx.wasCommitted();
    }
	
	@SuppressWarnings("unchecked")
	public List<PrenotazioneEsame> listPrenotazioneEsame() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PrenotazioneEsame> result = session.createQuery("from PrenotazioneEsame").list();
        session.getTransaction().commit();
        return result;
    }
	
	private PrenotazioneEsame hashToPrenotazioneEsame(PrenotazioneEsame resExam, Hashtable data) {
		if(resExam == null)
			resExam = new PrenotazioneEsame();
    	if(data.get("idStudente") != null)
    		resExam.setIdStudente((String) data.get("idStudente"));
    	if(data.get("idEsame") != null)
    		resExam.setIdEsame((Long) data.get("idEsame"));
    	if(data.get("dataEsame") != null)
    		resExam.setDataEsame((Date) data.get("dataEsame"));
    	if(data.get("dataPrenotazione") != null)
    		resExam.setDataPrenotazione((Date) data.get("dataPrenotazione"));
    	if(data.get("presenzaEsame") != null)
    		resExam.setPresenzaEsame((Boolean) data.get("presenzaEsame"));
    	if(data.get("votoEsame") != null)
    		resExam.setVotoEsame((String) data.get("votoEsame"));
    	if(data.get("esaminatore") != null)
    		resExam.setEsaminatore((String) data.get("esaminatore"));
    	if(data.get("votoAccettato") != null)
    		resExam.setVotoAccettato((Boolean) data.get("votoAccettato"));
    	if(data.get("nota") != null)
    		resExam.setNota((String) data.get("nota"));
    	if(data.get("status") != null)
	        resExam.setStatus((String) data.get("status"));
	    return resExam;
	}
	
	public Long createAndStorePrenotazioneEsame(Hashtable data) throws Exception {
		PrenotazioneEsame resExam = this.hashToPrenotazioneEsame(null, data);
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(resExam);
        session.getTransaction().commit();
        return resExam.getId();
    }
	
	public boolean updatePrenotazioneEsame(Long idPrenotazioneEsame, Hashtable data) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        PrenotazioneEsame resExam = (PrenotazioneEsame) session.load(PrenotazioneEsame.class, idPrenotazioneEsame);
        resExam = this.hashToPrenotazioneEsame(resExam, data);
        session.update(resExam);
        tx.commit();
        return tx.wasCommitted();
    }
	
	@SuppressWarnings("unchecked")
	public List<Progetto> listProgetto() throws Exception {
        Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Progetto> result = session.createQuery("from Progetto").list();
        session.getTransaction().commit();
        return result;
    }
	
	private Progetto hashToProgetto(Progetto aProject, Hashtable data) {
		if(aProject == null)
			aProject = new Progetto();
		if(data.get("idCorso") != null)
        	aProject.setIdCorso((Long) data.get("idCorso"));
        if(data.get("titolo") != null)
        	aProject.setTitolo((String) data.get("titolo"));
        if(data.get("maxUploadPerStudente") != null)
        	aProject.setMaxUploadPerStudente((Integer) data.get("maxUploadPerStudente"));
        if(data.get("maxDimGruppo") != null)
        	aProject.setMaxDimGruppo((Integer) data.get("maxDimGruppo"));
        if(data.get("dataConsegna") != null)
        	aProject.setDataConsegna((Date) data.get("dataConsegna"));
        if(data.get("status") != null)
	        aProject.setStatus((String) data.get("status"));
	    return aProject;
	}
	
	public Long createAndStoreProgetto(Hashtable data) throws Exception {
		Progetto aProject = this.hashToProgetto(null, data);
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(aProject);
        session.getTransaction().commit();
        return aProject.getId();
    }
	
	public boolean updateProgetto(Long idProgetto, Hashtable data) throws Exception {
		Session session = DAOFactory.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Progetto aProject = (Progetto) session.load(Progetto.class, idProgetto);
        aProject = this.hashToProgetto(aProject, data);
        session.update(aProject);
        tx.commit();
        return tx.wasCommitted();
    }
}
