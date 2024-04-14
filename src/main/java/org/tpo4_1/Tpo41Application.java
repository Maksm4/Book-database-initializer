package org.tpo4_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Tpo41Application {


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Tpo41Application.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
        BlogRepository blogRepository = context.getBean(BlogRepository.class);
        ArticleRepository articleRepository = context.getBean(ArticleRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);


        //copying reference so that user has a blog
        for (User u : userRepository.listAllUsers())
        {
            Blog blog = blogRepository.searchBlogByManager(u);
            u.setBlog(blog);
            userRepository.save(u);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Blogger database");
            System.out.println("------------------");
            System.out.println("1. view Roles");
            System.out.println("2. view Articles");
            System.out.println("3. view Blogs");
            System.out.println("4. view Users");
            System.out.println("5. add role");
            System.out.println("6. add User");
            System.out.println("7. add Blog");
            System.out.println("8. add Article");
            System.out.println("9. delete role");
            int action = scanner.nextInt();


             if (action == 1)
            {
                System.out.println(roleRepository.listAllRoles());

            }else if (action == 2)
            {
                System.out.println(articleRepository.listAllArticles());
             }else if (action == 3)
             {
                 for (Blog b : blogRepository.listAllBlogs())
                 {
                     System.out.println(b);
                 }

             }else if (action == 4)
             {
                 System.out.println(userRepository.listAllUsers());
             } else if (action == 5) {
                System.out.println("enter name of the role:");
                String name = scanner.next();
                Role role = new Role(name);
                roleRepository.save(role);
            } else if (action == 6) {
                User user = new User();
                System.out.println("enter email:");
                String email = scanner.next();
                user.setEmail(email);
                System.out.println("do you want to add blog?1- yes /2 - no");
                int decision = scanner.nextInt();
                if (decision == 1) {
                    System.out.println("enter blog id");
                    Long id = scanner.nextLong();
                    if (blogRepository.findById(id).isPresent()){
                        Blog blog = blogRepository.findById(id).get();
                        user.setBlog(blog);
                        userRepository.save(user);
                        blog.setManager(user);
                        blogRepository.save(blog);
                    }
                }else {
                    userRepository.save(user);
                }
            } else if (action == 7) {
                Blog blog = new Blog();
                System.out.println("enter name");
                String name = scanner.next();
                blog.setName(name);
                System.out.println("do oyu want to add manager 1-yes/ 2-no");
                int decision = scanner.nextInt();
                if (decision == 1) {
                    System.out.println("enter manager id");
                    Long id = scanner.nextLong();
                    if (userRepository.findById(id).isPresent()){
                        User user = userRepository.findById(id).get();
                        blog.setManager(user);
                        blogRepository.save(blog);
                        user.setBlog(blog);
                        userRepository.save(user);
                    }
                }else {
                    blogRepository.save(blog);
                }
            } else if (action == 8) {
                Article article = new Article();
                System.out.println("enter title ");
                String title = scanner.next();
                article.setTitle(title);
                System.out.println("do you want to add author?1-yes/ 2-no");
                int decision = scanner.nextInt();
                if (decision == 1) {
                    System.out.println("enter manager id");
                    Long id = scanner.nextLong();
                    if (userRepository.findById(id).isPresent())
                        article.setAuthor(userRepository.findById(id).get());

                }
                System.out.println("do you want to add blog? 1-yes /2- no");
                int decision2 = scanner.nextInt();
                if (decision2 == 1) {
                    System.out.println("enter blog id");
                    Long id = scanner.nextLong();
                    if (blogRepository.findById(id).isPresent())
                        article.setBlog(blogRepository.findById(id).get());

                }
                articleRepository.save(article);
            }else if (action == 9){
                 System.out.println("by what you want to delete:");
                 System.out.println("1. id 2. name");
                 int decision = scanner.nextInt();
                 if (decision == 1)
                 {
                     System.out.println("enter id of role");
                     Long id = scanner.nextLong();
                     roleRepository.deleteAllById(id);
                 }else if(decision ==2)
                 {
                     System.out.println("enter name of role");
                     String name = scanner.next();
                     roleRepository.deleteAllByName(name);
                 }
             }

        }

    }
}
