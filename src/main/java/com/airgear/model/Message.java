package com.airgear.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String text;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(name = "send_at")
    private OffsetDateTime sentAt;

    public Message() {
    }

    public Message(String text, Goods goods,
                   User user, OffsetDateTime sentAt) {
        this.text = text;
        this.goods = goods;
        this.user = user;
        this.sentAt = sentAt;
    }

    public Message(UUID id, String text,
                   Goods goods, User user) {
        this.id = id;
        this.text = text;
        this.goods = goods;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OffsetDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(OffsetDateTime sendedAt) {
        this.sentAt = sendedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(text, message.text) && Objects.equals(goods, message.goods) && Objects.equals(user, message.user) && Objects.equals(sentAt, message.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, goods, user, sentAt);
    }
}
