package main;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class Main
{

  public static void main(String[] args)
  {
    System.setProperty("hadoop.home.dir", "C:\\Users\\eiorio\\hadoop");
    countEventsForVisitor();
  }
 
  private static void countEventsForVisitor(){

    String logFile = "C:\\Users\\eiorio\\Downloads\\almamediatest.log";    
    JavaSparkContext jsc = new JavaSparkContext(new SparkConf().setMaster("local").setAppName("DataReader"));

    // An RDD containing all lines
    JavaRDD<String> rdd = jsc.textFile(logFile);
    JavaPairRDD<String, Integer> words = rdd.map(line -> Arrays.asList(line.split("&")).get(0)).mapToPair(ns_vid -> new Tuple2<>(ns_vid, 1)).reduceByKey((ns_vid,number) -> (ns_vid + number));
   
    //Sorting
    words = words.mapToPair(tuple -> tuple.swap()).sortByKey(true).mapToPair(tuple2 -> tuple2.swap());

    
    for(Tuple2<String, Integer> touple : words.collect())
      System.out.println(touple._1 +" > "+touple._2);
    
    jsc.close();
  }
  
  /**
   * Reads a file containing only stream events and sums the playing time.
   */
  private static void streamTimePerVisitor(){
    JavaSparkContext jsc = new JavaSparkContext(new SparkConf().setMaster("local").setAppName("DataReader"));
    String streamingFile = "C:\\Users\\eiorio\\Downloads\\streamlines.txt";
    // An RDD containing all lines
    JavaRDD<String> rdd = jsc.textFile(streamingFile);
    //In order to collect the playing times we need to collect the ns_st_pt, problem is that this label does not have a fixed position.
  }
 }
