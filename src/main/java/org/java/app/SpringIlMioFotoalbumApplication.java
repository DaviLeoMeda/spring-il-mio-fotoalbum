package org.java.app;



import org.java.app.photo.mvc.auth.pojo.Role;
import org.java.app.photo.mvc.auth.pojo.User;
import org.java.app.photo.mvc.auth.service.RoleService;
import org.java.app.photo.mvc.auth.service.UserService;
import org.java.app.photo.pojo.Category;
import org.java.app.photo.pojo.Picture;
import org.java.app.photo.service.CategoryService;
import org.java.app.photo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {
	
	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category mount = new Category("mountains");
		Category sea = new Category("seas");
		Category people = new Category("person");
		Category women = new Category("women");
		Category landscapes = new Category("landscape");
		
		categoryService.save(mount);
		categoryService.save(sea);
		categoryService.save(people);
		categoryService.save(women);
		categoryService.save(landscapes);
		
		Picture pic1 = new Picture("Immensità", "paesaggi di montagna", "https://th.bing.com/th/id/OIP.gEh6kPR-dylbWCK_BjwMUAHaEo?pid=ImgDet&rs=1"
									, true, mount, landscapes);
		Picture pic2 = new Picture("Calma", "paesaggi marittimi", "https://th.bing.com/th/id/R.ac964908b7497244b1b50631898b4925?rik=jhJr3rCSQjUY8A&pid=ImgRaw&r=0"
				, true, sea, landscapes );
		Picture pic3 = new Picture("Miss", "ritratto di donna", "https://thumbs.dreamstime.com/b/donna-anziana-1023223.jpg"
				, true, women, people );
		
		pictureService.save(pic1);
		pictureService.save(pic2);
		pictureService.save(pic3);
		
		System.out.println("Insert done!");
		
		
		
		
		Role userRole = new Role("USER");
		Role adminRole = new Role("ADMIN");
		
		roleService.save(adminRole);
		roleService.save(userRole);
		
		final String pwdAdmin = new BCryptPasswordEncoder().encode("pwd");
		final String pwdUser = new BCryptPasswordEncoder().encode("pwd");
		
		User laforgeAdmin = new User("laforge", pwdAdmin, adminRole);
		User tuckerUser = new User("tucker", pwdUser, userRole);
		
		userService.save(laforgeAdmin);
		userService.save(tuckerUser);
	
	}

	
	
	

}
