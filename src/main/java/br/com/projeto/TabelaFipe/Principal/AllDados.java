package br.com.projeto.TabelaFipe.Principal;

import br.com.projeto.TabelaFipe.Model.Dados;
import br.com.projeto.TabelaFipe.Model.Modelos;
import br.com.projeto.TabelaFipe.Model.Veiculos;
import br.com.projeto.TabelaFipe.Service.ConsumoApi;
import br.com.projeto.TabelaFipe.Service.ConverterDados;
import ch.qos.logback.core.encoder.JsonEscapeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AllDados {

    private Scanner leitura =  new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();
    private final String URI_BASE  = "https://parallelum.com.br/fipe/api/v1/";


//    carros/marcas

    public void menu(){

        String menu= """
                ******OPÇÕES*******
                
                Carro
                Moto
                Caminhção
                
                Digite uma das opções para consulta:
                
                """;

        System.out.println(menu);
        String json;
        String opcao = leitura.next();

        if (opcao.toLowerCase().contains("carr")){

            json = URI_BASE + "carros/marcas";

        }else if (opcao.toLowerCase().contains("mot")){
            json = URI_BASE + "motos/marcas";
        }
        else {
            json = URI_BASE + "caminhoes/marcas";
        }

        System.out.println(consumoApi.ApiHttp(json));
        String endereco = consumoApi.ApiHttp(json);


        var marca =  conversor.converterList(endereco, Dados.class);


        marca.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("\nDigite o codido da marca ");
        String codigoMarca = leitura.next();

        endereco = json+"/"+codigoMarca+"/modelos";
        json = consumoApi.ApiHttp(endereco);

        var modelosLista = conversor.converterDados(json, Modelos.class);

        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


        System.out.println("\nDigite o codigo do modelo para buscar os valores de avaliações: ");
        String codigo = leitura.next();

        endereco = endereco+"/"+codigo+"/anos";
        json = consumoApi.ApiHttp(endereco);
        List<Dados> anos =  conversor.converterList(json, Dados.class);
        List<Veiculos> automovel = new ArrayList<>();

        for (int i = 0; i < anos.size();i++){
            var enderecoAnos = endereco+"/"+ anos.get(i).codigo();
            json = consumoApi.ApiHttp(enderecoAnos);
            Veiculos veiculo = conversor.converterDados(json, Veiculos.class);
            automovel.add(veiculo);
        }
        System.out.println("Todos os veiculos filtrados");
        automovel.forEach(System.out::println);










    }





}
