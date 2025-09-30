/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sepehrjokanian
 */
@Stateless
public class SecurityVisitorRecordFacade extends AbstractFacade<SecurityVisitorRecord> {

    @PersistenceContext(unitName = "Test1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SecurityVisitorRecordFacade() {
        super(SecurityVisitorRecord.class);
    }
    
    
    //additional
    public void create(SecurityVisitorRecord record) {
        em.persist(record);
    }
    //additional
    
}
