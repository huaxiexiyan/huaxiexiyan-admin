package com.huaxiexiyan.erp.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.huaxiexiyan.api.common.domain.AbstractEntity;
import com.huaxiexiyan.api.common.type.CatTreeNode;
import com.huaxiexiyan.erp.domain.type.MenuMeta;
import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * 菜单资源
 *
 * @author xiyan
 * @date 2025/7/1 15:40
 */
@Data
@TableName
public class CatMenu extends AbstractEntity implements CatTreeNode<CatMenu, Long> {

	private String path;

	private String name;

	private CatMenu parentMenu;

	/**
	 * 菜单展示属性
	 */
	private MenuMeta meta;

	private String component;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String components;

	private String redirect;

	private List<CatMenu> children;

	@JsonDeserialize(as = Long.class)
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return super.getId();
	}

	@JsonDeserialize(as = Long.class)
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getParentId() {
		return parentMenu == null ? 0L : parentMenu.getId() == null ? 0L : parentMenu.getId();
	}

	@Override
	public void setChildren(Collection<CatMenu> children) {
		this.children = List.copyOf(children);
	}

	public String getLabel() {
		return meta == null ? null : meta.getTitle();
	}

	@JsonDeserialize(as = Long.class)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getValue() {
		return super.getId();
	}

	@JsonIgnore
	public Boolean hasParentMenu() {
		return getParentId() != 0L;
	}

}
