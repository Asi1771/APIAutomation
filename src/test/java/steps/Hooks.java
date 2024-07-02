package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Scanner;

public class Hooks {

    @Before
    public static void setUp(Scenario scenario){
        System.out.println("Starting scenario: " + scenario.getName());
    }

    @After
    public static void cleanUp(Scenario scenario){
        System.out.println("Ending scenario: " + scenario.getName());
    }
}
