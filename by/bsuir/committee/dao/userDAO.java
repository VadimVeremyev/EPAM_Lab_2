package by.bsuir.committee.dao;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.committee.entity.enrollee;

public class userDAO implements DAO<enrollee>{

    private static userDAO ourInstance = new userDAO();

    static userDAO getInstance() {
        return ourInstance;
    }
    
    private userDAO() {
    }

    private List<enrollee> enrolleeList = new ArrayList<>();
	
	@Override
	public void delete(enrollee obj) {
        for (enrollee enrollee : enrolleeList) {
            if (enrollee.equals(obj)) {
            	enrolleeList.remove(obj);
                return;
            }
        }	
	}

	@Override
	public void add(enrollee obj) {
		boolean inList = false;
        if (obj != null) {
        	for (enrollee enrollee : enrolleeList) {
            	if (enrollee.equals(obj)) {
                	inList = true;
            	}
        	}
        	if(!inList)
        		enrolleeList.add(obj);
        }
		
	}

	@Override
	public enrollee get(String id) {
        for (enrollee enrollee : enrolleeList) {
            if (enrollee.getId() == Integer.parseInt(id)) {
                return enrollee;
            }
        }
		return null;
	}

	@Override
	public void addAll(List<enrollee> list) {
        if (list != null) {
        	enrolleeList.addAll(list);
        }
	}

	@Override
	public List<enrollee> getAll() {
		return enrolleeList;
	}
}
