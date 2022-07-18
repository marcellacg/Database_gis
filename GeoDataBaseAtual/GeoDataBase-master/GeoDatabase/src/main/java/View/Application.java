
package View;

import java.util.List;
import model.Aeroporto;
import model.AeroportoService;
import model.Estado;
import model.EstadoService;
import model.MesorregioesService;
import model.Microrregioes;
import model.MicrorregioesService;
import model.MunicipioService;
import model.Municipio;
import model.Rodovia;
import model.RodoviaService;
import util.JPAUtil;

public class Application {
    public static void main(String[] args) {
       MunicipioService municipioService = new MunicipioService();
       
       //EXEMPLOS
       
       //listar municipios vizinhos de campina grande
       List<Municipio> municipios = municipioService.listarMunicipiosVizinhos("Campina Grande");
       for (Municipio m: municipios){
            System.out.println(m);
       }  
       
       //distancia entre os municipios de joão pessoa e guarabira
       double distancia = municipioService.distanciaEntreMunicipios("João Pessoa", "Guarabira");
       System.out.printf("Distância em km: %.2f\n", distancia / 1000);
       
       
       //----------------------------------------------*----------------------------------------------//
       //----------------------------------------------*----------------------------------------------//
       //----------------------------------------------*----------------------------------------------//
       //-------------------------------------MINHAS PESQUISAS----------------------------------------//
       
       
       //MUNICIPIO
       
        //Qual o tipo de geometria de Cajazeiras?
       String tipoGeometria = municipioService.geometriaTipoMunicipio("Cajazeiras");
       System.out.printf("Tipo da geometria: %s", tipoGeometria);
       
       //A geometria de Guarabira está vazia?
       Boolean geometriaEmpty = municipioService.geometriaEmpty("Guarabira");
       System.out.printf("A geometria está vazia? %B%n", geometriaEmpty);
       
 
       //AEROPORTO
       
       //Quais os aeroportos localizados em Santa Catarina?
       AeroportoService aeroportoService = new AeroportoService();
       List<Aeroporto> aeroportos = aeroportoService.listarAeroportos("Santa Catarina");
       for (Aeroporto a: aeroportos){
           System.out.println(a);
       }
       
       //MICRORREGIÕES
       
       //Quais são os municipios que fazem parte da microrregião de Patos?
       MicrorregioesService microrregioesService = new MicrorregioesService();
       List<Municipio> microrregioes = microrregioesService.listarMunicipiosDaMicrorregiao("Patos");
       for (Municipio m: microrregioes){
            System.out.println(m);
       }
       
       //Quais os municipios fronteiriços da microrregião de Sousa?
       List<Municipio> microrregioess = microrregioesService.listarMunicipiosExtDaMicrorregiao("Sousa");
       for (Municipio m: microrregioess){
            System.out.println(m);
       }
       
       //RODOVIAS
       
        //Quais as rodovias com a situação "Em obras de duplicação"?
       RodoviaService rodoviaService = new RodoviaService();
       List<Rodovia> rodovias = rodoviaService.listarRodovias("Em obras de duplicação");
       for (Rodovia r: rodovias){
           System.out.println(r);
       }
       
       
       //MESORREGIÕES
       
       //Qual a dimensão topológica da mesorregião de Marajó?
       MesorregioesService mesorregioesService = new MesorregioesService();
       int tipoDimensao = mesorregioesService.tipoDimensao("Marajó");
       System.out.printf("Tipo da geometria: %d%n", tipoDimensao);
       
       
       //Quais os dados geométricos da região de Jaguaribe? (POLYGON)
       String tipoPoligono = mesorregioesService.tipoPoligono("Jaguaribe");
       System.out.printf("Dado do poligono: %s", tipoPoligono);
       
       //Quais as microrregiões do município de Garanhuns/PE?
       List<Microrregioes> micrope = microrregioesService.municipiosMicrorregiaoPE("PE", "Garanhuns");
       for (Microrregioes mz: micrope){
            System.out.println(mz);
       }
       
       //ESTADO
       
       //Qual a região onde se encontra Guarabira, no estado da Paraíba?
       EstadoService estadoService = new EstadoService();
       List<Estado> estados = estadoService.qualRegiao("PB", "Guarabira");
       for (Estado es: estados){
            System.out.println(es);
       }
       
       
       
       JPAUtil.close();
    }

  
}
