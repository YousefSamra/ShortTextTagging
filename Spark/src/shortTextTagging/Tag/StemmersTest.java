package shortTextTagging.Tag;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.arabicStemmer;

import com.qcri.farasa.segmenter.Farasa;

import shortTextTagging.AlkhojaStemmer;
import shortTextTagging.YTiming;
import stanfordNLP.StanfordSegDemo;

public class StemmersTest {
	static List<String> stopWords= new ArrayList<String>();
	static File outFile = new File("");
	public StemmersTest() {
		 	
		String SWPath= "path/MyStopWords.txt";
		System.out.println(SWPath);
		Scanner ss;
		try {
			ss = new Scanner (new File(SWPath), "UTF-8");
			@SuppressWarnings("unused")
			String sw = "";
			while(ss.hasNext())
				stopWords.add(ss.next().trim());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		long time = System.currentTimeMillis();
		List<String> terms = new ArrayList<String>();
		@SuppressWarnings("resource")
		Scanner scan= new Scanner (new File("path\newNLPEvalu.txt"), "UTF-8");
		while (scan.hasNext())
			terms.add(scan.next().trim());
		
		List<String> cleanTerms = new ArrayList<String>();
		//--------------farasa
		
//		Farasa farasa = new Farasa();
//		
//		for(int i =0;i<terms.size();i++)
//			cleanTerms.add(farasa.segmentLine(terms.get(i)).toString().replace("[","").replace("]",""));
//		
//		outFile = new File("path/farasa.txt");
//		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("path/farasa.txt", true),"UTF-8" ));
//		for(String t : cleanTerms)
//			out.write(t+"\n");
//		out.flush();
//		System.out.println(cleanTerms.size());
	
		//System.out.print("sb");
		//----------snowball
//		arabicStemmer snowBall = new arabicStemmer(); int u =0;
//		for (String s : terms){ System.out.println(u++);
//		snowBall.setCurrent(s); snowBall.stem();
//		cleanTerms.add(snowBall.getCurrent()); 
//			}
//		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("path/snowBall.txt", true),"UTF-8" ));
//		for(String t : cleanTerms){System.out.println(t);
//			out.write(t+"\n");}
//		out.flush();out.close();
		
		
		
		//------------- stanford
		StanfordSegDemo stan = new StanfordSegDemo();
		for (String s : terms){
			System.out.println(s);
			
			try {
			cleanTerms.add(stan.segment(s));
			}catch(Exception e){}}
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("path/stan11.txt", true),"UTF-8" ));
		for(String t : cleanTerms){System.out.println(t);
			out.write(t+"\n");}
		out.flush();out.close();
		
		
		//----khoja
//		AlkhojaStemmer kh = new AlkhojaStemmer();
//		for (String s : terms){
//			System.out.println(s);
//			try {
//			cleanTerms.add(kh.stemWord(s));	
//			}catch(Exception e){cleanTerms.add("¿");}
//		}
//		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("path/khoj.txt", true),"UTF-8" ));
//		for(String t : cleanTerms){System.out.println(t);
//			out.write(t+"\n");}
//		out.flush();out.close();
		new YTiming().getTime(time);
	}
	public static String removePunc (String s){

		
				Pattern p = Pattern.compile("[\\p{P}\\w]");//"[\\p{P}\\p{Mn}]"
				java.util.regex.Matcher m = p.matcher(s);
				while (m.find()) {	 
				}
				m.reset();
				s = m.replaceAll(" ");
				
				
				 p = Pattern.compile("[\\p{Mn}\\p{Nd}\\p{InLatin-1Supplement}]+");
				 m = p.matcher(s);
				while (m.find()) {
				}
				m.reset();
				s = m.replaceAll("");
				s=s.replace('|','\u0009')
						.replace('=', '\u0009')
						.replace('+', '\u0009')
						.replace('<', '\u0009')
						.replace('>', '\u0009')
						.replace('$', '\u0009')
						.replace('^', '\u0009')
						.replace('Ü', '\u0009')
						.replaceAll("\t", " ");
				
				for(String w: stopWords){
					s=" "+ s + " ";
					s=s.replaceAll(" "+ w + " ", " ");}

		return s;
		} 
}
