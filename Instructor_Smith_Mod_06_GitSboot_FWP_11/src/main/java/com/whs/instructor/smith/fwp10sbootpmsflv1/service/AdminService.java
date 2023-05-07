package com.whs.instructor.smith.fwp10sbootpmsflv1.service;

import java.util.List;

import com.whs.instructor.smith.fwp10sbootpmsflv1.model.Admin;

public interface AdminService { 
	public void saveAdmin(Admin p);
	public Admin getAdminById(Long id); 
	public List<Admin> getAllAdmins();
	public void deleteAdminById(Long id);
	public boolean verifyAdmin(String user, String pass);
	public Admin lastAdmin(); 
}
