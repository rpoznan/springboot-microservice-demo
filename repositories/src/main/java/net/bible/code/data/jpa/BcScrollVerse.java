package net.bible.code.data.jpa;
// Generated Jan 25, 2020 6:04:54 PM by Hibernate Tools 4.3.4.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BcScrollVerse generated by hbm2java
 */
@Entity
@Table(name="bc_scroll_verse"
    ,catalog="nanzopco_torah"
)
public class BcScrollVerse  implements java.io.Serializable {


     private Integer id;
     private BcScroll bcScroll;
     private TorahBook torahBook;
     private Integer chapter;
     private Integer verse;
     private String createdBy;
     private String modifiedBy;
     private Date createdDate;
     private Date modifiedDate;

    public BcScrollVerse() {
    }

	
    public BcScrollVerse(String createdBy, Date createdDate, Date modifiedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    public BcScrollVerse(BcScroll bcScroll, TorahBook torahBook, Integer chapter, Integer verse, String createdBy, String modifiedBy, Date createdDate, Date modifiedDate) {
       this.bcScroll = bcScroll;
       this.torahBook = torahBook;
       this.chapter = chapter;
       this.verse = verse;
       this.createdBy = createdBy;
       this.modifiedBy = modifiedBy;
       this.createdDate = createdDate;
       this.modifiedDate = modifiedDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="scroll_id")
    public BcScroll getBcScroll() {
        return this.bcScroll;
    }
    
    public void setBcScroll(BcScroll bcScroll) {
        this.bcScroll = bcScroll;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    public TorahBook getTorahBook() {
        return this.torahBook;
    }
    
    public void setTorahBook(TorahBook torahBook) {
        this.torahBook = torahBook;
    }

    
    @Column(name="chapter")
    public Integer getChapter() {
        return this.chapter;
    }
    
    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    
    @Column(name="verse")
    public Integer getVerse() {
        return this.verse;
    }
    
    public void setVerse(Integer verse) {
        this.verse = verse;
    }

    
    @Column(name="created_by", nullable=false, length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
    @Column(name="modified_by", length=45)
    public String getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", nullable=false, length=19)
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date", nullable=false, length=19)
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }




}


