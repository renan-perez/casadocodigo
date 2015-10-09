package org.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.models.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Product product) {
		entityManager.persist(product);
	}
	
	public void save(List<Product> products) {
		for (Product product : products) {
			entityManager.persist(product);
		}
	}
	
}
