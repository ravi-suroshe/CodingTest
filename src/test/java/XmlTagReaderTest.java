import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.nisum.XmlTagReader;

/**
 * 
 */

/**
 * @author Ronny
 *
 */

public class XmlTagReaderTest {
	
	private XmlTagReader reader = new XmlTagReader();

	@Test
	public void testSimpleTag() {
		
		String xml = "<h1>India is my country.</h1>";
		
		String[] actualOutput = reader.readXmlTagContent(xml);
		
		System.out.println(Arrays.toString(actualOutput));
		String[] expectedOutput = new String[] {"India is my country."};
		
		Assert.assertEquals(expectedOutput, actualOutput);		
	}

	@Test
	public void testInvalidSimpleTag() {
		
		String xml = "<h1>Hello World</h2>";
		
		String[] actualOutput = reader.readXmlTagContent(xml);
		
		System.out.println(Arrays.toString(actualOutput));
		String[] expectedOutput = new String[] {"None"};
		
		Assert.assertEquals(expectedOutput, actualOutput);		
	}
	
	@Test
	public void testNestedTag() {
		
		String xml = "<h1>India is my country.</h1>\r\n" + 
				"<h1><h1>COVID-19 is not over yet.</h1></h1><par>Take care of you and others. </par>\r\n" + 
				"<Nisum>Nisum is hiring Java developer.</Nisum>\r\n" + 
				"<java developer>The Java developer should always follow best coding practices.</java developer>\r\n" + 
				"";
		
		String[] actualOutput = reader.readXmlTagContent(xml);
		
		System.out.println(Arrays.toString(actualOutput));
		String[] expectedOutput = new String[] {"India is my country.", 
				"COVID-19 is not over yet.", 
				"Take care of you and others. ",
				"Nisum is hiring Java developer.", 
				"The Java developer should always follow best coding practices."};
		
		Assert.assertEquals(expectedOutput, actualOutput);		
	}
	
	@Test
	public void testNestedSameTag() {
		
		String xml = "<h1><h1>COVID-19 is not over yet.</h1></h1>";
		
		String[] actualOutput = reader.readXmlTagContent(xml);
		
		System.out.println(Arrays.toString(actualOutput));
		String[] expectedOutput = new String[] {"COVID-19 is not over yet."};
		
		Assert.assertEquals(expectedOutput, actualOutput);		
	}
	
	@Test
	public void testDifferentTag() {
		
		String xml = "<h1><a>contents</a>invalid</h1>";
		
		String[] actualOutput = reader.readXmlTagContent(xml);
		
		System.out.println(Arrays.toString(actualOutput));
		String[] expectedOutput = new String[] {"contents"};
		
		Assert.assertEquals(expectedOutput, actualOutput);		
	}
	
}
