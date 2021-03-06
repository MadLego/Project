package com.epam.web.command;

import com.epam.Path;
import com.epam.dao.impl.MySQLEmployeeDAO;
import com.epam.db.DBManager;
import com.epam.db.TransactionManager;
import com.epam.entity.CrewMan;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class PrepareNewThisCrew extends Command {
    private static final Logger LOG = Logger.getLogger(PrepareNewThisCrew.class);

    MySQLEmployeeDAO dao;
    DBManager dbManager;

    public PrepareNewThisCrew(MySQLEmployeeDAO dao, DBManager dbManager) {
        this.dao=dao;
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");

        Connection connection = TransactionManager.prepareConnection(dbManager.getConnection());

        List<CrewMan> list = dao.showAllEmployers(connection);
        request.getSession().setAttribute("employees", list);
        request.getSession().setAttribute("flightName", request.getParameter("name"));
        request.getSession().setAttribute("flightId", request.getParameter("flightId"));
        LOG.trace("List of Employees --> " + list);
        TransactionManager.close(connection);

        LOG.debug("Command finished");
        return Path.CREW_THIS;
    }
}
