package Factory;

import DAO.*;
import DAO.Persistance;

public abstract class DAOFactory {
	
	
	public static DAOFactory getDAOfactory(Persistance cible) {
		
		DAOFactory daoF = null;
		switch(cible) {
		case MYSQL:
			daoF = new MYSQLDAOFactory();
			break;
		case LISTE_MEMOIRE:
			daoF = new LISTEMEMOIREDAOFactory();
			break;
		}
		return daoF;
	}
	
	public abstract AbonnementDAO getAbonnementDAO();
	public abstract ClientDAO getClientDAO();
	public abstract RevueDAO getRevueDAO();
	public abstract PeriodiciteDAO getPeriodiciteDAO();
}
