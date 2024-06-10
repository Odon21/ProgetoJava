package progeto_semestral;
import java.io.File;

import java.io.FileWriter;
import java.util.Scanner;
public class Consultas extends Paciente {
	protected String ficheiro = ".system_consultas.dat";
	Scanner ke = new Scanner(System.in);
	File F =new File(ficheiro);
	public Consultas() {}
	public void marcarConsulta() {
		try {
			if(!F.exists()) {
				System.out.println("\n\t\033[33mFicheiro \033[32m"+ ficheiro+ "\033[33m nao encontrado...");
	    		F.createNewFile();
	    		System.out.println("\tCriando novo ficheiro em "+F.getAbsolutePath()+"\n\033[0m");
			}
			//String nome[] = null;
			if (F.exists()) {
				FileWriter FW = new FileWriter(ficheiro);
				//nome[0] = marcar()[0];
				
				System.out.print("Quais sao os sintomas que tens  "+marcar()[0]+": ");
				FW.append(ke.nextLine()+"#");
				System.out.print("A quanto tempo tens os sintomas: ");
				FW.append(ke.next()+"#");
				FW.write(formattedDateTime+"\n");
				System.out.println("Pre consulta marcada as "+formattedDateTime);
				FW.close();
				System.out.println("\n Aproxima a unidade sanitaria mais proxima...\n Estaremos la por te atender");
			}
		}catch (Exception e) {
			System.out.println("");
		}
	}
}
