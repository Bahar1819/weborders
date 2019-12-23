package com.weborders.utulities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    //this class will be responsible for loading properties file and will provide access
    // to values based on key names
    // we use Properties class to load custom .properties files

    private static Properties configFile;

    //static block runs first and only once,
    static {
        try {
            //provides access to file
            //try/catch block stands for handling exceptions
            //if exception occurs, code inside a catch block will be executed
            //any class that is related to InputOutput produce checked exceptions
            //without handling checked exception, you cannot run a code
            FileInputStream fileInputStream = new FileInputStream("configuration.properties");
           // initialize properties object
            configFile = new Properties();
            //load configuration.properties file
            configFile.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }


    }
    public static String getProperty(String key) {
        return configFile.getProperty(key);
    }

    public static class Driver {
        private static WebDriver driver;

        // to prevent this class being used for object creation
        private Driver(){

        }

        // getter method
        public static WebDriver get(){

            if (driver == null){

                String browser = getProperty("browser");
                switch (browser){
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        break;
                    case "opera":
                        WebDriverManager.operadriver().setup();
                        driver = new OperaDriver();
                        break;
                    default:
                        throw  new RuntimeException("Wrong browser type selected. Check config file!");
                }
            }
            return driver;
        }


        public static void close(){
            if (driver!=null){
                driver.quit();
                driver=null;
            }
        }


    }
}
