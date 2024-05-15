package br.com.projeto.TabelaFipe.Service;

import java.util.List;

public interface IgenericDados {

    <T> T converterDados( String json, Class<T> classe);


    <T> List<T> converterList(String json, Class<T> classe);


}
