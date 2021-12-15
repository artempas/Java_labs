package lab6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

enum Operation {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
}

public class Lab6 extends JFrame {

    private JFrame sqrtFrame = new JFrame ( );
    private JPanel sqrtPanel = new JPanel ( new GridLayout ( ) );
    private JButton sqrtBtn = new JButton ( );

    private float memory = 0;

    private Operation currentOperation;
    private float lastInput = 0;
    private JPanel panel1;
    private JTextField currentResult;
    private JTextArea operation;
    private JTextField inputField;
    private JPanel Buttons;
    private JPanel rightOperations;
    private JPanel topOperations;

    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JButton button5;
    private JButton button8;
    private JButton button0;
    private JButton button4;
    private JButton button6;
    private JButton button7;
    private JButton button9;
    private JButton deleteBtn;
    private JButton dotBtn;
    private JButton clear;
    private JButton saveMemoryButton;
    private JButton writeMemoryButton;
    private JButton multiply;
    private JButton equalsBtn;
    private JButton addBtn;
    private JButton subtractBtn;
    private JButton divideBtn;
    private JPanel topPanel;


    private void createUIComponents ( ) {
        // TODO: place custom component creation code here
        topPanel = new JPanel ( );
        topPanel.setOpaque ( false );

        button1 = new CalcButton ( "0" );
        button3 = new CalcButton ( "0" );
        button2 = new CalcButton ( "0" );
        button5 = new CalcButton ( "0" );
        button8 = new CalcButton ( "0" );
        button0 = new CalcButton ( "0" );
        button4 = new CalcButton ( "0" );
        button6 = new CalcButton ( "0" );
        button7 = new CalcButton ( "0" );
        button9 = new CalcButton ( "0" );
        deleteBtn = new CalcButton ( "0" );
        dotBtn = new CalcButton ( "0" );
        clear = new CalcButton ( "0" );
        saveMemoryButton = new CalcButton ( "0" );
        writeMemoryButton = new CalcButton ( "0" );
        multiply = new CalcButton ( "0" );
        equalsBtn = new CalcButton ( "0" );
        addBtn = new CalcButton ( "0" );
        subtractBtn = new CalcButton ( "0" );
        divideBtn = new CalcButton ( "0" );


        currentResult = new JTextField ( );

        inputField = new JTextField ( );
        currentResult.setBorder ( new EmptyBorder ( 10 , 0 , 0 , 15 ) );
//        operation.setBorder ( new EmptyBorder ( 0,10,5,0));
        inputField.setBorder ( new EmptyBorder ( 0 , 0 , 0 , 15 ) );
        currentResult.setOpaque ( false );
//        operation.setOpaque ( false );
        inputField.setOpaque ( false );
        currentResult.setHorizontalAlignment ( SwingConstants.RIGHT );
//        operation.setHorizontalAlignment ( SwingConstants.LEFT);
        inputField.setHorizontalAlignment ( SwingConstants.RIGHT );
        currentResult.setPreferredSize ( new Dimension ( topPanel.getWidth ( ) , 50 ) );
//        operation.setPreferredSize ( new Dimension ( 1, 80 ) );
//        operation.setMaximumSize ( new Dimension ( 1,80 ) );
        inputField.setPreferredSize ( new Dimension ( topPanel.getWidth ( ) , 80 ) );


        panel1 = new GradientPanel ( );
        Buttons = new RoundedGradientPanel ( Color.decode ( "#1C1087" ).brighter ( ) , Color.decode ( "#230c5e" ) , 50 , true );
        rightOperations = new RoundedGradientPanel ( Color.decode ( "#FED717" ) , Color.decode ( "#FF1C55" ) , 50 );
        topOperations = new RoundedGradientPanel ( Color.decode ( "#1C1087" ).brighter ( ) , Color.decode ( "#1d0a4c" ) , 50 , ' ' );
        Buttons.setBorder ( new EmptyBorder ( 10 , 0 , 10 , 10 ) );

    }

