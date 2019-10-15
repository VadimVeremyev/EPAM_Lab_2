package by.bsuir.committee.service;

import by.bsuir.committee.entity.enrollee;

public class ServiceFactory {
    private static ServiceFactory ourInstance = new ServiceFactory();

    public static ServiceFactory getInstance() {

        return ourInstance;
    }

    private final Service<enrollee> UserService = userService.getInstance();

    public Service<enrollee> getUserService() {

        return UserService;
    }

    private ServiceFactory() {
    }
}
