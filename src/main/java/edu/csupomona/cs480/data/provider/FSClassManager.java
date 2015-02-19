package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.Class1;
import edu.csupomona.cs480.data.ClassMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link ClassManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class FSClassManager implements ClassManager {

   /**
    * We persist all the user related objects as JSON.
    * <p>
    * For more information about JSON and ObjectMapper, please see:
    * http://www.journaldev.com/2324/jackson-json-processing-api-in-java-example-tutorial
    *
    * or Google tons of tutorials
    *
    */
   private static final ObjectMapper JSON = new ObjectMapper();

   /**
    * Load the user map from the local file.
    *
    * @return
    */
   private ClassMap getClassMap() {
      ClassMap classMap = null;
      File userFile = ResourceResolver.getClassFile();
      if (userFile.exists()) {
         // read the file and convert the JSON content
         // to the ClassMap object
         try {
            classMap = JSON.readValue(userFile, ClassMap.class);
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         classMap = new ClassMap();
      }
      return classMap;
   }

   /**
    * Save and persist the user map in the local file.
    *
    * @param ClassMap
    */
   private void persistClassMap(ClassMap ClassMap) {
      try {
         // convert the user object to JSON format
         JSON.writeValue(ResourceResolver.getClassFile(), ClassMap);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public Class1 getClass(String classId) {
      ClassMap ClassMap = getClassMap();
      return ClassMap.get(classId);
   }

   @Override
   public void updateClass(Class1 clas) {
      ClassMap ClassMap = getClassMap();
      ClassMap.put(clas.getId(), clas);
      persistClassMap(ClassMap);
   }

   @Override
   public void deleteClass(String userId) {
      ClassMap ClassMap = getClassMap();
      ClassMap.remove(userId);
      persistClassMap(ClassMap);
   }

   @Override
   public List<Class1> listAllClasses() {
      ClassMap ClassMap = getClassMap();
      return new ArrayList<Class1>(ClassMap.values());
   }

}
