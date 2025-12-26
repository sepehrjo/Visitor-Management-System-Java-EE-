package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReportFacade {

    @PersistenceContext(unitName = "Test1-ejbPU")
    private EntityManager em;

    // Gender Distribution
    public List<Object[]> getGenderReport() {
        return em.createQuery(
            "SELECT COALESCE(m.gender, 'Unknown'), COUNT(m) " +
            "FROM Member m GROUP BY m.gender", Object[].class)
            .getResultList();
    }

    // Age Distribution (Simplified)
    public List<Object[]> getAgeReport() {
        return em.createQuery(
            "SELECT 'All Ages', COUNT(m) FROM Member m", Object[].class) // Temporary simple version
            .getResultList();
    }

    // Location Distribution
    public List<Object[]> getLocationReport() {
        return em.createQuery(
            "SELECT COALESCE(m.address, 'Unknown'), COUNT(m) " +
            "FROM Member m GROUP BY m.address", Object[].class)
            .getResultList();
    }

    // Role Distribution
    public List<Object[]> getRoleReport() {
        return em.createQuery(
            "SELECT m.role, COUNT(m) FROM Member m GROUP BY m.role", Object[].class)
            .getResultList();
    }

    // Visit Status
    public List<Object[]> getVisitStatusReport() {
        return em.createQuery(
            "SELECT COALESCE(vr.status, 'Unknown'), COUNT(vr) " +
            "FROM VisitorRequest vr GROUP BY vr.status", Object[].class)
            .getResultList();
    }
}
