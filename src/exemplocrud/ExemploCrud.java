/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exemplocrud;

import com.mycompany.produto.Produto;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Optional;

/**
 *
 * @author 10156
 */
public class ExemploCrud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        
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
                cadastrar(listaProdutos);
            }else if(opcaoMenu == 2){
                if(listaProdutos.size() > 0)
                    alterar(listaProdutos);
                else
                    System.out.println("Não existem produtos cadastrados!");
            }else if(opcaoMenu == 3){
                if(listaProdutos.size() > 0)
                    deletar(listaProdutos);
                else
                    System.out.println("Não existem produtos cadastrados!");
            }else if(opcaoMenu == 4){
                if(listaProdutos.size() > 0)
                    listar(listaProdutos);
                else
                    System.out.println("Não existem produtos cadastrados!");
            }else{
                continuar = false;
            }
        }
        scanner.close();
    }
    
    public static void cadastrar(ArrayList<Produto> listaProdutos){
        Scanner scanner = new Scanner(System.in);
        
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

            try{
                //Pega o id do último item da lista e adiciona + 1
                id = listaProdutos.get(listaProdutos.size() - 1).getId() + 1;
            }catch(IndexOutOfBoundsException e){
                id = 0;
            }
            
            listaProdutos.add(new Produto(id, descricao, preco));
            System.out.println("Produto cadastrado com sucesso!");
            System.out.println("================================");
        }catch(NumberFormatException e){
            System.err.println("O preço deve ser um valor numérico!");
        }
    }
    
    public static void alterar(ArrayList<Produto> listaProdutos){
        Scanner scanner = new Scanner(System.in);
        
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
    
            Optional<Produto> prod = listaProdutos.stream().filter(p -> p.getId() == idSelecionado).findFirst();
            System.out.println("Produto selecionado para alteração: " + prod.get().toString());

            int index = listaProdutos.indexOf(prod.get());
            
            descricao = prod.get().getDescricao();
            preco = prod.get().getPreco();
            
            System.out.println("Nova descrição: ");
            String descAux = scanner.nextLine();
            if(descAux.trim().length() > 0)
                descricao = descAux;
            
            System.out.println("Novo valor: ");
            String precoAux = scanner.nextLine();
            if(precoAux.trim().length() > 0)
                preco = Double.parseDouble(precoAux);

            listaProdutos.set(index, new Produto(idSelecionado, descricao, preco));

            System.out.println("Produto alterado com sucesso!");
            System.out.println("================================");
        }catch(NumberFormatException e){
            System.err.println("O preço deve ser um valor numérico!");
        }catch(NoSuchElementException e){
            System.err.println("Produto não encontrado!");
        }catch(Exception e){
            System.err.println("Não foi possível alterar o produto!");
        }
    }
    
    public static void deletar(ArrayList<Produto> listaProdutos){
        Scanner scanner = new Scanner(System.in);
        
        try{
            System.out.println("================================");
            System.out.println("REMOÇÃO");
            System.out.println("================================");

            System.out.println("Qual produto deseja REMOVER? (selecione pelo id)");
            for(Object p : listaProdutos){
                System.out.println(p.toString());
            }

            int idSelecionado = scanner.nextInt();
            scanner.nextLine();

            Optional<Produto> prod = listaProdutos.stream().filter(p -> p.getId() == idSelecionado).findFirst();
            
            System.out.println("Deseja realmente remover o produto: " + prod.get().toString() + "? (S/N)");
            String opcao = scanner.nextLine();

            if(opcao.toLowerCase().equals("s")){
                listaProdutos.remove(prod.get());
                System.err.println("Produto removido com sucesso!");
            }else
                System.out.println("Remoção cancelada.");
            System.out.println("================================");
        }catch(NoSuchElementException e){
            System.err.println("Produto não encontrado!");
        }catch(Exception e){
            System.err.println("Não foi possível remover o produto!" + e);
        }
    }
    
    public static void listar(ArrayList<Produto> listaProdutos){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("================================");
        System.out.println("LISTAGEM");
        System.out.println("================================");

        for(Object p : listaProdutos){
            System.out.println(p.toString());
        }
        System.out.println("================================");
    }
}
