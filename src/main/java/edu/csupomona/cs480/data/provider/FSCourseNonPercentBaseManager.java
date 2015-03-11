package edu.csupomona.cs480.data.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.csupomona.cs480.data.CourseNonPercentBase;
import edu.csupomona.cs480.data.CourseNonPercentBaseMap;
import edu.csupomona.cs480.util.ResourceResolver;

public class FSCourseNonPercentBaseManager implements CourseNonPercentBaseManager{

   private static final ObjectMapper JSON = new ObjectMapper();
   private Object CourseNonPercentBaseMap;
   
   private CourseNonPercentBaseMap getCourseNonMap(){
      CourseNonPercentBaseMap calcMap = null;
      File calcFile = ResourceResolver.getCourseFile();
       if (calcFile.exists()) {
            try {
               calcMap = JSON.readValue(calcFile, CourseNonPercentBaseMap.class);
            } catch (IOException e) {
               e.printStackTrace();
            }
         } else {
            calcMap = new CourseNonPercentBaseMap();
         }
         return calcMap;
   }
   private void persistCalculationMap(CourseNonPercentBaseMap calcMap) {
      try {
         // convert the user object to JSON format
         JSON.writeValue(ResourceResolver.getCourseFile(), CourseNonPercentBaseMap);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   @Override
   public void updateCourseNonPercent(CourseNonPercentBase courseNon) {
      CourseNonPercentBaseMap CourseNonPercentBaseMap = getCourseNonMap();
      CourseNonPercentBaseMap.put("course", courseNon);
      persistCalculationMap(CourseNonPercentBaseMap);
   }

   @Override
   public CourseNonPercentBase getCourseNonPercent(String courseNonId) {
      CourseNonPercentBaseMap CourseNonPercentBaseMap = getCourseNonMap();
      return CourseNonPercentBaseMap.get(courseNonId);
   }

   @Override
   public void deleteCourseNonPercent(String courseNonId) {
      CourseNonPercentBaseMap CourseNonPercentBaseMap = getCourseNonMap();
      CourseNonPercentBaseMap.remove(courseNonId);
      persistCalculationMap(CourseNonPercentBaseMap);
   }

   @Override
   public List<CourseNonPercentBase> listAllCourseNonPercent() {
      CourseNonPercentBaseMap CourseNonPercentBaseMap = getCourseNonMap();
      return new ArrayList<CourseNonPercentBase>(CourseNonPercentBaseMap.values());
   }

}
