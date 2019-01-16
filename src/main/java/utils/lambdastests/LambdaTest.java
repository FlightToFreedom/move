package utils.lambdastests;

import java.util.ArrayList;
import java.util.List;

import utils.lambdastests.interfaces.Tester;
import utils.lambdastests.worker.Person;
import utils.lambdastests.worker.Person.Sex;

public class LambdaTest
{

  public static void main(String[] args){
    //Create 10 random persons.
    List<Person> persons = new ArrayList<Person>();
    for(int i=0; i<=9; i++){
        int sexRandom = (int) Math.round(Math.random()*1);
        int ageRandom = (int) Math.round(Math.random()*110);
        Person.Sex sex = Person.Sex.values()[sexRandom]; 
        Person localPerson = new Person(ageRandom,sex);
        persons.add(localPerson);
    }
    System.out.println("We have now "+persons.size()+" persons");
    //Here we pass the argument for the Tester interface "which is Person" and the body of the method, which determines the boolean value to accept a person.
    printPersons(persons, (Person p) -> (p.getAge() >= 18 && p.getAge() <= 99) && p.getSex() == Sex.MALE);
  }
  
  //Pass a list of persons and a function.
  public static void printPersons(List<Person> persons, Tester tester){
    for(Person person : persons){
      if(tester.isPersonAcceptable(person)){
        System.out.println("--Accepted person");
        person.explainPerson();
      }else{
        System.out.println("--NON accepted person");
        person.explainPerson();
      }
    }
  }
}
