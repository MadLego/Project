
import com.epam.dao.impl.MySQLEmployeeDAO;
import com.epam.db.DBManager;
import com.epam.web.command.DeleteEmployee;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static org.mockito.Mockito.when;

public class DeleteEmployeeTest {

    @Mock
    MySQLEmployeeDAO mySQLEmployeeDAO;
    @Mock
    private DBManager dbManager;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Connection connection;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(request.getSession()).thenReturn(session);

        when(dbManager.getConnection()).thenReturn(connection);
        when(request.getParameter("id")).thenReturn(String.valueOf(1));
    }
    @Test
    public void test() throws IOException, ServletException {
        DeleteEmployee deleteEmployee = new DeleteEmployee(mySQLEmployeeDAO,dbManager);
        deleteEmployee.execute(request,response);
    }
}
