package test.co.edu.uptc.persistence;


import co.edu.uptc.logic.DisciplineType;
import co.edu.uptc.logic.Event;
import co.edu.uptc.logic.Participant;
import co.edu.uptc.persistence.ParticipantDAOFactory;
import org.junit.Test;

import java.util.ArrayList;

public class ParticipantDAOImplTest {

    @Test
    public void addStudent() {

        ArrayList<Event> events = new ArrayList<>();
        Event e = new Event("Interescuelas", "Futbol", DisciplineType.Grupal, 2);
        Event e2 = new Event("Interescuelas", "Baloncesto", DisciplineType.Grupal, 7);

        events.add(e);
        events.add(e2);
        Participant p = new Participant("ana", "12", events);
        Participant p2 = new Participant("carlos", "123", events);
        ParticipantDAOFactory.createParticipantDAO().addParticipant(p);
        ParticipantDAOFactory.createParticipantDAO().addParticipant(p2);
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void delete() {
        ParticipantDAOFactory.createParticipantDAO().deleteParticipant("123");
        ParticipantDAOFactory.createParticipantDAO().deleteParticipant("12");
    }

    @Test
    public void findByCode() {
    }

    @Test
    public void getParticipants() {
        ParticipantDAOFactory.createParticipantDAO().getParticipants()
                .forEach(System.out::println);
    }

} 
