package net.bible.code.servicelayer.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BibleVerseRequest {
	
	@NotNull(message = "bookId is required")
	@Pattern(regexp = "\\d+", message = "bookId must be digits")
	private String bookId;
	@NotNull(message = "chapter is required")
	@Pattern(regexp = "\\d+", message = "chapter must be digits")
	private String chapter;
	@NotNull(message = "verseX is required")
	@Pattern(regexp = "\\d+", message = "verseX must be digits")
	private String verseX;
	@Pattern(regexp = "\\d+", message = "verseY must be digits")
	private String verseY;

}
