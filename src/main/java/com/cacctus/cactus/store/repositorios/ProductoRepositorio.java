/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cacctus.cactus.store.repositorios;

import com.cacctus.cactus.store.entidades.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FlowUser
 */
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    @Query("SELECT m FROM Producto m WHERE m.modelo = :modelo")
    public Optional<Producto> buscarPorModelo(@Param("modelo")Long modelo);
    
    
    @Query("SELECT n FROM Producto n WHERE n.nombre = :nombre")
    public List<Producto> buscarPorNombre(@Param("nombre") String nombre);

}
