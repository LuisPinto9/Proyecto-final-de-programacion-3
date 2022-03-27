package co.edu.uptc.proyectofinalprogra3;

import co.edu.uptc.logic.Participant;
import co.edu.uptc.persistence.ParticipantDAOFactory;
import co.edu.uptc.persistence.ParticipantDAOImpl;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "Servlet", value = "/servlet-control")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParticipantDAOFactory factory = new ParticipantDAOFactory();
        Gson gson = new Gson();
        int option = Integer.parseInt(request.getParameter("option"));

        switch (option) {
            case 1:

                response.setContentType("text/json");
                ArrayList<Participant> participants = factory.createParticipantDAO().getParticipants();
                String stAux = gson.toJson(participants);

                try (
                        PrintWriter out = response.getWriter();
                ) {
                    out.println(stAux);
                }

                break;
            case 2:

                
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
