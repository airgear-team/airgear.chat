package com.airgear.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "complaint_category")
public class ComplaintCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name length must be between 3 and 100 characters")
    private String name;

    @OneToMany(mappedBy = "complaintCategory")
    private List<Complaint> complaints;

    public ComplaintCategory() {
    }

    public ComplaintCategory(Long id, String name,
                             List<Complaint> complaints) {
        this.id = id;
        this.name = name;
        this.complaints = complaints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplaintCategory that = (ComplaintCategory) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(complaints, that.complaints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, complaints);
    }
}
