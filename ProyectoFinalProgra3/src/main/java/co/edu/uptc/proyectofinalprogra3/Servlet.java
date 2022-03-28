package co.edu.uptc.proyectofinalprogra3;

import co.edu.uptc.logic.DisciplineType;
import co.edu.uptc.logic.Event;
import co.edu.uptc.logic.Participant;
import co.edu.uptc.persistence.ParticipantDAOFactory;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

@WebServlet(name = "Servlet", value = "/servlet-control")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("option").equals("1")) {
            ParticipantDAOFactory factory = new ParticipantDAOFactory();
            Gson gson = new Gson();
            response.setContentType("text/json");
            ArrayList<Participant> participants = factory.createParticipantDAO().getParticipants();
            String stAux = gson.toJson(participants);

            try (
                    PrintWriter out = response.getWriter();
            ) {
                out.println(stAux);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("option").equals("2")) {


            ParticipantDAOFactory factory = new ParticipantDAOFactory();
            DisciplineType disciplineType = request.getParameter("discipline_type").equals("Grupal") ? DisciplineType.Grupal : DisciplineType.Individual;

            Event event = new Event(request.getParameter("event"), request.getParameter("discipline"), disciplineType, Integer.parseInt(request.getParameter("eventPosition")));
            ArrayList<Event> events = new ArrayList<>();
            events.add(event);
            Participant participant = new Participant(request.getParameter("name"), request.getParameter("id"), events);
            factory.createParticipantDAO().addParticipant(participant);

            try (
                    PrintWriter out = response.getWriter()
            ) {
                out.println(request.getParameter("name"));
                out.println(request.getParameter("id"));
                out.println(request.getParameter("event"));
                out.println(request.getParameter("discipline"));
                out.println(disciplineType);
                out.println(request.getParameter("eventPosition"));

            }
        }
    }
}
