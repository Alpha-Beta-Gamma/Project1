package edu.csupomona.cs480.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.math3.stat.StatUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Optional;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.Calculation;
import edu.csupomona.cs480.data.Class1;
import edu.csupomona.cs480.data.AttributeOfClass;
import edu.csupomona.cs480.data.CourseNonPercentBase;
import edu.csupomona.cs480.data.CoursePercentBase;
import edu.csupomona.cs480.data.School;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.CalculationManager;
import edu.csupomona.cs480.data.provider.ClassManager;
import edu.csupomona.cs480.data.provider.CourseNonPercentBaseManager;
import edu.csupomona.cs480.data.provider.SchoolManager;
import edu.csupomona.cs480.data.provider.UserManager;

/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {
   /**
    * When the class instance is annotated with
    * {@link Autowired}, it will be looking for the actual
    * instance from the defined beans.
    * <p>
    * In our project, all the beans are defined in
    * the {@link App} class.
    */
   @Autowired
   private UserManager userManager;

   @Autowired
   private ClassManager classManager;

   @Autowired
   private SchoolManager schoolManager;

   @Autowired
   private CalculationManager calculationManager;
   
   @Autowired
   private CourseNonPercentBaseManager courseManager;
      

   /**
    * This is a simple example of how the HTTP API works.
    * It returns a String "OK" in the HTTP response.
    * To try it, run the web application locally,
    * in your web browser, type the link:
    * 	http://localhost:8080/cs480/ping
    */
   @RequestMapping(value = "/cs480/ping", method = RequestMethod.GET)
   String healthCheck() {
      // You can replace this with other string,
      // and run the application locally to check your changes
      // with the URL: http://localhost:8080/
      return "OK!";
   }

   //for commonsIO this reads and prints bytes from a url
   @RequestMapping(value = "/cs480/commonsIO", method = RequestMethod.GET)
   void commons() throws IOException {
      InputStream in = new URL("http://cs480-projects.github.io/teams/alphabetagamma.html").openStream();
      try {
         System.out.println(IOUtils.toString(in));
      } finally {
         IOUtils.closeQuietly(in);
      }
   }

   @RequestMapping(value = "/cs480/guava", method = RequestMethod.GET)
   String guava() {

      Optional<Integer> possible = Optional.of(5);
      possible.isPresent(); // returns true
      int out = possible.get(); // returns 5

      return "this uses guava and should be five: " + out;
   }

   @RequestMapping(value = "/cs480/jsoup", method = RequestMethod.GET)
   void jsoup() {
      Document doc;
      try {

         // need http protocol
         doc = Jsoup.connect("http://facebook.com").get();

         // get page title
         String title = doc.title();
         System.out.println("title : " + title);

         // get all links
         Elements links = doc.select("a[href]");
         for (Element link : links) {

            // get the value from href attribute
            System.out.println("\nlink : " + link.attr("href"));
            System.out.println("text : " + link.text());

         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @RequestMapping(value = "/cs480/whattoexpect", method = RequestMethod.GET)
   String whatToExpect() {
      // You can replace this with other string,
      // and run the application locally to check your changes
      // with the URL: http://localhost:8080/
      return "You can expect a grade distribution of: things" + "(github upload test API)";
   }

   @RequestMapping(value = "/cs480/mathtest", method = RequestMethod.GET)
   String mathTest() {
      double[] testNumbers = new double[100];
      double result = 0;
      for (int i = 0; i < 100; i++) {
         testNumbers[i] = i + 1;
      }
      result = StatUtils.percentile(testNumbers, 5.0);
      return Double.toString(result);
   }

   @RequestMapping(value = "/cs480/whatyouget", method = RequestMethod.GET)
   String whatYouGet() {
      return "IHEARTPRESTON";
   }

   /**
    * This is a simple example of how to use a data manager
    * to retrieve the data and return it as an HTTP response.
    * <p>
    * Note, when it returns from the Spring, it will be
    * automatically converted to JSON format.
    * <p>
    * Try it in your web browser:
    * 	http://localhost:8080/cs480/user/user101
    */
   @RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.GET)
   User getUser(@PathVariable("userId") String userId) {
      User user = userManager.getUser(userId);
      return user;
   }

   @RequestMapping(value = "/classes/{classId}", method = RequestMethod.GET)
   Class1 getClass(@PathVariable("classId") String classId) {
      Class1 clas = classManager.getClass(classId);
      return clas;
   }

   @RequestMapping(value = "/classAtt", method = RequestMethod.GET)
   AttributeOfClass getClass(@RequestParam("classId") String classId, @RequestParam("index") String index) {
      return classManager.getClass(classId).getClassAttributes().get(Integer.parseInt(index));
   }

   /**
    * This is an example of sending an HTTP POST request to
    * update a user's information (or create the user if not
    * exists before).
    *
    * You can test this with a HTTP client by sending
    *  http://localhost:8080/cs480/user/user101
    *  	name=John major=CS
    *
    * Note, the URL will not work directly in browser, because
    * it is not a GET request. You need to use a tool such as
    * curl.
    *
    * @param id
    * @param name
    * @param major
    * @return
    */
   @RequestMapping(value = "/register_submit/{id}", method = RequestMethod.POST)
   User updateUser(@PathVariable("id") String id, @RequestParam("email") String email, @RequestParam("password") String password) {
      System.out.println("User registered...");
      User user = new User();
      user.setId(id);
      user.setName("Default");
      user.setMajor("Default");
      user.setEmail(email);
      user.setPass(password);
      userManager.updateUser(user);
      return user;
   }

   @RequestMapping(value = "/add_calculator", method = RequestMethod.POST)
   Calculation newCalc(@RequestParam("id") String id) {
      Calculation calc = new Calculation();
      return calc;
   }

   @RequestMapping(value = "/add_school", method = RequestMethod.POST)
   School newSchool(@RequestParam("name") String name) {
      School school = new School();
      school.setName(name);
      school.setId(schoolManager.numSchools());

      schoolManager.updateSchool(school);
      return school;
   }
   @RequestMapping(value = "/calculate", method = RequestMethod.POST)
   double calc(@RequestParam("goal") int goal, @RequestParam("attNames") String[] attNames, @RequestParam("attValues") Integer[] attValues, @RequestParam("total") Integer[] total){
      CourseNonPercentBase course;   
      double scoreTot = 0.0;
      HashMap<String, Double> dist = new HashMap<String, Double>();
       HashMap<String, Double> max = new HashMap<String, Double>();
      for(int i = 0; i < attNames.length; i++)
      {
         dist.put(attNames[i], (double) attValues[i]);
         max.put(attNames[i], (double) total[i]);
      }
         course = new CourseNonPercentBase(dist, max);
         course.finalCalculate(goal);
         scoreTot = course.getScore();
          return scoreTot;     

   }
   @RequestMapping(value = "/add_class", method = RequestMethod.POST)
   Class1 newClass(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("instructor") String instructor, @RequestParam("school") String school,
         @RequestParam("subject") String subject, @RequestParam("uniqueNumber") String uniqueNumber, @RequestParam("total") String total, @RequestParam("attNames") String[] attNames,
         @RequestParam("attValues") String[] attValues) {

      Class1 clas = new Class1();
      clas.setId(schoolManager.numSchools() + id);
      clas.setInstructor(instructor);
      clas.setName(name);
      clas.setSchool(school);
      clas.setSubject(subject);
      clas.setUniqueNumber(uniqueNumber);
      clas.setTotal(Double.parseDouble(total));

      for (int i = 0; i < attNames.length; i++) {
         AttributeOfClass ca = new AttributeOfClass(attNames[i], Double.parseDouble(attValues[i]));
         clas.addClassAttributes(ca);
      }

      classManager.updateClass(clas);
      return clas;
   }

   @RequestMapping(value = "/add_class_no_new_school", method = RequestMethod.POST)
   Class1 newClass2(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("instructor") String instructor, @RequestParam("school") String school,
         @RequestParam("subject") String subject, @RequestParam("uniqueNumber") String uniqueNumber, @RequestParam("total") String total, @RequestParam("attNames") String[] attNames,
         @RequestParam("attValues") String[] attValues) {

      Class1 clas = new Class1();
      clas.setId(id);
      clas.setInstructor(instructor);
      clas.setName(name);
      clas.setSchool(school);
      clas.setSubject(subject);
      clas.setUniqueNumber(uniqueNumber);
      clas.setTotal(Double.parseDouble(total));

      for (int i = 0; i < attNames.length; i++) {
         AttributeOfClass ca = new AttributeOfClass(attNames[i], Double.parseDouble(attValues[i]));
         clas.addClassAttributes(ca);
      }

      classManager.updateClass(clas);
      return clas;
   }

   @RequestMapping(value = "/schoolcount", method = RequestMethod.GET)
   int schoolCount() {
      return schoolManager.numSchools();
   }

   @RequestMapping(value = "/attcount/{id}", method = RequestMethod.GET)
   int atCount(@PathVariable("id") String classId) {
      return classManager.getClass(classId).getClassAttributes().size();
   }

   /**
    * This API deletes the user. It uses HTTP DELETE method.
    *
    * @param userId
    */
   @RequestMapping(value = "/cs480/user/{userId}", method = RequestMethod.DELETE)
   void deleteUser(@PathVariable("userId") String userId) {
      userManager.deleteUser(userId);
   }

   /**
    * This API lists all the users in the current database.
    *
    * @return
    */
   @RequestMapping(value = "/cs480/users/list", method = RequestMethod.GET)
   List<User> listAllUsers() {
      return userManager.listAllUsers();
   }

   @RequestMapping(value = "/classes/list", method = RequestMethod.GET)
   List<Class1> listAllClasses() {
      return classManager.listAllClasses();
   }

   @RequestMapping(value = "/schools/list", method = RequestMethod.GET)
   List<School> listAllSchools() {
      return schoolManager.listAllSchools();
   }

   @RequestMapping(value = "/schools", method = RequestMethod.GET)
   School getSchool(@RequestParam("schoolId") String schoolId) {
      School school = schoolManager.getSchool(schoolId);
      return school;
   }

   /**
    * This API lists all the majors in the database
    *
    * @return
    */
   @RequestMapping(value = "/cs480/users/listmajors", method = RequestMethod.GET)
   List<String> listAllMajors() {
      return userManager.listAllMajors();
   }

   /*********** Web UI Test Utility **********/
   /**
    * This method provide a simple web UI for you to test the different
    * functionalities used in this web service.
    */
   @RequestMapping(value = "/cs480/home", method = RequestMethod.GET)
   ModelAndView getUserHomepage() {
      ModelAndView modelAndView = new ModelAndView("home");
      modelAndView.addObject("users", listAllUsers());
      return modelAndView;
   }

   /**
    * This does something
    * @return
    */
   @RequestMapping(value = "/cs480/users/freePage", method = RequestMethod.GET)
   String placeHoler() {
      // You can replace this with other string,
      // and run the application locally to check your changes
      // with the URL: http://localhost:8080/
      return "free page";
   }

}