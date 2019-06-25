package cn.fulong.tra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="scada_node")
public class ScadaNode implements Serializable{
	private String id;
	private int orderNo;
	private String name;
	private String ip;
	private String fanIp;
	private int port;
	private int slaveId;
	private String point01File;
	private String point02File;
	private String point03File;
	private String point04File;
	private String groupType;
	private String lineType;
	private String pitch;
	private String gearbox;
	private String generator;
	private String converter;
	private String turbineType;
	private Double gridPower;
	
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
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getFanIp() {
		return fanIp;
	}
	public void setFanIp(String fanIp) {
		this.fanIp = fanIp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getLineType() {
		return lineType;
	}
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	public String getPitch() {
		return pitch;
	}
	public void setPitch(String pitch) {
		this.pitch = pitch;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public String getConverter() {
		return converter;
	}
	public void setConverter(String converter) {
		this.converter = converter;
	}
	public String getTurbineType() {
		return turbineType;
	}
	public void setTurbineType(String turbineType) {
		this.turbineType = turbineType;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getSlaveId() {
		return slaveId;
	}
	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}
	public String getPoint01File() {
		return point01File;
	}
	public void setPoint01File(String point01File) {
		this.point01File = point01File;
	}
	public String getPoint02File() {
		return point02File;
	}
	public void setPoint02File(String point02File) {
		this.point02File = point02File;
	}
	public String getPoint03File() {
		return point03File;
	}
	public void setPoint03File(String point03File) {
		this.point03File = point03File;
	}
	public String getPoint04File() {
		return point04File;
	}
	public void setPoint04File(String point04File) {
		this.point04File = point04File;
	}
	public Double getGridPower() {
		return gridPower;
	}
	public void setGridPower(Double gridPower) {
		this.gridPower = gridPower;
	}
	
	
}
