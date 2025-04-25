package co.edu.umanizales.myfirstapi.Controller;

import co.edu.umanizales.myfirstapi.Service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/all-records")
    public List<String> getAllLocationRecords() {
        return locationService.getAllLocationRecords();
    }

    @GetMapping("/department-code/{code}")
    public List<String> getLocationsByDeptCode(@PathVariable String code) {
        return locationService.getLocationsByDeptCode(code);
    }

    @GetMapping("/search-by-name/{name}")
    public List<String> getLocationsByName(@PathVariable String name) {
        return locationService.getLocationsByExactName(name);
    }

    @GetMapping("/name-starts-with/{letters}")
    public List<String> getLocationsByletters(@PathVariable String letters) {
        return locationService.getLocationsByNamePrefix(letters);
    }

    @GetMapping("/state-name/{state}")
    public List<String> getLocationsByState(@PathVariable String state) {
        return locationService.getLocationsByStateName(state);
    }

    @GetMapping("/municipality-code/{code}")
    public String getLocationByMunicipalityCode(@PathVariable String code) {
        return locationService.getLocationByMunicipalityCode(code);
    }

    @GetMapping("/all-states")
    public List<String> getAllStates() {
        return locationService.getAllStates();
    }

    @GetMapping("/state-name-from-code/{code}")
    public String getStateNameByCode(@PathVariable String code) {
        return locationService.getStateNameByCode(code);
    }

    @GetMapping("/capital-cities")
    public List<String> getAllCapitals() {
        return locationService.getAllCapitalCities();
    }
}
