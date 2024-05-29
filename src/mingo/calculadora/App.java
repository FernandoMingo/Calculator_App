package mingo.calculadora;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JPanel root;
    private JPanel Header;
    private JPanel Body;
    private JButton btnReset;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a1Button;
    private JButton btnDecimalPoint;
    private JButton a8Button;
    private JButton a9Button;
    private JButton btnDivision;
    private JButton a5Button;
    private JButton a6Button;
    private JButton btnMultiplication;
    private JButton a2Button;
    private JButton a3Button;
    private JButton btnAddition;
    private JButton a0Button;
    private JButton btnEqual;
    private JButton btnSubtraction;
    private JLabel lblDisplay;

    public App() {
        //Initialization of the listener for the reset button
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDisplay.setText("");
            }
        });

        //common listener for the rest of JButtons
        ActionListener listenerNumbers = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JButton source = (JButton) e.getSource();
                lblDisplay.setText(lblDisplay.getText() + source.getText());
            }
        };
        //numeric button array
        JButton[] numericButtons = {a0Button, a1Button, a2Button, a3Button, a4Button,
                a5Button, a6Button,  a7Button, a8Button, a9Button, btnDecimalPoint};
        for (JButton numericButton : numericButtons) {
            numericButton.addActionListener(listenerNumbers);
        }

        //common listener for the expressions
        ActionListener listenerExpressions = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String result = calcular(lblDisplay.getText());
                lblDisplay.setText(result);
                if (e.getSource() != btnEqual) {
                    JButton source = (JButton) e.getSource();
                    lblDisplay.setText(lblDisplay.getText() + source.getText());
                }
            }
        };
        //expressions button array
        JButton[] expressionButtons = {btnAddition, btnSubtraction, btnMultiplication, btnDivision, btnEqual};
        for (JButton expressionButton : expressionButtons) {
            expressionButton.addActionListener(listenerExpressions);
        }
    }

    //function to calculate the expression
    public String calcular (String operation){
        String result;
        String[] ops = operation.split("[\\+\\-*/]");
        if (ops.length == 1){
            result = ops[0];
        } else {
            float v1 = Float.parseFloat(ops[0]);
            float v2 = Float.parseFloat(ops[1]);
            float r;
            if (operation.contains("+")){
                r = v1 + v2;
            } else if (operation.contains("-")){
                r = v1 + v2;
            } else if (operation.contains("*")){
                r = v1 * v2;
            } else{
                r = v1 / v2;
            }
            result = String.valueOf(r);
        }
        return result;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
