package br.com.clinicaMed.api.common.utils;

import br.com.clinicaMed.api.modules.paciente.Paciente;
import br.com.clinicaMed.api.modules.paciente.PacienteRepository;
import br.com.clinicaMed.api.modules.recepcionista.Recepcionista;
import br.com.clinicaMed.api.modules.recepcionista.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static java.lang.Integer.parseInt;

@Component
public class CpfUtils {

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private RecepcionistaRepository recepcionistaRepo;

    public Boolean existeOutroCadastradoComMesmoCpf(Object cadastro) {
        if (cadastro instanceof Paciente) {
            Paciente cadastroPaciente = (Paciente) cadastro;
            Paciente pacienteComMesmoCPF = pacienteRepo.findByCpf(cadastroPaciente.getCpf());
            Recepcionista recepcionistaComMesmoCPF = recepcionistaRepo.findByCpf(cadastroPaciente.getCpf());
            return recepcionistaComMesmoCPF != null || (pacienteComMesmoCPF != null && (pacienteComMesmoCPF.getId() != cadastroPaciente.getId()));
        } else if (cadastro instanceof Recepcionista) {
            Recepcionista cadastroRecepcionista = (Recepcionista) cadastro;
            Paciente pacienteComMesmoCPF = pacienteRepo.findByCpf(cadastroRecepcionista.getCpf());
            Recepcionista recepcionistaComMesmoCPF = recepcionistaRepo.findByCpf(cadastroRecepcionista.getCpf());
            return pacienteComMesmoCPF != null || (recepcionistaComMesmoCPF != null && (recepcionistaComMesmoCPF.getId() != cadastroRecepcionista.getId()));
        }

        return false;
    }

    public boolean ehCPFValido(String cpf) {
        Integer soma = 0;
        Integer resto;

        if (StringUtils.isEmpty(cpf))
            return true;

        if (cpf.length() != 11)
            return false;

        if (!contemSomenteNumeros(cpf))
            return false;

        if (cpfContemTodosOsDigitosIguais(cpf))
            return false;

        for (Integer i = 1; i <= 9; i++)
            soma = soma + parseInt(cpf.substring(i - 1, i)) * (11 - i);
        resto = (soma * 10) % 11;

        if ((resto == 10) || (resto == 11))
            resto = 0;

        if (resto != parseInt(cpf.substring(9, 10)))
            return false;

        soma = 0;
        for (Integer i = 1; i <= 10; i++)
            soma = soma + parseInt(cpf.substring(i - 1, i)) * (12 - i);
        resto = (soma * 10) % 11;

        if ((resto == 10) || (resto == 11))
            resto = 0;

        if (resto != parseInt(cpf.substring(10, 11)))
            return false;

        return true;
    }

    private static boolean contemSomenteNumeros(String cpf) {
        return cpf.matches("[0-9]+");
    }

    private static boolean cpfContemTodosOsDigitosIguais(String cpf) {
        return cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999");
    }
}
