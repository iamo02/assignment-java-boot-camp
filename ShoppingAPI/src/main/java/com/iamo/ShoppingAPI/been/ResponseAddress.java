package com.iamo.ShoppingAPI.been;

public class ResponseAddress {

	private String code;
	private String message;
	private String email;
	private String fullName;
	private String address;
	private String zipCode;
	private String district;
	private String province;
	private String mobile;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}

//"code":"00",
//"message":"success",
//"email":"iamo.java@gmail.com",
//"fullname":"พงษ์ชัย บุญมี",
//"address":"111/222 xxxx",
//"ZIPCode":"11111",
//"district":"city",
//"province":"BKK",
//"mobile":"0812345678"