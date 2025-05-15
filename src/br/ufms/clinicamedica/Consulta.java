package br.ufms.clinicamedica;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora, double valor) {
        this(medico, paciente, null, dataHora, valor);
        this.valor = valor;
    }

    public Consulta(Medico medico, Paciente paciente, String sintomas, LocalDateTime dataHora, double valor) {
        this.codigo = contador;
        contador++;
        this.medico = medico;
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.dataHora = dataHora;
        this.valor = valor;
        this.exames = new ArrayList<>();
    }

    @Override
    public String toString() {// esse método que descobri é bom pra eu conseguir ver os dados dentro de um objeto, pra fins de teste mesmo.
        return "Consulta{" +
                "codigo=" + codigo +
                ", medico=" + medico.getNome() +
                ", paciente=" + paciente.getNome() +
                ", sintomas='" + sintomas + '\'' +
                ", dataHora=" + dataHora +
                ", valor=" + valor +
                ", receita='" + receita + '\'' +
                ", exames=" + exames +
                '}';
        }
    }
