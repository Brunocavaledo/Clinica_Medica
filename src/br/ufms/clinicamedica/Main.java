package br.ufms.clinicamedica;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

            // Criando um endereço pro médico 1
            Endereco enderecoMedico = new Endereco(); // Criamos o objeto vazio
            enderecoMedico.setLogradouro("Avenida Central");
            enderecoMedico.setNumero(456);
            enderecoMedico.setComplemento("Sala 203");
            enderecoMedico.setBairro("Jardim América");
            enderecoMedico.setCep("79400-000");
            enderecoMedico.setCidade("Coxim");
            enderecoMedico.setEstado(Estado.MS);

            Medico medico = clinica.criarMedico(
                    "Dra Nathalie Cavalcante",
                    "03281723160",
                    enderecoMedico, // Agora passamos o objeto Endereco completo
                    "67999095242",
                    LocalDate.of(1988, 11, 01),
                    "178254-5"
            );

            // Criando um endereço pro médico 2
            Endereco enderecoMedico2 = new Endereco(); // Criamos o objeto vazio
            enderecoMedico2.setLogradouro("Avenida Mato Grosso");
            enderecoMedico2.setNumero(789);
            enderecoMedico2.setComplemento("Casa de esquina");
            enderecoMedico2.setBairro("Vila Bela");
            enderecoMedico2.setCep("79400-000");
            enderecoMedico2.setCidade("Coxim");
            enderecoMedico2.setEstado(Estado.MS);

            Medico medico2 = clinica.criarMedico(
                    "Dr Pimpolho",
                    "46835645059",
                    enderecoMedico2, // Agora passamos o objeto Endereco completo
                    "67999586352",
                    LocalDate.of(1990, 10, 15),
                    "20514-5"
            );


            // Criando um endereço pro paciente e adicionando um paciente
            Endereco enderecoPaciente = new Endereco();
            enderecoPaciente.setLogradouro("Rua dos Peixes Fritos");
            enderecoPaciente.setNumero(258);
            enderecoPaciente.setComplemento("Sala 171");
            enderecoPaciente.setBairro("Jardim Empamonado");
            enderecoPaciente.setCep("79400-000");
            enderecoPaciente.setCidade("Coxim");
            enderecoPaciente.setEstado(Estado.MS);

            Paciente paciente = clinica.criarPaciente(
                    "Bruno Cavalcante",
                    "94372047134",
                    enderecoPaciente,
                    "67999095498",
                    LocalDate.of(1979, 9, 14)
            );

            // Criando uma consulta:
            Medico medicoSelecionado = clinica.buscarMedicoPorNome("Dra Nathalie Cavalcante");
            Consulta consulta = clinica.criarConsulta(
                    medico,
                    paciente,
                    "Dor do ombro direito",
                    LocalDateTime.of(2025, 6, 17, 10, 30),
                    450.00
            );

            // Aqui é um teste que imprime tudo que foi salvo no console, para confirmar que deu certo a criação dos objetos e seus atributos.
            System.out.println(medico);
            System.out.println(paciente);
            System.out.println(secretario);
            System.out.println(consulta);


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
