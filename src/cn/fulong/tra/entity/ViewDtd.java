package cn.fulong.tra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="view_dtd")
public class ViewDtd implements Serializable{
	private static final long serialVersionUID = 7216454618794424293L;
	private String id;
	private String deviceName;
	private String deviceId;
	private String errorNo;
	private String name;
	private String type;
	private String fileNo;
	private String ip;
	private int slaveId;
	private int port;
	private int busAddr;
	private int busPoints;
	private String busType;
	private String englishName;
	private String ownname;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getErrorNo() {
		return errorNo;
	}
	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
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
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getSlaveId() {
		return slaveId;
	}
	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getOwnname() {
		return ownname;
	}
	public void setOwnname(String ownname) {
		this.ownname = ownname;
	}
	
	
}
