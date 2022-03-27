package co.edu.uptc.persistence;

import co.edu.uptc.logic.Participant;

import java.util.ArrayList;

public interface ParticipantDAO extends java.lang.AutoCloseable{
    void addParticipant(Participant participant);
    void updateParticipant(Participant participant);
    void delete(String id);
    Participant findById(String id);
    ArrayList<Participant> getParticipants();
}
