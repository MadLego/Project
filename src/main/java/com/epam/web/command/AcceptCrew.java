package com.epam.web.command;

import com.epam.Path;
import com.epam.dao.impl.MySQLCrewDAO;
import com.epam.dao.impl.MySQLEmployeeDAO;
import com.epam.dao.impl.MyFlightDAO;
import com.epam.db.DBManager;
import com.epam.db.TransactionManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

public class AcceptCrew extends Command {

    private static final Logger LOG = Logger.getLogger(AcceptCrew.class);

    private MyFlightDAO fDao;
    private MySQLCrewDAO cDao;
    private MySQLEmployeeDAO eDao;
    DBManager db;

    public AcceptCrew(MyFlightDAO fDao, MySQLCrewDAO cDao, MySQLEmployeeDAO eDao, DBManager db) {
        this.fDao = fDao;
        this.cDao = cDao;
        this.eDao = eDao;
        this.db = db;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Connection connection = TransactionManager.prepareConnection(db.getConnection());
        LOG.debug("Command starts");
        String decision = request.getParameter("admission");

        LOG.trace("Decision of user: menuItemsList --> " + decision);

        int flightId = fDao.searchFlightIdByNumber(connection, request.getParameter("flightId"));

        cDao.acceptAdmission(connection, flightId, decision);

        Timestamp[] time = fDao.searchFlightTimeByNumber(connection, request.getParameter("flightId"));
        eDao.setAdmission(connection, decision, time);

        LOG.debug("Command finished");
        TransactionManager.close(connection);
        return Path.CREW_RETURN_LIST_CREW;
    }

}
