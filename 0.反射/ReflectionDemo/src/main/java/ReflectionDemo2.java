import java.lang.reflect.Method;

public class ReflectionDemo2 {
    public static void main(String[] args) {

    }
    public static void configMethod(Object o){
        Method method = o.getClass().getDeclaredMethod();

    }
}
