package com.example.myseckill.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_user
 */
public class TUser implements Serializable {
    /**
     * 手机ID，手机号码
     */
    private Long id;

    /**
     * 
     */
    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt)+salt)
     */
    private String password;

    /**
     * 
     */
    private String salt;

    /**
     * 头像
     */
    private String head;

    /**
     * 注册时间
     */
    private Date registerDate;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    private Integer loginCount;

    private static final long serialVersionUID = 1L;

    /**
     * 手机ID，手机号码
     */
    public Long getId() {
        return id;
    }

    /**
     * 手机ID，手机号码
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * MD5(MD5(pass明文+固定salt)+salt)
     */
    public String getPassword() {
        return password;
    }

    /**
     * MD5(MD5(pass明文+固定salt)+salt)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 头像
     */
    public String getHead() {
        return head;
    }

    /**
     * 头像
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 注册时间
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 注册时间
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 最后一次登录时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 最后一次登录时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TUser other = (TUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
            && (this.getHead() == null ? other.getHead() == null : this.getHead().equals(other.getHead()))
            && (this.getRegisterDate() == null ? other.getRegisterDate() == null : this.getRegisterDate().equals(other.getRegisterDate()))
            && (this.getLastLoginDate() == null ? other.getLastLoginDate() == null : this.getLastLoginDate().equals(other.getLastLoginDate()))
            && (this.getLoginCount() == null ? other.getLoginCount() == null : this.getLoginCount().equals(other.getLoginCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
        result = prime * result + ((getHead() == null) ? 0 : getHead().hashCode());
        result = prime * result + ((getRegisterDate() == null) ? 0 : getRegisterDate().hashCode());
        result = prime * result + ((getLastLoginDate() == null) ? 0 : getLastLoginDate().hashCode());
        result = prime * result + ((getLoginCount() == null) ? 0 : getLoginCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nickname=").append(nickname);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", head=").append(head);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", lastLoginDate=").append(lastLoginDate);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}