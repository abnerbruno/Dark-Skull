package br.com.fatec.DarkSkull.fachada;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

@AllArgsConstructor


public class Fachada implements IFachada {


    private Map<JavaClass, JpaRepository>listaDaos;

    public Fachada() {
    }


    @Override
    public String salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public String alterar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }
}
