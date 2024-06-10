package progeto_semestral;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Itens {
	private int id;
	private String nome;
	private Short quantidade;
	private double pricePorUnidade;
	private double pricePorStock;
	protected String ficheiro = ".system_itens.dat";
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'as' HH:mm:ss");;
	LocalDateTime currentDateTime = LocalDateTime.now();
	String formattedDateTime = currentDateTime.format(formatter);
	Scanner keyboard = new Scanner(System.in);
	File F = new File(ficheiro);
	
	public Itens() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public double getPricePorUnidade() {
		return pricePorUnidade;
	}

	public void setPricePorUnidade(double pricePorUnidade) {
		this.pricePorUnidade = pricePorUnidade;
	}

	public double getPricePorStock() {
		return pricePorStock;
	}

	public void setPricePorStock(double pricePorStock) {
		this.pricePorStock = pricePorStock;
	}
	//////////////////////////////////////////////
	public void adicionarIten() {
		if (!F.exists()) {
			try {
				System.out.println("\n\t\033[33mFicheiro \033[32m"+ ficheiro+ "\033[33m nao encontrado...");
				F.createNewFile();
				System.out.println("\tCriando novo ficheiro em "+F.getAbsolutePath()+"\n\033[0m");
			}catch (Exception e) {
				System.out.println("Erro na crircao de ficheiro "+ficheiro+".");
			}
		}
		if(F.exists()) {
			try {
				FileWriter FW = new FileWriter(ficheiro, true);
				System.out.print("Nome do item: ");
				setNome(keyboard.nextLine());
				FW.write(getNome()+";");
	
				System.out.print("Quantidade: ");
				setQuantidade(keyboard.nextShort());
				FW.append(getQuantidade()+";");
				boolean s = true;
				do {
					System.out.print("Unidade 'KG' ou 'L' ou 'Unidades' :");
					String n = keyboard.next().toLowerCase();
					if ((n.toLowerCase().equals("kg"))||(n.toLowerCase().equals("l")) || (n.toLowerCase().equals("unidades"))) {
						if (n.toLowerCase().equals("kg")) FW.write("KG;");
						else if(n.toLowerCase().equals("unidades")) FW.write("Unidades;");
						else  FW.write("L;");
						s=false;
					}
					
					else s = true;
					
				}while(s);
				
				System.out.print("Preco por Unidade: ");
				setPricePorUnidade(keyboard.nextDouble());
				FW.append(getPricePorUnidade()+";");
								
				System.out.print("Preco por Stock: ");
				setPricePorStock(keyboard.nextDouble());
				FW.append(getPricePorStock()+";");
				
				setId((int)(Math.random()*999999));
				FW.append(getId()+";");
				
				FW.write(formattedDateTime+"\n");
				System.out.println("\nItem registrado.");
				FW.close();
			}catch (Exception e) {
				System.out.println("Erro ao adicionar item");
			}
		}
	}
	public void listarItensDisponiveis() {
		if (!F.exists()) System.out.println("Imposivel listar, o ficheiro de dados esta perdido.");
    	if (F.exists()) {
    		try {
    			FileReader FR = new FileReader(ficheiro);
    			try (BufferedReader BR = new BufferedReader(FR)) {
					String line;			
					int i=1;
					while((line= BR.readLine())!= null) {
						String linex[] = line.split("[,;]");
						System.out.println(" [ "+i+" ]→↓");
						System.out.println("\tNome do item________: "+linex[0]);
						System.out.println("\tID__________________: "+linex[5]);
						System.out.println("\tQuantidade__________: "+linex[1]+" "+linex[2]);
						System.out.println("\tPreco por unidade___: "+linex[3]);
						System.out.println("\tPreco por stock_____: " +linex[4]);
						System.out.println("\tData de cadastro____: "+linex[6]);
						System.out.println("");
						i++;
					}if (i>1)System.out.println("|←←←←←←←←←←←←[\033[1;33mFim da lista dos itens and\033[0m]→←→→←←←←←→→→|\n");
					else System.out.println("\nNao ha itens registrados");
	    			
				}catch (Exception e) {
					System.out.println("Erro na leitura do aquivo nos itens");
				}
    		}catch (Exception e) {
        		System.out.println("Erro ao  abertura do aquivo nos itens");
        		e.printStackTrace();
        	}
    	}
	}
	public void removerItem() {
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
	    		System.out.print("\nDigite o numero da linha: ");
				int num = keyboard.nextInt();
	    		if(num > 0 && num<= lines.size()) {
	    			String x[] =lines.get(num-1).split("[,;]");
	    			System.out.println("Dodos do item \033[1;31m"+x[0]+"\033[0m foram removidos");
	    			lines.remove(num -1);
	    		}
	    		else if(num>lines.size()) System.out.println("Item selecionado nao disponivel");
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
				System.out.println("ERRO! Por favor echolha o numero da linha");
			}
		}
	}

}
