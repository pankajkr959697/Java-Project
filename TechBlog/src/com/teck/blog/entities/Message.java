package com.teck.blog.entities;

public class Message {
private String content;
private String type;
private String csClass;
public Message(String content, String type, String csClass) {
	super();
	this.content = content;
	this.type = type;
	this.csClass = csClass;
}
//getter and setter
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getCsClass() {
	return csClass;
}
public void setCsClass(String csClass) {
	this.csClass = csClass;
}

}
