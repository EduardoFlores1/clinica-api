package com.nolimits.clinica.Controllers;

import com.nolimits.clinica.exception.CustomResponseException;
import com.nolimits.clinica.exception.ModelNotFoundException;
import com.nolimits.clinica.models.Examen;
import com.nolimits.clinica.services.IExamenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

    private final IExamenService service;

    public ExamenController(IExamenService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Examen>> index() {
        List<Examen> lista = service.findAll();
        if(lista.isEmpty()) throw new ModelNotFoundException("Registros no encontrados");
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> show(@PathVariable Long id) {
        Optional<Examen> opt = service.findById(id);
        if(opt.isEmpty()) throw new ModelNotFoundException("Registro no encontrado");
        return ResponseEntity.ok(opt.get());
    }

    // objRequest no debe poseer id, de lo contrario se rechaza
    @PostMapping
    public ResponseEntity<Examen> save(@Valid @RequestBody Examen objRequest) {
        if(objRequest.getIdExamen() == null) {
            Examen newObj = service.save(objRequest);
            return new ResponseEntity<>(newObj, HttpStatus.CREATED);
        }
        throw new CustomResponseException("Petición inválida");
    }

    // Él id del url y el de objRequest deben ser iguales,
    // por último, el objeto debe existir para actualizarse
    @PutMapping("/{id}")
    public ResponseEntity<Examen> update(@PathVariable Long id, @Valid @RequestBody Examen objRequest) {
        if(id.equals(objRequest.getIdExamen())) {
            Optional<Examen> opt = service.findById(id);
            if(opt.isPresent()) {
                Examen objUpt = service.update(objRequest);
                return ResponseEntity.ok(objUpt);
            }
            throw new ModelNotFoundException("Registro no encontrado");
        }
        throw new CustomResponseException("Petición inválida");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Examen> opt = service.findById(id);
        if(opt.isEmpty()) throw new ModelNotFoundException("Registro no encontrado");
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
