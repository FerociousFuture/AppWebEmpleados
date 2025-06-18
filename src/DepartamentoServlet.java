import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;x`
import java.util.stream.*;

public class DepartamentoServlet extends HttpServlet {
    private List<String[]> departamentos;

    @Override
    public void init() throws ServletException {
        try {
            String ruta = getServletContext().getRealPath("/WEB-INF/data/departamentos.csv");
            departamentos = new BufferedReader(new FileReader(ruta))
                    .lines()
                    .map(linea -> linea.split(","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            departamentos = new ArrayList<>();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Lista de Departamentos</h2><ul>");

        departamentos.stream()
                .forEach(dep -> out.println("<li>" + Arrays.toString(dep) + "</li>"));

        out.println("</ul></body></html>");
    }
}
