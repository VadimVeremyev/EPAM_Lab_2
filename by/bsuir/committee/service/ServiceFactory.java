package by.bsuir.committee.service;

import by.bsuir.committee.entity.Enrollee;

public class ServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();

    public static ServiceFactory getInstance() {

        return ourInstance;
    }

    private final Service<Enrollee> UserService = userService.getInstance();

    public Service<Enrollee> getUserService() {

        return UserService;
    }

    private ServiceFactory() {
    }
}
