package br.com.clinicamed.api.modules.solicitacaoexame;

import br.com.clinicamed.api.common.exception.FormatoArquivoInvalido;
import br.com.clinicamed.api.common.exception.TamanhoArquivoExcedido;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/solicitacaoExame")
public class SolicitacaoExameRestController {

    @Autowired
    private SolicitacaoExameRepository repo;

    @Autowired
    private SolicitacaoExameBO bo;

    @RequestMapping(method = RequestMethod.GET)
    public Object findAll(String filtro) {
        return bo.pesquisarSolicitacaoExame(filtro);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SolicitacaoExame findOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public SolicitacaoExame insert(@RequestBody SolicitacaoExame solicitacaoExame) throws Exception {
        return bo.inserirSolicitacaoExame(solicitacaoExame);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SolicitacaoExame update(@RequestBody SolicitacaoExame updatedSolicitacaoExame, @PathVariable Long id) {
        return bo.atualizarSolicitacaoExame(updatedSolicitacaoExame, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        bo.removerSolicitacaoExame(id);
    }

    @RequestMapping(value = "/{id}/downloadResultadoExame", method = RequestMethod.GET)
    public void downloadResultadoExame(@PathVariable Long id, HttpServletResponse response) throws IOException {
        SolicitacaoExame solicitacaoExame = repo.findOne(id);
        IOUtils.copy(new ByteArrayInputStream(Base64.decodeBase64(solicitacaoExame.getResultado())), response.getOutputStream());
        response.setContentType(solicitacaoExame.getArquivoMimeType());
        response.setHeader("Content-Disposition", "attachment; filename=" + solicitacaoExame.getNomeArquivoResultado());
        response.flushBuffer();
    }

    @RequestMapping(value = "/{id}/uploadResultadoExame", method = RequestMethod.PUT, params = {"nomeArquivo", "mimeType"})
    public SolicitacaoExame uploadResultadoExame(@RequestBody byte[] resultadoExame,
                                                 @PathVariable Long id,
                                                 @RequestParam("nomeArquivo") String nomeArquivo,
                                                 @RequestParam("mimeType") String mimeType) {
        if (!"application/pdf".equals(mimeType))
            throw new FormatoArquivoInvalido();

        if (resultadoExame.length > 20971520)
            throw new TamanhoArquivoExcedido();

        return bo.uploadResultadoExame(resultadoExame, nomeArquivo, mimeType, id);
    }

    @RequestMapping(value = "/{id}/removerResultadoExame", method = RequestMethod.PUT)
    public SolicitacaoExame removerResultadoExame(@PathVariable Long id) {
        return bo.removerResultadoExame(id);
    }
}