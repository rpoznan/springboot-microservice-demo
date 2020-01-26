package net.bible.code.servicelayer.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Scroll {
	
	private Integer id;
	@NotNull(message = "torahBook is required")
	@Valid
    private Book torahBook;
    @NotNull(message = "title is required")
	@Size(max = 100, message = "title max is 100")
    private String title;
    @NotNull(message = "age is required")
	@Size(max = 45, message = "age max is 45")
    private String age;
    @NotNull(message = "verseAddress is required")
   	@Size(max = 50, message = "verseAddress max is 50")
    private String verseAddress;
    @NotNull(message = "orgin is required")
   	@Size(max = 50, message = "orgin max is 50")
    private String orgin;
    @NotNull(message = "material is required")
   	@Size(max = 100, message = "material max is 100")
    private String material;
    @NotNull(message = "imageUrl is required")
   	@Size(max = 500, message = "imageUrl max is 500")
    private String imageUrl;
	@Size(max = 500, message = "imageUrl2 max is 500")
    private String imageUrl2;
	@Valid
	@NotNull(message = "scrollLookups is required")
    private List<ScrollLookup> scrollLookups;
	@Valid
	@NotNull(message = "imageUrls is required")
    private List<ScrollImage> imageUrls;

}
