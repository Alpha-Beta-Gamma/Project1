package edu.csupomona.cs480.data.provider;

import java.util.List;

import edu.csupomona.cs480.data.Class1;

public interface ClassManager {

   public Class1 getClass(String classId);

   public void updateClass(Class1 clas);

   public void deleteClass(String userId);

   public List<Class1> listAllClasses();

}
