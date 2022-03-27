package test.co.edu.uptc.persistence;


import co.edu.uptc.logic.DisciplineType;
import co.edu.uptc.logic.Event;
import co.edu.uptc.logic.Participant;
import co.edu.uptc.persistence.ParticipantDAOFactory;
import org.junit.Test;

import java.util.ArrayList;

public class ParticipantDAOImplTest {

    private final ParticipantDAOFactory factory = new ParticipantDAOFactory();

    @Test
    public void addStudent() {

        ArrayList<Event> events = new ArrayList<>();
        Event e = new Event("Interescuelas", "Futbol", DisciplineType.Grupal, 2);
        Event e2 = new Event("Interescuelas", "Baloncesto", DisciplineType.Grupal, 7);

        events.add(e);
        events.add(e2);
        Participant p = new Participant("Luis", "12345", events);
        factory.createParticipantDAO().addParticipant(p);
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByCode() {
    }

    @Test
    public void getParticipants() {
        factory.createParticipantDAO().getParticipants()
                .forEach(System.out::println);
    }

} 
