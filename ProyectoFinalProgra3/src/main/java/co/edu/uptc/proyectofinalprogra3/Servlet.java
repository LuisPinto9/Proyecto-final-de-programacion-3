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

    private ParticipantDAOFactory factory = new ParticipantDAOFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/json");
        ArrayList<Participant> participants = factory.createParticipantDAO().getParticipants();

        Gson gson = new Gson();
        String stAux = gson.toJson( participants );
        PrintWriter out = response.getWriter();
        out.println(participants);
        String id = request.getParameter("id");
        try(
                PrintWriter out1 = response.getWriter()
        ){
            out1.println(stAux);
        }
        request.setAttribute("participants", stAux);
        RequestDispatcher rd = request.getRequestDispatcher("script.js");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*esponse.setContentType("text/json");
        ArrayList<Participant> participants = factory.createParticipantDAO().getParticipants();
        Gson gson = new Gson();
        String stAux = gson.toJson( participants );
        PrintWriter out = response.getWriter();
        out.println(participants);
        request.setAttribute("participants", stAux);
        RequestDispatcher rd = request.getRequestDispatcher("script.js");
        rd.forward(request,response);*/
    }
}
