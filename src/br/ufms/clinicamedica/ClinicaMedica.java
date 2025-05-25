package br.ufms.clinicamedica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClinicaMedica {
    private String nome;
    private final String cnpjFormatado;
    private final String cnpj;

    private final List<Medico> medicos;
    private final List<Secretario> secretarios;
    private final List<Paciente> pacientes;

    public ClinicaMedica(String nome, String cnpj) {
        this.nome = nome;

        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        cnpj = cnpj.trim();
        if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}") || cnpj.chars().distinct().count() == 1) { //regex para cnpf, exige que cnpj seja escrito da forma tradicional.
            throw new IllegalArgumentException("CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX.");
        }
        this.cnpj = cnpj;
        this.cnpjFormatado = formatarCnpj(cnpj);

        this.medicos = new ArrayList<>();
        this.secretarios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public void adicionarSecretario(Secretario secretario) {
        if (secretario == null) {
            throw new IllegalArgumentException("Secretário inválido.");
        }
        if (secretarios.contains(secretario)) {
            throw new IllegalArgumentException("Secretário já adicionado.");
        }
        secretarios.add(secretario);
    }

    public void adicionarMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("Médico inválido.");
        }
        if (medicos.contains(medico)) {
            throw new IllegalArgumentException("Médico já adicionado.");
        }
        medicos.add(medico);
    }

    public void adicionarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente inválido.");
        }
        if (pacientes.contains(paciente)) {
            throw new IllegalArgumentException("Paciente já adicionado.");
        }
        pacientes.add(paciente);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCnpjFormatado() {
        return cnpjFormatado;
    }

    private String formatarCnpj(String cnpj) {
        return cnpj.substring(0, 2) + "." +
                cnpj.substring(2, 5) + "." +
                cnpj.substring(5, 8) + "/" +
                cnpj.substring(8, 12) + "-" +
                cnpj.substring(12, 14);
    }

    // Método para criar uma consulta garantindo que o médico está disponível
    public Consulta criarConsulta(Medico medico, Paciente paciente, String sintomas, LocalDateTime dataHora, double valor) {
        if (!medico.medicoDisponivel(dataHora)) {
            throw new IllegalArgumentException("O médico já tem uma consulta agendada neste horário.");
        }

        Consulta consulta = new Consulta(medico, paciente, sintomas, dataHora, valor);
        medico.agendarConsulta(consulta); // Adiciona a consulta diretamente na agenda do médico
        return consulta;
    }

    public Medico buscarMedicoPorNome(String nome) {
        for (Medico medico : medicos) {
            if (medico.getNome().equalsIgnoreCase(nome)) {
                return medico;
            }
        }
        return null;
    }
}