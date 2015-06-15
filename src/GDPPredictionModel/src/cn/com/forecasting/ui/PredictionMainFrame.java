package cn.com.forecasting.ui;

import java.awt.Color;

import javax.swing.JFrame;

public class PredictionMainFrame extends JFrame{
	public PredictionMainUI ui  = new PredictionMainUI();
	public PredictionMainFrame(){
		super("经济预测");
		this.setSize(500,150);
		this.setResizable(false);
		
		Thread t1 =  new Thread(ui);
		this.add(ui);
		this.ui.setBounds(0, 0, 500, 150);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t1.start();
		this.setVisible(true);
	}
}
