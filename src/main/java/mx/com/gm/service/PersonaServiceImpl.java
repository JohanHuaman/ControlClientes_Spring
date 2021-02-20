package mx.com.gm.service;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService{
    
    //Para implementar los métodos CRUD
    @Autowired
    private PersonaDao personaDao;

    @Override
    @Transactional(readOnly = true) //porque vamos a leer
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll(); //Hacemos un cast y recuperamos todos las personas.
    }

    @Override
    @Transactional //Inicie una nueva transacción
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true) //solo va consultar no va a modificar.
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
