package Projeto.ControleDeCarro.service;

import Projeto.ControleDeCarro.Model.Estacionamento;
import Projeto.ControleDeCarro.exception.EstacionamentoNotFoundException;
import Projeto.ControleDeCarro.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EstacionamentoService {

    @Autowired
    private final EstacionamentoRepository estacionamentoRepository;

    public EstacionamentoService(EstacionamentoRepository estacionamentoRepository) {
        this.estacionamentoRepository = estacionamentoRepository;
    }

    public List<Estacionamento> findAll(){
        return estacionamentoRepository.findAll();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Estacionamento findById(String id) {
        return estacionamentoRepository.findById(id).orElseThrow(() ->
                new EstacionamentoNotFoundException(id));
    }

    public Estacionamento create(Estacionamento estacionamentoCreate) {
        String uuid = getUUID();
        estacionamentoCreate.setId(uuid);
        estacionamentoCreate.setEntrada(LocalDateTime.now());
        estacionamentoRepository.save(estacionamentoCreate);
        return estacionamentoCreate;
    }

    public void delete(String id) {
        findById(id);
        estacionamentoRepository.deleteById(id);
    }

    public Estacionamento update(String id, Estacionamento estacionamentoCreate) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setCor(estacionamentoCreate.getCor());
        estacionamento.setEstado(estacionamentoCreate.getEstado());
        estacionamento.setModelo(estacionamentoCreate.getModelo());
        estacionamento.setPlaca(estacionamentoCreate.getPlaca());
        estacionamentoRepository.save(estacionamento);
        return estacionamento;
    }

    public Estacionamento checarSaida(String id) {
        Estacionamento estacionamento = findById(id);
        estacionamento.setSaida(LocalDateTime.now());
        estacionamento.setConta(EstacionamentoChecarSaida.getConta(estacionamento));
        estacionamentoRepository.save(estacionamento);
        return estacionamento;
    }


}
