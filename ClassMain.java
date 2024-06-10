package progeto_semestral;

public class ClassMain {
	public static void main(String[] args) {
		Paciente L1 = new Paciente();
		Informacao information = new Informacao();
		Itens item1 = new Itens();
		Consultas cn1 = new Consultas();
		cn1.marcarConsulta();
		item1.adicionarIten();
		item1.listarItensDisponiveis();
		item1.removerItem();
		L1.login();
		L1.cadastrarFuncionario();
		L1.listarFuncionarios();
		L1.cadastrarPaciente();
		L1.listarPacientes();
		L1.cadastrarPaciente();
		L1.listarPacientes();
		L1.removerFuncionario();
		L1.removerPaciente();
		L1.localizarPaciente();
		information.ideiasBemEstarDeSaude();
		information.comoPrevenirDoencas();
		
	}
}
