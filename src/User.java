import java.util.UUID;

public class User
    {
        //definicao dos elementos da classe
        private UUID id;
        private String name;
        private String password;
        private String email;
        private boolean active;
        private PrivacyLevel postPrivacy = PrivacyLevel.PUBLIC;
        private PrivacyLevel messagePrivacy = PrivacyLevel.PUBLIC;
        private PrivacyLevel friendListPrivacy = PrivacyLevel.PUBLIC;

        //construtor da classe
        public User(UUID id, String name, String password, String email, boolean active)
            {
             this.id = UUID.randomUUID();
             this.name = name;
             this.password = password;
             this.email = email;
             this.active = true;
            }

        //funcoes da classe user usadas para retornar os elementos(Getters)
        public UUID getId()
            {
                return id;
            }
        public String getName()
            {
                return name;
            }
        public String getPassword()
            {
                return password;
            }
        public String getEmail()
            {
                return email;
            }
        public boolean isActive()
            {
                return active;
            }

        //funcoes para definir os elementos(Setter)
        public void setName(String name)
            {
                this.name = name;
            }
        public void setPassword(String password)
            {
                this.password = password;
            }
        public void setEmail(String email)
            {
                this.email = email;
            }
        public void deactivate()
            {
                this.active = false;
            }
        public PrivacyLevel getPostPrivacy() {
            return postPrivacy;
        }

        public void setPostPrivacy(PrivacyLevel postPrivacy) {
            this.postPrivacy = postPrivacy;
        }

        public PrivacyLevel getMessagePrivacy() {
            return messagePrivacy;
        }

        public void setMessagePrivacy(PrivacyLevel messagePrivacy) {
            this.messagePrivacy = messagePrivacy;
        }

        public PrivacyLevel getFriendListPrivacy() {
            return friendListPrivacy;
        }

        public void setFriendListPrivacy(PrivacyLevel friendListPrivacy) {
            this.friendListPrivacy = friendListPrivacy;
        }


        @Override
        public String toString()
            {
                return "User{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", email='" + email + '\'' +
                        ", active=" + active +
                        '}';
            }
    }
