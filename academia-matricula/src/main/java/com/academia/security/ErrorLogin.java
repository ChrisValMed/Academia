package com.academia.security;

import java.util.Date;

public class ErrorLogin {

	private String mensaje;
	private Date timestamp;	
	
	public ErrorLogin(String mensaje, Date timestamp) {		
		this.mensaje = mensaje;
		this.timestamp = timestamp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getTimestamp() {
		return timestamp;
	}	

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
