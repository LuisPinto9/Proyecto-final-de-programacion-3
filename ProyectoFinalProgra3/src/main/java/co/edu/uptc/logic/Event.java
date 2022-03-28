package co.edu.uptc.logic;

public class Event {

    private String eventName;
    private String discipline;
    private DisciplineType disciplineType;
    private int eventPosition;

    public Event(String eventName, String discipline, DisciplineType disciplineType, int eventPosition) {
        this.eventName = eventName;
        this.discipline = discipline;
        this.disciplineType = disciplineType;
        this.eventPosition = eventPosition;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public DisciplineType getDisciplineType() {
        return disciplineType;
    }

    public int getEventPosition() {
        return eventPosition;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", discipline='" + discipline + '\'' +
                ", disciplineType=" + disciplineType +
                ", eventPosition=" + eventPosition +
                '}';
    }
}
