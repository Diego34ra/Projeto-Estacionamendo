package Projeto.ControleDeCarro.repository;


import Projeto.ControleDeCarro.Model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, String> {
}
