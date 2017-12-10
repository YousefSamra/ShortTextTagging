package stanfordNLP;


import java.io.*;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.international.arabic.process.ArabicSegmenter;


/** This is a very simple demo of calling the Chinese Word Segmenter
 *  programmatically.  It assumes an input file in UTF8.
 *  <p/>
 *  <code>
 *  Usage: java -mx1g -cp seg.jar SegDemo fileName
 *  </code>
 *  This will run correctly in the distribution home directory.  To
 *  run in general, the properties for where to find dictionaries or
 *  normalizations have to be set.
 *
 *  @author Christopher Manning
 */

public class StanfordSegDemo {

  public  String segment(String s)  {
    //if (args.length != 1) {
    //  System.err.println("usage: java -mx1g SegDemo filename");
    //  return;
    //}

    Properties props = new Properties();
    props.setProperty("inputEncoding", "UTF-8");

    ArabicSegmenter segmenter = new ArabicSegmenter(props);
    segmenter.loadSegmenter("data/arabic-segmenter-atbtrain.ser.gz");
    String output = segmenter.segmentString(s);
   // System.out.println(output);
	return output;
  }

}
