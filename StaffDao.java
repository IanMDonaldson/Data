package com.iandonaldson.data;

import java.util.List;

public interface StaffDao {
	public List<Staff> getStaffAtStore(int storeID);
	public List<Staff> getAllStaff();
	public Staff getStaff(int managerStaffID);
	public boolean updateStaff(Staff staffMember);
	public boolean deleteStaff(int managerStaffID);
}
