package cn.fulong.tra.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="fan_date")
public class fanDate implements Serializable{
	private String id;
	private String fanName;
	private Date createDate;
	private int testPeriod;
	private Date lastDate;
	private Date nextDate;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFanName() {
		return fanName;
	}
	public void setFanName(String fanName) {
		this.fanName = fanName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getTestPeriod() {
		return testPeriod;
	}
	public void setTestPeriod(int testPeriod) {
		this.testPeriod = testPeriod;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public Date getNextDate() {
		return nextDate;
	}
	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

}
