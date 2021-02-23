import cn.example.UserWebApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

/**
 * @author 一枚路过的程序猿
 * @Title:
 * @date 2021/2/22 13:33
 */
@SpringBootTest(classes = UserWebApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test(){

        /*System.out.println(redisTemplate.opsForValue().setIfAbsent("key", 1, 10, TimeUnit.SECONDS));
        System.out.println(redisTemplate.opsForValue().setIfAbsent("key", 1, 10, TimeUnit.SECONDS));*/


    }


}
