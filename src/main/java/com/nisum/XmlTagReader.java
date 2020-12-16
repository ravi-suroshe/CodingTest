package com.nisum;
/**
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Ravi Suroshe
 * @contact +91 8888714344
 * @email suroshe12rs@gmail
 * 
 * Read Content enclosed between a start tag and an end
 * Criteria :
 * 1. The name of the start and end tags must be same. The HTML code <h1>Hello World</h2> is not valid, because the text starts with an h1 tag and ends with a non-matching h2 tag.
 * 2. Tags can be nested, but content between nested tags is considered not valid. For example, in <h1><a>contents</a>invalid</h1>, contents is valid but invalid is not valid.
 * 3.Tags can consist of any printable characters
 * 
 * How to Run
 * 1. Run Main Method
 * 2. First Input will be Number of Lines and then Enter Lines in iteration
 * 3. After all lines, you will see tag content in console output
 * 
 * Run Test Cases
 * 1. Find XmlTagReaderTest.java
 * 2. Run Required Test Case 
 *
 * Note : For Advance XML and HTML processing -> Java DOM Parser, SAX Parser, StAX Parser
 */
public class XmlTagReader {
	
	public static final String SINGLE_WHITE_SPACE = " ";
	public static final String BLANK = "";
	public static final String FORWARD_SLASH = "/";
	public static final String CLOSE_BRACKET = ">";
	public static final String OPEN_BRACKET = "<";
	
	//Logger log = Logger.getLogger(XmlTagReader.class.getName());
	//Level LOG_LEVEL = Level.OFF;
	
	/*
	 * Actual Execution Method 
	 */
	public static void main(String[] args) {
		
		 Scanner in = new Scanner(System.in); 
		 XmlTagReader reader = new XmlTagReader();
		 	
		 //Enter Number of lines
	        int numberOfLines = in.nextInt(); 
	       	        
	        List<String> tags = new ArrayList<String>();
	        while (numberOfLines != -1) {
	        	//Enter Line
	        	String line = in.nextLine(); 
	 	        tags.addAll(Arrays.asList(reader.readXmlTagContent(line)));
			--numberOfLines;
		}
		  
	       //Print Tag Content on Console
	       for (String tag : tags) {
	    	   System.out.println(tag);
	       }
		
	       in.close();
	}
	
	
	/*
	 * Line Processing Method to extract tag content in array
	 */
	public String[] readXmlTagContent(String str) {
		
		//log.log(LOG_LEVEL, "INPUT XML "+str);
		
		if (null == str) {
			return new String[0];
		}
		
		final List<String> result = new ArrayList<String>();
		final List<String> tagList = new ArrayList<String>();
		
		final String openAngleBracket = OPEN_BRACKET;
		final String closeAngleBracket = CLOSE_BRACKET;
		
		final int closeLen =  closeAngleBracket.length();
		final int openLen =  openAngleBracket.length();
		
		int pos = 0, lastTagIndex = 0, preOpenTagIndex = 0;
		
		String preOpenTag = null;
		
		final int strLen =  str.length();
		
		while (pos < strLen - closeLen) {
			//Find Open Angle Bracket
			int start = str.indexOf(openAngleBracket, pos);
			
			if (start < 0) {
				break;
			}
			
			start += openLen;
			//Find Close Angle Bracket
			final int end = str.indexOf(closeAngleBracket, start);
			
			if (end < 0) {
				break;
			}
			
			//Get Tag
			String tag = str.substring(start - openLen , end + closeLen);
			
			if (isClosingTag(tag)) {
				
				//Get Tag content
				String tagContent = str.substring(preOpenTagIndex, start-1);
				
				//Check content string has nested tag
				String nestedTag = getFirstTag(tagContent);
				
				//check tag is not nested
				if(null == nestedTag) {
					if (null != preOpenTag && preOpenTag.equals(getOpenTag(tag))) {
						result.add(tagContent);
					}else {
						result.add("None");
					}
				}
				
			}else {
				preOpenTag =  tag;
				preOpenTagIndex = end + 1;
			}
			
			tagList.add(tag);
			pos = end + closeLen;
			lastTagIndex = pos;
		}
		
		if (result.isEmpty()) {
			return new String[0];
		}
		
		//Return actual tag content
		return result.toArray(new String[0]);
	}
	
	/*
	 * Check Tag is Close Tag
	 */
	private static boolean isClosingTag(String tag) {
		
		int forwardSlashIndex = tag.indexOf(FORWARD_SLASH);
		
		if (forwardSlashIndex == 1) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * Get Open Tag from Close Tag
	 */
	private static String getOpenTag(String tag) {
		if(isClosingTag(tag)) {
			tag = tag.replace(FORWARD_SLASH, BLANK);
		}
		return tag;
	}
	
	/*
	 * Get First Occurrence of tag from content 
	 */
	private static String getFirstTag(String str) {
		
		final String openAngleBracket = OPEN_BRACKET;
		final String closeAngleBracket = CLOSE_BRACKET;
		
		final int closeLen =  closeAngleBracket.length();
		final int openLen =  openAngleBracket.length();
		
		//Find Index of Open Angle Bracket
		int start = str.indexOf(openAngleBracket);
		
		if (start < 0) {
			return null;
		}
		
		start += openLen;
		//Find Index of Close Angle Bracket
		final int end = str.indexOf(closeAngleBracket, start);
		
		if (end < 0) {
			return null;
		}

		//Get Tag String
		String tag = str.substring(start - openLen , end + closeLen);
		
		//Check Tag String does not have white space.
		if (tag.contains(SINGLE_WHITE_SPACE)) {
			return null;
		}
		
		return tag;
	}

}
