import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RomanCalculator extends JFrame {

    private JPanel tfPanel = null;
    private JPanel buttonPanels = null;
    private JButton [] romanButtons = new JButton[14];
    private String [] buttonSymbols = {"I", "V","X","L","C","D","M",
                                       "CE","-","+","/","%","*","="};
    private JTextField [] textFields = new JTextField[6];
    ButtonListener bl = null;
    private Roman romanObj,romanObj2 = null;
    private String btnLabelToEquation = null;
    private int doOperation ;




    public RomanCalculator(){

        super("Giantte's Roman Calculator");
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2,0));

        // Turns the button clicks into string equation
        btnLabelToEquation= "";

        // Created Roman Objects
        romanObj = new Roman();
        romanObj2 = new Roman();

        // Initializes the two main panels
        tfPanel = new JPanel();
        tfPanel.setBackground(Color.BLACK);
        buttonPanels = new JPanel();
        buttonPanels.setBackground(Color.BLACK);

        // Adds the panels to the frame
        this.add(tfPanel);
        this.add(buttonPanels);

        // Creates the Object with the implemented methods
        bl = new ButtonListener();


        // Initialize Buttons and add the the button panel
        for (int i = 0;i<romanButtons.length;i++){
            romanButtons[i] = new JButton(buttonSymbols[i]);
            buttonPanels.add(romanButtons[i]);
            romanButtons[i].addActionListener(bl);
        }

        // Layout the button panel
        buttonPanels.setLayout(new GridLayout(4,4));

        // Initialize TextFields and add to the textfield panel
        for (int i = 0; i<textFields.length; i++){

            textFields[i] = new JTextField(10);
            textFields[i].setEditable(false);
            textFields[i].setFont(new Font("Times New Roman", Font.BOLD, 24));
            textFields[i].setHorizontalAlignment(JTextField.CENTER);
            tfPanel.add(textFields[i]);

        }

        tfPanel.setLayout(new GridLayout(3,2));


    }

    /************************ whichOperation()******************************
     *  Simply Chooses Which Operation to do
     *
     *  returns void
     */
    public void whichOperation(){

        if (btnLabelToEquation.contains("+") && btnLabelToEquation.contains("="))
            calculate("+","=");
        else if (btnLabelToEquation.contains("-") && btnLabelToEquation.contains("="))
            calculate("-","=");
        else if (btnLabelToEquation.contains("*") && btnLabelToEquation.contains("="))
            calculate("*","=");
        else if (btnLabelToEquation.contains("/") && btnLabelToEquation.contains("="))
            calculate("/","=");
        else
            calculate("%","=");

    }

    /************************* calculate() *****************************
     * Calculates all of the operations on the calculator and display
     * the roman/integer values on the calculator
     *
     * @param operation
     * @param equalSign
     *
     * returns voud
     */


    public void calculate(String operation, String equalSign){
        // Declarations
        doOperation = 0;
        String firstNums = "";
        String lastNums = "";
        // Finds index of the operation and equal sign
        int storeOperation = btnLabelToEquation.indexOf(operation);
        int storeEqual = btnLabelToEquation.indexOf(equalSign);

        // Loops through to find the first roman numerals and last roman numerals
            for (int i = 0; i < storeOperation;i++)
                firstNums += btnLabelToEquation.substring(i, i + 1);
            for(int i = storeOperation+1; i< storeEqual;i++)
                lastNums += btnLabelToEquation.substring(i, i + 1);



            // Store those numbers in our roman objects
            romanObj.setRoman(firstNums);
            romanObj2.setRoman(lastNums);

            // Display Roman Numeral Values
            textFields[0].setText(romanObj.getRoman());
            textFields[2].setText(romanObj2.getRoman());
            // Display Integer value
            textFields[1].setText(romanObj.convert_Roman_To_Int(romanObj.getRoman()) + ""); //
            textFields[3].setText(romanObj2.convert_Roman_To_Int(romanObj2.getRoman()) + "");




        // Calculate the roman numerals
        switch (operation){
            case "+":
                    doOperation = romanObj.convert_Roman_To_Int(romanObj.getRoman()) +
                                        romanObj2.convert_Roman_To_Int(romanObj2.getRoman());

                    textFields[4].setText(romanObj.convert_Int_To_Roman(doOperation)); // Result Roman TF
                    textFields[5].setText(doOperation + ""); // Result Integer Result
                    break;
            case "-":
                    doOperation = romanObj.convert_Roman_To_Int(romanObj.getRoman()) -
                            romanObj2.convert_Roman_To_Int(romanObj2.getRoman());

                    textFields[4].setText(romanObj.convert_Int_To_Roman(doOperation));
                    textFields[5].setText(doOperation + ""); // Result Integer Result
                    break;
            case "*":
                    doOperation = romanObj.convert_Roman_To_Int(romanObj.getRoman()) *
                            romanObj2.convert_Roman_To_Int(romanObj2.getRoman());

                    textFields[4].setText(romanObj.convert_Int_To_Roman(doOperation));
                    textFields[5].setText(doOperation + ""); // Result Integer Result
                    break;
            case "/":
                    doOperation = romanObj.convert_Roman_To_Int(romanObj.getRoman()) /
                            romanObj2.convert_Roman_To_Int(romanObj2.getRoman());

                    textFields[4].setText(romanObj.convert_Int_To_Roman(doOperation));
                    textFields[5].setText(doOperation + ""); // Result Integer Result
                    break;
            case "%":
                    doOperation = romanObj.convert_Roman_To_Int(romanObj.getRoman()) %
                            romanObj2.convert_Roman_To_Int(romanObj2.getRoman());

                    textFields[4].setText(romanObj.convert_Int_To_Roman(doOperation));
                    textFields[5].setText(doOperation + ""); // Result Integer Result
                    break;
        }

    }

    /************************* startCalculator() ******************************
     * Starts the entire calculator after checking the syntax errors and equal sign
     *
     * returns void
     */

    public void startCalculator(){
        // Starts everything after =
        int start = btnLabelToEquation.length() - 1;
        int end = btnLabelToEquation.length();
        if (btnLabelToEquation.substring(start,end).equalsIgnoreCase("=")){

            // Checks if they are duplicate operations
            try{

                if (btnLabelToEquation.contains("++") || btnLabelToEquation.contains("--") || btnLabelToEquation.contains("**")
                        || btnLabelToEquation.contains("//") || btnLabelToEquation.contains("=="))
                    throw new RomanExceptions();

                whichOperation();
                btnLabelToEquation = romanObj.convert_Int_To_Roman(doOperation) ;

            }
            catch (RomanExceptions ex){

                for (int i = 0; i<textFields.length;i++)
                    textFields[i].setText(ex.getMessage());
            }

        }
    }


    /******************* possibleErrors() ********************
     * Checks for possible errors such as syntax errors for the calculator
     *
     * returns void
     */
    public void possibleErrors(){

        // Doesn't allow weird syntax with operation starting first before numbers
        try{

            switch (btnLabelToEquation.substring(0,1)){
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "=":
                    throw new RomanExceptions();
            }

            // Doesn't allow equal before operation
            if (btnLabelToEquation.length()<=2 && btnLabelToEquation.length()>1){
                if (btnLabelToEquation.substring(1,2).equalsIgnoreCase("="))
                    throw new RomanExceptions();
            }


        }
        catch (RomanExceptions ex){
            for (int i = 0; i<textFields.length;i++)
                textFields[i].setText(ex.getMessage());
        }

    }



    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String buttonLabel = e.getActionCommand(); // Stores button labels
            btnLabelToEquation += buttonLabel; // Add those labels to change into string equations
            textFields[4].setText(btnLabelToEquation); // Displays the equation in the result section

            // Checks for errors
            possibleErrors();

            // Clears the entire calculator
            if (buttonLabel.equalsIgnoreCase("CE")){
                btnLabelToEquation = "";
                for (int i = 0; i<textFields.length;i++)
                    textFields[i].setText(btnLabelToEquation);
            }

            // Only start the calculator if the equation is complete
            if (btnLabelToEquation.length() > 2)
                startCalculator();


        }



    }



}
