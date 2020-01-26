package net.bible.code.servicelayer.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BibleVerse {
	
	private Integer id;
    private Book torahBook;
    private String verse;
    private String engVerse;
    private String tranVerse;
    private int numChapter;
    private int numVerse;
    private String HChapter;
    private String HVerse;
    private Integer numValue;

}
