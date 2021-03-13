package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class Runner {

    @Before
    public void beforeCucumber(){
        System.out.println("BEFORE CUCUMBER");
    }

    @After
    public void afterCucumber(){
        System.out.println("AFTER CUCUMBER");
    }



}
