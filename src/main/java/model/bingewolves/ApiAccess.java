package model.bingewolves;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * This program is a model program that will obtain an access token, check if it is set,
 * check if it is expired, get data from the Blizzard API using the access token and fields.
 * 
 * @author David Alvarez, 2/22/19
 */
public class ApiAccess 
{
	//Obtained from registering your application at Blizzard Developer Portal
	private final String CLIENT_KEY = "ba526934026a4c94a4562abc2af9699c";
	//Obtained from registering your application at Blizzard Developer Portal
	private final String CLIENT_SECRET = "MIiCraNwVIlA4aHdd62ZvD5NM9KArLMO";
	private final String ACCESS_TOKEN_SERVER_URL = "https://us.battle.net/oauth/token";
	private static String accessToken;
	private static long tokenExpiration;
	private final HttpClient httpClient = HttpClientBuilder.create().build();
	private String requestedData;
	/**
	 * This method will make a Http request to the Ouath website with my client key and secret
	 * and obtain an access token in the form of a Json it will then parse that Json and seperate
	 * the access token from it.
	 * 
	 * @return A String containing the access token
	 * @throws IOException If the Http request fails to connect to the url
	 */
	private String getAccessToken() throws IOException
	{
		CloseableHttpClient client = HttpClients.createDefault();
		
		List<NameValuePair> params = new Vector<NameValuePair>();
		params.add(new BasicNameValuePair("client_id", CLIENT_KEY));
		params.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
		params.add(new BasicNameValuePair("grant_type", "client_credentials"));
		try {
			HttpPost request = new HttpPost(ACCESS_TOKEN_SERVER_URL);
			request.setEntity(new UrlEncodedFormEntity(params));

			HttpResponse response = client.execute(request);
			String token = (EntityUtils.toString(response.getEntity(), "UTF-8"));
			JsonElement jElement = new JsonParser().parse(token);
			JsonObject  jToken = jElement.getAsJsonObject();
			accessToken = jToken.get("access_token").getAsString();
			tokenExpiration = jToken.get("expires_in").getAsLong();
		}catch (IOException e) {
			e.printStackTrace();
	    }finally {
	    	client.close();
	    }
	    return accessToken;
	}
	/**
	 * This method will check if the access token has expired.
	 * 
	 * @return True or False if the token has expired
	 */
	private boolean tokenExpired()
	{
		Date now = new Date();
    	Date expiryDate = new Date(now.getTime() + tokenExpiration*1000);
    	long currentTime = now.getTime();
    	long expirationTime = expiryDate.getTime();
    	if (currentTime == expirationTime) {
    		return true;
    	}
    	else
    		return false;
	}
	/**
	 * This method will check if the access token has been obtained and if not call
	 * getAccessToken()
	 * 
	 */
	private void checkAccessToken()
	{
		if (accessToken == null) {
			try {
				getAccessToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * This method will make a Http get request and obtain data from the Blizzard API using
	 * the url for either a character request, a pet request, and a mount request.
	 * 
	 * @param requestUrl The url that will be used to request the data
	 * @return A String of the requested data from the Blizzard API
	 * @throws IOException If the url fails to connect to the Blizzard API
	 */
	public String getRequestedData(String requestUrl) throws IOException
	{
		checkAccessToken();
		if (tokenExpired()) {
			getAccessToken();
		}
		HttpGet httpGet = new HttpGet(requestUrl);
		httpGet.addHeader("Authorization", "Bearer " + accessToken);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		this.requestedData = (EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
		return requestedData;
	}
}

