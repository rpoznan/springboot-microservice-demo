package net.bible.code.servicelayer.pojo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ScrollLookup {
	
	private Integer id;
	@Valid
	@NotNull(message = "torahBook is required")
	private Book torahBook;
	@NotNull(message = "chapter is required")
    private Integer chapter;
	@NotNull(message = "verseX is required")
    private Integer verseX;
	@NotNull(message = "verseY is required")
    private Integer verseY;
	@Size(max = 1, message = "crossRef max is 1")
	@Pattern(regexp = "Y|N", message = "crossRef value is Y or N")
    private String crossRef;

}
