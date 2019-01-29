package com.epam.ankov.LearnSpring;

import com.epam.ankov.LearnSpring.controller.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnSpringApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
    public void testList() {
        MessageController mc = new MessageController();
        mc.list();
    }
}

