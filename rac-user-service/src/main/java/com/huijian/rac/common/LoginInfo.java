package com.huijian.rac.common;

public class LoginInfo {
	public String PersonID;
    public String PersonName;
    public String DepartID;
 
    public String UserName;
    public Integer DefaultWorkGroupID;
    public String HospitalID;
    public Integer roleId;//角色id
	public String getPersonID() {
		return PersonID;
	}
	public void setPersonID(String personID) {
		PersonID = personID;
	}
	public String getPersonName() {
		return PersonName;
	}
	public void setPersonName(String personName) {
		PersonName = personName;
	}
	public String getDepartID() {
		return DepartID;
	}
	public void setDepartID(String departID) {
		DepartID = departID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public Integer getDefaultWorkGroupID() {
		return DefaultWorkGroupID;
	}
	public void setDefaultWorkGroupID(Integer defaultWorkGroupID) {
		DefaultWorkGroupID = defaultWorkGroupID;
	}
	public String getHospitalID() {
		return HospitalID;
	}
	public void setHospitalID(String hospitalID) {
		HospitalID = hospitalID;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
