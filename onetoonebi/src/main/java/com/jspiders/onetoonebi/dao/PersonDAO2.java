package com.jspiders.onetoonebi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetoonebi.dto.AadharDTO;
import com.jspiders.onetoonebi.dto.PersonDTO;

public class PersonDAO2 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		openConnection();
		entityTransaction.begin();
		PersonDTO person=entityManager.find(PersonDTO.class, 2);
		entityManager.remove(person);
		AadharDTO aadhar=entityManager.find(AadharDTO.class, 2);
		entityManager.remove(aadhar);
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
