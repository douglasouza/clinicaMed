package br.com.clinic.service;

import br.com.clinic.entity.Usuario;
import br.com.clinic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public List findAll() {
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Usuario findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Usuario insert(@RequestBody Usuario usuario) throws Exception {
        usuario.setId(null);
        return repo.saveAndFlush(usuario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Usuario update(@RequestBody Usuario updatedUsuario, @PathVariable Long id) {
        updatedUsuario.setId(id);
        return repo.saveAndFlush(updatedUsuario);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        repo.delete(id);
    }
}