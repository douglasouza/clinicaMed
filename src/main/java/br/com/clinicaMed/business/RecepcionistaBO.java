package br.com.clinicaMed.business;

import br.com.clinicaMed.entity.QRecepcionista;
import br.com.clinicaMed.entity.Recepcionista;
import br.com.clinicaMed.entity.Usuario;
import br.com.clinicaMed.exception.LoginNaoUnicoException;
import br.com.clinicaMed.repository.RecepcionistaRepository;
import br.com.clinicaMed.repository.UsuarioRepository;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RecepcionistaBO {

    @Autowired
    private RecepcionistaRepository repo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Object pesquisarRecepcionista(String nomeLogin) {
        if (StringUtils.isEmpty(nomeLogin))
            return repo.findAll();
        else
            return repo.findAll(getBooleanExpression(nomeLogin));
    }

    public Recepcionista inserirRecepcionista(Recepcionista recepcionista) {
        if (existeUsuarioCadastradoComMesmoLogin(recepcionista))
            throw new LoginNaoUnicoException();

        Usuario usuario = usuarioRepo.save(recepcionista.getUsuario());
        recepcionista.setId(null);
        recepcionista.setUsuario(usuario);
        return repo.saveAndFlush(recepcionista);
    }

    public Recepcionista atualizarRecepcionista(Recepcionista updatedRecepcionista, Long id) {
        if (existeUsuarioCadastradoComMesmoLogin(updatedRecepcionista))
            throw new LoginNaoUnicoException();

        Usuario usuario = usuarioRepo.save(updatedRecepcionista.getUsuario());
        updatedRecepcionista.setId(id);
        updatedRecepcionista.setUsuario(usuario);
        return repo.saveAndFlush(updatedRecepcionista);
    }

    private BooleanExpression getBooleanExpression(String nomeLogin) {
        return QRecepcionista.recepcionista.nome.containsIgnoreCase(nomeLogin)
                .or(QRecepcionista.recepcionista.usuario.login.containsIgnoreCase(nomeLogin));
    }

    private Boolean existeUsuarioCadastradoComMesmoLogin(Recepcionista recepcionista) {
        Usuario usuarioComMesmoLogin = usuarioRepo.findByLogin(recepcionista.getUsuario().getLogin());
        return usuarioComMesmoLogin != null && (usuarioComMesmoLogin.getId() != recepcionista.getUsuario().getId());
    }
}
