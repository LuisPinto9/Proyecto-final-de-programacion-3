package co.edu.uptc.persistence;

import co.edu.uptc.logic.DisciplineType;
import co.edu.uptc.logic.Event;
import co.edu.uptc.logic.Participant;

import java.sql.*;
import java.util.ArrayList;

public class ParticipantDAOImpl implements ParticipantDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB = "deportive_club";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "adminClub";
    private static final String PASSWORD = "12345";

    @Override
    public void addParticipant(Participant participant) {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(URL + DB, USER, PASSWORD)) {
            Statement statement = connection.createStatement();

            String id = participant.getId();
            String name = participant.getName();

            ArrayList<Event> events = participant.getEvents();

            final String query = "INSERT INTO participants VALUES(" + "'" + id + "','" + name + "')";
            statement.execute(query);

            for (Event event : events) {
                String discpilineType = event.getDisciplineType().equals(DisciplineType.Grupal) ? "Grupal" : "Individual";

                PreparedStatement ps = connection.prepareStatement("INSERT INTO events (event_name,discipline,discipline_type,ref_participant,event_position) VALUES (?,?,?,?,?)");
                ps.setString(1, event.getEventName());
                ps.setString(2, event.getDiscipline());
                ps.setString(3, discpilineType);
                ps.setString(4, id);
                ps.setInt(5, event.getEventPosition());
                ps.executeUpdate();

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateParticipant(Participant participant) {

    }

    @Override
    public void deleteParticipant(String id2) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Participant participant = findById(id2);
        try (Connection connection = DriverManager.getConnection(URL + DB, USER, PASSWORD)) {
            Statement statement2 = connection.createStatement();
            String id3 = participant.getId();
            String query = "DELETE from participants WHERE participants . id=" + "'" + id3 + "'  ";
            String query2 = "DELETE from events WHERE ref_participant=" + "'" + id3 + "'  ";
            statement2.execute(query2);
            statement2.execute(query);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }}

    @Override
    public Participant findById(String id) {
        if (getParticipants()!= null){
            for (Participant participant: getParticipants()) {
                if (participant.getId().equals(id)){
                    return participant;
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<Participant> getParticipants() {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Participant> participants = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select * from participants")
        ) {

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");

                Participant participant = new Participant(name, id, getEvents(id));
                participants.add(participant);
            }
            return participants;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    private ArrayList<Event> getEvents(String id) {

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Event> events = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL + DB, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("select * from events")
        ) {
            while (result.next()) {
                if (result.getString("ref_participant").equals(id)) {
                    DisciplineType disciplineType = result.getString("discipline_type").equals("Grupal") ? DisciplineType.Grupal : DisciplineType.Individual;
                    Event e = new Event(result.getString("event_name"), result.getString("discipline"), disciplineType, result.getInt("event_position"));
                    events.add(e);
                }
            }
            return events;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