    private float calculate ( ) throws NumberFormatException, ArithmeticException {
        float first, second;
        try {
            first = Float.parseFloat ( currentResult.getText ( ) );
            second = Float.parseFloat ( inputField.getText ( ) );
        } catch (NumberFormatException exc) {
            if ( currentOperation == Operation.SUBTRACT ) {
                try {
                    second = Float.parseFloat ( inputField.getText ( ) );
                    return -second;
                } catch (NumberFormatException exc1) {
                    JOptionPane.showMessageDialog ( this , exc.getMessage ( ) );
                    throw exc1;
                }
            }
            JOptionPane.showMessageDialog ( this , exc.getMessage ( ) );
            throw exc;
        }
        switch (currentOperation) {
            case ADD -> {
                return first + second;
            }
            case SUBTRACT -> {
                return first - second;
            }
            case MULTIPLY -> {
                return first * second;
            }
            case DIVIDE -> {
                if ( second == 0 ) {
                    JOptionPane.showMessageDialog ( this , "Ошибка, деление на 0!" );
                    throw new ArithmeticException ( "division by zero" );
                }
                return first / second;
            }

        }
        return 0;
    }


    public Lab6 ( String title ) {
        super ( title );
        this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        this.setContentPane ( panel1 );
        //panel1.setBackground ( null );
        this.pack ( );
        currentResult.setText ( null );
        sqrtFrame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        sqrtFrame.setResizable ( false );
        sqrtBtn.setPreferredSize ( new Dimension ( 200 , 50 ) );
        sqrtBtn.setText ( "√" );
        sqrtFrame.add ( sqrtPanel );
        sqrtPanel.add ( sqrtBtn );
        sqrtFrame.setVisible ( true );
        sqrtFrame.setLocation ( 600 , 600 );
        sqrtFrame.pack ( );

        button1.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "1" : (inputField.getText ( ) + 1) );
            }
        } );
        button2.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "2" : (inputField.getText ( ) + 2) );
            }
        } );
        button3.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "3" : (inputField.getText ( ) + 3) );
            }
        } );
        button4.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "4" : (inputField.getText ( ) + 4) );
            }
        } );
        button5.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "5" : (inputField.getText ( ) + 5) );
            }
        } );
        button6.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "6" : (inputField.getText ( ) + 6) );
            }
        } );
        button7.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "7" : (inputField.getText ( ) + 7) );
            }
        } );
        button8.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "8" : (inputField.getText ( ) + 8) );
            }
        } );
        button9.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "9" : (inputField.getText ( ) + 9) );
            }
        } );
        button0.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( (Objects.equals ( inputField.getText ( ) , "0" )) ? "0" : (inputField.getText ( ) + 0) );
            }
        } );
        clear.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                currentResult.setText ( null );
                inputField.setText ( "0" );
                operation.setText ( null );
            }
        } );
        multiply.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( Objects.equals ( inputField.getText ( ) , "0" ) ) {
                    currentOperation = Operation.MULTIPLY;
                    operation.setText ( "×" );
                } else {
                    if ( currentResult.getText ( ).length ( ) == 0 ) {
                        currentResult.setText ( inputField.getText ( ) );
                        currentOperation = Operation.MULTIPLY;
                        inputField.setText ( "0" );
                        operation.setText ( "×" );
                        try {
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException ignored) {
                        }
                    } else {
                        try {
                            float result = calculate ( );
                            currentResult.setText ( Float.toString ( result ) );
                            inputField.setText ( "0" );
                            currentOperation = Operation.MULTIPLY;
                            operation.setText ( "×" );
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException | ArithmeticException exc) {
                            System.out.println ( exc.getMessage ( ) );
                        }
                    }
                }
            }
        } );
        divideBtn.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( Objects.equals ( inputField.getText ( ) , "0" ) ) {
                    currentOperation = Operation.DIVIDE;
                    operation.setText ( "÷" );
                } else {
                    if ( currentResult.getText ( ).length ( ) == 0 ) {
                        currentResult.setText ( inputField.getText ( ) );
                        currentOperation = Operation.DIVIDE;
                        inputField.setText ( "0" );
                        operation.setText ( "÷" );
                        try {
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException ignored) {
                        }
                    } else {
                        try {
                            float result = calculate ( );
                            currentResult.setText ( Float.toString ( result ) );
                            inputField.setText ( "0" );
                            currentOperation = Operation.DIVIDE;
                            operation.setText ( "÷" );
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException | ArithmeticException exc) {
                            System.out.println ( exc.getMessage ( ) );
                        }
                    }
                }
            }
        } );
        subtractBtn.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( Objects.equals ( inputField.getText ( ) , "0" ) ) {
                    currentOperation = Operation.SUBTRACT;
                    operation.setText ( "–" );
                } else {
                    if ( currentResult.getText ( ).length ( ) == 0 ) {
                        currentResult.setText ( inputField.getText ( ) );
                        currentOperation = Operation.SUBTRACT;
                        inputField.setText ( "0" );
                        operation.setText ( "–" );
                        try {
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException ignored) {
                        }
                    } else {
                        try {
                            float result = calculate ( );
                            currentResult.setText ( Float.toString ( result ) );
                            inputField.setText ( "0" );
                            currentOperation = Operation.SUBTRACT;
                            operation.setText ( "–" );
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException | ArithmeticException exc) {
                            System.out.println ( exc.getMessage ( ) );
                        }
                    }
                }
            }
        } );
        addBtn.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( Objects.equals ( inputField.getText ( ) , "0" ) ) {
                    currentOperation = Operation.ADD;
                    operation.setText ( "+" );
                } else {
                    if ( currentResult.getText ( ).length ( ) == 0 ) {
                        currentResult.setText ( inputField.getText ( ) );
                        currentOperation = Operation.ADD;
                        inputField.setText ( "0" );
                        operation.setText ( "+" );
                        try {
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException ignored) {
                        }
                    } else {
                        try {
                            float result = calculate ( );
                            currentResult.setText ( Float.toString ( result ) );
                            inputField.setText ( "0" );
                            currentOperation = Operation.ADD;
                            operation.setText ( "+" );
                            lastInput = Float.parseFloat ( inputField.getText ( ) );
                        } catch (NumberFormatException | ArithmeticException exc) {
                            System.out.println ( exc.getMessage ( ) );
                        }
                    }

                }
            }
        } );
        equalsBtn.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( Objects.equals ( inputField.getText ( ) , "0" ) ) {
                    inputField.setText ( Float.toString ( lastInput ) );
                    float res = calculate ( );
                    inputField.setText ( "0" );
                    currentResult.setText ( Float.toString ( res ) );
                } else {
                    try {
                        lastInput = Float.parseFloat ( inputField.getText ( ) );
                        float res = calculate ( );
                        inputField.setText ( "0" );
                        currentResult.setText ( Float.toString ( res ) );
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        } );
        sqrtBtn.addActionListener ( new ActionListener ( ) {
            public void actionPerformed ( ActionEvent e ) {
                if ( Float.parseFloat ( inputField.getText ( ) ) <= 0 ) {
                    JOptionPane.showMessageDialog ( sqrtFrame , "Ошибка, извлечение корня из числа >=0" );
                    throw new ArithmeticException ( "root from negative number" );
                }

                inputField.setText ( Float.toString ( (float) Math.sqrt ( Float.parseFloat ( inputField.getText ( ) ) ) ) );
            }
        } );


        dotBtn.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( !inputField.getText ( ).contains ( "." ) ) {
                    inputField.setText ( inputField.getText ( ) + "." );
                }
            }
        } );
        deleteBtn.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                if ( !Objects.equals ( inputField.getText ( ) , "0" ) )
                    if ( inputField.getText ( ).length ( ) == 1 )
                        inputField.setText ( "0" );
                    else
                        inputField.setText ( inputField.getText ( ).substring ( 0 , inputField.getText ( ).length ( ) - 1 ) );
            }
        } );

        saveMemoryButton.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                try {
                    memory = Float.parseFloat ( currentResult.getText ( ) );
                    JOptionPane.showMessageDialog ( sqrtFrame , String.format ( "%f stored in memory" , memory ) );
                }
                catch (NumberFormatException exc){
                    memory = Float.parseFloat ( inputField.getText ( ) );
                    JOptionPane.showMessageDialog ( sqrtFrame , String.format ( "%f stored in memory" , memory ) );
                }
            }
        } );
        writeMemoryButton.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                inputField.setText ( Float.toString ( memory ) );
            }
        } );
    }


    public static void main ( String[] args ) {

        JFrame frame = new Lab6 ( "Calculator" );
        frame.setVisible ( true );
        frame.setResizable ( true );
        frame.setMinimumSize ( frame.getSize ( ) );


    }

}
