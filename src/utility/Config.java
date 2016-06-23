package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

public class Config {
	public static class ConfigTest {
		public static void main(String args[]) throws IOException {
			Config cf = new Config();
			cf.getConfigurations();
		}
	}

	public Properties getConfigurations() throws IOException {
		// Get the inputStream
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		System.out.println("Configuration value is: " + properties.getProperty("a"));
		for (Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		return properties;
	}

}