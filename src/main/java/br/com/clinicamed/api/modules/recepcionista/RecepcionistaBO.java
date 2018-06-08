package br.com.clinicamed.api.modules.recepcionista;

import br.com.clinicamed.api.common.exception.CpfInvalidoException;
import br.com.clinicamed.api.common.exception.CpfNaoUnicoException;
import br.com.clinicamed.api.common.exception.LoginNaoUnicoException;
import br.com.clinicamed.api.common.utils.CpfUtils;
import br.com.clinicamed.security.usuario.Usuario;
import br.com.clinicamed.security.usuario.UsuarioBO;
import br.com.clinicamed.security.usuario.UsuarioRepository;
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

    @Autowired
    private UsuarioBO usuarioBO;

    @Autowired
    private CpfUtils cpfUtils;

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

        Usuario usuario = usuarioBO.salvarUsuario(recepcionista.getUsuario());
        recepcionista.setId(null);
        recepcionista.setUsuario(usuario);
        return repo.saveAndFlush(recepcionista);
    }

    public Recepcionista atualizarRecepcionista(Recepcionista updatedRecepcionista, Long id) {
        if (existeUsuarioCadastradoComMesmoLogin(updatedRecepcionista))
            throw new LoginNaoUnicoException();

        validarCPF(updatedRecepcionista);

        Usuario usuario = usuarioBO.salvarUsuario(updatedRecepcionista.getUsuario());
        updatedRecepcionista.setId(id);
        updatedRecepcionista.setUsuario(usuario);
        return repo.saveAndFlush(updatedRecepcionista);
    }

    public void removerRecepcionista(Long idRecepcionista) {
        Recepcionista recepcionista = repo.findOne(idRecepcionista);
        repo.delete(recepcionista.getId());
        usuarioRepo.delete(recepcionista.getUsuario().getId());
    }

    private Boolean existeUsuarioCadastradoComMesmoLogin(Recepcionista recepcionista) {
        Usuario usuarioComMesmoLogin = usuarioRepo.findByLogin(recepcionista.getUsuario().getLogin());
        return usuarioComMesmoLogin != null && (usuarioComMesmoLogin.getId() != recepcionista.getUsuario().getId());
    }

    private void validarCPF(Recepcionista recepcionista) {
        if (cpfUtils.existeOutroCadastradoComMesmoCpf(recepcionista))
            throw new CpfNaoUnicoException();

        if (!cpfUtils.ehCpfValido(recepcionista.getCpf()))
            throw new CpfInvalidoException();
    }
}
