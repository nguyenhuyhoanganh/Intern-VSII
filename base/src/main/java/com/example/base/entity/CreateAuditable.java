package com.example.base.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @author AnhNHH.
 * CreateAuditable là một abstract class đại diện cho các entities cần kiểm soát các thông tin
 * về việc tạo mới bao gồm người tạo (User - created_by), thời gian tạo và ID.
 * Class này đóng vai trò như một base class cho các entities mà cần kiểm soát thông tin về việc tạo mới.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreateAuditable {

    /**
     * Định danh duy nhất cho entity.
     */
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User là người tạo ra entity.
     * Mối quan hệ many-to-one với entity User.
     */
    @CreatedBy
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    /**
     * Timestamp cho biết thời điểm entity được toạ.
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Method setter cho trường 'id'. Nó trả về 1 exception để ngăn việc đặt ID một cách thủ công
     * @param id ID được đặt (không được sử dụng do exception).
     * @throws UnsupportedOperationException nếu cố gắng đặt ID một cách thủ công.
     */
    public void setId(Long id) {
        throw new UnsupportedOperationException("Setting ID manually is not allowed");
    }
}
