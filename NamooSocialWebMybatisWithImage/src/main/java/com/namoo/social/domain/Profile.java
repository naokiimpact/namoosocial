package com.namoo.social.domain;

import com.namoo.social.web.press.Count;

public class Profile {

	private String introduction;
	private String region;
	private String homepage;

	// 프로필 이미지
	private ImageFile image;
	
	private Count count;

	// --------------------------------------------------------------------------

	public Profile() {
		//
	}

	public Profile(String introduction, String region,
			String homepage, ImageFile image, Count count) {
		//
		this.introduction = introduction;
		this.region = region;
		this.homepage = homepage;
		this.image = image;
		this.count = count;
	}

	// --------------------------------------------------------------------------

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public ImageFile getImage() {
		return image;
	}

	public void setImage(ImageFile image) {
		this.image = image;
	}

	public Count getCount() {
		return count;
	}

	public void setCount(Count count) {
		this.count = count;
	}
	
}
