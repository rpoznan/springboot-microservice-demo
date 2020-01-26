package net.bible.code.servicelayer.pojo;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ScrollImage {
	
	private Integer id;
	@NotNull(message = "imageUrl is required")
   	@Size(max = 500, message = "imageUrl max is 500")
	private String imageUrl;

}
