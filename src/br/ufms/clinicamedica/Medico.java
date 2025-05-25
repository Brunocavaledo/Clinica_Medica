package br.ufms.clinicamedica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario {
    private String crm;
    private final List<Consulta> agenda; // Lista de consultas do médico

    /**
     * Constrói um objeto Medico especificando todos os atributos.
     *
     * @param nome           nome do médico
     * @param cpf            CPF do médico
     * @param endereco       endereço do médico (pode ser null)
     * @param telefone       telefone de contato
     * @param dataNascimento data de nascimento do médico
     * @param crm            CRM do médico
     */
    public Medico(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento, String crm) {
        super(nome, cpf, endereco, telefone, dataNascimento);
        setCrm(crm);
        this.agenda = new ArrayList<>(); // Inicializa a agenda do médico
    }

    /**
     * Verifica se o médico está disponível no horário desejado.
     *
     * @param dataHora Horário da consulta desejada.
     * @return true se o médico estiver disponível, false caso contrário.
     */
    public boolean medicoDisponivel(LocalDateTime dataHora) {
        for (Consulta consulta : agenda) {
            if (consulta.getDataHora().equals(dataHora)) {
                return false; // Médico já tem uma consulta nesse horário
            }
        }
        return true;
    }

    /**
     * Agenda uma consulta se o médico estiver disponível.
     *
     * @param consulta Consulta a ser agendada.
     */
    public void agendarConsulta(Consulta consulta) {
        if (!medicoDisponivel(consulta.getDataHora())) {
            throw new IllegalArgumentException("O médico já tem uma consulta agendada neste horário.");
        }
        agenda.add(consulta);
    }

    public List<Consulta> getAgenda() {
        return agenda;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        if (crm == null || crm.isBlank()) {
            throw new IllegalArgumentException("CRM inválido. O formato correto é: ######-AA");
        }

        crm = crm.trim();
        if (!crm.matches("^\\d{1,6}-[A-Za-z]{2}$")) {
            throw new IllegalArgumentException("CRM inválido.");
        }

        String siglaEstado = crm.split("-")[1].toUpperCase();

        try {
            Estado.fromSigla(siglaEstado);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("CRM inválido. Estado '" + siglaEstado + "' não existe.");
        }

        this.crm = crm.split("-")[0] + "-" + siglaEstado;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Medico outro) {
            return this.getCpf().equals(outro.getCpf());
        }
        return false;
    }
}