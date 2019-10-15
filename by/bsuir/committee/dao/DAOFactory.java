package by.bsuir.committee.dao;

import by.bsuir.committee.entity.enrollee;

public class DAOFactory {
    private static DAOFactory ourInstance = new DAOFactory();

    public static DAOFactory getInstance() {

        return ourInstance;
    }

    private final DAO<enrollee> DaoUser = userDAO.getInstance();

    public DAO<enrollee> getDAOUser() {

        return DaoUser;
    }

    private DAOFactory() {
    }
}