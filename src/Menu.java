import java.util.*;

public class Menu
{
    Scanner scanner = new Scanner(System.in);

    // Serviços do sistema
    UserService userService = new UserService();
    PostService postService = new PostService();
    FriendService friendService = new FriendService();
    MessageService messageService = new MessageService();
    EventService eventService = new EventService();
    GroupService groupService = new GroupService();
    NotificationService notificationService = new NotificationService();

    Map<String, UUID> userMap = new HashMap<>(); // nome → id
    UUID currentUserId = null;
    boolean runMenu()
    {
        System.out.println("\n===== REDE SOCIAL DO MTB DO LAL =====");
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
        System.out.println("14. Criar grupo");
        System.out.println("15. Ver grupos");
        System.out.println("16. Postar em grupo");
        System.out.println("17. Ver postagens do grupo");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");

        int op = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        switch (op)
        {
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
                return true;
            }
            case 2: {
                System.out.print("Digite seu e-mail: ");
                String email = scanner.nextLine();

                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();

                User user = userService.authenticate(email, senha);

                if (user != null) {
                    this.currentUserId = user.getId();
                    System.out.println("Login bem-sucedido! Olá, " + user.getName());
                } else {
                    System.out.println("E-mail ou senha incorretos.");
                }
                return true;
            }
            case 3: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo email: ");
                String email = scanner.nextLine();
                System.out.print("Nova senha: ");
                String senha = scanner.nextLine();
                boolean ok = userService.editUser(currentUserId, nome, senha, email);
                System.out.println(ok ? "Editado com sucesso." : "Falha ao editar.");
                return true;
            }
            case 4: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                userService.deactivateUser(currentUserId);
                System.out.println("Conta desativada.");
                return true;
            }
            case 5: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                System.out.print("Conteúdo do post: ");
                String content = scanner.nextLine();
                System.out.print("Tipo (texto, imagem, video): ");
                String tipo = scanner.nextLine();
                Post p = postService.createPost(currentUserId, content, tipo);
                System.out.println("Post criado: " + p);
                return true;
            }
            case 6: {

                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }

                List<Post> userPosts = postService.getPostsByUser(currentUserId);
                for (Post p : userPosts) {
                    System.out.println("ID: " + p.getId());
                    System.out.println("Conteúdo: " + p.getContent());
                    System.out.println("Curtidas: " + p.getLikes().size());

                    System.out.print("Deseja curtir este post? (s/n): ");
                    String escolha = scanner.nextLine();
                    if (escolha.equalsIgnoreCase("s")) {
                        boolean sucesso = postService.likePost(p.getId(), currentUserId);
                        if (sucesso) {
                            System.out.println("Post curtido com sucesso!");
                        } else {
                            System.out.println("Você já curtiu este post.");
                        }
                        System.out.println("Curtidas atualizadas: " + p.getLikes().size());
                    }
                    System.out.println("--------------------");
                }
                return true;


            }
            case 7: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                System.out.print("Nome do usuário para enviar pedido de amizade: ");
                String nome = scanner.nextLine();
                if (userMap.containsKey(nome)) {
                    boolean ok = friendService.sendFriendRequest(currentUserId, userMap.get(nome));
                    System.out.println(ok ? "Pedido enviado." : "Não foi possível enviar.");
                } else {
                    System.out.println("Usuário não encontrado.");
                }
                return true;
            }
            case 8: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;                }
                List<FriendRequest> pendentes = friendService.getPendingRequests(currentUserId);
                System.out.println("Pedidos pendentes:");
                for (FriendRequest r : pendentes) {
                    System.out.println("De: " + r.getSenderId());
                }
                return true;
            }
            case 9: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                System.out.print("Digite o nome de quem enviou o pedido: ");
                String nome = scanner.nextLine();
                if (userMap.containsKey(nome)) {
                    boolean aceito = friendService.acceptFriendRequest(userMap.get(nome), currentUserId);
                    System.out.println(aceito ? "Amizade aceita." : "Erro ao aceitar.");
                } else {
                    System.out.println("Usuário não encontrado.");
                }
                return true;
            }
            case 10: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
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
                return true;
            }
            case 11: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                System.out.print("Enviar para (nome): ");
                String nome = scanner.nextLine();
                if (!userMap.containsKey(nome)) {
                    System.out.println("Usuário não encontrado.");
                    return true;
                }
                System.out.print("Mensagem: ");
                String conteudo = scanner.nextLine();
                messageService.sendMessage(currentUserId, userMap.get(nome), conteudo);
                System.out.println("Mensagem enviada.");
                return true;
            }
            case 12: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                List<Message> inbox = messageService.getInbox(currentUserId);
                System.out.println("Caixa de entrada:");
                for (Message m : inbox) {
                    String senderName = userService.getUserById(m.getSenderId()).getName();
                    String receiverName = userService.getUserById(m.getReceiverId()).getName();
                    System.out.println("Mensagem de " + senderName + " para " + receiverName + ": " + m.getContent());
                }
                return true;
            }
            case 13: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                List<Message> sent = messageService.getSentMessages(currentUserId);
                System.out.println("Mensagens enviadas:");
                for (Message m : sent) {
                    System.out.println(m);
                }
                return true;

            }
            case 14: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                System.out.print("Nome do grupo: ");
                String nomeGrupo = scanner.nextLine();
                System.out.print("Descrição: ");
                String descricao = scanner.nextLine();

                Group grupo = groupService.createGroup(nomeGrupo, descricao, currentUserId);
                System.out.println("Grupo criado com sucesso: " + grupo);
                return true;
            }

            case 15: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }
                List<Group> grupos = groupService.getGroupsForUser(currentUserId);
                if (grupos.isEmpty()) {
                    System.out.println("Você não participa de nenhum grupo.");
                } else {
                    System.out.println("Seus grupos:");
                    for (Group g : grupos) {
                        System.out.println("- " + g.getName() + ": " + g.getDescription());
                    }
                }
                return true;
            }

            case 16: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }

                List<Group> grupos = groupService.getGroupsForUser(currentUserId);
                if (grupos.isEmpty()) {
                    System.out.println("Você não participa de nenhum grupo.");
                    return true;
                }

                System.out.println("Escolha o grupo para postar:");
                for (int i = 0; i < grupos.size(); i++) {
                    System.out.println((i + 1) + ". " + grupos.get(i).getName());
                }

                int escolha = scanner.nextInt();
                scanner.nextLine();
                if (escolha < 1 || escolha > grupos.size()) {
                    System.out.println("Escolha inválida.");
                    return true;
                }

                Group grupo = grupos.get(escolha - 1);
                System.out.print("Conteúdo da postagem: ");
                String conteudo = scanner.nextLine();
                System.out.print("Tipo (texto, imagem, video): ");
                String tipo = scanner.nextLine();

                Post post = postService.createPost(currentUserId, conteudo, tipo);
                boolean adicionado = groupService.addPostToGroup(grupo.getId(), post);
                System.out.println(adicionado ? "Post publicado no grupo!" : "Falha ao postar.");
                return true;
            }

            case 17: {
                if (currentUserId == null) {
                    System.out.println("Faça login primeiro.");
                    return true;
                }

                List<Group> grupos = groupService.getGroupsForUser(currentUserId);
                if (grupos.isEmpty()) {
                    System.out.println("Você não participa de nenhum grupo.");
                    return true;
                }

                System.out.println("Escolha o grupo para ver as postagens:");
                for (int i = 0; i < grupos.size(); i++) {
                    System.out.println((i + 1) + ". " + grupos.get(i).getName());
                }

                int escolha = scanner.nextInt();
                scanner.nextLine();
                if (escolha < 1 || escolha > grupos.size()) {
                    System.out.println("Escolha inválida.");
                    return true;
                }

                Group grupo = grupos.get(escolha - 1);
                System.out.println("Postagens no grupo '" + grupo.getName() + "':");
                for (Post p : grupo.getPosts()) {
                    System.out.println("Usuário: " + userService.getUserById(p.getUserId()).getName());
                    System.out.println("Conteúdo: " + p.getContent());
                    System.out.println("Curtidas: " + p.getLikes().size());

                    System.out.print("Deseja curtir este post? (s/n): ");
                    String resposta = scanner.nextLine();
                    if (resposta.equalsIgnoreCase("s")) {
                        boolean sucesso = postService.likePost(p.getId(), currentUserId);
                        if (sucesso) {
                            System.out.println("Post curtido com sucesso!");
                        } else {
                            System.out.println("Você já curtiu este post.");
                        }
                    }
                    System.out.println("-------------------------");
                }
                return true;
            }

            case 0:
                System.out.println("Encerrando...");
                return false;
            default:
                System.out.println("Opção inválida.");


        }
        return true;
    }

}


