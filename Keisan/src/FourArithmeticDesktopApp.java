import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FourArithmeticDesktopApp {
    JFrame frame = new JFrame("四則計算アプリ");  // JFrameのインスタンス化
    JTextField text1 = new JTextField(10);  // 1つ目のテキストフィールド
    JTextField text2 = new JTextField(10);  // 2つ目のテキストフィールド
    JButton addBtn = new JButton("　＋　");  // ＋ボタン
    JButton subtractBtn = new JButton("　ー　");  // －ボタン
    JButton multiplyBtn = new JButton("　×　");  // ×ボタン
    JButton divideBtn = new JButton("　÷　");  // ÷ボタン
    JButton deleteBtn = new JButton("履歴削除"); // 履歴削除ボタン
    JLabel resultLabel = new JLabel();  // 計算結果表示用のラベル
    JTextArea historyText = new JTextArea();  // 履歴表示用のテキストフィールド
    JPanel contentPaneWrap = new JPanel(new GridLayout(2,1));// コンテンツパネル
    JPanel contentPane = new JPanel(new GridLayout(4, 1));  // コンテンツパネル
    
    // コンストラクタ
    public FourArithmeticDesktopApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ウィンドウを閉じる設定
        
        contentPaneWrap.add(contentPane);// コンテンツパネル

        contentPaneWrap.add(historyText);
        
        JPanel inPanel = new JPanel(new GridLayout(1, 4));  // 入力欄用のパネル
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));  // ボタン用のパネル
        JPanel outPanel = new JPanel(new GridLayout(1, 1));  // 出力欄用のパネル
        JPanel deletePanel = new JPanel(new GridLayout(1, 1)); // 履歴削除用のパネル
        
        contentPane.add(inPanel); // パネルを追加
        contentPane.add(buttonPanel);
        contentPane.add(outPanel);
        contentPane.add(deletePanel);
        
        inPanel.add(new JLabel("Num1:"));  // ラベルとテキストフィールドの追加
        inPanel.add(text1);
        inPanel.add(new JLabel("Num2:"));
        inPanel.add(text2);
        
        buttonPanel.add(addBtn);  // ボタンの追加とイベントリスナーの設定
        buttonPanel.add(subtractBtn);
        buttonPanel.add(multiplyBtn);
        buttonPanel.add(divideBtn);
        
        deletePanel.add(deleteBtn);// 履歴削除ボタン追加
        deleteBtn.addActionListener(new ButtonListenerDelete());
        
        outPanel.add(resultLabel, BorderLayout.SOUTH);  // 計算結果ラベルの追加
        
        frame.setContentPane(contentPaneWrap); // フレームをセット
        
        FileManager fm = new FileManager(); // ファイル操作
        String history[] = fm.readHistory(); // 履歴を読み込む
        String historyStr = "";
        
        // 配列の順番を逆にする
        for(Integer i = 0, l = history.length - 1; i < l; i ++, l --) {
        	String tmp = history[i];
        	history[i] = history[l];
        	history[l] = tmp;
        }
        
        // 履歴が入っている配列の要素を全て表示
        for(String str: history) {
        	historyStr = historyStr + str + "\n";
        }
        
        historyText.setText(historyStr);
    }
    
    // 画面表示設定
    public void showDisplay() {
        frame.setSize(400, 600);  // フレームのサイズ設定
        frame.setResizable(false);  // フレームのリサイズ不可
        frame.setVisible(true);  // フレームの表示
    }
    
    // ボタンが押されたときの処理
    class ButtonListener implements ActionListener {
        String operator;  // ボタンに対応する演算子を格納
        
        // コンストラクタ
        public ButtonListener(String operator) {
            this.operator = operator;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
        	// Text1とText2が両方とも数字の場合のみ計算する
        	if(text1.getText().length() != 0 && text2.getText().length() != 0) {
        		String input1 = text1.getText();
        		String input2 = text2.getText();
        		
        		Pattern pattern = Pattern.compile("[0-9]+");
        		Matcher matcher1 = pattern.matcher(input1);
        		Matcher matcher2= pattern.matcher(input2);
        		
        		if(matcher1.find() && matcher2.find()) {
        			int num1 = Integer.parseInt(text1.getText());  // テキストフィールドから数値を取得
        			int num2 = Integer.parseInt(text2.getText());
        			
        			String result = "";  // 計算結果を格納する変数
        			
        			// 演算子に応じて計算を実行
        			switch (operator) {
        			case "＋":
        				result = String.valueOf(num1 + num2);
        				break;
        			case "ー":
        				result = String.valueOf(num1 - num2);
        				break;
        			case "×":
        				result = String.valueOf(num1 * num2);
        				break;
        			case "÷":
        				result = String.valueOf(num1 / num2) + "あまり" + String.valueOf(num1 % num2);
        				break;
        			default:
        				break;
        			}
        			
        			// 計算結果の表示文字列を作成
        			String operatorSymbol = " " + operator + " ";
        			String equation = num1 + operatorSymbol + num2 + " ＝ " + result;
        			String equationDisp = "計算結果：" + num1 + operatorSymbol + num2 + " ＝ " + result;
        			resultLabel.setText(equationDisp);  // 計算結果を表示する
        			
        			FileManager fm = new FileManager(); // ファイル操作
        			fm.saveHistory(equation); // 履歴に計算結果を保存する
        			String history[] = fm.readHistory(); // 履歴を読み込む
        			String historyStr = "";
        			
        			// 配列の順番を逆にする
        			for(Integer i = 0, l = history.length - 1; i < l; i ++, l --) {
        				String tmp = history[i];
        				history[i] = history[l];
        				history[l] = tmp;
        			}
        			
        			// 配列の要素をすべて表示
        			for(String str: history) {
        				historyStr = historyStr + str + "\n";
        			}
        			
        			historyText.setText(historyStr);
        			//System.out.println(Arrays.toString(history));
        			
        			text1.setText(""); // 入力欄を空欄にする
        			text2.setText("");
        		}
        	}
        }
    }
    
    // 履歴を削除する
    class ButtonListenerDelete implements ActionListener{
    	@Override
        public void actionPerformed(ActionEvent e) {
    		FileManager fm = new FileManager(); // ファイル操作
        	fm.deleteHistory(); // 履歴を削除
        	
        	resultLabel.setText(""); // 計算結果表示をなくす
        	historyText.setText(""); // 履歴表示欄を空白にする
    	}
    }
}
