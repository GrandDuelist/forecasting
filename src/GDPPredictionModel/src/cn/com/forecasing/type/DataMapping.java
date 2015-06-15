package cn.com.forecasing.type;

public class DataMapping {
	public static String BP_WEIGHT_DIRECTORY = "../../data/bpweight/"; //神经网络权值存放位置
	public static int bpTrainTime = 10000; //神经网络训练次数
	public static int numberX = 8; //输入属性个数
	public static int numberY = 1; //输出属性个数
	public static int minPreviousDataNumber = 10; //bp 训练的最小数据数目
	public static final int PAST_YEAR_NUMBER = 10;
	public static final int EARLEST_YEAR  = 1980;
}
