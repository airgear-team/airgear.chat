package com.airgear.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "complaint_category_id", referencedColumnName = "id")
    private ComplaintCategory complaintCategory;

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private Goods goods;

    @Column(name = "description")
    @NotBlank(message = "Description cannot be blank")
    @Size(min = 10, max = 1000, message = "Description length must be between 10 and 1000 characters")
    private String description;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime createdAt;

    public Complaint() {
    }

    public Complaint(Long id, User user, ComplaintCategory complaintCategory,
                     Goods goods, String description, OffsetDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.complaintCategory = complaintCategory;
        this.goods = goods;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ComplaintCategory getComplaintCategory() {
        return complaintCategory;
    }

    public void setComplaintCategory(ComplaintCategory complaintCategory) {
        this.complaintCategory = complaintCategory;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(id, complaint.id)
                && Objects.equals(user, complaint.user)
                && Objects.equals(complaintCategory, complaint.complaintCategory)
                && Objects.equals(goods, complaint.goods)
                && Objects.equals(description, complaint.description)
                && Objects.equals(createdAt, complaint.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, complaintCategory, goods, description, createdAt);
    }
}
