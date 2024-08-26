package com.nolimits.clinica.Controllers;

import com.nolimits.clinica.exception.CustomResponseException;
import com.nolimits.clinica.exception.ModelNotFoundException;
import com.nolimits.clinica.models.Medico;
import com.nolimits.clinica.services.IMedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final IMedicoService service;

    public MedicoController(IMedicoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Medico>> index() {
        List<Medico> lista = service.findAll();
        if(lista.isEmpty()) throw new ModelNotFoundException("Registros no encontrados");
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> show(@PathVariable Long id) {
        Optional<Medico> opt = service.findById(id);
        if(opt.isEmpty()) throw new ModelNotFoundException("Registro no encontrado");
        return ResponseEntity.ok(opt.get());
    }

    // objRequest no debe poseer id, de lo contrario se rechaza
    @PostMapping
    public ResponseEntity<Medico> save(@Valid @RequestBody Medico objRequest) {
        if(objRequest.getIdMedico() == null) {
            Medico newObj = service.save(objRequest);
            return new ResponseEntity<>(newObj, HttpStatus.CREATED);
        }
        throw new CustomResponseException("Petición inválida");
    }

    // Él id del url y el de objRequest deben ser iguales,
    // por último, el objeto debe existir para actualizarse
    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable Long id, @Valid @RequestBody Medico objRequest) {
        if(id.equals(objRequest.getIdMedico())) {
            Optional<Medico> opt = service.findById(id);
            if(opt.isPresent()) {
                Medico objUpt = service.update(objRequest);
                return ResponseEntity.ok(objUpt);
            }
            throw new ModelNotFoundException("Registro no encontrado");
        }
        throw new CustomResponseException("Petición inválida");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Medico> opt = service.findById(id);
        if(opt.isEmpty()) throw new ModelNotFoundException("Registro no encontrado");
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
