package com.jspiders.onetoonebi.dao;


import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.onetoonebi.dto.PersonDTO;



public class PersonDAO4 {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		openConnection();
		entityTransaction.begin();
		List<PersonDTO> persons=findAll();
		for (PersonDTO person : persons) {
			System.out.println(person);
		}
		entityTransaction.commit();
		closeConnection();
	}
	@SuppressWarnings("unchecked")
	private static List<PersonDTO> findAll() {
		Query query=entityManager.createQuery("SELECT person FROM PersonDTO person");
		List<PersonDTO> persons=query.getResultList();
		return persons;
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

