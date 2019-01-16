package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmallTests
{

  public static void main(String[] args) throws IOException{
   
     List<Integer> numbers = new ArrayList<Integer>();
     numbers.add(10);
     numbers.add(10);
     numbers.add(10);
     
     int total = 0;
     for(Integer i: numbers){
       System.out.println("Adding "+(i * 0.2));
       total += i * 0.2;
     }
     System.out.println(total);
  }
  
  public static void doSomething() throws RuntimeException{
  }
}
