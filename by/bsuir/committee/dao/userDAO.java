package by.bsuir.committee.dao;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.committee.entity.Enrollee;

public class UserDAO implements DAO<Enrollee>{

    private static UserDAO ourInstance = new UserDAO();

    static UserDAO getInstance() {
        return ourInstance;
    }
    
    private UserDAO() {
    }

    private List<Enrollee> enrolleeList = new ArrayList<>();
	
	@Override
	public void delete(Enrollee obj) {
        for (Enrollee enrollee : enrolleeList) {
            if (enrollee.equals(obj)) {
            	enrolleeList.remove(obj);
                return;
            }
        }	
	}

	@Override
	public void add(Enrollee obj) {
		boolean inList = false;
        if (obj != null) {
        	for (Enrollee enrollee : enrolleeList) {
            	if (enrollee.equals(obj)) {
                	inList = true;
            	}
        	}
        	if(!inList)
        		enrolleeList.add(obj);
        }
		
	}

	@Override
	public Enrollee get(String id) {
        for (Enrollee enrollee : enrolleeList) {
            if (enrollee.getId() == Integer.parseInt(id)) {
                return enrollee;
            }
        }
		return null;
	}

	@Override
	public void addAll(List<Enrollee> list) {
        if (list != null) {
        	enrolleeList.addAll(list);
        }
	}

	@Override
	public List<Enrollee> getAll() {
		return enrolleeList;
	}
}
