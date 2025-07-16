import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService
    {
        private List<User> users;

        public UserService()
            {
                this.users = new ArrayList<>();
            }

        public User createUser(String name, String password, String email)
            {
                User user = new User(UUID.randomUUID(),name, password, email, true);
                users.add(user);
                return user;
            }

        public boolean editUser(UUID userId, String newName, String newEmail, String newPassword)
            {
                for (User user : users)
                    {
                        if (user.getId().equals(userId) && user.isActive())
                            {
                                user.setName(newName);
                                user.setEmail(newEmail);
                                user.setPassword(newPassword);
                                return true;
                            }
                    }
                return false;
            }

        public boolean deactivateUser(UUID userId)
            {
                for (User user : users)
                    {
                        if (user.getId().equals(userId) && user.isActive())
                            {
                                user.deactivate();
                                return true;
                            }
                    }
                return false;
            }

        public User getUserByEmail(String email)
            {
                for (User user : users)
                    {
                        if (user.getEmail().equalsIgnoreCase(email))
                            {
                                return user;
                            }
                    }
                return null;
            }

        public List<User> listAllUsers()
            {
                return users;
            }
    }
