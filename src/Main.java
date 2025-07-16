public class Main
    {
        public static void main(String[] args)
            {
                UserService userservice = new UserService();


                User user1 = userservice.createUser("Nous", "321", "nous@gmail.com");
                System.out.println(user1);

            }
    }
