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

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public DisciplineType getDisciplineType() {
        return disciplineType;
    }

    public void setDisciplineType(DisciplineType disciplineType) {
        this.disciplineType = disciplineType;
    }

    public int getEventPosition() {
        return eventPosition;
    }

    public void setEventPosition(int eventPosition) {
        this.eventPosition = eventPosition;
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
