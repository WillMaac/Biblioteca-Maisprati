package biblioteca;

public class Biblioteca {

    private ItemBiblioteca[] itens;
    private Usuario[] usuarios;

    private int quantidadeItens;
    private int quantidadeUsuarios;

    public Biblioteca(int capacidadeItens, int capacidadeUsuarios) {
        itens = new ItemBiblioteca[capacidadeItens];
        usuarios = new Usuario[capacidadeUsuarios];

        quantidadeItens = 0;
        quantidadeUsuarios = 0;
    }

    public void cadastrarItem(ItemBiblioteca item) {

        if (quantidadeItens >= itens.length) {
            System.out.println("Não foi possível cadastrar o item. Acervo cheio.");
            return;
        }

        itens[quantidadeItens] = item;
        quantidadeItens++;

        String sufixo = item.ehFeminino() ? "a" : "o";
        System.out.println(item.getTipo() + " cadastrad" + sufixo + ": " + item.getTitulo());
    }

    public void cadastrarUsuario(Usuario usuario) {

        if (quantidadeUsuarios >= usuarios.length) {
            System.out.println("Não foi possível cadastrar o usuário. O limite foi atingido.");
            return;
        }

        usuarios[quantidadeUsuarios] = usuario;
        quantidadeUsuarios++;

        System.out.println(usuario.getTipo() + " cadastrado: " + usuario.getNome());
    }

    public void emprestar(String codigoItem, String nomeUsuario) {

        ItemBiblioteca item = buscarItem(codigoItem);
        Usuario usuario = buscarUsuario(nomeUsuario);

        if (item == null) {
            System.out.println("O item não foi encontrado.");
            return;
        }

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (!item.estaDisponivel()) {
            System.out.println(item.getTipo() + " indisponível. Empréstimo recusado.");
            return;
        }

        if (!usuario.podeEmprestar()) {
            System.out.println(
                    "Empréstimo recusado: " +
                            usuario.getNome() +
                            " atingiu o limite de " +
                            usuario.getLimiteItens() +
                            " itens."
            );
            return;
        }

        item.marcarComoEmprestado();
        usuario.adicionarEmprestimo();

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println(item.getTipo() + ": " + item.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Prazo: " + item.getPrazo() + " dias");
        System.out.println("Multa: R$ " + String.format("%.2f", item.getMulta()) + "/dia");
    }

    public void devolver(String codigoItem, String nomeUsuario) {

        ItemBiblioteca item = buscarItem(codigoItem);
        Usuario usuario = buscarUsuario(nomeUsuario);

        if (item == null) {
            System.out.println("Item não encontrado.");
            return;
        }

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (item.estaDisponivel()) {
            String artigo = item.ehFeminino() ? "Essa" : "Esse";
            System.out.println(artigo + " " + item.getTipo().toLowerCase() + " já está disponível.");
            return;
        }

        item.marcarComoDevolvido();
        usuario.removerEmprestimo();

        System.out.println("Devolução realizada com sucesso!");
    }

    public void listarAcervo() {

        System.out.println("\n===== COLEÇÃO DA BIBLIOTECA =====");

        for (ItemBiblioteca item : itens) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    private ItemBiblioteca buscarItem(String codigo) {
        for (ItemBiblioteca item : itens) {
            if (item != null && item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    private Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }
}