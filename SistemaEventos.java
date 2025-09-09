import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEventos {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n1. Cadastrar usuário\n2. Cadastrar evento\n3. Listar eventos\n4. Confirmar presença\n5. Cancelar presença\n6. Ver eventos confirmados\n7. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> cadastrarEvento();
                case 3 -> listarEventos();
                case 4 -> confirmarPresenca();
                case 5 -> cancelarPresenca();
                case 6 -> verEventosConfirmados();
            }
        } while (opcao != 7);
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        usuarios.add(new Usuario(nome, email, cidade));
    }

    private void cadastrarEvento() {
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria (Festa, Esporte, Show, Feira, Outros): ");
        String categoria = scanner.nextLine();
        System.out.print("Horário (AAAA-MM-DDTHH:MM): ");
        LocalDateTime horario = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        eventos.add(new Evento(nome, endereco, categoria, horario, descricao));
    }

    private void listarEventos() {
        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);
            System.out.println(i + " - " + e.getNome() + " (" + e.getCategoria() + ") - " + e.getHorario());
        }
    }

    private void confirmarPresenca() {
        Usuario usuario = selecionarUsuario();
        listarEventos();
        System.out.print("Escolha o evento: ");
        int index = scanner.nextInt();
        usuario.confirmarEvento(eventos.get(index));
    }

    private void cancelarPresenca() {
        Usuario usuario = selecionarUsuario();
        List<Evento> confirmados = usuario.getEventosConfirmados();
        for (int i = 0; i < confirmados.size(); i++) {
            System.out.println(i + " - " + confirmados.get(i).getNome());
        }
        System.out.print("Escolha o evento para cancelar: ");
        int index = scanner.nextInt();
        usuario.cancelarEvento(confirmados.get(index));
    }

    private void verEventosConfirmados() {
        Usuario usuario = selecionarUsuario();
        for (Evento e : usuario.getEventosConfirmados()) {
            System.out.println(e.getNome() + " - " + e.getHorario());
        }
    }

    private Usuario selecionarUsuario() {
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println(i + " - " + usuarios.get(i).getNome());
        }
        System.out.print("Escolha o usuário: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return usuarios.get(index);
    }
}
