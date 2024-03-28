/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exemplocrud;

import com.mycompany.produto.Produto;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 10156
 */
public class ExemploCrud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList listaProdutos = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        
        boolean continuar = true;
        int opcaoMenu;
        
        while(continuar){
            System.out.println("Selecione uma oção: ");
            System.out.println("1 - Cadastrar: ");
            System.out.println("2 - Alterar: ");
            System.out.println("3 - Deletar: ");
            System.out.println("4 - Listar: ");
            opcaoMenu = scanner.nextInt();
            scanner.nextLine();
            
            if(opcaoMenu == 1){
                try{
                    int id = 0;
                    String descricao;
                    Double preco;

                    System.out.println("================================");
                    System.out.println("CADASTRO");
                    System.out.println("================================");
                    System.out.println("Descrição: ");
                    descricao = scanner.nextLine();

                    System.out.println("Preço: ");
                    preco = Double.parseDouble(scanner.nextLine());

                    id = listaProdutos.size();

                    listaProdutos.add(new Produto(id, descricao, preco));
                    System.out.println("Produto cadastrado com sucesso!");
                    System.out.println("================================");
                }catch(NumberFormatException e){
                    System.err.println("O preço deve ser um valor numérico!");
                }
            }else if(opcaoMenu == 2){
                try{
                    String descricao;
                    Double preco;

                    System.out.println("================================");
                    System.out.println("ALTERAÇÃO");
                    System.out.println("================================");

                    System.out.println("Qual produto deseja ALTERAR? (selecione pelo id)");
                    for(Object p : listaProdutos){
                        System.out.println(p.toString());
                    }

                    int idSelecionado = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Produto selecionado para alteração: " + listaProdutos.get(idSelecionado));

                    System.out.println("Nova descrição: ");
                    descricao = scanner.nextLine();

                    System.out.println("Novo valor: ");

                    preco = Double.parseDouble(scanner.nextLine());

                    listaProdutos.set(idSelecionado, new Produto(idSelecionado, descricao, preco));

                    System.out.println("Produto alterado com sucesso!");
                    System.out.println("================================");
                }catch(NumberFormatException e){
                    System.err.println("O preço deve ser um valor numérico!");
                }catch(IndexOutOfBoundsException e){
                    System.err.println("Produto não encontrado!");
                }catch(Exception e){
                    System.err.println("Não foi possível alterar o produto!");
                }
            }else if(opcaoMenu == 3){
                try{
                    System.out.println("================================");
                    System.out.println("REMOÇÃO");
                    System.out.println("================================");

                    if(listaProdutos.size() <= 0){
                        System.out.println("Não existem produtos cadastrados.");
                        System.out.println("================================");
                        continue;
                    }

                    System.out.println("Qual produto deseja REMOVER? (selecione pelo id)");
                    for(Object p : listaProdutos){
                        System.out.println(p.toString());
                    }

                    int idSelecionado = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Deseja realmente remover o produto: " + listaProdutos.get(idSelecionado) + "? (S/N)");
                    String opcao = scanner.nextLine();

                    if(opcao.toLowerCase().equals("s")){
                        listaProdutos.remove(idSelecionado);
                        System.err.println("Produto removido com sucesso!");
                    }else
                        System.out.println("Remoção cancelada.");
                    System.out.println("================================");
                }catch(IndexOutOfBoundsException e){
                    System.err.println("Produto não encontrado!");
                }catch(Exception e){
                    System.err.println("Não foi possível remover o produto!");
                }
            }else if(opcaoMenu == 4){
                System.out.println("================================");
                System.out.println("LISTAGEM");
                System.out.println("================================");
                    
                if(listaProdutos.size() <= 0){
                    System.out.println("Não existem produtos cadastrados.");
                    System.out.println("================================");
                    continue;
                }
                
                for(Object p : listaProdutos){
                    System.out.println(p.toString());
                }
                System.out.println("================================");
            }
            else{
                continuar = false;
            }
        }
        scanner.close();
    }
}
