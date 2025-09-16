package cl.prueba.proyectum.services.impl;

import cl.prueba.proyectum.entity.ProductoEntity;
import cl.prueba.proyectum.mappers.ProductoMapper;
import cl.prueba.proyectum.models.ProductoRequest;
import cl.prueba.proyectum.models.ProductoResponse;
import cl.prueba.proyectum.repository.ProductoRepository;
import cl.prueba.proyectum.services.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoResponse> listar() {
        try {
            List<ProductoEntity> productoEntityList = productoRepository.findAll();
            return ProductoMapper.toResponseList(productoEntityList);
        } catch (Exception e) {
            log.error("Problemas al obtener listado de Productos: " + e.getMessage(), e);
            return Collections.emptyList(); // mejor que null
        }
    }

    @Override
    public Optional<ProductoResponse> obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toResponse);
    }

    @Override
    public ProductoResponse guardar(ProductoRequest productoRequest) {
        ProductoEntity entity = ProductoMapper.toEntity(productoRequest);
        ProductoEntity saved = productoRepository.save(entity);
        return ProductoMapper.toResponse(saved);
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest productoRequest) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(productoRequest.getNombre());
                    p.setPrecio(productoRequest.getPrecio());
                    ProductoEntity actualizado = productoRepository.save(p);
                    return ProductoMapper.toResponse(actualizado);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }


    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
