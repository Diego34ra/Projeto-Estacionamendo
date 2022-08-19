package Projeto.ControleDeCarro.controller;


import Projeto.ControleDeCarro.Model.Estacionamento;
import Projeto.ControleDeCarro.controller.dto.EstacionamentoCreateDTO;
import Projeto.ControleDeCarro.controller.dto.EstacionamentoDTO;
import Projeto.ControleDeCarro.controller.mapper.EstacionamentoMapper;
import Projeto.ControleDeCarro.service.EstacionamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Estacionamento")
@Api(tags = "Estacionamento Controller")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;
    private final EstacionamentoMapper estacionamentoMapper;

    public EstacionamentoController(EstacionamentoService estacionamentoService, EstacionamentoMapper estacionamentoMapper) {
        this.estacionamentoService = estacionamentoService;
        this.estacionamentoMapper = estacionamentoMapper;
    }

    @GetMapping
    public ResponseEntity<List<EstacionamentoDTO>> findAll() {
        List<Estacionamento> estacionamentoList = estacionamentoService.findAll();
        List<EstacionamentoDTO> result = estacionamentoMapper.toEstacionamentoDTOList(estacionamentoList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionamentoDTO> findById(@PathVariable String id) {
        Estacionamento estacionamento = estacionamentoService.findById(id);
        EstacionamentoDTO result = estacionamentoMapper.toEstacionamentoDTO(estacionamento);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        estacionamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EstacionamentoDTO> create(@RequestBody EstacionamentoCreateDTO dto) {
        var estacionamentocreate = estacionamentoMapper.toEstacionamentoCreate(dto);
        var estacionamento = estacionamentoService.create(estacionamentocreate);
        var result = estacionamentoMapper.toEstacionamentoDTO(estacionamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstacionamentoDTO> update(@PathVariable String id, @RequestBody EstacionamentoCreateDTO estacionamentoCreateDTO) {
        Estacionamento estacionamentoUpdate = estacionamentoMapper.toEstacionamentoCreate(estacionamentoCreateDTO);
        Estacionamento estacionamento = estacionamentoService.update(id, estacionamentoUpdate);
        return ResponseEntity.ok(estacionamentoMapper.toEstacionamentoDTO(estacionamento));
    }

    @PostMapping("/{id}")
    public ResponseEntity<EstacionamentoDTO> checarSaida(@PathVariable String id) {
        Estacionamento estacionamento = estacionamentoService.checarSaida(id);
        return ResponseEntity.ok(estacionamentoMapper.toEstacionamentoDTO(estacionamento));
    }
}
