package progeto_semestral;
import java.io.BufferedReader;
import java.io.FileReader;
public class Login extends Funcionario {
	public Login() {
	}
	public boolean login() {
		try {
			FileReader FR = new FileReader(ficheiro);
			try (BufferedReader BR = new BufferedReader(FR)) {
				String line;
				//
				System.out.print("Digite o seu ID: ");
				setId(keybord.next());
				System.out.print("Digite a sua palavra pass:\033[1;8m ");
				setPass(keybord.next());
				System.out.println("\33[0m");
				while((line= BR.readLine())!= null) {
					String linex[] = line.split("[,;]");
					if((getId().equals(linex[3])) & (getPass().equals(linex[2]))) {
						System.out.println("[!] Bem vindo(a) "+linex[0].toUpperCase());
						return true;
					}
				}
				BR.close();
			}catch (Exception e) {
				System.out.println("Erro na leitura do aquivo no login");
			}
			FR.close();
		}catch (Exception e) {
			System.out.println("Erro na abertura do aquivo no login");
		}
		return false;
	}
}
