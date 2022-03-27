package co.edu.uptc.persistence;

public class ParticipantDAOFactory {
    public ParticipantDAO createParticipantDAO(){
        return new ParticipantDAOImpl();
    }
}
