
public class Main {
	public static void main(String[] args) {
		FourArithmeticDesktopApp app = new FourArithmeticDesktopApp();
        app.addBtn.addActionListener(app.new ButtonListener("＋"));  // ＋ボタンにイベントリスナーを追加
        app.subtractBtn.addActionListener(app.new ButtonListener("ー"));  // －ボタンにイベントリスナーを追加
        app.multiplyBtn.addActionListener(app.new ButtonListener("×"));  // ×ボタンにイベントリスナーを追加
        app.divideBtn.addActionListener(app.new ButtonListener("÷"));  // ÷ボタンにイベントリスナーを追加
        app.showDisplay();  // アプリケーションを表示する
    }
}
