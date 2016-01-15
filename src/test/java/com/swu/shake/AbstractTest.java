package com.swu.shake;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
//@ContextConfiguration(locations = { "file:D:/Develop/Projects/Luna/Shake/src/main/resources/beans.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class AbstractTest {

}
