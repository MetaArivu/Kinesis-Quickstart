package com.metaarivu.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaarivu.model.TweetModel;
import com.metaarivu.service.TwitterService;

@RestController
@RequestMapping("/api/v1")
public class TwitterController {

	@Autowired
	private TwitterService svc;

	@RequestMapping(value = "/tweets/{hashTag}")
	public TweetModel getTweets(@PathVariable final String hashTag) {
		return svc.fetchTweets(hashTag);
	}

	
}
