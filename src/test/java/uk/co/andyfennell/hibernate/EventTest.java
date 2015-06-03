package uk.co.andyfennell.hibernate;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;

import uk.co.andyfennell.hibernate.util.HibernateUtil;
import uk.co.andyfennell.model.hibernate.domain.Event;

public class EventTest {

    @Test
    public void testCreateAndStoreEvent()  {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
            try {
        
                session.beginTransaction();
        
                Calendar calendar = Calendar.getInstance();
                // Just want whole seconds
                calendar.clear(Calendar.MILLISECOND);
                java.util.Date now = calendar.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
                
                Event event = new Event();
                event.setTitle("Test Event");
                event.setDate(currentTimestamp);
                session.save(event);
                Long id = event.getId();
        
                session.getTransaction().commit();

                // Now test to see what has been written to database
                
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                event = (Event)session.get(Event.class, id);
                
                assertEquals("Test Event", event.getTitle());
                
                assertEquals(currentTimestamp, event.getDate());
                
                session.getTransaction().commit();
        
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
