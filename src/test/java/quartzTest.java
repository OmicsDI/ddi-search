import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class quartzTest {
    @Test
    public void test1() throws InterruptedException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("springMVC/quartzJob.xml");
        Thread.sleep(60000);
    }
}
