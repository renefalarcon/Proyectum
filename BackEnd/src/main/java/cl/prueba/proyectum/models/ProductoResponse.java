package cl.prueba.proyectum.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private Long id;
    private String nombre;
    private Double precio;

}
