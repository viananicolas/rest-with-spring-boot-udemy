package br.com.spring.restwithspringbootudemy.data.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookViewModel extends RepresentationModel<BookViewModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String author;
    private String title;
    private Double price;
    private Date launchDate;

    public BookViewModel() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date gender) {
        this.launchDate = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel person = (BookViewModel) o;
        return key.equals(person.key) &&
                author.equals(person.author) &&
                title.equals(person.title) &&
                price.equals(person.price) &&
                launchDate.equals(person.launchDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, author, title, price, launchDate);
    }
}
