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
public class TestResult implements Serializable{
	private String id;
	private String fanName;
	private String testNo;
	private Timestamp testTime;
	private Double yajiang1;
	private Double yajiang2;
	private Double yajiang3;
	private String status1;
	private String status2;
	private String status3;
	
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
	public String getTestNo() {
		return testNo;
	}
	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}
	public Timestamp getTestTime() {
		return testTime;
	}
	public void setTestTime(Timestamp testTime) {
		this.testTime = testTime;
	}
	public Double getYajiang1() {
		return yajiang1;
	}
	public void setYajiang1(Double yajiang1) {
		this.yajiang1 = yajiang1;
	}
	public Double getYajiang2() {
		return yajiang2;
	}
	public void setYajiang2(Double yajiang2) {
		this.yajiang2 = yajiang2;
	}
	public Double getYajiang3() {
		return yajiang3;
	}
	public void setYajiang3(Double yajiang3) {
		this.yajiang3 = yajiang3;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	public String getStatus3() {
		return status3;
	}
	public void setStatus3(String status3) {
		this.status3 = status3;
	}
	
	
}
