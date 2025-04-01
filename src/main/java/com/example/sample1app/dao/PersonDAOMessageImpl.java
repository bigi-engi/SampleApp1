package com.example.sample1app.dao;

import org.springframework.stereotype.Repository;

import com.example.sample1app.entity.MessageEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import com.example.sample1app.entity.MessageEntity;
import java.util.List;

@Repository
public class PersonDAOMessageImpl implements MessageDAO<MessageEntity>{
	public static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public PersonDAOMessageImpl() {
		super();
	}
	
	@Override
	public List<MessageEntity> getAll(){
		List<MessageEntity> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MessageEntity> query = builder.createQuery(MessageEntity.class);
		Root<MessageEntity> root = query.from(MessageEntity.class);
		query.select(root).orderBy(builder.desc(root.get("datetime")));
		list =(List<MessageEntity>)entityManager.createQuery(query).getResultList();
		return list;
	}
	
	@Override
	public List<MessageEntity> getPage(int page,int limit) {
		int offset=page*limit; //取り出す位置の指定
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MessageEntity> query=builder.createQuery(MessageEntity.class);
		Root<MessageEntity> root=query.from(MessageEntity.class);
		query.select(root);
		return (List<MessageEntity>)entityManager.createQuery(query).setFirstResult(offset)
				.setMaxResults(limit).getResultList();				
	}
	@Override
	public MessageEntity findById(long id) {
		return (MessageEntity)entityManager.createQuery("from MessageEntity where id="+id)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageEntity> findByName(String name){
		return (List<MessageEntity>)entityManager.createQuery("from MessageEntity where name ="+name)
				.getResultList();
	}
	@Override
	public List<MessageEntity> find(String fstr){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<MessageEntity> query=builder.createQuery(MessageEntity.class);
		Root<MessageEntity> root = query.from(MessageEntity.class);
		query.select(root).where(builder.equal(root.get("content"),fstr));
		List<MessageEntity> list = null;
		list = (List<MessageEntity>)entityManager.createQuery(query).getResultList();
		return list;
	}
}
