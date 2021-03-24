/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio.fixação.lambda;

import entities.Trabalhador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import sun.net.www.content.audio.x_aiff;

/**
 *
 * @author Lucas Murilo
 */
public class ExercicioFixaçãoLambda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner(System.in);
        System.out.println("informe o endereco do arquivo: ");
        String path = sc.nextLine();
        
        try(BufferedReader bf = new BufferedReader(new FileReader(path))){
            
            List<Trabalhador> list = new ArrayList<>();
            String line = bf.readLine();
            
            while(line != null){
                String[] campos = line.split(",");
                list.add(new Trabalhador(campos[0], campos[1], Double.parseDouble(campos[2])));
                line = bf.readLine();
            }
            
            System.out.println("Informe o valor do salário para mostrar que recebe acima: ");
            double salario = sc.nextDouble();
            
            List<String> email = list.stream()
                    .filter(p -> p.getSalario() > salario)
                    .map(p -> p.getEmail())
                    .sorted()
                    .collect(Collectors.toList());
            
            System.out.println("Lista de emails ordenados de pessoas com sálario acima de " + salario);
            email.forEach(System.out::println);
            
            System.out.println("Digite a primeira letra do nome, para calcular o salario de todas as pessosas que comeca começam com essa letra: ");
            char letraNome = sc.next().charAt(0);
            
            double  mediasSalarioNome = list.stream()
                   .filter(p ->  p.getNome().charAt(0) == letraNome)
                   .map(p -> p.getSalario())
                   .reduce(0.0, (x , y) -> x + y);
            
            System.out.println("A soma de todos os nome com letra " + letraNome + " é de R$: " + mediasSalarioNome);
                   
                   
            
        }
        catch(IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
        
    }
    
}
