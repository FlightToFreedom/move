package utils.lambdastests.interfaces;

import utils.lambdastests.worker.Person;

@FunctionalInterface
public interface Tester
{
  public boolean isPersonAcceptable(Person person);
}
