import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JButton;

public class MainClass extends JFrame {
	private JTextField txtSoru;
	private JTextField txtCvp1;
	private JTextField txtCvp2;
	private JTextField txtCvp3;
	private JTextField txtCvp4;
	private JTextField txtDogru;
	private ArrayList<Soru> listSoru = new ArrayList<>();
	public MainClass() {
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtSoru = new JTextField();
		getContentPane().add(txtSoru);
		txtSoru.setColumns(10);
		
		txtCvp1 = new JTextField();
		getContentPane().add(txtCvp1);
		txtCvp1.setColumns(10);
		
		txtCvp2 = new JTextField();
		getContentPane().add(txtCvp2);
		txtCvp2.setColumns(10);
		
		txtCvp3 = new JTextField();
		getContentPane().add(txtCvp3);
		txtCvp3.setColumns(10);
		
		txtCvp4 = new JTextField();
		getContentPane().add(txtCvp4);
		txtCvp4.setColumns(10);
		
		txtDogru = new JTextField();
		txtDogru.setText("Dogru Cevap Index");
		getContentPane().add(txtDogru);
		txtDogru.setColumns(10);
		
		JButton btnEkle = new JButton("Ekle");
		getContentPane().add(btnEkle);
		
		btnEkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listSoru.add(new Soru(Integer.parseInt(txtDogru.getText()), new String[]{
						txtCvp1.getText(),
						txtCvp2.getText(),
						txtCvp3.getText(),
						txtCvp4.getText()
				}, txtSoru.getText()));
				txtCvp1.setText("");
				txtCvp2.setText("");
				txtCvp3.setText("");
				txtCvp4.setText("");
				txtSoru.setText("");
				txtDogru.setText("");
			}
		});
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JSONObject object = new JSONObject();
					JSONArray arr = new JSONArray();
					for(int i =0;i<listSoru.size();i++){
						JSONObject ques = new JSONObject();
						ques.put("rightAnswerIndex", listSoru.get(i).dogruCevap);
						JSONArray arAnswers = new JSONArray();
						for(int j =0;j<listSoru.get(i).cevaplar.length;j++){
							arAnswers.put(listSoru.get(i).cevaplar[j]);
						}
						ques.put("answers", arAnswers);
						arr.put(ques);
					}
					object.put("questions", arr);
					FileWriter writer = new FileWriter(new File("questions"));
					writer.write(object.toString());
					writer.close();
					JOptionPane.showMessageDialog(null, "Kayit Basarili");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		getContentPane().add(btnKaydet);
		
	}

	public static void main(String[] args) {
			MainClass mainClass = new MainClass();
			mainClass.setVisible(true);
	}

	public class Soru {
	public int dogruCevap;
	public String[] cevaplar;
	public String soru;
	private Soru(int dogruCevap, String[] cevaplar, String soru) {
		super();
		this.dogruCevap = dogruCevap;
		this.cevaplar = cevaplar;
		this.soru = soru;
	}
	
}

}
