package com.iandonaldson.data;

public interface PaymentDao {
	public Payment setPayment(int paymentID);
	public boolean updatePayment(int paymentID);
	public boolean deletePayment(int paymentID);
}
