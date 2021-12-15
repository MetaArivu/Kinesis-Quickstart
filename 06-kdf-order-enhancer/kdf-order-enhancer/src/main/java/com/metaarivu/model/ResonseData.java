package com.metaarivu.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResonseData {

	private String recordId;
	private String result;
	private Object data;

	public ResonseData(String recordId, String result, Object data) {
		super();
		this.recordId = recordId;
		this.result = result;
		this.data = data;
	}

	public String getRecordId() {
		return recordId;
	}

	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}
	
	public String toJSON() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}

