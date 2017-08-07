package by.kharitonov.model;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "eventName")
    private String eventName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_events", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

//    @Column(name = "text")
//    private byte[] text;
//
//    @Column(name = "type")
//    private String type;
//
//    @Column(name = "city")
//    private String city;
//
//    @Column(name = "street")
//    private String street;
//
//    @Column(name = "building")
//    private int building;
//
//    @Column(name = "buildingIndex")
//    private String buildingIndex;

    @Column(name = "mobilePhone")
    private String mobilePhone;

//    @Column(name = "phone")
//    private String phone;
//

    @Column(name = "eventStartTime")
    private Time eventStartTime;

    @Column(name = "date")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

//    public byte[] getText() {
//        return text;
//    }
//
//    public void setText(byte[] text) {
//        this.text = text;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public int getBuilding() {
//        return building;
//    }
//
//    public void setBuilding(int building) {
//        this.building = building;
//    }
//
//    public String getBuildingIndex() {
//        return buildingIndex;
//    }
//
//    public void setBuildingIndex(String buildingIndex) {
//        this.buildingIndex = buildingIndex;
//    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
//        if (building != event.building) return false;
        if (eventName != null ? !eventName.equals(event.eventName) : event.eventName != null) return false;
//        if (!Arrays.equals(text, event.text)) return false;
//        if (type != null ? !type.equals(event.type) : event.type != null) return false;
//        if (city != null ? !city.equals(event.city) : event.city != null) return false;
//        if (street != null ? !street.equals(event.street) : event.street != null) return false;
//        if (buildingIndex != null ? !buildingIndex.equals(event.buildingIndex) : event.buildingIndex != null)
//            return false;
        if (mobilePhone != null ? !mobilePhone.equals(event.mobilePhone) : event.mobilePhone != null) return false;
//        if (phone != null ? !phone.equals(event.phone) : event.phone != null) return false;
//        if (eventStartTime != null ? !eventStartTime.equals(event.eventStartTime) : event.eventStartTime != null)
//            return false;
//        if (date != null ? !date.equals(event.date) : event.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
//        result = 31 * result + Arrays.hashCode(text);
//        result = 31 * result + (type != null ? type.hashCode() : 0);
//        result = 31 * result + (city != null ? city.hashCode() : 0);
//        result = 31 * result + (street != null ? street.hashCode() : 0);
//        result = 31 * result + building;
//        result = 31 * result + (buildingIndex != null ? buildingIndex.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
//        result = 31 * result + (phone != null ? phone.hashCode() : 0);
//        result = 31 * result + (eventStartTime != null ? eventStartTime.hashCode() : 0);
//        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
