/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cacctus.cactus.store.servicios;

import com.cacctus.cactus.store.entidades.Imagen;
import com.cacctus.cactus.store.entidades.Producto;
import com.cacctus.cactus.store.excepciones.MiException;
import com.cacctus.cactus.store.repositorios.ProductoRepositorio;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author FlowUser
 */
@Service
public class ProductoServicio {
    
    @Autowired
    ProductoRepositorio productoRepositorio;
    @Autowired
    ImagenServicio imagenServicio;

    @Transactional
    public void crearProducto(Long modelo, String nombre, String descipcion, double precio, int cantidad, MultipartFile imagen) throws MiException {
        validaciones(modelo, nombre, descipcion, precio, cantidad, imagen);
        Producto p = new Producto();
        p.setModelo(modelo);
        p.setNombre(nombre);
        p.setDescripcion(descipcion);
        p.setPrecio(0);
        p.setCantidad(cantidad);
        p.setFechaPublicacion(new Date());
        List<Imagen> imagenes = new ArrayList();
        imagenes.add(imagenServicio.guardar(imagen));        
        p.setImagenes(imagenes);
        productoRepositorio.save(p);
    }
    
    @Transactional
    public void modificarProducto(Long modelo, String nombre, String descipcion, double precio, int cantidad, MultipartFile imagen) throws MiException {
        validaciones(modelo, nombre, descipcion, precio, cantidad, imagen);
        Optional<Producto> respuesta = productoRepositorio.buscarPorModelo(modelo);
        
        if (respuesta.isPresent()) {
            
            Producto p = respuesta.get();
            p.setModelo(modelo);
            p.setNombre(nombre);
            p.setDescripcion(descipcion);
            p.setPrecio(0);
            p.setCantidad(cantidad);
            p.setFechaPublicacion(new Date());
            
            List<Imagen> imagenes = p.getImagenes();
            imagenes.add(imagenServicio.guardar(imagen)); // la imagen se agrega a la lista no se pisa 
            p.setImagenes(imagenes);
            productoRepositorio.save(p);
            
        }
        
    }
    
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        
        productos = productoRepositorio.findAll();
        return productos;
        
    }
    
    public void validaciones(Long modelo, String nombre, String descripcion, double precio, int cantidad, MultipartFile imagen) throws MiException {
        if (modelo == null) {
            throw new MiException("el modelo no puede ser nulo");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("el nombre no puede ser nulo o estra vacio");
        }
        
        if (descripcion == null || descripcion.isEmpty()) {
            throw new MiException("la descipcion no puede ser nula o estar vacia");
        }
        if (precio == 00.00) {
            throw new MiException("el precio no puede estar vacio");
        }
        if (cantidad == 0) {
            throw new MiException("la cantidad no puede ser 0");
        }
        if (imagen == null) {
            throw new MiException("la imagen no puede ser nula  ");
        }
    }
    
}
