package cn.fulong.tra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="test_result")
public class TestData implements Serializable{
	private String id;
	private String testNo;
	private String fanName;
	private Timestamp testTime;
	private Double jiangjujiao1;
	private Double jiangjujiao2;
	private Double jiangjujiao3;
	private Double sudu1;
	private Double sudu2;
	private Double sudu3;
	private Double dianya1;
	private Double dianya2;
	private Double dianya3;
	private Double wendu1;
	private Double wendu2;
	private Double wendu3;
	
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
	public Timestamp getTestTime() {
		return testTime;
	}
	public void setTestTime(Timestamp testTime) {
		this.testTime = testTime;
	}
	public String getTestNo() {
		return testNo;
	}
	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}
	public Double getJiangjujiao1() {
		return jiangjujiao1;
	}
	public void setJiangjujiao1(Double jiangjujiao1) {
		this.jiangjujiao1 = jiangjujiao1;
	}
	public Double getJiangjujiao2() {
		return jiangjujiao2;
	}
	public void setJiangjujiao2(Double jiangjujiao2) {
		this.jiangjujiao2 = jiangjujiao2;
	}
	public Double getJiangjujiao3() {
		return jiangjujiao3;
	}
	public void setJiangjujiao3(Double jiangjujiao3) {
		this.jiangjujiao3 = jiangjujiao3;
	}
	public Double getSudu1() {
		return sudu1;
	}
	public void setSudu1(Double sudu1) {
		this.sudu1 = sudu1;
	}
	public Double getSudu2() {
		return sudu2;
	}
	public void setSudu2(Double sudu2) {
		this.sudu2 = sudu2;
	}
	public Double getSudu3() {
		return sudu3;
	}
	public void setSudu3(Double sudu3) {
		this.sudu3 = sudu3;
	}
	public Double getDianya1() {
		return dianya1;
	}
	public void setDianya1(Double dianya1) {
		this.dianya1 = dianya1;
	}
	public Double getDianya2() {
		return dianya2;
	}
	public void setDianya2(Double dianya2) {
		this.dianya2 = dianya2;
	}
	public Double getDianya3() {
		return dianya3;
	}
	public void setDianya3(Double dianya3) {
		this.dianya3 = dianya3;
	}
	public Double getWendu1() {
		return wendu1;
	}
	public void setWendu1(Double wendu1) {
		this.wendu1 = wendu1;
	}
	public Double getWendu2() {
		return wendu2;
	}
	public void setWendu2(Double wendu2) {
		this.wendu2 = wendu2;
	}
	public Double getWendu3() {
		return wendu3;
	}
	public void setWendu3(Double wendu3) {
		this.wendu3 = wendu3;
	}
	
	
}
