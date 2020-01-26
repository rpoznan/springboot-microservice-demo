package net.bible.code.servicelayer.pojo;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Book {
	
	@NotNull(message = "book id is required")
	private Integer id;
    private String bookName;
    private String engBookName;

}
