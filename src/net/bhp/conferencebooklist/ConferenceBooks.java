package net.bhp.conferencebooklist;

import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConferenceBooks {

	public static void main(String[] args) {
		List<TalkCitations> rawCites = new ArrayList<>();
		Pattern pattern = Pattern.compile("\\(.+?\\)");
//		System.out.println("Checking complete documents...");
		Path dir = Paths.get("C:\\Users\\randallbooth\\Downloads\\GC Text");
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.html")) {
			String[] exclude = { "Updated" };
//			int count = 0;
			for (Path entry : stream) {
				String source = new String(Files.readAllBytes(entry), StandardCharsets.UTF_8);
				Document doc = Jsoup.parse(source);
				Matcher m = pattern.matcher(doc.text());
//				System.out.println(entry.toAbsolutePath());
				TalkCitations cite = new TalkCitations(entry);
				while (m.find()) {
					StringBuilder groupBuilder = new StringBuilder(m.group());
					String group = groupBuilder.substring(1, groupBuilder.length() - 1);
					if (group.length() > 8 && !StringUtils.startsWithAny(group, exclude)) {
//						System.out.println(Integer.toString(++count) + "\t\t" + group);
						cite.addCitation(group);
					}
				}
				rawCites.add(cite);
			}
			List<String> refinedCites = new ArrayList<>();
			rawCites.parallelStream().forEach(rc -> {
//				rc.getCitations().stream().forEach(c -> refinedCites.addAll(CiteCleaner.clean(c)));
				rc.getCitations().stream().forEach(c -> {
					String cleanedCite = CiteCleaner.clean2(c);
					if (cleanedCite != null && cleanedCite.length() > 7) {
	                    refinedCites.add(cleanedCite);
                    }
				});
			});
			refinedCites.stream().filter(cite -> cite != null).sorted().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
