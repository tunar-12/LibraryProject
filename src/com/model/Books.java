
package com.model;


public class Books {
     private Integer id;
    private String name;
    private String theme;
    private String author;
    private String language;
    private String status;
    private String pageCount;
    private Double amount;

    @Override
    public String toString() {
        return "Books{" + "id=" + id + ", name=" + name + ", theme=" + theme + ", author=" + author + ", language=" + language + ", status=" + status + ", pageCount=" + pageCount + ", amount=" + amount + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
    
    
    
    
    
}
