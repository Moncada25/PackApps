package com.bookverse.development.packapps.views;

import static com.bookverse.development.packapps.core.Settings.MEDIUM;
import static com.bookverse.development.packapps.utils.DatabaseConstants.QUESTIONS_AND_ANSWERS;
import static java.awt.Font.BOLD;

import com.bookverse.development.packapps.core.Resources;
import com.bookverse.development.packapps.core.Settings;
import com.bookverse.development.packapps.utils.Alerts;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuestionsAndAnswers extends JDialog implements ActionListener {

  private JLabel title, image;
  private JButton start, records, exit, settings;
  private Resources resources = new Resources();
  private JComboBox<String> categoryList;
  private JTextField player;
  private List<JButton> responses = new ArrayList<>();
  private JLabel lblQuestion, lblAccumulate, lblLevel;
//  private static Round round = getDefaultRound();
//  private List<Category> categories;
//  private static Question currentQuestion;

  public QuestionsAndAnswers(JFrame parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public QuestionsAndAnswers(JDialog parent, boolean modal) {
    super(parent, modal);
    createComponents();
  }

  public void start(JFrame parent) {
    setSize(750, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(QUESTIONS_AND_ANSWERS + " (beta)");
    Settings.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesPreguntas();
//pack();
    setVisible(true);
  }

  public void start(JDialog parent) {
    setSize(750, 500);
    setResizable(false);
    setLocationRelativeTo(parent);
    setTitle(QUESTIONS_AND_ANSWERS + " (beta)");
    Settings.fadeIn(this);
    parent.setVisible(false);
    Alerts.instruccionesPreguntas();

    setVisible(true);
  }

  private void createComponents() {

    setLayout(null);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    //categories = getCategories("SELECT * FROM category");

    JLabel title = resources.getLabel("Category", Color.RED, this,
        new Font("Times New Roman", BOLD, 20));
    title.setBounds(30, 10, 86, 30);

    start = resources.getButton("Start", getContentPane().getBackground(), this, this);
    start.setBounds(30, 160, 86, 30);

    exit = resources.getButton("Close", getContentPane().getBackground(), this, this);
    exit.setBounds(140, 160, 86, 30);

    categoryList = new JComboBox<>();
    categoryList.setBounds(30, 50, 220, 30);
    categoryList.setFont(MEDIUM);
    add(categoryList);

    DefaultComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel();
    defaultComboBoxModel.addElement("Select a option");

//    categories.forEach(category -> {
//      categoryList.addItem(category.getName());
//      defaultComboBoxModel.addElement(category.getName());
//    });

    categoryList.setModel(defaultComboBoxModel);

    player = new JTextField();
    player.setBounds(30, 100, 220, 40);
    add(player);

    lblQuestion = resources.getLabel("", Color.BLACK, this, new Font("Times New Roman", BOLD, 20));
    lblQuestion.setBounds(320, 30, 400, 60);
    lblQuestion.setVisible(false);

    lblAccumulate = resources.getLabel("", Color.BLACK, this,
        new Font("Times New Roman", BOLD, 20));
    lblAccumulate.setBounds(30, 300, 200, 30);
    lblAccumulate.setVisible(false);

    lblLevel = resources.getLabel("", Color.BLACK, this, new Font("Times New Roman", BOLD, 20));
    lblLevel.setBounds(30, 350, 200, 30);
    lblLevel.setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(0).setBounds(320, 100, 400, 30);
    responses.get(0).setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(1).setBounds(320, 180, 400, 30);
    responses.get(1).setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(2).setBounds(320, 260, 400, 30);
    responses.get(2).setVisible(false);

    responses.add(resources.getButton("", new Color(255, 255, 255), this, this));
    responses.get(3).setBounds(320, 340, 400, 30);
    responses.get(3).setVisible(false);

    add(responses.get(0));
    add(responses.get(1));
    add(responses.get(2));
    add(responses.get(3));

    image = resources.getLabel("", getContentPane().getBackground(), this, null);
    image.setBounds(150, 120, 200, 200);
    image.setIcon(new ImageIcon(resources.getImage("question.png")));
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == exit){
      dispose();
    }
  }


}
