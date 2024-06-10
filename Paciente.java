package progeto_semestral;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Login{
	protected int codigo;
	protected String doenca;
	protected String familhar;
	protected int idade;
	protected String sintomas;
	protected String ficheiro = ".system_pacientes.dat";
	
	public Paciente() {
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDoenca() {
		return doenca;
	}
	public void setDoenca(String goenca) {
		this.doenca = goenca;
	}
	public String getFamilhar() {
		return familhar;
	}
	public void setFamilhar(String familhar) {
		this.familhar = familhar;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getSintomas() {
		return sintomas;
	}
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	public void cadastrarPaciente() {
		try {
			if(!F.exists()) {
				System.out.println("\n\t\033[33mFicheiro \033[32m"+ ficheiro+ "\033[33m nao encontrado...");
	    		F.createNewFile();
	    		System.out.println("\tCriando novo ficheiro em "+F.getAbsolutePath()+"\n\033[0m");
			}
			if (F.exists()) {
				FileWriter FWP = new FileWriter(ficheiro, true);
				System.out.println("| Digite os dados do Paciente |");
	            System.out.println(" -----------------------------");
	            System.out.print("Nome: ");
	            FWP.write(keybord.nextLine()+";");
	            
	            System.out.print("Idade: ");
	            setIdade(keybord.nextInt());
	            FWP.append(getIdade()+";");
	            
	            System.out.print("Nome do familhar Proximo: ");
	            setFamilhar(keybord.next());
	            FWP.append(getFamilhar()+";");
	            
	            System.out.print("Nome da doenca: ");
	            setDoenca(keybord.next());
	            FWP.append(getDoenca()+";");
	            
	            System.out.print("Sintomas: ");
	            setSintomas(keybord.next()+";");
	            FWP.append(getSintomas());
	            int id = (int)(Math.random()*999999);
	            FWP.append(id+";");
	            
	            FWP.append(formattedDateTime);
	            FWP.append("\n");
	            FWP.close();
	            System.out.println("\nPaciente cadastrado.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao cadastrara paciente");
		}
		
	}
	public void listarPacientes() {
		if (!F.exists()) System.out.println("Imposivel listar, o ficheiro de dados esta perdido.");
		if (F.exists()) {
			try {
    			FileReader FR = new FileReader(ficheiro);
    			try (BufferedReader BR = new BufferedReader(FR)) {
					String line;			
					int i=1;
					while((line= BR.readLine())!= null) {
						String linex[] = line.split("[,;]");
						System.out.println("[==========================[ "+i+" ]===========================]\n");
						System.out.println("\tNome...............: "+linex[0]);
						System.out.println("\tIdade..............: "+linex[1]);
						System.out.println("\tIdentificacao ID...: "+linex[5]);
						System.out.println("\tFamilhar proximo...: "+linex[2]);
						System.out.println("\tNome da doenca.....: "+linex[3]);
						System.out.println("\tSintomas...........: "+linex[4]);
						System.out.println("\tData de cadastro...: "+linex[6]);
						System.out.println("\n");
						i++;
					}
					System.out.println(" ___________________________________________________________");
					System.out.println("/********************[ Fim da lista ]***********************\\");
					FR.close();
					BR.close();
				}catch (Exception e) {
					System.out.println("Erro na leitura do aquivo no funcionario");
				}
			}catch (Exception e) {
				System.out.println("Erro ao abrir o ficheiro no funcionario");
			}
		}
	}
	public String[] localizarPaciente() {
		if (!F.exists())System.out.println("Erro! Ficheiro nao encontrado");
		if (F.exists()) {
			try {
				BufferedReader BR = new BufferedReader(new FileReader(ficheiro));
				String line;
				System.out.print("Digite o nome ou o codigo do paciente: ");
				String nome = keybord.next().toLowerCase();
				int i=0;
				while((line = BR.readLine())!=null) {
					String linex[] = line.split("[,;]");
					if ((linex[0].toLowerCase().contains(nome)) || (linex[5].contains(nome))) {
						System.out.println("\n[::::::::::::::::{ "+linex[0]+" }:::::::::::::::::::::::]");
						System.out.println("\tNome completo......: "+linex[0]);
						System.out.println("\tIdade..............: "+linex[1]);
						System.out.println("\tIdentificacao ID...: "+linex[5]);
						System.out.println("\tFamilhar proximo...: "+linex[2]);
						System.out.println("\tNome da doenca.....: "+linex[3]);
						System.out.println("\tSintomas...........: "+linex[4]);
						System.out.println("\tData de cadastro...: "+linex[6]);
						System.out.println("\n");
						i=9999998;
					}i++;
					return linex;
				}
				if(i!=9999999)System.out.println("Paciente nao encontrado");
				if(i<1)System.out.println("Nehum paciente cadastrado");
				BR.close();
			}catch (Exception e) {
				System.out.println("Erro na pesquisa de paciente");
			}
		}
		return null;
	}
	public void removerPaciente() {
		if(!F.exists()) System.out.println("\nErro! Ficheiro nao encontrado");
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
        			System.out.println("\nNao ha pacientes Cadastrados");
        			return;
        		}
        		System.out.println("\nDigite o numero da linha: ");
    			int num = keybord.nextInt();
        		if(num > 0 && num<= lines.size()) {
        			String x[] =lines.get(num-1).split("[,;]");
        			System.out.println("Dodos do Paciente \033[1;31m"+x[0]+"\033[0m removidos");
        			lines.remove(num -1);
        		}else {
        			System.out.println("\nNao ha pacientes Cadastrados");
        			return;
        		}
        		BufferedWriter W = new BufferedWriter(new FileWriter(ficheiro));
        		for (String l: lines) {
        			W.write(l);
        			W.newLine();;
        		}
        		W.close();
        	}catch(Exception e) {
        		//e.printStackTrace();
    			System.out.println("ERRO");
    		}
    	}
	}
	public String[] marcar() {
		if (!F.exists())System.out.println("Erro! Ficheiro nao encontrado");
		if (F.exists()) {
			try {
				BufferedReader BR = new BufferedReader(new FileReader(ficheiro));
				String line;
				System.out.print("Digite o nome ou o codigo do paciente: ");
				String nome = keybord.next().toLowerCase();
				int i=0;
				while((line = BR.readLine())!=null) {
					String linex[] = line.split("[,;]");
					if ((linex[0].toLowerCase().contains(nome)) || (linex[5].contains(nome))) {
						
						i=9999998;
						return linex;
					}i++;
				}
				if(i!=9999999) {
					System.out.println("\n\tPaciente nao encontrado \n\tPor favor faca cadastro e tente novamente");
					return null;
				}
				if(i<1)System.out.println("");
				BR.close();
			}catch (Exception e) {
				System.out.println("Erro na pesquisa de paciente");
			}
		}
		return null;
	}
}
