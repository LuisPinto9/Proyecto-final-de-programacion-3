package co.edu.uptc.persistence;

public class ParticipantDAOFactory {
    public static ParticipantDAO createParticipantDAO(){
        return new ParticipantDAOImpl();
    }
}
