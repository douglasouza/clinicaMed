package br.com.clinicaMed.business;

import br.com.clinicaMed.dto.RecepcionistaDTO;
import br.com.clinicaMed.entity.QRecepcionista;
import br.com.clinicaMed.entity.Recepcionista;
import br.com.clinicaMed.entity.Usuario;
import br.com.clinicaMed.exception.CpfInvalidoException;
import br.com.clinicaMed.exception.CpfNaoUnicoException;
import br.com.clinicaMed.exception.LoginNaoUnicoException;
import br.com.clinicaMed.repository.RecepcionistaRepository;
import br.com.clinicaMed.repository.UsuarioRepository;
import br.com.clinicaMed.utils.CpfUtils;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecepcionistaBO {

    @Autowired
    private RecepcionistaRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Object pesquisarRecepcionista(String nomeLogin) {
        Iterable<Recepcionista> recepcionistas;
        if (StringUtils.isEmpty(nomeLogin))
            recepcionistas = repo.findAll();
        else
            recepcionistas = repo.findAll(getBooleanExpression(nomeLogin));

        return getRecepcionistasDTO(recepcionistas);
    }

    private BooleanExpression getBooleanExpression(String nomeLogin) {
        return QRecepcionista.recepcionista.nome.containsIgnoreCase(nomeLogin)
                .or(QRecepcionista.recepcionista.usuario.login.containsIgnoreCase(nomeLogin));
    }

    private List<RecepcionistaDTO> getRecepcionistasDTO(Iterable<Recepcionista> recepcionistas) {
        List<RecepcionistaDTO> recepcionistasDTO = new ArrayList<RecepcionistaDTO>();
        for (Recepcionista recepcionista : recepcionistas)
            recepcionistasDTO.add(new RecepcionistaDTO(recepcionista.getId(), recepcionista.getNome(), recepcionista.getCpf(), recepcionista.getUsuario().getLogin()));

        return recepcionistasDTO;
    }

    public Recepcionista inserirRecepcionista(Recepcionista recepcionista) {
        if (existeUsuarioCadastradoComMesmoLogin(recepcionista))
            throw new LoginNaoUnicoException();

        validarCPF(recepcionista);

        Usuario usuario = usuarioRepo.save(recepcionista.getUsuario());
        recepcionista.setId(null);
        recepcionista.setUsuario(usuario);
        return repo.saveAndFlush(recepcionista);
    }

    public Recepcionista atualizarRecepcionista(Recepcionista updatedRecepcionista, Long id) {
        if (existeUsuarioCadastradoComMesmoLogin(updatedRecepcionista))
            throw new LoginNaoUnicoException();

        validarCPF(updatedRecepcionista);

        Usuario usuario = usuarioRepo.save(updatedRecepcionista.getUsuario());
        updatedRecepcionista.setId(id);
        updatedRecepcionista.setUsuario(usuario);
        return repo.saveAndFlush(updatedRecepcionista);
    }

    private Boolean existeUsuarioCadastradoComMesmoLogin(Recepcionista recepcionista) {
        Usuario usuarioComMesmoLogin = usuarioRepo.findByLogin(recepcionista.getUsuario().getLogin());
        return usuarioComMesmoLogin != null && (usuarioComMesmoLogin.getId() != recepcionista.getUsuario().getId());
    }

    private void validarCPF(Recepcionista recepcionista) {
        if (existeRecepcionistaCadastradoComCpf(recepcionista))
            throw new CpfNaoUnicoException();

        if (!CpfUtils.ehCPFValido(recepcionista.getCpf()))
            throw new CpfInvalidoException();
    }

    private Boolean existeRecepcionistaCadastradoComCpf(Recepcionista recepcionista) {
        Recepcionista recepcionistaComMesmoLogin = repo.findByCpf(recepcionista.getCpf());
        return recepcionistaComMesmoLogin != null && (recepcionistaComMesmoLogin.getId() != recepcionista.getId());
    }
}
