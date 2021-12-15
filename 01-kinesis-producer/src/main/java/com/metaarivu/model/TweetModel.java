package com.metaarivu.model;

import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TweetModel {

	private List<Tweet> data;

	private LinkedHashMap<String, String> meta;

	public List<Tweet> getData() {
		return data;
	}

	public void setData(List<Tweet> data) {
		this.data = data;
	}

	public LinkedHashMap<String, String> getMeta() {
		return meta;
	}

	public void setMeta(LinkedHashMap<String, String> meta) {
		this.meta = meta;
	}

	public String toJSON() {
		try {
			return new ObjectMapper().writeValueAsString(this.getData());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
