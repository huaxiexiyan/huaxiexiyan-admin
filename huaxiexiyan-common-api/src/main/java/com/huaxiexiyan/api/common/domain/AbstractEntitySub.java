package com.huaxiexiyan.api.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @author xiyan
 * @date 2023/7/31 17:26
 */
@Data
public abstract class AbstractEntitySub {

	@TableId(type = IdType.ASSIGN_ID)
    @Comment("记录主键")
    @Id
    @JsonDeserialize(as = Long.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

	/**
	 * 最后修改时间
	 **/
	@Comment("记录最后修改时间")
	@JsonIgnore
	private LocalDateTime lastModifyTime;

	@Comment("记录最后修改人id")
	@JsonIgnore
	private Long lastModifyBy;

    /**
     * 删除时间
     */
    @Comment("记录逻辑删除时间")
    @JsonIgnore
    private LocalDateTime deTime;


}
