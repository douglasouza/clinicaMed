package br.com.clinicaMed.security.service;

import br.com.clinicaMed.security.utils.SegurancaUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguranca")
public class UsuarioService {

    @RequestMapping(value = "/logado", method = RequestMethod.GET)
    public Object getUsuarioLogado() {
        return SegurancaUtils.getLoginUsuarioLogado();
    }
}