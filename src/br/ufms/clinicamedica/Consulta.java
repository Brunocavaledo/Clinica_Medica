package br.ufms.clinicamedica;

import java.time.LocalDateTime;
import java.util.List;

public class Consulta {

    private final long codigo;
    private final Medico medico;
    private final Paciente paciente;
    private String sintomas;
    private LocalDateTime dataHora;
    private double valor;
    private String receita;
    private List<String> exames;

    private static long contador = 1; // Variável compartilhada por todas as instâncias

    //Construtor da consulta:
    public Consulta(Medico medico, Paciente paciente, String sintomas, LocalDateTime dataHora, double valor) {
        this.codigo = contador;
        contador++;
        this.medico = medico;
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.dataHora = dataHora;
        this.valor = valor;
    }
    @Override
    public String toString() {// esse método que descobri é bom pra eu conseguir ver os dados dentro de um objeto, pra fins de teste mesmo.
        return "Consulta{" +
                "codigo=" + codigo + '\'' +
                "médico='" + medico.getNome() + '\'' +
                ", sintomas='" + sintomas + '\'' +
                ", paciente=" + paciente.getNome() + '\'' +
                ", dataHora='" + dataHora + '\'' +
                ", valor=" + valor +
                '}';
    }
}
