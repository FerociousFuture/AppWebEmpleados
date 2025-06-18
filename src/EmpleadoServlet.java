import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class EmpleadoServlet extends HttpServlet {
    private List<String[]> empleados;

    @Override
    public void init() throws ServletException {
        try {
            String ruta = getServletContext().getRealPath("/WEB-INF/data/empleados.csv");
            empleados = new BufferedReader(new FileReader(ruta))
                    .lines()
                    .map(linea -> linea.split(","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            empleados = new ArrayList<>();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Lista de Empleados</h2><ul>");

        empleados.stream()
                .limit(50)
                .forEach(emp -> out.println("<li>" + Arrays.toString(emp) + "</li>"));

        out.println("</ul></body></html>");
    }
}
