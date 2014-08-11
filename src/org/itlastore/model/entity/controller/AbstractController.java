package org.itlastore.model.entity.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import org.itlastore.model.EM;
import org.itlastore.model.entity.controller.exceptions.NonexistentEntityException;

/**
 *
 * @author Diego Ivan Perez Michel <ivanevil31@gmail.com>
 * @param <T>
 */
public abstract class AbstractController<T> {

    private final Class<T> entityClass;

    public AbstractController(Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager()
    {
        return EM.getInstance().createEntityManager();
    }

    public void create(T entity)
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void edit(T entity) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
                if (id == null)
                {
                    throw new NonexistentEntityException("The entity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            T entity;
            try
            {
                entity = em.getReference(entityClass, id);

            } catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The entity with id " + id + " no longer exists.", enfe);
            }
            em.remove(entity);
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<T> findEntities()
    {
        return findEntities(true, -1, -1);
    }

    public List<T> findEntities(int maxResults, int firstResult)
    {
        return findEntities(false, maxResults, firstResult);
    }

    private List<T> findEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = em.createQuery(cq);
            if (!all)
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    public T find(Long id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(entityClass, id);
        } finally
        {
            em.close();
        }
    }

    public T findByCodigo(String codigo)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityClass);
            Root<T> root = cq.from(entityClass);
            cq.select(root);
            ParameterExpression<String> params = cb.parameter(String.class);
            cq.where(cb.equal(root.get("codigo"),params));
            TypedQuery<T> query = em.createQuery(cq);
            query.setParameter(params, codigo);
            
            return query.getSingleResult();
        } finally
        {
            em.close();
        }
    }

    public List<T> findAll()
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range)
    {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally
        {
            em.close();
        }
    }

}
