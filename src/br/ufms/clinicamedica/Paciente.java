package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Paciente extends Usuario {

    /**
     * Constrói um objeto Paciente informando apenas os atributos obrigatórios.
     *
     * @param nome    nome do paciente
     * @param cpf     CPF do paciente
     * @param telefone telefone de contato
     * @param dataNascimento data de nascimento do paciente
     */
    public Paciente(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        this(nome, cpf, null, telefone, dataNascimento);
    }

    /**
     * Constrói um objeto Paciente especificando todos os atributos.
     *
     * @param nome           nome do paciente
     * @param cpf            CPF do paciente
     * @param endereco       endereço do paciente
     * @param telefone       telefone de contato
     * @param dataNascimento data de nascimento do paciente
     */
    public Paciente(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento) {
        super(nome, cpf, endereco, telefone, dataNascimento);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Paciente outro) {
            return this.getCpf().equals(outro.getCpf());
        }
        return false;
    }
}