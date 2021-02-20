package mx.com.gm.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.gm.service.PersonaService;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaService personaService;
    
    
    @GetMapping("/")
    public String inicio(Model model){
        
        var personas = personaService.listarPersonas();
        
        log.info("Ejecutando controlador Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }
    
    @GetMapping("/agregar") //se va a usar para agregar y modificar 
    public String agregar(Persona persona){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){ // deben de estar juntos estos atributos para validar.
        //Se agrega model en caso quisieramos enviar algpun atributo. Como parámetro.
        if(errores.hasErrors()){
            return "modificar"; //Si tiene un error se va a modificar.
        }
         personaService.guardar(persona);
         return "redirect:/";
    }
    
    //recibe el idPersona y modifica lo que se tiene de ese id, pero por ahora no guarda.
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona,Model model){ //solo es necesario usar Persona para llamar al id,
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){ //Spring , al poner un objeto de la clase, ya reconoce que hablamos de idPersona
        personaService.eliminar(persona);
        return "redirect:/"; // después de hacer dicha acción volvemos a la págian de inicio.
    }
}
