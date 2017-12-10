package shortTextTagging.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.qcri.farasa.segmenter.Farasa;

public class YSegmenter {
	ArrayList<String> stopWords ;
	 Farasa farasa ;
	 
	 
	public YSegmenter() {
		
		try {
			farasa = new Farasa();
			stopWords = new ArrayList<String>();
			String SWPath= System.getProperty ( "user.dir" ) + "\\data\\MyStemmer\\MyStopWords.txt";
			@SuppressWarnings("resource")
			Scanner ss= new Scanner (new File(SWPath), "UTF-8");
			
			
			while(ss.hasNext())
				stopWords.add(ss.next().trim());

		} catch (Exception e) {e.printStackTrace();}
		
	}//----end of constructor
	
	
	
	
	
	public  ArrayList<String> segmentLine(String input){
		/*
		  * ----------------------stopwords initial removal
		  */
		for (String s: stopWords){
			input = input.replaceAll(" " +s+" ", " ");
			}
		
		/*
		  * ----------------------segment words by Farasa
		  */
		
		List<String> seagmentedWords = farasa.segmentLine(input);
		String seagmentedLine = " ";
		for (String s : seagmentedWords)
			seagmentedLine += s+" ";
		
		/*
		 * -------------------------- remove Latin, special characters and numbers
		 */
		Pattern p = Pattern.compile("[\\p{P}\\w]");
		java.util.regex.Matcher m = p.matcher(seagmentedLine);
		while (m.find()) {	 
		}
		m.reset();
		seagmentedLine = m.replaceAll(" ");
		
		
		p = Pattern.compile("[\\p{Mn}\\p{Nd}\\p{InLatin-1Supplement}]+");
		 m = p.matcher(seagmentedLine);
		while (m.find()) {
		}
		m.reset();
		seagmentedLine = m.replaceAll("");
		
		seagmentedLine= seagmentedLine.replace('|','\u0009');
		seagmentedLine =seagmentedLine.replace('=', '\u0009');
		seagmentedLine =seagmentedLine.replace('+', '\u0009');
		seagmentedLine =seagmentedLine.replace('<', '\u0009');
		seagmentedLine =seagmentedLine.replace('>', '\u0009');
		seagmentedLine =seagmentedLine.replace('$', '\u0009');
		seagmentedLine =seagmentedLine.replace('^', '\u0009');
		seagmentedLine =seagmentedLine.replace('Ü', '\u0009');
		seagmentedLine =seagmentedLine.replaceAll("\t", " ");
		input.replaceAll("Ã", "Ç");
		input.replaceAll("Å", "Ç");
		input.replaceAll("Â", "Ç");
		
		/*
		 * --------------remove stop words last time
		 */
		for (String s: stopWords)
			seagmentedLine = seagmentedLine.replaceAll(" " +s+" ", " ");
			
		 		
		 /*
		  * -------------remove generated letters and white spaces
		  */
		String[] tokens = seagmentedLine.split(" ");
		 ArrayList<String> lineTokens = new ArrayList<String>();
		 
		 for (int i =0; i<tokens.length; i++)
			 if ((tokens[i].trim().length() >1))
				 lineTokens.add(tokens[i].trim());
		
		 return lineTokens;
		}
	
	
	
}
