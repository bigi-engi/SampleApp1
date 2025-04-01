package com.example.sample1app.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.sample1app.entity.PersonEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class PersonDAOPersonImpl implements PersonDAO<PersonEntity>{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public PersonDAOPersonImpl() {
		super();
	}
	
	
	@Override
	public List<PersonEntity> getPage(int page,int limit){
		int offset= page * limit;	//取り出す位置
		List<PersonEntity> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonEntity> query = builder.createQuery(PersonEntity.class);
		Root<PersonEntity> root = query.from(PersonEntity.class);
		query.select(root);
		return (List<PersonEntity>)entityManager.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
//		query.select(root).orderBy(builder.asc(root.get("name")));
//		list = (List<PersonEntity>)entityManager.createQuery(query).getResultList();
//		return list;
	}
	
	@Override
	public List<PersonEntity> getAll(){
		Query query = entityManager.createQuery("from PersonEntity");
		@SuppressWarnings("unchecked")
		List<PersonEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}
	
	@Override
	public List<PersonEntity> getAll2(){
		List<PersonEntity> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonEntity> query = builder.createQuery(PersonEntity.class);
		Root<PersonEntity> root = query.from(PersonEntity.class);
		query.select(root).orderBy(builder.asc(root.get("name")));
		list = (List<PersonEntity>)entityManager.createQuery(query).getResultList();
		return list;		
	}
	
	@Override
	public PersonEntity findById(long id) {
		return (PersonEntity)entityManager.createQuery("from PersonEntity where id =" + id).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<PersonEntity> findByName(String name){
		return (List<PersonEntity>)entityManager.createQuery("from PersonEntity where name = '" + name + "'").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonEntity> findByName2(String fstr){
		List<PersonEntity> list = null;
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonEntity> query = builder.createQuery(PersonEntity.class);
		Root<PersonEntity> root = query.from(PersonEntity.class);
		query.select(root).where(builder.equal(root.get("name"), fstr));
		list = (List<PersonEntity>)entityManager.createQuery(query).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonEntity> find2(String fstr){
		List<PersonEntity> list = null;
		String qstr = "from PersonEntity where id = ?1 or name like ?2 or mail like ?3";
		Long fid = 0L;
		try {
			fid=Long.parseLong(fstr);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		Query query = entityManager.createQuery(qstr)
				.setParameter(1,fid)
				.setParameter(2, "%"+fstr+"%")
				.setParameter(3,fstr+"%@%");
		list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonEntity> find3(String fstr){
		List<PersonEntity> list = null;
		Query query = entityManager.createNamedQuery("findWithName")
				.setParameter("fname", "%"+fstr+"%");
		list = query.getResultList();
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonEntity> findByAge(int min,int max){
		return (List<PersonEntity>)entityManager
				.createNamedQuery("findByAge")
				.setParameter("min", min)
				.setParameter("max",max)
				.getResultList();
	}
}
