package cl.prueba.proyectum.services;

import cl.prueba.proyectum.models.ProductoRequest;
import cl.prueba.proyectum.models.ProductoResponse;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<ProductoResponse> listar();

    Optional<ProductoResponse> obtenerPorId(Long id);

    ProductoResponse guardar(ProductoRequest productoRequest);

    ProductoResponse actualizar(Long id, ProductoRequest productoRequest);

    void eliminar(Long id);
}
