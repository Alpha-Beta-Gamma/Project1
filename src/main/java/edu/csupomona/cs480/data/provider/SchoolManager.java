package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.School;

public interface SchoolManager {

   public School getSchool(String schoolId);

   public void updateSchool(School school);

   public void deleteSchool(String schoolId);

   public List<School> listAllSchools();

   public int numSchools();

}
