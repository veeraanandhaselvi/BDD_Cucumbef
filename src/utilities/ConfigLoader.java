/**
 * @author Veera Anandha Selvi Saravana Muthu
 *
 * Explanation: Read values based on property file ex: environments.
 */

/***************************************************/
package utilities;

import static constants.FrameworkConstants.DIRECTORY_ENV_CONFIG;
import static constants.FrameworkConstants.ENV_CONFIG_INT;
import static constants.FrameworkConstants.ENV_CONFIG_PROD;
import static constants.FrameworkConstants.ENV_CONFIG_QA;
import static constants.FrameworkConstants.ENV_CONFIG_DEV;
import static constants.FrameworkConstants.PARAMETER_ENV;

import java.util.Properties;

import enums.EnvType;

/*Singleton Design Pattern is implemented here*/
public class ConfigLoader {

	private static final String BASE_URL = "baseUrl";

	private Properties properties;

	private static ConfigLoader configLoader;

	private ConfigLoader() {

		/* By default, Dev will be taken*/
		String env = System.getProperty(PARAMETER_ENV, String.valueOf(EnvType.DEV));

		switch (EnvType.valueOf(env)) {

		case QA: {
			properties = PropertyUtils.propertyLoader(DIRECTORY_ENV_CONFIG + ENV_CONFIG_QA);
			break;
		}
		case INT: {
			properties = PropertyUtils.propertyLoader(DIRECTORY_ENV_CONFIG + ENV_CONFIG_INT);
			break;
		}
		case DEV: {
			properties = PropertyUtils.propertyLoader(DIRECTORY_ENV_CONFIG + ENV_CONFIG_DEV);
			break;
		}
		case PROD: {
			properties = PropertyUtils.propertyLoader(DIRECTORY_ENV_CONFIG + ENV_CONFIG_PROD);
			break;
		}
		default:
			throw new IllegalStateException("INVALID ENV: " + env);
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getBaseUrl() {
		return getPropertyValue(BASE_URL);
	}
	
	private String getPropertyValue(String propertyKey) {
		String prop = properties.getProperty(propertyKey);
		if (prop != null) {
			return prop.trim();
		} else {
			throw new RuntimeException("Property " + propertyKey + " is not specified in the config.properties file");
		}
	}
	
	
}
