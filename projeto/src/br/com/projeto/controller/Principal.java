package br.com.projeto.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.projeto.entities.Funcionario;

public class Principal {

    public static void main(String[] args) {
        Principal controller = new Principal();
        
        List<Funcionario> funcionarios = controller.criarTabelaFuncionarios();
        
        //Toda lista
        System.out.println("################### LISTA COMPLETA");
        funcionarios.stream().forEach(System.out::println);
        
        
        //Remove João
        funcionarios.remove(1);
        
        //Print configurado
        System.out.println("\r\n################### JOAO REMOVIDO");
        funcionarios.stream().forEach(System.out::println);
        
        
        //Aumento de 10%
        System.out.println("\r\n################### AUMENTO 10%");
        funcionarios.stream().forEach(x -> {
            x.setSalario(x.getSalario().multiply(new BigDecimal(1.1)));
            System.out.println(x.toString());;           
        });
        
        Map<String, List<Funcionario>> mapFunc = new HashMap<String, List<Funcionario>>();
        
        for(Funcionario funcionario : funcionarios) {
            List<Funcionario> funcsPorFuncao = mapFunc.get(funcionario.getFuncao());

            if (funcsPorFuncao == null) {
                funcsPorFuncao = new ArrayList<>();
            }
            funcsPorFuncao.add(funcionario);
            mapFunc.put(funcionario.getFuncao(), funcsPorFuncao);
        }
        
        //Funcionarios por função
        System.out.println("\r\n################### FUNCIONARIOS POR FUNÇÃO");
        for (String funcao : mapFunc.keySet()) {
            System.out.println("Função: " + funcao);
            mapFunc.get(funcao).stream().forEach(System.out::println);                 
        }
        
        //Funcionarios que fazer aniversário no mes 10 e 12
        System.out.println("\r\n################### ANIVERSARIO EM 10 ou 12");
        funcionarios.stream().forEach(x -> {
                                if(x.getDataNascimento().getMonth().equals(Month.OCTOBER) || x.getDataNascimento().getMonth().equals(Month.DECEMBER)) {
                                    System.out.println(x.toString());
                                }
                            });
        
        //Funcionario mais velho 
        System.out.println("\r\n################### FUNCIONARIO MAIS VELHO");
        LocalDate dtNasc = funcionarios.stream().map(Funcionario::getDataNascimento).min(Comparator.naturalOrder()).get();
        funcionarios.stream().filter(x -> x.getDataNascimento() == dtNasc).forEach(System.out::println);
        
        //Ordenação por nome
        System.out.println("\r\n################### ORDEM POR NOME");
        funcionarios = funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).collect(Collectors.toList());
        funcionarios.stream().forEach(System.out::println);
        
        //Soma dos salários
        System.out.println("\r\n################### SOMA DOS SALARIOS");
        DecimalFormat df = new DecimalFormat("#,###.00");
        System.out.println(df.format(funcionarios.stream().mapToDouble(x -> Double.parseDouble(x.getSalario().toString())).sum()));
        
        //Quantidade salários mínimos
        System.out.println("\r\n################### QUANTIDADE SALARIOS MINIMOS");
        funcionarios.stream().forEach(x -> {
                                            BigDecimal salarios = x.getSalario().divide(new BigDecimal(1212.00), RoundingMode.HALF_UP);
                                            System.out.println(x.getNome() + " ganha " + df.format(salarios) + " salários mínimos");
                                        });
        
    }
    
    public List<Funcionario> criarTabelaFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("2000-10-18"), new BigDecimal(2009.44), "Operador"));

        funcionarios.add(new Funcionario("João", LocalDate.parse("1990-05-12"), new BigDecimal(2284.38), "Operador"));

        funcionarios.add(new Funcionario("Caio", LocalDate.parse("1961-05-02"), new BigDecimal(9836.14), "Coordenador"));

        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("1988-10-14"), new BigDecimal(19119.88), "Diretor"));
        
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("1995-01-05"), new BigDecimal(2234.68), "Recepcionista"));
        
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("1999-11-19"), new BigDecimal(1582.72), "Operador"));
        
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("1993-03-31"), new BigDecimal(4071.84), "Contador"));
        
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("1994-07-08"), new BigDecimal(3017.45), "Gerente"));
        
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), new BigDecimal(1606.85), "Eletricista"));
        
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("1996-09-02"), new BigDecimal(2799.93), "Gerente"));

        return funcionarios;
    }
}
