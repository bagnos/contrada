package it.contrada.bean.rest.test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

public class HelloWorldResourceTest {

	private static Client client = null;
	private static WebTarget target = null;
	
	@BeforeClass
	public static void init()
	{
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/ContradaREST/rest/");
	}
	
	@Test
	public void helloTest()
	{
		
		WebTarget targetPath = target.path("helloworld");
		String message=targetPath.request(MediaType.TEXT_PLAIN).get(String.class);
		assertEquals(message,"hello world!");
		
	}
	
}
