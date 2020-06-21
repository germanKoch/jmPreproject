import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld beanH1 =
                applicationContext.getBean("helloworld", HelloWorld.class);
        HelloWorld beanH2 =
                applicationContext.getBean("helloworld", HelloWorld.class);
        Cat beanC1 = applicationContext.getBean("cat", Cat.class);
        Cat beanC2 = applicationContext.getBean("cat", Cat.class);

        System.out.println(beanH1 == beanH2);
        System.out.println(beanC1 == beanC2);
    }
}