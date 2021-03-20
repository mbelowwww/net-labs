package lab4;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JFileChooser extends JFrame {
  private static final long serialVersionUID = 1L;

  private JButton btnFileFilter;
  private javax.swing.JFileChooser fileChooser;
  private JLabel label;

  public JFileChooser() {
    super("Отправка файла на сервер");

    UIManager.put("FileChooser.openButtonText", "Отправить");
    UIManager.put("FileChooser.cancelButtonText", "Отмена");
    UIManager.put("FileChooser.fileNameLabelText", "Наименование файла");
    UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
    UIManager.put("FileChooser.lookInLabelText", "Директория");
    UIManager.put("FileChooser.saveInLabelText", "Сохранить в директории");
    UIManager.put("FileChooser.folderNameLabelText", "Путь директории");

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    btnFileFilter = new JButton("Выберите файл");
    fileChooser = new javax.swing.JFileChooser();

    label = new JLabel();
    label.setVisible(false);

    addFileChooserListeners();

    JPanel contents = new JPanel();
    contents.setLayout(new BorderLayout());
    contents.add(btnFileFilter, BorderLayout.SOUTH);
    contents.add(label, BorderLayout.CENTER);
    setContentPane(contents);

    setSize(400, 100);
    setVisible(true);
  }

  private void addFileChooserListeners() {
    btnFileFilter.addActionListener(e -> {
      fileChooser.setDialogTitle("Выберите файл");
      fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
      int result = fileChooser.showOpenDialog(JFileChooser.this);
      // Если файл выбран, покажем его в сообщении
      if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
        JOptionPane.showMessageDialog(JFileChooser.this,
            "Выбран файл ( " +
                fileChooser.getSelectedFile() + " ),  отправка...");
        File file = fileChooser.getSelectedFile();
        label.setVisible(true);
        label.setText("Отправка...");
      }
    });
  }
}
