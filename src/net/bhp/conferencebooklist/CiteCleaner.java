package net.bhp.conferencebooklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CiteCleaner {
	public static List<String> clean(String cite) {
		List<String> working = new ArrayList<>();
		working.addAll(Arrays.asList(cite.split(";")));
		working.stream().forEach(c -> c.trim().replaceFirst("see also ", "").replaceFirst("see ", "").replaceAll("emphasis added", "").replaceAll("italics added", ""));
		
		
		return working;
	}

	public static String clean2(String cite) {
//		List<String> working = new ArrayList<>();
		StringBuilder raw = new StringBuilder(cite.trim().replaceAll("see also ", "").replaceAll("emphasis added", "").replaceAll("italics added", "").trim());
//		working.stream().forEach(c -> c.trim().replaceFirst("see also ", "").replaceFirst("see ", "").replaceAll("emphasis added", "").replaceAll("italics added", ""));
		
		
//		return working;
		return raw.toString();
	}
}
