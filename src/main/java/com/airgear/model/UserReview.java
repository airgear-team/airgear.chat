package com.airgear.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
@Table(name = "user_reviews")
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id")
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_user_id", referencedColumnName = "id")
    private User reviewedUser;

    @Min(1)
    @Max(5)
    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    public UserReview() {
    }

    public UserReview(Long id, User reviewer, User reviewedUser,
                      int rating, String comment, OffsetDateTime createdAt) {
        this.id = id;
        this.reviewer = reviewer;
        this.reviewedUser = reviewedUser;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getReviewedUser() {
        return reviewedUser;
    }

    public void setReviewedUser(User reviewedUser) {
        this.reviewedUser = reviewedUser;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        UserReview that = (UserReview) o;
        return rating == that.rating && Objects.equals(id, that.id)
                && Objects.equals(reviewer, that.reviewer)
                && Objects.equals(reviewedUser, that.reviewedUser)
                && Objects.equals(comment, that.comment)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewer, reviewedUser, rating, comment, createdAt);
    }
}
