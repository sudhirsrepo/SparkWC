package com.sudhir.scala.spark  //The package

//Import all required spark classes
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCount extends App{
  println("Hello Spark from Scala .... ");
  
  // Create spark configuration
  val conf = new SparkConf().setAppName("WordCount").setMaster("local");
  
  //Create Spark context from spark configuration
  val sc = new SparkContext(conf);
  
  val f_in = "/Users/sudhir/Downloads/index.txt";
  println("Reading.... " + f_in);
  
  //Get reference of the input file
  val inputFile = sc.textFile(f_in);
  
  // iterate through the input file
  inputFile.flatMap{ line => line.split("\\W+")}.  // Read line by line and split b y word
            map { word => (word, 1) }.  // Map each word with count 1
            reduceByKey(_+_).  // Add all the 1's respect to each word
            saveAsTextFile("/Users/sudhir/Downloads/SparkOut/WordCount.txt1");  // Save to a local dir as text file
  
   println("WordCount completed");    
   
  // Stop the spark context
   sc.stop();
}