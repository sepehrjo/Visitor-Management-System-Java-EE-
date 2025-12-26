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
public class VisitorRequestFacade extends AbstractFacade<VisitorRequest> {

    @PersistenceContext(unitName = "Test1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VisitorRequestFacade() {
        super(VisitorRequest.class);
    }
    //additional
    public void create(VisitorRequest request) {
        em.persist(request);
       
    }
    
    public VisitorRequest find(Object id) {
        return em.find(VisitorRequest.class, id);
    }
    
    public void edit(VisitorRequest request) {
        em.merge(request);
    }
    
  public List<VisitorRequest> findByResident(Member resident) {
    return em.createQuery(
        "SELECT vr FROM VisitorRequest vr " +
        "LEFT JOIN FETCH vr.resident " + 
        "WHERE vr.resident.id = :residentId", 
        VisitorRequest.class
    )
    .setParameter("residentId", resident.getId())
    .getResultList();
}
    
    
    public VisitorRequest findByVisitorName(String visitorName) {
        TypedQuery<VisitorRequest> query = em.createQuery(
            "SELECT vr FROM VisitorRequest vr WHERE vr.visitorName = :visitorName", 
            VisitorRequest.class
        );
        query.setParameter("visitorName", visitorName);
        
        List<VisitorRequest> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0); // Return the first match or null
    }
    
    
    public List<VisitorRequest> findAll() {
        return em.createQuery("SELECT vr FROM VisitorRequest vr", VisitorRequest.class)
                 .getResultList();
    }
    
   

   
    
    
}
