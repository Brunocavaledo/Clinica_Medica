package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        try {
            Secretario secretario = new Secretario(
                    "Kleber Kruger",
                    "12345678909",
                    null,
                    "67991234567",
                    LocalDate.of(1988, 12, 8),
                    LocalDate.now(), 30000
            );

            ClinicaMedica clinica = new ClinicaMedica("Clínica Universitária", "52.223.432.0001-65");
            // Implemente seus testes aqui...
            // Experimente cadastrar um secretário, médico, paciente, agendar uma consulta...

            // Criando um endereço pro médico
            Endereco enderecoMedico = new Endereco(
                    setLogradouro("Avenida Central"),
                    456,
                    "Sala 203",
                    "Jardim América",
                    "79001-000",
                    "Coxim",
                    Estado.MS
            );

            Medico medico = new Medico(
                    "Dra Nathalie Cavalcante",
                    "03281723160",
                    enderecoMedico,
                    "67999095242",
                    LocalDate.of(1988, 11, 01),
                    "178254-5"
            );
            // Criando um endereço pro paciente e adicionando um paciente
            Endereco enderecoPaciente = new Endereco();
            enderecoPaciente.setLogradouro("Rua das Flores");
            enderecoPaciente.setNumero(123);

            Paciente paciente = clinica.criarPaciente(
                    "Bruno Cavalcante",
                    "94372047134",
                    enderecoPaciente,
                    "67999095498",
                    LocalDate.of(1973, 9, 14)
            );

            // Exibir médicos cadastrados
            clinica.listarMedicos();

            // Exibir pacientes cadastrados
            clinica.listarPacientes();


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
