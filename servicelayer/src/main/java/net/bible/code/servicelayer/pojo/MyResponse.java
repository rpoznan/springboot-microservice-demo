package net.bible.code.servicelayer.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MyResponse<T> {
	
	private int statusCode;
	private String errorMessage;
	private T payload;

}
