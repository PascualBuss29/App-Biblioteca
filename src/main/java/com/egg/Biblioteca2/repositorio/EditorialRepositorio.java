
package com.egg.Biblioteca2.repositorio;

import com.egg.Biblioteca2.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{

  
    
}
