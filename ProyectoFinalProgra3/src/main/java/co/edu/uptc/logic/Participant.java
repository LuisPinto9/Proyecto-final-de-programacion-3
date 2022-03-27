package co.edu.uptc.logic;

import java.util.ArrayList;

public class Participant {

    private String name;
    private String id;
    private ArrayList<Event> events;

    public Participant(String name, String id, ArrayList<Event> events) {
        this.name = name;
        this.id = id;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void addEvents(Event event){
        this.events.add(event);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", events=" + events +
                '}';
    }
}
