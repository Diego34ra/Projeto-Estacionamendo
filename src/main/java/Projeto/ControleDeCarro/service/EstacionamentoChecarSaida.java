package Projeto.ControleDeCarro.service;

import Projeto.ControleDeCarro.Model.Estacionamento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class EstacionamentoChecarSaida {
    public static final int UMA_HORA = 60;
    public static final int VINTE_QUATRO_HORAS = 24 * UMA_HORA;
    public static final double VALOR_UMA_HORA = 7.00;
    public static final double VALOR_ADICIONAL_POR_HORAS = 3.00;
    public static final double VALOR_DIA = 20.00;

    public static Double getConta(Estacionamento estacionamento) {
        return getConta(estacionamento.getEntrada(), estacionamento.getSaida());
    }

    private static Double getConta(LocalDateTime entrada, LocalDateTime saida) {
        long minutos = entrada.until(saida, ChronoUnit.MINUTES);
        Double conta = 0.0;
        if (minutos <= UMA_HORA) {
            return VALOR_UMA_HORA;
        }
        if(minutos <= VINTE_QUATRO_HORAS) {
            conta = VALOR_UMA_HORA;
            int horas = (int) (minutos / UMA_HORA);
            for (int i = 0; i < horas; i++) {
                conta += VALOR_ADICIONAL_POR_HORAS;
            }
            return conta;
        }
        int dias = (int) (minutos / VINTE_QUATRO_HORAS);
        for (int i = 0; i < dias; i++) {
            conta += VALOR_DIA;
        }
        return conta;
    }
}
