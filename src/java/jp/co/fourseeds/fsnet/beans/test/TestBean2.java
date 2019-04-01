package jp.co.fourseeds.fsnet.beans.test;

import java.io.Serializable;

public class TestBean2 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String depoCd;
	String bumonCd;
	
	public String getDepoCd() {
		return depoCd;
	}
	public void setDepoCd(String depoCd) {
		this.depoCd = depoCd;
	}
	public String getBumonCd() {
		return bumonCd;
	}
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}
	
}
