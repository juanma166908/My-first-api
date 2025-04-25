package co.edu.umanizales.myfirstapi.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    private final List<String> stores = new ArrayList<>();

    public StoreService() {
        loadStoresFromFile();
    }

    private void loadStoresFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("Departments.csv").getInputStream(),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stores.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo el archivo Departments.csv: " + e.getMessage(), e);
        }
    }

    public List<String> getAllStores() {
        return new ArrayList<>(stores);
    }


    public List<String> getDepartmentsGroupedByCapita() {
        List<String> resultado = new ArrayList<>();
        String nombreDepartamentoActual = "";

        // Aca recorremos las tiendas (locations) cargadas del csv
        for (String linea : stores) {
            String[] partes = linea.split(";");
            if (partes.length != 2) continue;

            String codigo = partes[0].trim();
            String nombre = partes[1].trim();

            // Y aqui verificamos si el código es acirde al departamento
            if (codigo.length() == 2) {
                nombreDepartamentoActual = nombre;
            } else if (codigo.endsWith("001")) {
                resultado.add(nombreDepartamentoActual + " -> " + nombre);  // Asociamos el departamento con su capital
            }
        }

        return resultado;
    }
}