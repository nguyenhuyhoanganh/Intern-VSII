package com.example.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @author AnhNHH.
 * ModifyAuditable là một abstract class đại diện cho một entity kiểm soát các thông tin
 * bao gồm cả việc tạo mới và sửa đổi. Nó kế thừa CreateAuditable để bao gồm các thuộc tính
 * liên quan đến việc tạo mới. Class này thêm các thuộc tính về người sửa đổi cuối cùng
 * (User - modified_by) và thời gian lần sửa đổi cuối cùng.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public abstract class ModifyAuditable extends CreateAuditable {

    /**
     * User người sửa đổi cuối cùng của entity.
     * Mối quan hệ many-to-one với entity User.
     */
    @LastModifiedBy
    @JoinColumn(name = "modified_by", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private User modifiedBy;

    /**
     * Timestamp cho biết thời điểm entity được sửa đổi lần cuối.
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modifiedAt;
}
