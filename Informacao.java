package progeto_semestral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Informacao {
	protected String file= ".system_info.dat";
	protected File FL = new File(file);
	public Informacao() {}
	public void ideiasBemEstarDeSaude() {
		if (!FL.exists())System.out.println("Ficheiro de informacao nao encontradeo.");
		if(FL.exists()) {
			try {
				BufferedReader BR = new BufferedReader(new FileReader(file));
				String linha;
				boolean p = false;
				System.out.print("\033[1;32m");
				while((linha = BR.readLine())!=null) {
					
					if (linha.trim().equals("-AN ST-")) p=false;
					if (p) {
						System.out.println("\t"+linha);
					}
					if (linha.trim().equals("-IN ST-")) p=true;
				}
				BR.close();
				System.out.print("\033[0m");
			}catch (Exception e) {
				System.out.println("Erro a ver informacaoes de saudde");
			}
		}
	}
	public void comoPrevenirDoencas() {
		if (!FL.exists())System.out.println("Ficheiro de informacao nao encontradeo.");
		if(FL.exists()) {
			try {
				BufferedReader BR = new BufferedReader(new FileReader(file));
				String linha;
				System.out.println("\n");
				boolean p = false;
				System.out.print("\033[1;33m");
				while((linha = BR.readLine())!=null) {
					
					if (linha.trim().equals("-AN_PDA-")) p=false;
					if (p) {
						System.out.println("\t"+linha);
					}
					if (linha.trim().equals("-AN_PDS-")) p=true;
				}
				System.out.print("\033[0m");
				BR.close();
			}catch (Exception e) {
				System.out.println("Erro a ver informacaoes de saudde");
			}
		}
	}

	public void comoCombaterMalaria() {
		
	}
	public void comoCombaterColera() {
		
	}
}
