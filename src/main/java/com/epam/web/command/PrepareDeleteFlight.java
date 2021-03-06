package com.epam.web.command;

import com.epam.Path;
import com.epam.db.DBManager;
import com.epam.db.TransactionManager;
import com.epam.entity.Flight;
import com.epam.dao.impl.MyFlightDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class PrepareDeleteFlight extends Command {
    private static final Logger LOG = Logger.getLogger(PrepareDeleteFlight.class);

    MyFlightDAO dao;
    DBManager dbManager;

    public PrepareDeleteFlight(MyFlightDAO dao, DBManager dbManager) {
        this.dao = dao;
        this.dbManager = dbManager;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");

        Connection connection = TransactionManager.prepareConnection(dbManager.getConnection());

        List<Flight> flightList = dao.showAllFlights(connection);
        request.getSession().setAttribute("flightList",flightList);
        LOG.trace("Flight list for delete --> "+flightList);

        TransactionManager.close(connection);

        LOG.debug("Command finished");
        return Path.FLIGHTS_LIST_FOR_DELETE;
    }

}
