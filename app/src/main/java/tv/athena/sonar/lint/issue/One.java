package tv.athena.sonar.lint.issue;

public class One {

//    public String message = "";

    private static final String FOO = "foo";
    public String foo() {
        return FOO;
    }

    public void test() {
        String.format("The value of my integer is %d", "Hello World");
    }
}
