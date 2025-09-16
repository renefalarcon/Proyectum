package cl.prueba.proyectum.mappers;

import cl.prueba.proyectum.entity.ProductoEntity;
import cl.prueba.proyectum.models.ProductoRequest;
import cl.prueba.proyectum.models.ProductoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductoMapper {

    public static ProductoEntity toEntity(ProductoRequest request) {
        ProductoEntity entity = new ProductoEntity();
        entity.setNombre(request.getNombre());
        entity.setPrecio(request.getPrecio());
        return entity;
    }

    public static ProductoResponse toResponse(ProductoEntity entity) {
        return new ProductoResponse(
                entity.getId(),
                entity.getNombre(),
                entity.getPrecio()
        );
    }

    public static List<ProductoResponse> toResponseList(List<ProductoEntity> entities) {
        return entities.stream()
                .map(ProductoMapper::toResponse)
                .collect(Collectors.toList());
    }
}

