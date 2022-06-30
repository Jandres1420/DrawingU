package co.edu.components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import co.edu.escuelaing.interactiveblackboard.endpoints.BBEndpoint;
@Component
@Scope("singleton")
public class TimedMessageBroker {

    /* Formato fecha */
    private static final SimpleDateFormat dateFormat = new
            SimpleDateFormat("HH:mm:ss");
    private static final Logger logger =
            Logger.getLogger(TimedMessageBroker.class.getName());

    @Scheduled(fixedRate = 5000)
    public void broadcast() {
        logger.log(Level.INFO, "broadcastingMessages");
        /* Usando la clase statica por ya ser singleton que envie la hora */
       // BBEndpoint.send("The time is now " + dateFormat.format(new Date()));
    }
}