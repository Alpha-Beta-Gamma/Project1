package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.CourseNonPercentBase;


public interface CourseNonPercentBaseManager {

   public void updateCourseNonPercent(CourseNonPercentBase courseNon);
   
   public CourseNonPercentBase getCourseNonPercent(String courseNonId);
   
   public void deleteCourseNonPercent(String courseNonId);
   
   public List<CourseNonPercentBase> listAllCourseNonPercent();
}
