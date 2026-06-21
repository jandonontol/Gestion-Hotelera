import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArchivoUtil {

    public static void guardarClientes(ArrayList<Cliente> clientes, String rutaArchivo) {
        crearCarpetaData();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));

            for (Cliente cliente : clientes) {
                bw.write(cliente.toArchivo());
                bw.newLine();
            }

            bw.close();
            System.out.println("Clientes guardados correctamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    public static ArrayList<Cliente> leerClientes(String rutaArchivo) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 4) {
                    Cliente cliente = new Cliente(datos[0], datos[1], datos[2], datos[3]);
                    clientes.add(cliente);
                }
            }

            br.close();
            System.out.println("Clientes cargados desde archivo.");

        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo. Puede que aun no exista: " + e.getMessage());
        }

        return clientes;
    }

    private static void crearCarpetaData() {
        File carpeta = new File("data");

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }
}
