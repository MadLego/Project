package com.epam.web.command;

import com.epam.Path;
import com.epam.dao.impl.MyFlightDAO;
import com.epam.db.DBManager;
import com.epam.db.TransactionManager;
import com.epam.entity.Flight;
import com.epam.validator.FlightsValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class SearchFlight extends Command {
    private static final Logger LOG = Logger.getLogger(SearchFlight.class);

    MyFlightDAO dao;
    DBManager dbManager;

    public SearchFlight(MyFlightDAO dao, DBManager dbManager) {
        this.dao = dao;
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");

        Connection connection = TransactionManager.prepareConnection(dbManager.getConnection());

        ArrayList<Flight> flights = dao.searchFlight(connection,request.getParameter("searchItem"));
        LOG.trace("List of flights --> "+flights);
        String result = FlightsValidator.searchValidate(flights);
        if (!result.equals("OK")){
            LOG.trace("Error --> "+result);
            request.setAttribute("errors", result);
        }else {
            request.getSession().setAttribute("searchingFlight", flights);
        }
        LOG.debug("Command finished");

        return Path.FLIGHTS_SEARCHING;

    }

}
