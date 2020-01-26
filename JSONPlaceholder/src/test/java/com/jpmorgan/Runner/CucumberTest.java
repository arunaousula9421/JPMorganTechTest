package com.jpmorgan.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        glue = {"com.jpmorgan.stepDefinitions"},
        plugin = {"pretty"},
        features = {"src/test/resources/features/JSONPlaceHolder.feature"})
public class CucumberTest extends AbstractTestNGCucumberTests {
}
