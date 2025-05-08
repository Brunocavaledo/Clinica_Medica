package br.ufms.clinicamedica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ClinicaMedica {

    private String nome;
    private final String cnpj; // o CNPJ após inicializado não deve ser modificado
    // as listas também são "final" porque a referência não será modificada, isto é,
    // estas variáveis sempre apontarão para a mesma lista (isto é apenas uma boa prática)
    private final List<Medico> medicos;
    private final List<Secretario> secretarios;
    private final List<Paciente> pacientes;

    public ClinicaMedica(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;

        this.medicos = new ArrayList<Medico>();
        this.secretarios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    // Implemente aqui os métodos que você considerar necessário para que o sistema
    // atenda a todas as funcionalidades descritas...

    // Método para criar e adicionar um médico à clínica
    public Medico criarMedico(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento, String crm) {
        Medico medico = new Medico(nome, cpf, endereco, telefone, dataNascimento, crm);
        medicos.add(medico);
        return medico;
    }

    // Método para criar e adicionar um paciente à clínica
    public Paciente criarPaciente(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento) {
        Paciente paciente = new Paciente(nome, cpf, endereco, telefone, dataNascimento);
        pacientes.add(paciente);
        return paciente;
    }

    //  método de teste pra puxar os nomes pra ver o que tem na lista médicos.
    public void listarMedicos() {
        for (Medico medico : medicos) {
            System.out.println("Médico: " + medico.getNome());
        }
    }

    //  método de teste pra puxar os nomes pra ver o que tem na lista pacientes.
    public void listarPacientes() {
        for (Paciente paciente : pacientes) {
            System.out.println("Paciente: " + paciente.getNome());
        }
    }
}
