package shortTextTagging.Tag;

import java.util.Comparator;
import java.util.Map;

public class YMapEntryIntegerValueComparator implements Comparator <Map.Entry<String, Integer>>{
	boolean asc = false;
	public YMapEntryIntegerValueComparator() {}
	public YMapEntryIntegerValueComparator(boolean ascending ) {asc=ascending;}

	@Override
	public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		if (asc)
			return o2.getValue() -o1.getValue();
		else return o1.getValue()- o2.getValue() ;
		
	}

}
