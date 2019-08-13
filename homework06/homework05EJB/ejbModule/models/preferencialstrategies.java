package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preferencialstrategies")
public class preferencialstrategies implements Serializable{
	private String orderId;
	private String preferencialstrategyId;
	
	@Id
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPreferencialstrategyId() {
		return preferencialstrategyId;
	}
	public void setPreferencialstrategyId(String preferencialstrategyId) {
		this.preferencialstrategyId = preferencialstrategyId;
	}
	
}
