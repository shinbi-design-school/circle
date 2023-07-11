package com.design_shinbi.circle.model.entity;

import java.io.InputStream;

public class Icon {
	private String fileName;
	private InputStream stream;

	public Icon(String fileName, InputStream stream) {
		this.fileName = fileName;
		this.stream = stream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}
}