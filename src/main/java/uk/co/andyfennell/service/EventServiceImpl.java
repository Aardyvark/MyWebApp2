package uk.co.andyfennell.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import uk.co.andyfennell.model.hibernate.domain.Event;
import uk.co.andyfennell.model.hibernate.domain.EventDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    static Logger LOG = LoggerFactory.getLogger(EventServiceImpl.class);
    private EventDao eventDao;
    
    public EventDao getEventDao() {
        return eventDao;
    }
    
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }
    
    @Transactional(readOnly = false)
    public void saveEvent(Event event) {
        System.out.println("EventService addEvent 1");
        //assert TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("EventService addEvent 2");
        
        LOG.debug("saveEvent() before insert at debug");        
        eventDao.saveOrUpdate(event);
        System.out.println("EventService addEvent 3 title:" + event.getTitle());
    }
    
    public List<Event> fetchAllEvents() {
        LOG.debug("fetchAllEvents() before selectAll() at debug");        
        LOG.info("fetchAllEvents() before selectAll() at info");        
        return eventDao.selectAll();
    }

    public Event getEvent(Long id) {
        return eventDao.getById(id);
    }
    
    @Transactional(readOnly = false)    
    public void addTwoEvents() {
        Event event = new Event();
        event.setTitle("Add 1st event");
        eventDao.saveOrUpdate(event);
        
        Event event2 = new Event();
        event2.setTitle("Add 2nd event");
        eventDao.saveOrUpdate(event2);        
        
        //try {
            // create an exception
            int a = 1 / 0;
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}
    }

}
