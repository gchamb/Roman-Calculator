
public class Roman {

    private String roman;
    private int [] romanValues = {1000,500,100,50,10,5,1};
    private String [] romanLabels ={"M","D","C","L","X","V","I"};


    public Roman(){
        roman = null;
    }



    public void setRoman(String r){
        this.roman = r;
    }
    public String getRoman(){
        return roman;
    }
    public int convert_Roman_To_Int(String r){
        int sum = 0 ;

        for (int i = 0; i<r.length();i++){
            String separateRoman  = r.substring(i,i+1); // Iterates through each roman in the string

            // Sums it all up
            switch (separateRoman){

                case "I": sum+=1;
                          break;
                case "V": sum+=5;
                          break;
                case "X": sum+=10;
                          break;
                case "L": sum+=50;
                          break;
                case "C": sum+=100;
                          break;
                case "D": sum+=500;
                          break;
                case "M": sum+=1000;
                          break;
                default: sum+=0;
                         System.out.println("Tried to add non-roman numeral");


            }
        }

        return sum;


    }
    public String convert_Int_To_Roman(int r){

        String conversion = "";
        int amountOfRomans = 0  ;


        for (int i  = 0 ; i<romanLabels.length; i++){

            if (r / romanValues[i] > 0){

                if (r == 0)
                    break;

                if (conversion.isEmpty()){

                    amountOfRomans = r/romanValues[i];
                    r = r % romanValues[i];

                    for (int j = 0; j <amountOfRomans;j++){

                        conversion += romanLabels[i];
                    }

                }
                else {

                    amountOfRomans = r / romanValues[i];
                    r = r % romanValues[i];
                    for (int j = 0; j<amountOfRomans;j++)
                        conversion += romanLabels[i];


                    }

                }




            }




        return conversion;

    }
    public String display_Roman(){
        return "Roman: " + roman;
    }





}
