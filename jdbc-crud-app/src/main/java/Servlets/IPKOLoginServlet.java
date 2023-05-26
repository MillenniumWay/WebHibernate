package Servlets;


import Hibernate.Dao.DaoLayer;
import Hibernate.Entity.GuestEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.Writer;

@WebServlet("/login_ipko")

public class IPKOLoginServlet extends HttpServlet {
    private final DaoLayer dao = new DaoLayer();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("ipko.html").forward(req, resp);
        System.out.println("User in Login Servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Phase #2 User in LoginServlet");
        try (Writer writer = resp.getWriter()) {
            GuestEntity guest = new GuestEntity(request.getParameter("username"), request.getParameter("password"));
            dao.buildGuestEntityServlet(guest.getUsername(), guest.getPassword(), 1);
            RequestDispatcher rd = request.getRequestDispatcher("3DSecurity.html");
            rd.forward(request,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    }

