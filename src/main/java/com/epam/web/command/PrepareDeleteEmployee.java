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

public class PrepareDeleteEmployee extends Command {
    private static final Logger LOG = Logger.getLogger(PrepareDeleteEmployee.class);

    MySQLEmployeeDAO dao;
    DBManager dbManager;

    public PrepareDeleteEmployee(MySQLEmployeeDAO dao, DBManager dbManager) {
        this.dao = dao;
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");

        Connection connection = TransactionManager.prepareConnection(dbManager.getConnection());

        List<CrewMan> employeeslist = dao.showAllEmployers(connection);
        request.getSession().setAttribute("employeesList", employeeslist);
        LOG.trace("Employee list --> " + employeeslist);
        TransactionManager.close(connection);

        LOG.debug("Command finished");
        return Path.EMPLOYEES_LIST_FOR_DELETE;
    }
}
