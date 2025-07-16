import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Serviços do sistema
        UserService userService = new UserService();
        PostService postService = new PostService();
        FriendService friendService = new FriendService();
        MessageService messageService = new MessageService();

        Map<String, UUID> userMap = new HashMap<>(); // nome → id
        UUID currentUserId = null;

        while (true) {
            System.out.println("\n===== MENU - REDE SOCIAL EM JAVA =====");
            System.out.println("1. Criar novo usuário");
            System.out.println("2. Entrar com nome de usuário");
            System.out.println("3. Editar usuário");
            System.out.println("4. Desativar usuário");
            System.out.println("5. Criar postagem");
            System.out.println("6. Ver postagens");
            System.out.println("7. Enviar pedido de amizade");
            System.out.println("8. Ver pedidos pendentes");
            System.out.println("9. Aceitar pedido de amizade");
            System.out.println("10. Ver amigos");
            System.out.println("11. Enviar mensagem privada");
            System.out.println("12. Ver inbox");
            System.out.println("13. Ver mensagens enviadas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int op = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (op) {
                case 1: {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    User novo = userService.createUser(nome, senha, email);
                    userMap.put(novo.getName(), novo.getId());
                    System.out.println("Usuário criado com sucesso: " + novo);
                    break;
                }
                case 2: {
                    System.out.print("Digite seu nome de usuário: ");
                    String nome = scanner.nextLine();
                    if (userMap.containsKey(nome)) {
                        currentUserId = userMap.get(nome);
                        System.out.println("Logado como " + nome);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                }
                case 3: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    System.out.print("Novo nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String senha = scanner.nextLine();
                    boolean ok = userService.editUser(currentUserId, nome, senha, email);
                    System.out.println(ok ? "Editado com sucesso." : "Falha ao editar.");
                    break;
                }
                case 4: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    userService.deactivateUser(currentUserId);
                    System.out.println("Conta desativada.");
                    break;
                }
                case 5: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    System.out.print("Conteúdo do post: ");
                    String content = scanner.nextLine();
                    System.out.print("Tipo (texto, imagem, video): ");
                    String tipo = scanner.nextLine();
                    Post p = postService.createPost(currentUserId, content, tipo);
                    System.out.println("Post criado: " + p);
                    break;
                }
                case 6: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    List<Post> posts = postService.getPostsByUser(currentUserId);
                    System.out.println("Seus posts:");
                    for (Post p : posts) {
                        System.out.println(p);
                    }
                    break;
                }
                case 7: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    System.out.print("Nome do usuário para enviar pedido de amizade: ");
                    String nome = scanner.nextLine();
                    if (userMap.containsKey(nome)) {
                        boolean ok = friendService.sendFriendRequest(currentUserId, userMap.get(nome));
                        System.out.println(ok ? "Pedido enviado." : "Não foi possível enviar.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                }
                case 8: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    List<FriendRequest> pendentes = friendService.getPendingRequests(currentUserId);
                    System.out.println("Pedidos pendentes:");
                    for (FriendRequest r : pendentes) {
                        System.out.println("De: " + r.getSenderId());
                    }
                    break;
                }
                case 9: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    System.out.print("Digite o nome de quem enviou o pedido: ");
                    String nome = scanner.nextLine();
                    if (userMap.containsKey(nome)) {
                        boolean aceito = friendService.acceptFriendRequest(userMap.get(nome), currentUserId);
                        System.out.println(aceito ? "Amizade aceita." : "Erro ao aceitar.");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                }
                case 10: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    Set<UUID> amigos = friendService.getFriends(currentUserId);
                    System.out.println("Seus amigos:");
                    for (UUID id : amigos) {
                        for (String nome : userMap.keySet()) {
                            if (userMap.get(nome).equals(id)) {
                                System.out.println("- " + nome);
                            }
                        }
                    }
                    break;
                }
                case 11: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    System.out.print("Enviar para (nome): ");
                    String nome = scanner.nextLine();
                    if (!userMap.containsKey(nome)) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }
                    System.out.print("Mensagem: ");
                    String conteudo = scanner.nextLine();
                    messageService.sendMessage(currentUserId, userMap.get(nome), conteudo);
                    System.out.println("Mensagem enviada.");
                    break;
                }
                case 12: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    List<Message> inbox = messageService.getInbox(currentUserId);
                    System.out.println("Caixa de entrada:");
                    for (Message m : inbox) {
                        System.out.println(m);
                    }
                    break;
                }
                case 13: {
                    if (currentUserId == null) {
                        System.out.println("Faça login primeiro.");
                        break;
                    }
                    List<Message> sent = messageService.getSentMessages(currentUserId);
                    System.out.println("Mensagens enviadas:");
                    for (Message m : sent) {
                        System.out.println(m);
                    }
                    break;
                }
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
