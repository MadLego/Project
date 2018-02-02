package com.epam.web;

import com.epam.Path;
import com.epam.web.command.Command;
import com.epam.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = req.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);

        // execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;
        try {
            forward = command.execute(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        LOG.trace("Forward address --> " + forward);

        LOG.debug("Controller finished, now go to forward address --> " + forward);
        // go to forward
        req.getRequestDispatcher(forward).forward(req, resp);

    }
}
