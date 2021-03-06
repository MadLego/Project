package com.epam.web.command;

import com.epam.Path;
import com.epam.dao.impl.MySQLEmployeeDAO;
import com.epam.db.DBManager;
import com.epam.db.TransactionManager;
import com.epam.parser.EmployeeParser;
import com.epam.entity.CrewMan;
import com.epam.validator.EmployeesValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class NewEmployee extends Command {
    private static final Logger LOG = Logger.getLogger(NewEmployee.class);

    MySQLEmployeeDAO dao;
    DBManager dbManager;

    public NewEmployee(MySQLEmployeeDAO dao, DBManager dbManager) {
        this.dao = dao;
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");

        Connection connection = TransactionManager.prepareConnection(dbManager.getConnection());

        CrewMan man = newEmployee(request);
        LOG.trace("New Employee --> " + man);
        ArrayList<String> list = EmployeesValidator.validateEmployee(man);
        if (list.isEmpty()) {
            dao.newEmployee(connection, newEmployee(request));
        } else {
            LOG.trace("Errors --> " + list);
            request.getSession().setAttribute("errors", list);
            return Path.EMPLOYEE_PREPARE_NEW;
        }

        TransactionManager.close(connection);
        LOG.debug("Command finished");
        return Path.EMPLOYEE_NEW;
    }

    public CrewMan newEmployee(HttpServletRequest req) {
        return dao.fillCrewMan(dbManager.getConnection(), EmployeeParser.flightDTOParser(req));
    }


}
