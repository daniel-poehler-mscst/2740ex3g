package ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame implements MouseListener, FocusListener, ActionListener, WindowListener {
	private JList employeeList;
	private JTextField enterHoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JButton sumHoursButton;
	private JButton clearHoursButton;
	private JButton clearFormButton;
	private JButton updateButton;
	private JTextField empIdTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JPanel contentPane;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(this);
		setTitle("DPoehler 2740 Ex3G Payroll I/O");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(26, 23, 108, 16);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(26, 52, 361, 102);
		contentPane.add(scrollPane);
		
//		employeeList = new JList();
//		employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Daniel Poehler", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Patti Weigand", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Neva Burdick", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList (employeeListModel);
		employeeList.addMouseListener(this);
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(26, 167, 142, 16);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setBounds(26, 196, 110, 16);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay rate (7.25 - 100):");
		lblPayRate.setBounds(26, 225, 142, 16);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter hours (0.1 - 20.0):");
		lblEnterHours.setBounds(26, 254, 147, 16);
		contentPane.add(lblEnterHours);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(26, 283, 78, 16);
		contentPane.add(lblTotalHours);
		
		JLabel lblGrossPay = new JLabel("Gross pay:");
		lblGrossPay.setBounds(26, 312, 78, 16);
		contentPane.add(lblGrossPay);
		
		enterHoursTextField = new JTextField();
		enterHoursTextField.addFocusListener(this);
		enterHoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		enterHoursTextField.setText("0.00");
		enterHoursTextField.setBounds(200, 255, 56, 22);
		contentPane.add(enterHoursTextField);
		enterHoursTextField.setColumns(10);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(200, 283, 50, 16);
		contentPane.add(totalHoursLabel);
		
		grossPayLabel = new JLabel("0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(200, 312, 56, 16);
		contentPane.add(grossPayLabel);
		
		sumHoursButton = new JButton("+");
		sumHoursButton.setEnabled(false);
		sumHoursButton.addActionListener(this);
		sumHoursButton.setBounds(278, 254, 41, 25);
		contentPane.add(sumHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(this);
		clearHoursButton.setBounds(322, 254, 97, 25);
		contentPane.add(clearHoursButton);
		
		clearFormButton = new JButton("Clear Form");
		clearFormButton.addActionListener(this);
		clearFormButton.setBounds(239, 363, 97, 25);
		contentPane.add(clearFormButton);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(this);
		updateButton.setBounds(131, 363, 97, 25);
		contentPane.add(updateButton);
		
		empIdTextField = new JTextField();
		empIdTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empIdTextField.setText("000");
		empIdTextField.setBounds(229, 167, 67, 22);
		contentPane.add(empIdTextField);
		empIdTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setBounds(148, 196, 148, 22);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateTextField.setText("7.25");
		payRateTextField.setBounds(229, 225, 67, 22);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
	}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
		
		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == employeeList) {
				do_employeeList_mouseClicked(arg0);
			}
		}
	
		protected void do_employeeList_mouseClicked(MouseEvent arg0) {
			Payroll item = (Payroll) employeeList.getSelectedValue();
			this.empIdTextField.setText(Integer.toString(item.getId()));
			this.empNameTextField.setText(item.getName());
			DecimalFormat dollarFmt = new DecimalFormat ("###0.00");
			this.payRateTextField.setText(dollarFmt.format(item.getPayRate()));
			this.totalHoursLabel.setText(Double.toString(item.getHours()));
			DecimalFormat payFmt = new DecimalFormat ("#,##0.00");
			this.grossPayLabel.setText(payFmt.format(item.calcGrossPay()));
			this.enterHoursTextField.requestFocus();
			this.sumHoursButton.setEnabled(true);
			this.clearHoursButton.setEnabled(true);
			this.updateButton.setEnabled(true);
		}
		
		public void focusGained(FocusEvent arg0) {
			if (arg0.getSource() == enterHoursTextField) {
				do_enterHoursTextField_focusGained(arg0);
			}
		}
		public void focusLost(FocusEvent arg0) {}	
		
		protected void do_enterHoursTextField_focusGained(FocusEvent arg0) {
			enterHoursTextField.selectAll();
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == updateButton) {
				do_updateButton_actionPerformed(arg0);
			}
			if (arg0.getSource() == clearFormButton) {
				do_clearFormButton_actionPerformed(arg0);
			}
			if (arg0.getSource() == clearHoursButton) {
				do_clearHoursButton_actionPerformed(arg0);
			}
			if (arg0.getSource() == sumHoursButton) {
				do_sumHoursButton_actionPerformed(arg0);
			}
		}
		
		protected void do_sumHoursButton_actionPerformed(ActionEvent arg0) {			
			Payroll item = (Payroll)employeeList.getSelectedValue();
			double hours = Double.parseDouble(this.enterHoursTextField.getText());
			
			if (!item.addHours(hours)) {
				JOptionPane.showMessageDialog(null,  "Invalid number of hours. \nMust be 0.1 - 20");
				enterHoursTextField.setText("0.00");
				enterHoursTextField.requestFocus();		
			}
			else {
				DecimalFormat hoursFmt = new DecimalFormat ("###0.00");
				this.totalHoursLabel.setText(hoursFmt.format(item.getHours()));
				DecimalFormat dollarFmt = new DecimalFormat ("$#,##0.00");
				this.grossPayLabel.setText(dollarFmt.format(item.calcGrossPay()));
				this.enterHoursTextField.setText("0.00");
				this.enterHoursTextField.requestFocus();
			}
		}
		
		protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
			Payroll item = (Payroll)employeeList.getSelectedValue();
			item.setHours(0.0);
			this.enterHoursTextField.setText("0.00");
			this.totalHoursLabel.setText("0.00");
			this.grossPayLabel.setText("0.00");
			this.enterHoursTextField.requestFocus();
		}		
		
		protected void do_clearFormButton_actionPerformed(ActionEvent arg0) {
			Payroll item = (Payroll)employeeList.getSelectedValue();
			this.employeeList.clearSelection();
			item.setHours(0.0);
			this.enterHoursTextField.setText("0.00");
			this.empIdTextField.setText("0");
			this.empNameTextField.setText("0");
			this.payRateTextField.setText("0.00");
			this.totalHoursLabel.setText("0.00");
			this.grossPayLabel.setText("0.00");			
			this.enterHoursTextField.requestFocus();
			this.sumHoursButton.setEnabled(false);
			this.clearHoursButton.setEnabled(false);
			this.updateButton.setEnabled(false);
		}
		protected void do_updateButton_actionPerformed(ActionEvent arg0) {			
			int id = Integer.parseInt(empIdTextField.getText());
			double rate = Double.parseDouble(payRateTextField.getText());
			String name = (empNameTextField.getText());
			Payroll item = (Payroll) employeeList.getSelectedValue();
			item.setId(id);
			item.setName(name);
			item.setPayRate(rate);
			if (!item.setId(id)) {
				JOptionPane.showMessageDialog(null,  "Invalid employeeID. \nMust be > 100");
				this.empIdTextField.setText(Integer.toString(item.getId()));
				empIdTextField.requestFocus();		
			}			
				else if (!item.setName(name)) {
					JOptionPane.showMessageDialog(null,  "Invalid name entry. \nPlease enter a name");
					this.empNameTextField.setText(item.getName());
					empNameTextField.requestFocus();
				}
					else if (!item.setPayRate(rate)) {
						JOptionPane.showMessageDialog(null,  "Invalid rate of pay. \nMust be 7.25 - 100");
						DecimalFormat dollarFmt = new DecimalFormat ("###0.00");
						this.payRateTextField.setText(dollarFmt.format(item.getPayRate()));
						payRateTextField.requestFocus();		
					}
			
			employeeList.repaint();
		}
	public void windowActivated(WindowEvent arg0) {
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			do_this_windowClosing(arg0);
		}
	}
	public void windowDeactivated(WindowEvent arg0) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null) {
			payrollObjMapper.writeAllPayroll(employeeListModel);
		}
	}
	
	}
