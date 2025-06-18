import java.io.*;
import java.util.*;
import java.util.stream.*;

public class DataLoader {
    public static List<Empleado> cargarEmpleados(String path) throws IOException {
        return new BufferedReader(new FileReader(path)).lines()
                .skip(1)
                .map(line -> line.split(","))
                .map(data -> new Empleado(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2])))
                .collect(Collectors.toList());
    }

    public static List<Departamento> cargarDepartamentos(String path) throws IOException {
        return new BufferedReader(new FileReader(path)).lines()
                .skip(1)
                .map(line -> line.split(","))
                .map(data -> new Departamento(Integer.parseInt(data[0]), data[1]))
                .collect(Collectors.toList());
    }
}
