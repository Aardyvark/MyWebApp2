package uk.co.andyfennell.model.hibernate.domain;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class EventDao extends HibernateDaoSupport {

    public void saveOrUpdate(Event event) {
        assert TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("EventDao saveOrUpdate before");
        //getHibernateTemplate().save(event);
        getHibernateTemplate().saveOrUpdate(event);
        System.out.println("EventDao saveOrUpdate after title:" + event.getTitle());
    }
    
    public List<Event> selectAll() {
        //getHibernateTemplate().getSessionFactory().getCurrentSession();        
        DetachedCriteria criteria = DetachedCriteria.forClass(Event.class);
        return (List<Event>) getHibernateTemplate().findByCriteria(criteria);
    }
    
    public Event getById(Long id) {
        //getHibernateTemplate().getSessionFactory().getCurrentSession();
        return getHibernateTemplate().get(Event.class, id);
    }
}
