package cn.com.forecasting.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import cn.com.forecasing.type.ModelType;
import cn.com.forecasting.service.PredictionPersistanceService;
import cn.com.sql.serviceimp.PredictionPersistanceServiceImp;

public class PredictionMainUI extends JPanel implements MouseListener,ActionListener,Runnable {

	
	public JLabel currentStatusHint = new JLabel("当前状态");
	public JLabel currentStatus = new JLabel("空闲");
	public JButton bpButton = new JButton("神经网络预测");
	public JButton regButton = new JButton("回归模型预测");
	public JButton mixedButton  = new JButton("混合模型预测");
	
	public JTextField yearInput  = new JTextField();
	public JTextField monthInput = new JTextField();
	
	public ModelType currentType =  ModelType.None;
	public boolean isChange = false;
	public boolean isOperation = false;
	public PredictionPersistanceService  pService= new  PredictionPersistanceServiceImp();
	
	public PredictionMainUI(){
		this.setLayout(null);
		this.setInstruction();
		this.setButton();
	}
	
	public void setInstruction(){
		JPanel LabelPanel = new JPanel();
		LabelPanel.setLayout(new GridLayout(1,3));
		///LabelPanel.setBackground(Color.pink);
		LabelPanel.setBounds(0, 0, 500, 60);
		Border b = BorderFactory.createEtchedBorder(); 
	    LabelPanel.setBorder(b); 
	    
	    JPanel labelPanel = new JPanel();
	    labelPanel.setLayout(new GridLayout(2,2));
	    labelPanel.add(new JLabel("输入年"));
	    labelPanel.add(this.yearInput);
	    labelPanel.add(new JLabel("输入月"));
	    labelPanel.add(this.monthInput);
	    
	    LabelPanel.add(labelPanel);
	    LabelPanel.add(this.currentStatusHint);
		LabelPanel.add(this.currentStatus);
		
		this.currentStatusHint.setFont(new Font("Serif",Font.ROMAN_BASELINE,18));
		this.currentStatus.setFont(new Font("Serif",Font.ROMAN_BASELINE,18));
		
		this.add(LabelPanel);
	}
	
	public void setButton(){
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new GridLayout(1,3));
		///LabelPanel.setBackground(Color.pink);
		ButtonPanel.setBounds(0, 60, 500, 60);
		ButtonPanel.add(this.bpButton);
		ButtonPanel.add(this.regButton);
		ButtonPanel.add(this.mixedButton);
		this.bpButton.addActionListener(this);
		this.mixedButton.addActionListener(this);
		this.regButton.addActionListener(this);
		this.add(ButtonPanel);
	}
	
	public void setLabel(){
		this.add(this.currentStatusHint);
		this.add(this.currentStatus);	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			
			if(this.isChange && !this.isOperation){
				try{
				this.isOperation = true;
				if(currentType != ModelType.None){
//					this.pService.bpPredictionPersistance(year, month)
					int year = Integer.parseInt(this.yearInput.getText());
					int month = Integer.parseInt(this.monthInput.getText());
							
					if(currentType==ModelType.BP){
						this.pService.bpPredictionPersistance(year, month);
					}else if(currentType==ModelType.Mix){
						this.pService.mixedPredictionPersistance(year, month);
					}else if(currentType ==ModelType.Reg){
						this.pService.regPredictionPersistance(year, month);
					}
					
				}
				
				
				
				this.isChange = false;
				this.currentStatus.setText("空闲");
				this.currentType = ModelType.None;
				
				this.isOperation = false;
				}catch(Exception e){
					this.isChange = false;
					this.isOperation = false;
					this.currentStatus.setText("年月输入错误");
				}
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == this.bpButton){
			currentType = ModelType.BP;
			this.isChange =true;
			if(!this.isOperation)
			this.currentStatus.setText("神经网络预测中...");
		}else if(arg0.getSource() == this.regButton){
			currentType = ModelType.Reg;
			this.isChange =true;
			if(!this.isOperation)
			this.currentStatus.setText("回归模型预测中...");
		}else if(arg0.getSource() == this.mixedButton){
			currentType = ModelType.Mix;
			this.isChange =true;
			if(!this.isOperation)
			this.currentStatus.setText("混合模型预测中...");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
