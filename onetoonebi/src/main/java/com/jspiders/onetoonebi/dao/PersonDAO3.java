package com.jspiders.onetoonebi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetoonebi.dto.AadharDTO;
import com.jspiders.onetoonebi.dto.PersonDTO;

public class PersonDAO3 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		openConnection();
		entityTransaction.begin();
		PersonDTO person=entityManager.find(PersonDTO.class, 2);
		person.setName("Rahul");
		person.setEmail("rahul@gmail.com");
		person.setMobile(9196210620l);
		AadharDTO aadhar=entityManager.find(AadharDTO.class, 2);
		aadhar.setAadharNumber(898767684567l);
		entityManager.persist(aadhar);
		entityManager.persist(person);
		entityTransaction.commit();
		closeConnection();
	}
	private static void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("person");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
		
	}
	private static void closeConnection() {
	    if(entityManagerFactory!=null) {
	    	entityManagerFactory.close();
	    }
	    if(entityManager!=null) {
	    	entityManager.close();
	    }
	    if(entityTransaction!=null) {
	    	if(entityTransaction.isActive()) {
	    		entityTransaction.rollback();
	    	}
	    }
	}

}
