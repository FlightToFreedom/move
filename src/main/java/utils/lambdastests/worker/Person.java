package utils.lambdastests.worker;

public class Person
{
  private int age;
  private Sex sex;

  public Person(int age, Sex sex)
  {
    this.age = age;
    this.sex = sex;
  }

  public enum Sex
  {
    MALE, FEMALE;
  }

  public void explainPerson()
  {
    System.out.println("Person is: " + this.sex + " of age: " + this.age);
  }
  
  public int getAge(){
    return this.age;
  }
  
  public Sex getSex(){
    return this.sex;
  }
}
