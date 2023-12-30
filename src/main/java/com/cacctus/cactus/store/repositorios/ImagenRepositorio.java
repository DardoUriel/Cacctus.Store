package com.cacctus.cactus.store.repositorios;

import com.cacctus.cactus.store.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{
    
}
