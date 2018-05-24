package com.epam.web.command;

import com.epam.Path;
import com.epam.dao.impl.MySQLEmployeeDAO;
import com.epam.db.DBManager;
import com.epam.db.TransactionManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class DeleteEmployee extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteEmployee.class);

    MySQLEmployeeDAO dao;
    DBManager dbManager;

    public DeleteEmployee(MySQLEmployeeDAO dao, DBManager dbManager) {
        this.dao = dao;
        this.dbManager = dbManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");
        Connection connection = TransactionManager.prepareConnection(dbManager.getConnection());

        dao.deleteEmployee(connection, itemForDelete(request));

        TransactionManager.close(connection);
        LOG.debug("Command finished");
        return Path.EMPLOYEE_RETURN_DELETE_LIST;
    }

    private int itemForDelete(HttpServletRequest req) {
        return Integer.parseInt(req.getParameter("id"));
    }
}
