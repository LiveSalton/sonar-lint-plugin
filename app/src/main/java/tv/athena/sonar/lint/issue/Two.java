package tv.athena.sonar.lint.issue;

public class Two {
  public String foo() {
    return "foo";
  }

  public void test() {
    String.format("The value of my integer is %d", "Hello World");
  }
}
