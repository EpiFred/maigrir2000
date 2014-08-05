package com.maigrir2000v03;

import java.io.Serializable;

public class RegimeData {
	
	public class SMSData implements Serializable {

		private static final long serialVersionUID = 69200L;
		
		// Title from the diet
		private String title_regime;
		// desc of the diet
		private String content_regime;
		
		public String getNumber() {
			return title_regime;
		}
		
		public void setTitle(String title_regime) {
			this.title_regime = title_regime;
		}
		
		public String getContent() {
			return content_regime;
		}
		
		public void setBody(String content_regime) {
			this.content_regime = content_regime;
		}
	}

}
