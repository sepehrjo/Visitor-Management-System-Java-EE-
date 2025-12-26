/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author sepehrjokanian
 */
@Stateless
public class MemberFacade extends AbstractFacade<Member> {

    @PersistenceContext(unitName = "Test1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemberFacade() {
        super(Member.class);
    }
    public Member findByName(String name) {
    TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class);
    query.setParameter("name", name);
    List<Member> members = query.getResultList();
    if(!members.isEmpty()){
        return members.get(0);
    }
    return null;
}
    public void update(Member member) {
        em.merge(member);
    }
    
    
    
    // Add these methods to MemberFacade.java
public List<Member> findByRole(String role) {
    TypedQuery<Member> query = em.createQuery(
        "SELECT m FROM Member m WHERE m.role = :role", 
        Member.class);
    query.setParameter("role", role);
    return query.getResultList();
}

public List<Member> searchByName(String name) {
    TypedQuery<Member> query = em.createQuery(
        "SELECT m FROM Member m WHERE LOWER(m.name) LIKE LOWER(:name)", 
        Member.class);
    query.setParameter("name", "%" + name + "%");
    return query.getResultList();
}

public void delete(Long id) {
    Member member = em.find(Member.class, id);
    if(member != null) {
        em.remove(member);
    }
}
public List<Member> findAllActive() {
        return em.createQuery("SELECT m FROM Member m", Member.class)
                 .getResultList();
    }
    
}
