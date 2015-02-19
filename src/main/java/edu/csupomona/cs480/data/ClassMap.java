package edu.csupomona.cs480.data;

import java.util.HashMap;

/**
 * This class is a HashMap, but we extend the HashMap
 * class so that we can rename it to something meaningful.
 * 
 * Basically, the key of the map is the class ID, and the
 * value is the actual Class object.
 * 
 * Using a HashMap allows us to quickly query the class
 * object.
 */

@SuppressWarnings("serial")
public class ClassMap extends HashMap<String, Class> {

}
