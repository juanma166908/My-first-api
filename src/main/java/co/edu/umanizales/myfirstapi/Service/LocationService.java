package co.edu.umanizales.myfirstapi.Service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LocationService {

    private final List<String> locations = new ArrayList<>();

    public LocationService() {
        loadLocationsFromFile();
    }

    private void loadLocationsFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource("DIVIPOLA-_C_digos_municipios_20250424.csv").getInputStream(),
                        StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                locations.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

    public List<String> getAllLocationRecords() {
        return new ArrayList<>(locations);
    }

    public List<String> getLocationsByDeptCode(String code) {
        List<String> result = new ArrayList<>();
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts[0].equalsIgnoreCase(code)) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> getLocationsByExactName(String name) {
        List<String> result = new ArrayList<>();
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 3 && parts[3].equalsIgnoreCase(name)) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> getLocationsByNamePrefix(String letters) {
        List<String> result = new ArrayList<>();
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 3 && parts[3].toLowerCase().startsWith(letters.toLowerCase())) {
                result.add(line);
            }
        }
        return result;
    }

    public List<String> getLocationsByStateName(String stateName) {
        List<String> result = new ArrayList<>();
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 1 && parts[1].equalsIgnoreCase(stateName)) {
                result.add(line);
            }
        }
        return result;
    }

    public String getLocationByMunicipalityCode(String mCode) {
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 2 && parts[2].equalsIgnoreCase(mCode)) {
                return line;
            }
        }
        return null;
    }

    public List<String> getAllStates() {
        Set<String> states = new HashSet<>();
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 1) {
                states.add(parts[0] + "," + parts[1]);
            }
        }
        return new ArrayList<>(states);
    }

    public String getStateNameByCode(String code) {
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 1 && parts[0].equalsIgnoreCase(code)) {
                return parts[1];
            }
        }
        return null;
    }

    public List<String> getAllCapitalCities() {
        List<String> result = new ArrayList<>();
        for (String line : locations) {
            if (line.startsWith("Código Departamento")) continue;
            String[] parts = line.split(",");
            if (parts.length > 2 && parts[2].endsWith("001")) {
                result.add(line);
            }
        }
        return result;
    }

}


