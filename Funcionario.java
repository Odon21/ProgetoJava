package progeto_semestral;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario {
	private String id;
	private String nome;
    protected String ficheiro = ".system_funcionario.dat";
    private String pass;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'as' HH:mm:ss");;
	LocalDateTime currentDateTime = LocalDateTime.now();
	String formattedDateTime = currentDateTime.format(formatter);
    Scanner keybord = new Scanner(System.in);
    File F = new File(ficheiro);
    
public Funcionario() {
        super();
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	boolean setPass() {
    	String pass1, pass2;
        System.out.print("Digite a nova seha:\033[8m ");
        pass1 = keybord.next();
        System.out.print("\033[0mDigite novamente a senha:\033[8m ");
        pass2 = keybord.next();
        System.out.println("\033[0m");
        if (pass1.equals(pass2)) {
        	setPass(pass1);
        	System.out.println("Funcionario Cadastrado!");
        	return true;
        }
        System.out.println("Palavas passes sao diferentes");
		return false;
    }
    public void cadastrarFuncionario() {
    	try {
	    	if(!F.exists()) {
	    		System.out.println("\n\t\033[33mFicheiro \033[32m"+ ficheiro+ "\033[33m nao encontrado...");
	    		F.createNewFile();
	    		System.out.println("\tCriando novo ficheiro em "+F.getAbsolutePath()+"\n\033[0m");
	    	}
	    	if (F.exists()) {
	    		FileWriter FW = new FileWriter(ficheiro, true);
	    		System.out.println("\n [ Digite o nome do funcionario ] ");
	            System.out.println(" --------------------------------");
	            System.out.print(" Nome: ");
	            FW.write(keybord.nextLine()+";");            
	            int option;
	            boolean status =false;
	            do {
	                System.out.println("\n [ Escolha o cargo do funcionario ] ");
	                System.out.println("======================================");
	                System.out.println(" 1 - Medico Enfermeiro");
	                System.out.println(" 2 - Tecnico de Enfermagem");
	                System.out.println(" 3 - Estagiario");
	                System.out.println(" 4 - Especialista");
	                System.out.println(" 5 - Terapeuta");
	                System.out.println(" 6 - Fisioterapeuta");
	                System.out.println(" 7 - Farmaceutico");
	                System.out.println(" 8 - Assistente Social");
	                System.out.println(" 9 - Seguranca");
	                System.out.println("=====================================");
	                System.out.print("\nCargo: ");
	                option = keybord.nextInt();	                
	                switch (option) {
	                    case 1: {
	                    	FW.write("Medico Enfermeiro;");
	                    	status =false;
	                    }break;
	                    case 2: {
	                    	FW.append("Tecnico de Enfermagem;");
	                    	status =false;
	                    }break;
	                    case 3: {
	                    	FW.append("Estagiario;");
	                    	status =false;
	                    }break;
	                    case 4: {
	                    	FW.append("Especialista;");
	                    	status =false;
	                    }break;
	                    case 5:{
	                    	FW.append("Terapeuta;");
	                    	status =false;
	                    }break;
	                    case 6: {
	                    	FW.append("Fisioterapeuta;");
	                    	status =false;
	                    }break;
	                    case 7: {
	                    	FW.append("Farmaceutico;");
	                    	status =false;
	                    }break;
	                    case 8: {
	                    	FW.append("Assistente Social;");
	                    	status =false;
	                    }break;
	                    case 9:{
	                    	FW.append("Seguranca;");
	                    	status = false;
	                    }break;
	                    case 0: System.out.println("_↓↓↓↓_");break;
	                    default: {
	                    	System.out.println("\n\033[31m Opcao invalida...\033[0m\n");
	                    	status =true; break;
	                    }
	                }
	            }while(status);
	            	        
	            do{        	
	            }while (!setPass());
	            FW.append(pass+";");
	            int identity = (int)(Math.random()*999999);
	            FW.write(identity+";");
	            
	            FW.write(formattedDateTime);
	            FW.append("\n");
	            FW.close();
	    	}
    	}catch (Exception e) {
    		System.out.println("\nErro ao adicionar Funcionario\n");
    		//e.printStackTrace();
		}   
    }
    public void listarFuncionarios() {
    	if (!F.exists()) System.out.println("Imposivel listar, o ficheiro de dados esta perdido.");
    	if (F.exists()) {
    		try {
    			FileReader FR = new FileReader(ficheiro);
    			try (BufferedReader BR = new BufferedReader(FR)) {
					String line;			
					int i=1;
					while((line= BR.readLine())!= null) {
						String linex[] = line.split("[,;]");
						System.out.println("[+++++++++++++++++++++++++++[ "+i+" ]++++++++++++++++++++++++++]\n");
						System.out.println("\tNome..............: "+linex[0]);
						System.out.println("\tID................: "+linex[3]);
						System.out.println("\tCargo.............: "+linex[1]);
						System.out.println("\tSenha.............: \033[8m"+linex[2]+"\033[0m");
						System.out.println("\tData de cadastro..: "+linex[4]);
						System.out.println("");
						i++;
					}if (i>1)System.out.println("|←←←←←←←←←←←←←←←←←←←←←←←←←[\033[1;33mThe and\033[0m]→→←→←←←←←←←→→←←←←←←←←→→→|\n");
					else System.out.println("\nNao ha funcionarios Cadastrados");
	    			
				}catch (Exception e) {
					System.out.println("Erro na leitura do aquivo no funcionario");
				}
    			
    			
    		}catch (Exception e) {
        		System.out.println("Erro ao  abertura do aquivo no funcionario");
        		e.printStackTrace();
        	}
    	}
    }
    //public void atualizarFuncionario() 
    public void removerFuncionario() {
    	if(!F.exists()) System.out.println("Erro Arquivo nao encontrado");
    	if (F.exists()) {
    		try {
    			List<String> lines = new ArrayList<>();
    			FileReader FR = new FileReader(ficheiro);
    			BufferedReader reader = new BufferedReader(FR);
        		String line;
        		int i=0;
        		while((line = reader.readLine())!= null) {
        			String x[]= line.split("[,;]");
        			lines.add(line);
        			System.out.println("["+(i+1)+"] "+x[0]);
        			i++;
        		}
        		reader.close();
        		if (i<1) {
        			System.out.println("Ficheiro vazio");
        			return;
        		}
        		System.out.println("\nDigite o numero da linha: ");
    			int num = keybord.nextInt();
        		if(num > 0 && num<= lines.size()) {
        			String x[] =lines.get(num-1).split("[,;]");
        			System.out.println("Dodos do funcionario \033[1;31m"+x[0]+"\033[0m removidos");
        			lines.remove(num -1);
        		}
        		else if(num>lines.size()) System.out.println("Funcionario selecionado nao disponivel");
        		else {
        			System.out.println("\nNao ha funcionarios Cadastrados");
        			return;
        		}
        		//FileWriter FW = new FileWriter(ficheiro);
        		BufferedWriter W = new BufferedWriter(new FileWriter(ficheiro));
        		for (String l: lines) {
        			W.write(l);
        			W.newLine();;
        		}
        		W.close();
        	}
    		catch(Exception e) {
    			//e.printStackTrace();
    			System.out.println("ERRO");
    		}
    	}
    	
    	
    }
   
}