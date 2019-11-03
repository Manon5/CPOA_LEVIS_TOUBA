package Factory;

import DAO.AbonnementDAO;
import DAO.ClientDAO;
import DAO.PeriodiciteDAO;
import DAO.RevueDAO;
import MySQL.MySQLAbonnementDAO;
import MySQL.MySQLClientDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;

public class MYSQLDAOFactory extends DAOFactory{

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return MySQLAbonnementDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
	}

	@Override
	public RevueDAO getRevueDAO() {
		return MySQLRevueDAO.getInstance();
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return MySQLPeriodiciteDAO.getInstance();
	}
	
}
