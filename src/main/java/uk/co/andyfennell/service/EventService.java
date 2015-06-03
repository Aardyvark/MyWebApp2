package uk.co.andyfennell.service;

import java.util.List;

import uk.co.andyfennell.model.hibernate.domain.Event;
import uk.co.andyfennell.model.hibernate.domain.EventDao;

public interface EventService {
    public EventDao getEventDao();
    public void setEventDao(EventDao eventDao);    
    public void saveEvent(Event event);
    public Event getEvent(Long id);
    public List<Event> fetchAllEvents();
    
    public void addTwoEvents();
}
