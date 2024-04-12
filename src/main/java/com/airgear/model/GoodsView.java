package com.airgear.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
public class GoodsView {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;

    @Column
    private String ip;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public GoodsView() {
    }

    public GoodsView(long userId, String ip, OffsetDateTime createdAt, Goods goods) {
        this.userId = userId;
        this.ip = ip;
        this.createdAt = createdAt;
        this.goods = goods;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsView goodsView = (GoodsView) o;
        return id == goodsView.id
                && userId == goodsView.userId
                && Objects.equals(ip, goodsView.ip)
                && Objects.equals(createdAt, goodsView.createdAt)
                && Objects.equals(goods, goodsView.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, ip, createdAt, goods);
    }
}
