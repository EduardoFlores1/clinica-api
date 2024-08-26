package com.nolimits.clinica.Controllers;

import com.nolimits.clinica.dtos.ConsultaExamenDTO;
import com.nolimits.clinica.exception.CustomResponseException;
import com.nolimits.clinica.exception.ModelNotFoundException;
import com.nolimits.clinica.models.Consulta;
import com.nolimits.clinica.models.ConsultaExamen;
import com.nolimits.clinica.services.IConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final IConsultaService service;

    public ConsultaController(IConsultaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> index() {
        List<Consulta> lista = service.findAll();
        if(lista.isEmpty()) throw new ModelNotFoundException("Registros no encontrados");
        return ResponseEntity.ok(lista);
    }

    // Busca la consulta y sus exámenes
    @GetMapping("/{id}")
    public ResponseEntity<ConsultaExamenDTO> show(@PathVariable Long id) {
        Optional<Consulta> opt = service.findById(id);
        if(opt.isEmpty()) throw new ModelNotFoundException("Registro no encontrado");

        ConsultaExamenDTO dto = new ConsultaExamenDTO();

        // Introducimos la consulta
        dto.setConsulta(opt.get());
        // Listamos todas las ConsultaExamen que tengan un, id específico
        List<ConsultaExamen> ce = service.withIdConsulta(List.of(new Consulta(id)));
        // Extraemos solo los exámenes y lo convertimos a lista
        dto.setExamenList(ce.stream().map(ConsultaExamen::getExamen).toList());

        return ResponseEntity.ok(dto);
    }

    // objRequest no debe poseer id, de lo contrario se rechaza
    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody Consulta objRequest) {
        if(objRequest.getIdConsulta() == null) {
            Consulta newObj = service.save(objRequest);
            return new ResponseEntity<>(newObj.getIdConsulta(), HttpStatus.CREATED);
        }
        throw new CustomResponseException("Petición inválida");
    }

    // consultaExamenDTO.getConsulta().getIdConsulta() no debe poseer id, de lo contrario se rechaza
    // Recibe el modelo DTO para Consulta y Examen
    @PostMapping("/consulta-examen")
    public ResponseEntity<Long> saveTransactional(@Valid @RequestBody ConsultaExamenDTO consultaExamenDTO) {
        if(consultaExamenDTO.getConsulta().getIdConsulta() == null) {
            Consulta newObj = service.registroTransactional(consultaExamenDTO);
            return new ResponseEntity<>(newObj.getIdConsulta(), HttpStatus.CREATED);
        }
        throw new CustomResponseException("Petición inválida");
    }

    // Él id del url y el de objRequest deben ser iguales,
    // por último, el objeto debe existir para actualizarse
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @Valid @RequestBody Consulta objRequest) {
        if(id.equals(objRequest.getIdConsulta())) {
            Optional<Consulta> opt = service.findById(id);
            if(opt.isPresent()) {
                Consulta objUpt = service.update(objRequest);
                return ResponseEntity.ok(objUpt);
            }
            throw new ModelNotFoundException("Registro no encontrado");
        }
        throw new CustomResponseException("Petición inválida");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Consulta> opt = service.findById(id);
        if(opt.isEmpty()) throw new ModelNotFoundException("Registro no encontrado");
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
