package com.jfak.fizzbuzz.record;

import java.text.SimpleDateFormat;
import java.util.Date;

public record RestError(int status, String errorCode, String title, String uri, String detail, String helpUrl, String date, String object) {
	
	public RestError() {
		this(-1, "unknown", "not defined", "", "", "about:blank", new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()), "error");
	}

	public RestError(int status, String errorCode, String title, String detail, String helpUrl, String uri) {
		this(status, errorCode, title, uri, detail, helpUrl, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()), "error");
	}
	
}
