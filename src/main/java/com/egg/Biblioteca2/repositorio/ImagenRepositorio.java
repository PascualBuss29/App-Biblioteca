package com.egg.Biblioteca2.repositorio;

import com.egg.Biblioteca2.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{

}
