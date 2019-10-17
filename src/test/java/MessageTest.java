import com.riverluoo.jms.producer.MessageProducer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: wangyang
 * @since: 2019-10-12 16:39
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void name() {

        for (int i = 0; i < 100; i++) {
            this.jmsTemplate.convertAndSend("aa", "查询期刊");

        }
    }

    @Test
    public void testTopic() {
        for (int i = 0; i < 100; i++) {
            this.messageProducer.publish("我吃饱了" + i);

        }
    }
}
