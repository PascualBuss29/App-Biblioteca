package com.egg.Biblioteca2.controladores;

import com.egg.Biblioteca2.Excepciones.MiException;
import com.egg.Biblioteca2.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    public EditorialServicio editorialServicio;
    
    @GetMapping("/registrar")
    private String registrar(){
        return "editorial_form.html";
    }
    
    @PostMapping("/registro")
    private String registro(@RequestParam String nombre, ModelMap modelo){
        
        try {
            editorialServicio.crearEditorial(nombre);
            modelo.put("Exito", "La editorial fue cargada correctamente");
        } catch (MiException ex){ 
            modelo.put("error", ex.getMessage());
            return "editorial_form.html";
        }
        return "index.html";
    }
    
}
