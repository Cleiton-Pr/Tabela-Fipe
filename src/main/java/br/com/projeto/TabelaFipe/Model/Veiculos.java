package br.com.projeto.TabelaFipe.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculos(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer ano,
        @JsonAlias("Combustivel") String tipoCombustivel
        ) {


    @Override
    public String toString() {
        return "\n***Veiculos***** " +
                "\n" +
                "valor= " + valor + '\n' +
                "marca= " + marca + '\n' +
                "modelo= " + modelo + '\n' +
                "ano= " + ano +"\n"+
                "tipo Combustivel='" + tipoCombustivel ;
    }
}
