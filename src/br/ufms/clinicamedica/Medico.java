package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Medico {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private LocalDate dataNascimento;
    private String crm;

    //construtor criado para instanciar os médicos
    public Medico(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento, String crm) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.crm = crm;
    }
    public String getNome() {// método de teste pra puxar os nomes pra ver o que tem na lista médicos.
        return nome;
    }
}
