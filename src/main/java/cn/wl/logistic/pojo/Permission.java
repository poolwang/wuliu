package cn.wl.logistic.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permission  implements Serializable{

	private static final long serialVersionUID = 2456199126782590741L;

	@JsonProperty("id")
    private Long permissionId;

    private String name;

    private String type;

    private String url;

    private String expression;
    
    @JsonProperty("pId")
    private Long parentId;
    
    private Permission permission ;

    private Integer sort;
    //zTreeÄ¬ÈÏÕ¹¿ª
    private boolean open = true;

    public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(Long permissionId, String name, String type, String url, String expression, Long parentId,
			Permission permission, Integer sort) {
		super();
		this.permissionId = permissionId;
		this.name = name;
		this.type = type;
		this.url = url;
		this.expression = expression;
		this.parentId = parentId;
		this.permission = permission;
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", name=" + name + ", type=" + type + ", url=" + url
				+ ", expression=" + expression + ", parentId=" + parentId + ", permission=" + permission + ", sort="
				+ sort + "]";
	}
    
    
    
}