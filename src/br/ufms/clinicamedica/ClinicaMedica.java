package br.ufms.clinicamedica;

import java.time.LocalDateTime;
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
    private final List<Consulta> consultas;// eu adicionei essa lista para guardar as consultas

    public ClinicaMedica(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;

        this.medicos = new ArrayList<Medico>();
        this.secretarios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.consultas = new ArrayList<>();// faz par com a  linha 17
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

    //Método para criar uma consulta
    public Consulta criarConsulta(Medico medico, Paciente paciente, String sintomas, LocalDateTime dataHora, double valor) {
        Consulta consulta = new Consulta(medico, paciente, sintomas, dataHora, valor);
        consultas.add(consulta);
        return consulta;
    }
    public Medico buscarMedicoPorNome(String nome) {
        for (Medico medico : medicos) {
            if (medico.getNome().equalsIgnoreCase(nome)) { // Ignora maiúsculas e minúsculas
                return medico;
            }
        }
        return null; // Retorna null se não encontrar
    }
}
