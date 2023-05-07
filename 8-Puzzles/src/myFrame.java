import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myFrame extends JFrame implements ActionListener {
	public String in;

	String steps = "";

	Node node = new Node();
	Result res = null;
	int cost;
	long time;
	int nodes;
	int depth;

	JFrame frame = new JFrame();
	JPanel[][] panel = new JPanel[3][3];
	JPanel[][] panels = new JPanel[3][3];
	JLabel label = new JLabel();
	JComboBox combo;
	JTextField text = new JTextField();
	JButton button1, button2, button3;
	JLabel[][] numbers = new JLabel[3][3];
	JPanel panel1 = new JPanel();

	JPanel panelCost = new JPanel();
	JPanel panelDepth = new JPanel();
	JPanel panelTime = new JPanel();
	JPanel panelNodes = new JPanel();
	JLabel labelCost = new JLabel();
	JLabel labelDepth = new JLabel();
	JLabel labelTime = new JLabel();
	JLabel labelNodes = new JLabel();

	myFrame() {
		states("         ");
		lastPanels();
		algorithms();
		input();
		combo.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		frame.setSize(800, 820);
		frame.setTitle("8-puzzle");
		frame.getContentPane().setBackground(new Color(3, 82, 88));
		frame.setLayout(null);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		labels();
		frame.setVisible(true);
	}

	void square() {
		int x = 327, y = 15;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				panel[i][j] = new JPanel();
				panel[i][j].setBounds(x, y, 140, 140);
				panel[i][j].setBackground(Color.white);
				frame.add(panel[i][j]);
				x = x + 145;
			}
			x = 327;
			y = y + 145;
		}

	}

	void labels() {
		label.setText("Choose the algorithm: ");
		label.setFont(new Font("amaze", Font.BOLD, 28));
		label.setForeground(Color.darkGray);
		label.setVerticalAlignment(JLabel.TOP);
		panel1.setBounds(7, 10, 300, 50);
		panel1.setBackground(new Color(3, 82, 88));
		frame.add(panel1);
		panel1.add(label);
		label.setOpaque(true);
	}

	void algorithms() {
		String[] algo = { "BFS", "DFS", "A*:Manhattan Distance", "A*Euclidean Distance" };
		combo = new JComboBox(algo);
		combo.setBounds(50, 70, 200, 50);
		combo.setBackground(Color.white);

		combo.setFont(new Font("times new roman", Font.BOLD, 20));
		frame.add(combo);
	}

	void input() {
		text.setBounds(50, 200, 200, 50);
		button1 = new JButton("submit the initial state");
		button1.setBounds(50, 250, 200, 50);
		frame.add(text);
		frame.add(button1);
		button2 = new JButton("Next step");
		button2.setBounds(50, 350, 200, 50);
		frame.add(button2);
	}

	void states(String state) {
		int k = 0;
		int x = 327, y = 15;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				numbers[i][j] = new JLabel();
				numbers[i][j].setText(String.valueOf(state.charAt(k)));
				System.out.println(String.valueOf(state.charAt(k)));
				numbers[i][j].setBounds(x, y, 100, 100);
				numbers[i][j].setForeground(Color.black);
				numbers[i][j].setFont(new Font("times new roman", Font.BOLD, 50));
				panels[i][j] = new JPanel();
				panels[i][j].setBounds(x, y, 140, 140);
				panels[i][j].setBackground(Color.white);
				frame.add(panels[i][j]);
				frame.revalidate();
				frame.repaint();
				panels[i][j].setLayout(new BoxLayout(panels[i][j], BoxLayout.X_AXIS));
				panels[i][j].add(Box.createHorizontalGlue());
				panels[i][j].add(numbers[i][j]);
				panels[i][j].add(Box.createHorizontalGlue());

				k++;
				x = x + 145;
			}
			x = 327;
			y = y + 145;
		}

	}

	void remove() {
		int x = 315, y = 10;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				frame.remove(panels[i][j]);
				x = x + 155;
			}
			x = 315;
			y = y + 155;
		}
	}

	int iterator = 0;

	void lastPanels() {
		panelCost.setBounds(20, 460, 750, 50);
		panelDepth.setBounds(20, 510, 750, 50);
		panelTime.setBounds(20, 560, 750, 50);
		panelNodes.setBounds(20, 610, 750, 50);
		panelCost.setBackground(Color.lightGray);
		panelDepth.setBackground(Color.lightGray);
		panelNodes.setBackground(Color.lightGray);
		panelTime.setBackground(Color.lightGray);
		frame.add(panelCost);
		frame.add(panelDepth);
		frame.add(panelTime);
		frame.add(panelNodes);
	}

	void output(int cost, long time, int depth, int nodes) {
		labelCost.setText("-->Cost of path :" + cost);
		labelDepth.setText("-->Search depth :" + depth);
		labelTime.setText("-->Running time :" + time);
		labelNodes.setText("-->Nodes expanded :" + nodes);

		labelCost.setFont(new Font("amaze", Font.BOLD, 32));
		labelDepth.setFont(new Font("amaze", Font.BOLD, 32));
		labelTime.setFont(new Font("amaze", Font.BOLD, 32));
		labelNodes.setFont(new Font("amaze", Font.BOLD, 32));

		frame.add(panelCost);
		frame.add(panelDepth);
		frame.add(panelTime);
		frame.add(panelNodes);
		panelCost.add(labelCost);
		panelDepth.add(labelDepth);
		panelTime.add(labelTime);
		panelNodes.add(labelNodes);
	}

	void output(String st) {
		labelDepth.setText(st);
		labelDepth.setFont(new Font("amaze", Font.BOLD, 32));
		frame.add(panelDepth);
		panelDepth.add(labelDepth);
	}

	void updateOutput() {
		panelCost.remove(labelCost);
		panelDepth.remove(labelDepth);
		panelTime.remove(labelTime);
		panelNodes.remove(labelNodes);
		frame.remove(panelCost);
		frame.remove(panelDepth);
		frame.remove(panelTime);
		frame.remove(panelNodes);

	}

	// function to check the validity of the input
	boolean valid_input(String input) {
		int len = input.length();
		if (len != 9)
			return false;
		else {
			for (int i = 0; i < len; i++) {
				if (Character.getNumericValue(in.charAt(i)) < 0 || Character.getNumericValue(in.charAt(i)) > 8)
					return false;
				else {
					for (int j = i + 1; j < len; j++) {
						if (input.charAt(i) == input.charAt(j))
							return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == combo) {
			System.out.println(combo.getSelectedItem());
		}

		String algo = (combo.getSelectedItem()).toString();
		Solver m = null;
		switch (algo) {
		case "BFS":
			m = new BFS();
			break;
		case "DFS":
			m = new DFS();
			break;
		case "A*:Manhattan Distance":
			m = new AStarManhattan();
			break;
		case "A*Euclidean Distance":
			m = new AStarEuclidean();
			break;
		}

		if (e.getSource() == button1) {
			steps = "";
			iterator = 0;

			System.out.println(text.getText());
			in = text.getText();

			if (!valid_input(in)) {
				// JOptionPane.showMessageDialog(null,"invalid input , please enter numbers from
				// 0 to 8","Alert",JOptionPane.WARNING_MESSAGE);
				remove();
				frame.revalidate();
				frame.repaint();
				states("         ");
				updateOutput();
				output("NOT VALID");
				System.out.println("NOT VALID");
			}
			else {
				// replace 0 with space to be appeared
				String s1 = "";
				for (int i = 0; i < in.length(); i++) {
					char c = in.charAt(i);
					if (c == '0')
						c = ' ';
					s1 += c;
				}
				in = s1;

				remove();
				frame.revalidate();
				frame.repaint();
				states(in); // print the boxxxxxxxxxxx with spaces instead of zeroo

				// replace the space to 0 again to be handled as an integer
				String s2 = "";
				if (in != null) {
					for (int i = 0; i < in.length(); i++) {
						char c = in.charAt(i);
						if (c == ' ')
							c = '0';
						s2 += c;
					}
					in = s2; // the index with 0

					if (in.equals("012345678")) {
						// JOptionPane.showMessageDialog(null,"init State is a goal
						// State","Alert",JOptionPane.WARNING_MESSAGE);
						System.out.println("goaaaaaaaal");
						steps = "";
						updateOutput();
						frame.revalidate();
						frame.repaint();
						output(0, 0, 0, 0);
					} else {
						node.setState(Integer.parseInt(in));
						res = m.solve(node);
						if (res.Cost() == 0) {
							// JOptionPane.showMessageDialog(null,"unsolvable","Alert",JOptionPane.WARNING_MESSAGE);
							remove();
							frame.revalidate();
							frame.repaint();
							states("         ");
							steps = " ";
							updateOutput();
							output("unsolvable");
							System.out.println("unsolvable");
							// output(0, 0, 0, 0);
						}

						else {
							System.out.println("leh 7llllllll");
							// res = m.solve(node);
							// l mtloooob
							cost = res.Cost();
							time = res.getTime();
							nodes = res.getNofNodes();
							depth = res.getMaxDepth();

							for (Node n : res.getPath()) {
								steps += n.toString();
							}

							updateOutput();
							frame.revalidate();
							frame.repaint();
							if (algo == "BFS")
								depth = cost;
							output(cost, time, depth, nodes);
						}
					}
				}
			}
		}

		if (e.getSource() == button2) {
			iterator += 9;

			if (iterator > steps.length() - 1) {
				System.out.println("finish");
			} else {
				String s = "";
				int i;
				for (i = iterator; i < iterator + 9; i++) {
					char c = steps.charAt(i);
					if (c == '0') {
						c = ' ';
					}
					s += c;
				}
				remove();
				frame.revalidate();
				frame.repaint();
				states(s);
			}
		}

	}
}