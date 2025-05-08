package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Paciente {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private LocalDate dataNascimento;

    //construtor criado para instanciar os pacientes
    public Paciente(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
    public String getNome() {// método de teste pra puxar os nomes pra ver o que tem na lista pacientes.
        return nome;
    }
}
