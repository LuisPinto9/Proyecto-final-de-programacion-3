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

            Gson gson = new Gson();
            response.setContentType("text/json");
            ArrayList<Participant> participants = ParticipantDAOFactory.createParticipantDAO().getParticipants();
            String stAux = gson.toJson(participants);

            try (PrintWriter out = response.getWriter()) {
                out.println(stAux);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("option").equals("2")) {

            ArrayList<Event> events = new ArrayList<>();

            DisciplineType disciplineType = request.getParameter("disciplineType").equals("Grupal") ? DisciplineType.Grupal : DisciplineType.Individual;
            Event event1 = new Event(request.getParameter("event"), request.getParameter("discipline"), disciplineType, Integer.parseInt(request.getParameter("eventPosition")));
            events.add(event1);

            Participant participant = new Participant(request.getParameter("name"), request.getParameter("id"), events);
            ParticipantDAOFactory.createParticipantDAO().addParticipant(participant);

            try (PrintWriter out = response.getWriter()) {
                out.println(request.getParameter("name"));
                out.println(request.getParameter("id"));
                out.println(request.getParameter("discipline"));
                out.println(disciplineType);
                out.println(request.getParameter("event"));
                out.println(request.getParameter("eventPosition"));
            }
        }else if(request.getParameter("option").equals("3")){

            ParticipantDAOFactory.createParticipantDAO().deleteParticipant(request.getParameter("id"));

            try (PrintWriter out = response.getWriter()) {
                out.println(request.getParameter("id"));
            }
        }
    }
}
