package net.bhp.conferencebooklist;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TalkCitations {

	private Path filePath = null;
	private List<String> citations = new ArrayList<>();
	
	public TalkCitations() {}
	
	public TalkCitations(Path filePath) {
		this.filePath = filePath;
	}

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}

	public List<String> getCitations() {
		return citations;
	}

	public void setCitations(List<String> citations) {
		this.citations = citations;
	}
	
	public void addCitation(String citation) {
		this.citations.add(citation);
	}
}
