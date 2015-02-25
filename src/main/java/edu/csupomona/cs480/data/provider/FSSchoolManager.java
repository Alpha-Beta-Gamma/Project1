package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.School;
import edu.csupomona.cs480.data.SchoolMap;
import edu.csupomona.cs480.util.ResourceResolver;

/**
 * The implementation of {@link ClassManager} interface
 * using file system.
 * <p>
 * This class demonstrates how you can use the file system
 * as a database to store your data.
 *
 */
public class FSSchoolManager implements SchoolManager {

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
   private SchoolMap getSchoolMap() {
      SchoolMap schoolMap = null;
      File userFile = ResourceResolver.getSchoolFile();
      if (userFile.exists()) {
         try {
            schoolMap = JSON.readValue(userFile, SchoolMap.class);
         } catch (IOException e) {
            e.printStackTrace();
         }
      } else {
         schoolMap = new SchoolMap();
      }
      return schoolMap;
   }

   private void persistSchoolMap(SchoolMap SchoolMap) {
      try {
         // convert the user object to JSON format
         JSON.writeValue(ResourceResolver.getSchoolFile(), SchoolMap);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public School getSchool(String schoolId) {
      SchoolMap SchoolMap = getSchoolMap();
      return SchoolMap.get(schoolId);
   }

   @Override
   public void updateSchool(School school) {
      SchoolMap SchoolMap = getSchoolMap();
      SchoolMap.put(school.getId(), school);
      persistSchoolMap(SchoolMap);
   }

   @Override
   public void deleteSchool(String userId) {
      SchoolMap SchoolMap = getSchoolMap();
      SchoolMap.remove(userId);
      persistSchoolMap(SchoolMap);
   }

   @Override
   public List<School> listAllSchools() {
      SchoolMap SchoolMap = getSchoolMap();
      return new ArrayList<School>(SchoolMap.values());
   }

   public int numSchools() {
      return getSchoolMap().values().size();
   }

}
