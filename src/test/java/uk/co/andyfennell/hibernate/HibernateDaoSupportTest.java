package uk.co.andyfennell.hibernate;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uk.co.andyfennell.model.hibernate.domain.Event;
import uk.co.andyfennell.service.EventService;
import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateDaoSupportTest {
    static Logger LOG = LoggerFactory.getLogger(HibernateDaoSupportTest.class);

    @Test
    public void testCreate() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        
        //URL[] urls = ((URLClassLoader)cl).getURLs();

        URL[] urls = ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();
        
        LOG.info("ClassLoader urls:" + urls.length);
        for(URL url: urls){
           LOG.info("{}", url.getFile());
        }        
        
        LOG.trace("testCreate() trace");
        LOG.debug("testCreate() debug");
        LOG.info("testCreate() info");
        LOG.warn("testCreate() warn");
        LOG.error("testCreate() error");
        
        ApplicationContext ctx = null;
        
        try {
        // Picks up the context from /src/main/resources    
        //ApplicationContext context = new ClassPathXmlApplicationContext("/test-application-context.xml");
        
            try {
                ctx = new ClassPathXmlApplicationContext("/root.xml");
        
                EventService eventService = (EventService) ctx.getBean("eventService");
        
                Event event = new Event();
                event.setTitle("Event DaoSupport");
        
                Calendar calendar = Calendar.getInstance();
                // Just want whole seconds
                calendar.clear(Calendar.MILLISECOND);
                java.util.Date now = calendar.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        
                event.setDate(currentTimestamp);
        
                eventService.saveEvent(event);
        
                Event event2 = new Event();
                event2.setTitle("Event 2");
                eventService.saveEvent(event2);
                
                List<Event> events = eventService.fetchAllEvents();
                for (Event tmp : events) {
                    System.out.println("Id:" + tmp.getId() + " Title:" + tmp.getTitle() + " Date:" + tmp.getDate());
                }
                
                // events shouldn't be null
                assertNotNull(events);
                // Should have 2 events
                assertEquals(2, events.size());
                // Test title is set as expected
                assertEquals("Event DaoSupport", events.get(0).getTitle());
                
                event = eventService.getEvent((long) 1);
                assertNotNull(event);
                assertEquals("Event DaoSupport", event.getTitle());
                
                event.setTitle("Amended Event 1");
                eventService.saveEvent(event);

                event = eventService.getEvent((long) 2);
                assertEquals("Event 2", event.getTitle());
                
                event = eventService.getEvent((long) 1);
                assertNotNull(event);
                assertEquals("Amended Event 1", event.getTitle());
                
                // Test transaction and catch the exception so can then list out events
                try {
                    eventService.addTwoEvents();
                } catch (Exception e) {
                    System.out.println("Exception:" + e.getMessage());
                }

                System.out.println("After addTwoEvents()");
                
                // Shouldn't see the two events that got rolled backed
                System.out.println("Listing events:");
                events = eventService.fetchAllEvents();
                for (Event tmp : events) {
                    System.out.println("Id:" + tmp.getId() + " Title:" + tmp.getTitle() + " Date:" + tmp.getDate());
                }
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                fail(e.getMessage());
            }
            
        } finally {
            if (ctx != null) {
                ((ConfigurableApplicationContext) ctx).close();
            }
        }
    }

}
