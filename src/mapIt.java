import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class mapIt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/MapIt/input.txt"));
            String line;
            line = br.readLine();
            System.out.println(line);
            br.close();
            
            File file = new File("C:/MapIt/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.google.com/maps");
            WebElement searchBox = driver.findElement(By.cssSelector("#searchboxinput"));
            searchBox.sendKeys(line);
            
            WebElement searchButton = driver.findElement(By.cssSelector("#searchbox-searchbutton"));
            searchButton.click();
            
            Thread.sleep(3000);
            
            String url = driver.getCurrentUrl();
            
            driver.get("https://www.google.com");
            WebElement googleSearch = driver.findElement(By.cssSelector("#lst-ib"));
            googleSearch.sendKeys(line+"\n");
            Thread.sleep(3000);
            WebElement information = null;
            String info = null;
            if(driver.findElements(By.xpath("//*[@id=\"rhs_block\"]/div/div[1]/div/div[1]/div[2]/div[2]")).size() > 0){
            	information = driver.findElement(By.xpath("//*[@id=\"rhs_block\"]/div/div[1]/div/div[1]/div[2]/div[2]"));
            	info = information.getText();
            }
            FileWriter writer = new FileWriter("C:/MapIt/output.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write("Search result for: " + line + "\n\n");
            bufferedWriter.write("Google map location: " + url + "\n\n");
	        if(info!=null){
	        	bufferedWriter.write(info);
	        }
	        bufferedWriter.close();
	        System.out.println("End of program");
            
		} catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (br != null) {
	                br.close();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
        
        
	}

}
