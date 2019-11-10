package by.bsuir.committee.dao;

import by.bsuir.committee.entity.Enrollee;

public class DAOFactory {
    private static DAOFactory ourInstance = new DAOFactory();

    public static DAOFactory getInstance() {

        return ourInstance;
    }

    private final DAO<Enrollee> DaoUser = UserDAO.getInstance();

    public DAO<Enrollee> getDAOUser() {

        return DaoUser;
    }

    private DAOFactory() {
    }
}