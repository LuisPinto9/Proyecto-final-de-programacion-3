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

                connection.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateParticipant(Participant participant) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Participant findById(String id) {
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
            connection.close();
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
            connection.close();
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
