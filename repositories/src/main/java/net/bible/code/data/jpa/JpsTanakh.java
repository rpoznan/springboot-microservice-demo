package net.bible.code.data.jpa;
// Generated Jan 25, 2020 6:04:54 PM by Hibernate Tools 4.3.4.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JpsTanakh generated by hbm2java
 */
@Entity
@Table(name="jps_tanakh"
    ,catalog="nanzopco_torah"
)
public class JpsTanakh  implements java.io.Serializable {


     private Integer id;
     private int page;
     private String bookName;
     private int chapter;
     private int verseX;
     private Integer verseY;

    public JpsTanakh() {
    }

	
    public JpsTanakh(int page, String bookName, int chapter, int verseX) {
        this.page = page;
        this.bookName = bookName;
        this.chapter = chapter;
        this.verseX = verseX;
    }
    public JpsTanakh(int page, String bookName, int chapter, int verseX, Integer verseY) {
       this.page = page;
       this.bookName = bookName;
       this.chapter = chapter;
       this.verseX = verseX;
       this.verseY = verseY;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="page", nullable=false)
    public int getPage() {
        return this.page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }

    
    @Column(name="book_name", nullable=false, length=50)
    public String getBookName() {
        return this.bookName;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    
    @Column(name="chapter", nullable=false)
    public int getChapter() {
        return this.chapter;
    }
    
    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    
    @Column(name="verse_x", nullable=false)
    public int getVerseX() {
        return this.verseX;
    }
    
    public void setVerseX(int verseX) {
        this.verseX = verseX;
    }

    
    @Column(name="verse_y")
    public Integer getVerseY() {
        return this.verseY;
    }
    
    public void setVerseY(Integer verseY) {
        this.verseY = verseY;
    }




}


