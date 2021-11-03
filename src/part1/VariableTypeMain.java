package part1;

public class VariableTypeMain {
    public static void main(String[] args) {
        System.out.printf("APPLICATION_CODE:%s", MyConstant.APPLICATION_CODE);
        System.out.printf("APPLICATION_CODE:%s", MyAbstract.APPLICATION_CODE);
    }

}

interface MyConstant {
    //Default scope in interface: public static final
    int APPLICATION_CODE = 775;
}

abstract class MyAbstract {
    public static final int APPLICATION_CODE = 775;
    abstract String getAbstractMethod();
    protected String getAbstractMethodWithDefault() {return null;}
}
