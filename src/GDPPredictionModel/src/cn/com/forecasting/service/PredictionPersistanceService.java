package cn.com.forecasting.service;

public interface PredictionPersistanceService {
	public void bpPredictionPersistance(int year,int month);
	public void mixedPredictionPersistance(int year, int month);
	public void regPredictionPersistance(int year, int month);
}
