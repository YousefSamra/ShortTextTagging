package shortTextTagging.Tag;

import java.util.Comparator;
import java.util.Map;

public class YMapEntryDoubleValueComparator implements Comparator <Map.Entry<String, Double>>{
	boolean asc = false;
	public YMapEntryDoubleValueComparator() {}
	public YMapEntryDoubleValueComparator(boolean ascending ) {asc=ascending;}

	@Override
	public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
		if (asc){
			if (o1.getValue().doubleValue()-o2.getValue().doubleValue()<0.0) return 1;
			else if(o1.getValue().doubleValue()-o2.getValue().doubleValue()>0.0) return -1;
			else return 0;
		}else {
			if (o1.getValue().doubleValue()-o2.getValue().doubleValue()<0.0) return -1;
			else if(o1.getValue().doubleValue()-o2.getValue().doubleValue()>0.0) return 1;
			else return 0;
		}
		
	}

}
