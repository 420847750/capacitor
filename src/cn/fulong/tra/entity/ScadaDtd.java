package cn.fulong.tra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="scada_dtd")
public class ScadaDtd implements Serializable{
	private static final long serialVersionUID = 7216454618794424293L;
	private String id;
	private String name;
	private String type;
	private String fileNo;
	private int busAddr;
	private int busPoints;
	private String englishName;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public int getBusAddr() {
		return busAddr;
	}
	public void setBusAddr(int busAddr) {
		this.busAddr = busAddr;
	}
	public int getBusPoints() {
		return busPoints;
	}
	public void setBusPoints(int busPoints) {
		this.busPoints = busPoints;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
}
