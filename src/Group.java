import java.util.*;
public class Group
    {
        private UUID id;
        private String name;
        private String description;
        private String ownerId;
        private String image;
        private Set<UUID> members;
        List <Post> posts;

        public Group(String name, String description, UUID ownerId)
        {
            this.name = name;
            this.description = description;
            this.ownerId = ownerId.toString();
            this.image = image;
            this.members = new HashSet<>();
            this.posts = new ArrayList <>();
            members.add(ownerId);
        }

        public UUID getId()
        {
            return id;
        }
        public String getName()
            {
                return name;
            }
        public String getDescription()
            {
                return description;
            }
        public Set<UUID> getMembers()
            {
                return members;
            }

        public List<Post> getPosts()
            {
                return posts;
            }

        public boolean addMember(UUID userId)
            {
                return members.add(userId);
            }

        public boolean removeMember(UUID userId)
            {
                if (userId.equals(ownerId)) return false; // dono não pode ser removido
                return members.remove(userId);
            }

        public boolean addPost(Post post)
            {
                if (members.contains(post.getUserId()))
                    {
                        posts.add(post);
                        return true;
                    }
                return false;
            }

        @Override
        public String toString()
            {
                return "Group{" +
                        "name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", members=" + members.size() +
                        '}';
            }
    }
