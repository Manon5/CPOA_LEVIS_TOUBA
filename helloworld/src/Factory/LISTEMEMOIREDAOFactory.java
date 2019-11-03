package Factory;

import DAO.AbonnementDAO;
import DAO.ClientDAO;
import DAO.PeriodiciteDAO;
import DAO.RevueDAO;
import Liste_Memoire.ListeMemoireAbonnementDAO;
import Liste_Memoire.ListeMemoireClientDAO;
import Liste_Memoire.ListeMemoirePeriodiciteDAO;
import Liste_Memoire.ListeMemoireRevueDAO;

public class LISTEMEMOIREDAOFactory extends DAOFactory{

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return ListeMemoireAbonnementDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return ListeMemoireClientDAO.getInstance();
	}

	@Override
	public RevueDAO getRevueDAO() {
		return ListeMemoireRevueDAO.getInstance();
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return ListeMemoirePeriodiciteDAO.getInstance();
	}

}
