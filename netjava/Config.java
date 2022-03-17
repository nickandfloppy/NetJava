package netjava;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {
	public String token;
	public String prefix;

	public Config() throws Exception {
		JSONObject js = parseConfig();
		this.token = (String) js.get("token");
		this.prefix = (String) js.get("prefix");
	}

	public static JSONObject parseConfig() throws Exception {
		Object object = new JSONParser().parse(new FileReader("config.json"));
		JSONObject js = (JSONObject) object;
		return js;
	}
}
