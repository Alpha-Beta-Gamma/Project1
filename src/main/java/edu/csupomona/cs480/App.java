package edu.csupomona.cs480;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.csupomona.cs480.data.provider.CalculationManager;
import edu.csupomona.cs480.data.provider.ClassManager;
import edu.csupomona.cs480.data.provider.CourseNonPercentBaseManager;
import edu.csupomona.cs480.data.provider.FSCalculationManager;
import edu.csupomona.cs480.data.provider.FSClassManager;
import edu.csupomona.cs480.data.provider.FSCourseNonPercentBaseManager;
import edu.csupomona.cs480.data.provider.FSSchoolManager;
import edu.csupomona.cs480.data.provider.FSUserManager;
import edu.csupomona.cs480.data.provider.SchoolManager;
import edu.csupomona.cs480.data.provider.UserManager;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {

   /**
    * This is a good example of how Spring instantiates
    * objects. The instances generated from this method
    * will be used in this project, where the Autowired
    * annotation is applied.
    */
   @Bean
   public UserManager userManager() {
      UserManager userManager = new FSUserManager();
      return userManager;
   }

   @Bean
   public ClassManager classManager() {
      ClassManager classManager = new FSClassManager();
      return classManager;
   }

   @Bean
   public SchoolManager schoolManager() {
      SchoolManager schoolManager = new FSSchoolManager();
      return schoolManager;
   }

   @Bean
   public CalculationManager calculationManager() {
      CalculationManager calculationManager = new FSCalculationManager();
      return calculationManager;
   }
   
   @Bean 
   public CourseNonPercentBaseManager courseManager(){
      CourseNonPercentBaseManager courseManager = new FSCourseNonPercentBaseManager();
      return courseManager;
   }
   /**
    * This is the running main method for the web application.
    * Please note that Spring requires that there is one and
    * ONLY one main method in your whole program. You can create
    * other main methods for testing or debugging purposes, but
    * you cannot put extra main method when building your project.
    */
   public static void main(String[] args) throws Exception {
      // Run Spring Boot
      SpringApplication.run(App.class, args);
   }
}
