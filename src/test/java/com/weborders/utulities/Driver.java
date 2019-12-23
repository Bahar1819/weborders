

        package com.weborders.utulities;

        import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;


        public class Driver {
            private static WebDriver driver;

            // to prevent this class being used for object creation
            private Driver(){

            }

            // getter method
            public static WebDriver get(){

                if (driver == null){

                    String browser = ConfigurationReader.getProperty("browser");
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
