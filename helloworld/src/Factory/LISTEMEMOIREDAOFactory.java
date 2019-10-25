package Factory;

import DAO.AbonnementDAO;
import DAO.ClientDAO;
import DAO.PeriodiciteDAO;
import DAO.RevueDAO;
import Liste_Memoire.ListeMemoireAbonnementDAO;

public class LISTEMEMOIREDAOFactory extends DAOFactory{

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return ListeMemoireAbonnementDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public RevueDAO getRevueDAO() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
