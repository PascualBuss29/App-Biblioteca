package com.egg.Biblioteca2.servicios;

import com.egg.Biblioteca2.Excepciones.MiException;
import com.egg.Biblioteca2.entidades.Autor;
import com.egg.Biblioteca2.entidades.Editorial;
import com.egg.Biblioteca2.entidades.Libros;
import com.egg.Biblioteca2.repositorio.AutorRepositorio;
import com.egg.Biblioteca2.repositorio.EditorialRepositorio;
import com.egg.Biblioteca2.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = (Editorial) editorialRepositorio.findById(idEditorial).get();
        Libros libro = new Libros();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }

    public List<Libros> listarLibros() {

        List<Libros> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        Optional<Libros> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();

        }

        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();

        }

        if (respuesta.isPresent()) {

            Libros libro = respuesta.get();

            libro.setTitulo(titulo);

            libro.setAutor(autor);

            libro.setEditorial(editorial);

            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);
        }
    }

    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException {
        if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo");
        }

        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede estar vacio o ser nulo");
        }

        if (ejemplares == null) {
            throw new MiException("Los ejemplares no pueden  ser nulo");
        }

        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El autor no puede estar vacio o ser nulo");
        }

        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("La editorial no puede estar vacia o ser nulo");
        }
    }
}
