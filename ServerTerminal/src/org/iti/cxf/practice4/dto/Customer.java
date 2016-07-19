package org.iti.cxf.practice4.dto;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2117592767847649890L;
	
	private String id;
    private String name;
    private Date birthday;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    @Override
    public String toString() {
    	return new StringBuilder().append(id).append(name).append(birthday).toString();
        //return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
    }
}