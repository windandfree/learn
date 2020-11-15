import com.umbrella.learnmq.MqProductApplcation;
import com.umbrella.learnmq.mqproduct.Queue_Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @JIRA:HY3-
 * @Des:
 * @Author:WL
 * @Date: 13:29 2020/11/7
 */
@SpringBootTest(classes = MqProductApplcation.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SpringbootAndMQTest {
    @Autowired
    private Queue_Product product;
    
    @Test
    public void sendMessageTest(){
        product.sendMessage("test 1");
    }
}
