package com.huijian.rac.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class User implements UserDetails {
    private String personID;
    private String personName;
    private String departCode;
    private String memo;
    private String srmOne;
    private String srmTwo;
    private String srmThree;
    private String gender;
    private String birthday;
    private String iccardNo;
    private String address;
    private String telephone;
    private String  email;
    private int isOperate;
    private String username;
    private String password;
    private int isActive;
    private byte[] signedPhoto;
    private int defaultWorkGroupID;
    private String hospitalID;
    private int workMode;
    private int pjqDoctor;
    private int zdbcTXM;
    private String roleID;
    private List<Role> roles;

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getROLENAME()));
        }
        return authorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }


    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSrmOne() {
        return srmOne;
    }

    public void setSrmOne(String srmOne) {
        this.srmOne = srmOne;
    }

    public String getSrmTwo() {
        return srmTwo;
    }

    public void setSrmTwo(String srmTwo) {
        this.srmTwo = srmTwo;
    }

    public String getSrmThree() {
        return srmThree;
    }

    public void setSrmThree(String srmThree) {
        this.srmThree = srmThree;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIccardNo() {
        return iccardNo;
    }

    public void setIccardNo(String iccardNo) {
        this.iccardNo = iccardNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsOperate() {
        return isOperate;
    }

    public void setIsOperate(int isOperate) {
        this.isOperate = isOperate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public byte[] getSignedPhoto() {
        return signedPhoto;
    }

    public void setSignedPhoto(byte[] signedPhoto) {
        this.signedPhoto = signedPhoto;
    }

    public int getDefaultWorkGroupID() {
        return defaultWorkGroupID;
    }

    public void setDefaultWorkGroupID(int defaultWorkGroupID) {
        this.defaultWorkGroupID = defaultWorkGroupID;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public int getWorkMode() {
        return workMode;
    }

    public void setWorkMode(int workMode) {
        this.workMode = workMode;
    }

    public int getPjqDoctor() {
        return pjqDoctor;
    }

    public void setPjqDoctor(int pjqDoctor) {
        this.pjqDoctor = pjqDoctor;
    }

    public int getZdbcTXM() {
        return zdbcTXM;
    }

    public void setZdbcTXM(int zdbcTXM) {
        this.zdbcTXM = zdbcTXM;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
