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

    private static long contador = 1;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora, double valor) {
        this(medico, paciente, null, dataHora, valor);
    }

    public Consulta(Medico medico, Paciente paciente, String sintomas, LocalDateTime dataHora, double valor) {
        this.codigo = contador++;
        this.medico = medico;
        this.paciente = paciente;
        this.sintomas = sintomas;
        this.dataHora = dataHora;
        this.valor = valor;
        this.exames = new ArrayList<>();
        this.receita = "";
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getReceita() {
        return receita;
    }

    public List<String> getExames() {
        return exames;
    }

    /**
     * Gera uma receita médica para o paciente.
     *
     * @param receita Texto da receita com os medicamentos e instruções.
     */
    public void gerarReceita(String receita) {
        if (receita == null || receita.isBlank()) {
            throw new IllegalArgumentException("A receita não pode estar vazia.");
        }
        this.receita = receita;
    }

    /**
     * Solicita um exame para o paciente.
     *
     * @param exame Nome do exame solicitado pelo médico.
     */
    public void solicitarExame(String exame) {
        if (exame == null || exame.isBlank()) {
            throw new IllegalArgumentException("O exame não pode estar vazio.");
        }
        exames.add(exame);
    }

    @Override
    public String toString() {
        return "Consulta #" + codigo +
                " | Médico: " + medico.getNome() +
                " | Paciente: " + paciente.getNome() +
                " | Data/Hora: " + dataHora +
                " | Valor: R$" + valor +
                " | Receita: " + (receita.isBlank() ? "Nenhuma" : receita) +
                " | Exames: " + (exames.isEmpty() ? "Nenhum" : exames);
    }
}