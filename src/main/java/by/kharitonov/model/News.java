package by.kharitonov.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "text")
    private byte[] text;

//    @Column(name = "date")
//    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (header != null ? !header.equals(news.header) : news.header != null) return false;
        if (!Arrays.equals(text, news.text)) return false;
//        if (date != null ? !date.equals(news.date) : news.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(text);
//        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
