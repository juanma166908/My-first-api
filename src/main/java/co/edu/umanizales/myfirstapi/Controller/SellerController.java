package co.edu.umanizales.myfirstapi.Controller;

import co.edu.umanizales.myfirstapi.Model.Seller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/Seller")

public class SellerController {
    @GetMapping()
    public List<Seller> getSellers() {
        Seller Manuel = new Seller(1002577, "Manuel", "Vanegas", "Masculino", (byte) 21, 300874213, "hola", "Medellin");
        Seller Isabell = new Seller(1058138, "Isabell", "Vargas", "Feminine", (byte) 40, 312883770, "hola", "Manizales");
        Seller Dahiana = new Seller(303580, "Dahiana", "Sanchez", "Feminine", (byte) 32, 321674359, "hola", "Bogota");
        Seller Juan = new Seller(100897, "Juan", "Salazar", "Masculino", (byte) 26, 311842789, "hola", "Medellin");
        Seller Sofia = new Seller(1002875, "Sofia", "Restrepo", "Feminine", (byte) 38, 30024321, "hola", "Pereira");

        return Arrays.asList(Manuel, Isabell, Dahiana, Juan, Sofia);
    }
}