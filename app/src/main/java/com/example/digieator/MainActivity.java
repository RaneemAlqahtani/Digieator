package com.example.digieator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.digieator.database.DatabaseHelper;


public class MainActivity extends AppCompatActivity {

    TextView input, signBox, tagBox;
    String sign, value1, value2, specialFunction;
    boolean hasSpecFunc;
    public BottomNavigationView buttom_nav_view;
    SharedPreferences sp;


    private DatabaseHelper db;


    Button btn_zero;
    Button btn_one;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;
    Button btn_and;
    Button btn_or;
    Button btn_nand;
    Button btn_nor;
    Button btn_xor;
    Button btn_not;
    Button btn_a;
    Button btn_b;
    Button btn_c;
    Button btn_d;
    Button btn_e;
    Button btn_f;
    Button one_complement;
    Button two_omplement;
    Button right_shift;
    Button left_shift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sp = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
        Boolean state = sp.getBoolean("switchKey", false);
        String themeValue = sp.getString("Theme", "Default");
        try {
            switch (themeValue) {
                case "Dark":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case "Light":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error changing theme", Toast.LENGTH_SHORT).show();
        }




        db = new DatabaseHelper(this);

        input = findViewById(R.id.input);
        signBox = findViewById(R.id.sign);
        tagBox = findViewById(R.id.tag);
        btn_zero = findViewById(R.id.btn_zero);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_seven = findViewById(R.id.btn_seven);
        btn_eight = findViewById(R.id.btn_eight);
        btn_nine = findViewById(R.id.btn_nine);
        btn_and = findViewById(R.id.btn_and);
        btn_or = findViewById(R.id.btn_or);
        btn_nand = findViewById(R.id.btn_nand);
        btn_nor = findViewById(R.id.btn_nor);
        btn_xor = findViewById(R.id.btn_xor);
        btn_not = findViewById(R.id.btn_not);
        btn_a = findViewById(R.id.btn_a);
        btn_b = findViewById(R.id.btn_b);
        btn_c = findViewById(R.id.btn_c);
        btn_d = findViewById(R.id.btn_d);
        btn_e = findViewById(R.id.btn_e);
        btn_f = findViewById(R.id.btn_f);
        one_complement = findViewById(R.id.one_complemnt);
        two_omplement = findViewById(R.id.two_complemnt);
        right_shift = findViewById(R.id.btn_left_shift);
        left_shift = findViewById(R.id.btn_right_shift);

        tagBox.setText("D");
        hasSpecFunc = false;

        BinaryMode(false);
        HexMode(false);
        NormalMode(true);

        initButtomNavigationMenu();
    }


    private char flip(char c) {
        return (c == '0') ? '1' : '0';
    }


    // Print 1's and 2's complement of binary number
    // represented by "bin"
    private String OneAndTwosComplement(String bin, Boolean onetwo) {
        int n = bin.length();
        int i;

        String ones = "", twos = "";
        ones = twos = "";

        // for ones complement flip every bit
        for (i = 0; i < n; i++) {
            ones += flip(bin.charAt(i));  //
        }

        // for two's complement go from right to left in
        // ones complement and if we get 1 make, we make
        // them 0 and keep going left when we get first
        // 0, make that 1 and go out of loop
        twos = ones;
        for (i = n - 1; i >= 0; i--) {
            if (ones.charAt(i) == '1') {
                twos = twos.substring(0, i) + '0' + twos.substring(i + 1);
            } else {
                twos = twos.substring(0, i) + '1' + twos.substring(i + 1);
                break;
            }
        }


        if (i == -1) {
            twos = '1' + twos;
        }


        if (onetwo)
            return ones;
        else
            return twos;


    }


    private void initButtomNavigationMenu() {
        buttom_nav_view = (BottomNavigationView) findViewById(R.id.buttom_nav_view);
        buttom_nav_view.setItemIconTintList(null);
        buttom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.btm_nav_mem:

                        Intent myIntent = new Intent(com.example.digieator.MainActivity.this,
                                HistoryActivity.class);
                        startActivity(myIntent);
                        break;
                    case R.id.btm_nav_calculator:

                        break;

                    case R.id.btm_nav_colors:

                        Intent myIntent1 = new Intent(com.example.digieator.MainActivity.this,
                                ThemeActivity.class);
                        startActivity(myIntent1);
                        break;
                    case R.id.btm_nav_ins:

                        Intent myIntent2 = new Intent(com.example.digieator.MainActivity.this,
                                InstructionsActivity.class);
                        startActivity(myIntent2);
                        break;
                    case R.id.btm_nav_ref:
                        Intent myIntent3 = new Intent(com.example.digieator.MainActivity.this, ReferencesActivity.class);
                        startActivity(myIntent3);
                        break;

                }
                return true;
            }
        });

    }


    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String note) {

    db.insertAnswer(note);


    }


    @SuppressLint("SetTextI18n")
    public void btnClick_0(View view) {

        String currentTag = tagBox.getText().toString();

        switch (currentTag) {
            case "B":
                input.setText(input.getText() + "0");
                break;
            default:
                input.setText("0");
                break;
        }

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_1(View view) {


        String currentTag = tagBox.getText().toString();

        switch (currentTag)
        {
            case "B":
                input.setText(input.getText() + "1");
                break;
            default:
                input.setText("1");
                break;
        }


    }





    @SuppressLint("SetTextI18n")
    public void btnClick_2(View view) {

        if (input.getText().equals("0"))
            input.setText("2");
        else
            input.setText(input.getText() + "2");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_3(View view) {
        String currentTag = tagBox.getText().toString();


        if (input.getText().equals("0"))
            input.setText("3");
        else
            input.setText(input.getText() + "3");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_4(View view) {


        if (input.getText().equals("0"))
            input.setText("4");
        else
            input.setText(input.getText() + "4");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_5(View view) {
        if (input.getText().equals("0"))
            input.setText("5");
        else
            input.setText(input.getText() + "5");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_6(View view) {
        if (input.getText().equals("0"))
            input.setText("6");
        else
            input.setText(input.getText() + "6");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_7(View view) {
        if (input.getText().equals("0"))
            input.setText("7");
        else
            input.setText(input.getText() + "7");
    }

    @SuppressLint("SetTextI18n")
    public void btnClick_8(View view) {
        if (input.getText().equals("0"))
            input.setText("8");
        else
            input.setText(input.getText() + "8");

    }

    @SuppressLint("SetTextI18n")
    public void btnClick_9(View view) {
        if (input.getText().equals("0"))
            input.setText("9");
        else
            input.setText(input.getText() + "9");
    }

    public void btnClick_b(View view) {
        if (input.getText().equals("0"))
            input.setText("B");
        else
            input.setText(input.getText() + "B");
    }

    public void btnClick_a(View view) {
        if (input.getText().equals("0"))
            input.setText("A");
        else
            input.setText(input.getText() + "A");
    }

    public void btnClick_c(View view) {
        if (input.getText().equals("0"))
            input.setText("C");
        else
            input.setText(input.getText() + "C");
    }

    public void btnClick_d(View view) {
        if (input.getText().equals("0"))
            input.setText("D");
        else
            input.setText(input.getText() + "D");

    }


    public void btnClick_f(View view) {
        if (input.getText().equals("0"))
            input.setText("F");
        else
            input.setText(input.getText() + "F");

    }

    public void btnClick_e(View view) {
        if (input.getText().equals("0"))
            input.setText("E");
        else
            input.setText(input.getText() + "E");

    }


    public void btnClick_backspace(View view) {
        if (input.getText().equals("")) {
            input.setText(null);
        } else {
            int len = input.getText().length();
            String s = input.getText().toString();
            input.setText(input.getText().subSequence(0, input.getText().length() - 1));
        }
    }

    public void btnClick_Clear(View view) {
        input.setText("0");
        signBox.setText(null);
        tagBox.setText("D");
        BinaryMode(false);
        HexMode(false);
        value1 = null;
        value2 = null;
        sign = null;

    }


    //logical gates
    public void btnClick_nand(View view) {
        value1 = input.getText().toString();
        hasSpecFunc = false;
        input.setText(null);
        signBox.setText("nand");
        sign = "nand";

    }


    public void btnClick_xor(View view) {
        value1 = input.getText().toString();
        input.setText(null);
        hasSpecFunc = false;
        signBox.setText("xor");
        sign = "xor";

    }

    public void btnClick_not(View view) {
        specialFunction = "not";
        hasSpecFunc = true;
        value1 = input.getText().toString();
        input.setText(null);
        signBox.setText("not");
        sign = "not";

    }

    public void btnClick_nor(View view) {
        hasSpecFunc = false;
        value1 = input.getText().toString();
        input.setText(null);
        signBox.setText("nor");
        sign = "nor";

    }

    public void btnClick_or(View view) {
        value1 = input.getText().toString();
        input.setText(null);
        hasSpecFunc = false;
        signBox.setText("or");
        sign = "or";

    }

    public void btnClick_and(View view) {

        value1 = input.getText().toString();
        input.setText(null);
        hasSpecFunc = false;
        signBox.setText("and");
        sign = "and";

    }


    //1's and 2's complement
    public void btnClick_2s(View view) {
        value1 = input.getText().toString();
        hasSpecFunc = true;
        input.setText(null);
        signBox.setText("2's");
        sign = "2s";


    }

    public void btnClick_1s(View view) {
        value1 = input.getText().toString();
        hasSpecFunc = true;
        input.setText(null);
        signBox.setText("1's");
        sign = "1s";

    }


    // bit shifting
    public void btnClick_rightShift(View view) {
        value1 = input.getText().toString();
        input.setText(null);
        hasSpecFunc = false;
        signBox.setText(">>");
        sign = "right_shift";

    }


    public void btnClick_leftShift(View view) {
        value1 = input.getText().toString();
        input.setText(null);
        hasSpecFunc = false;
        signBox.setText("<<");
        sign = "left_shift";
    }


    //Save button function
    public void btnClick_save(View view) {

        if (!input.getText().equals("")) {

            String str = input.getText().toString() + " (" + tagBox.getText() + ")";
            createNote(str);

            Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();
        }

    }


    //Number system conversion
    public void btnClick_Dec(View view) {

        String currentTag = tagBox.getText().toString();
        int dec;
        switch (currentTag) {
            case "D":
                dec = Integer.parseInt(input.getText().toString());
                break;
            case "H":
                dec = Integer.parseInt(input.getText().toString(), 16);
                break;
            case "B":
                dec = Integer.parseInt(input.getText().toString(), 2);
                break;
            case "O":
                dec = Integer.parseInt(input.getText().toString(), 8);
                break;
            default:
                dec = Integer.parseInt(input.getText().toString());
                break;
        }

        String decstring = Integer.toString(dec);
        input.setText(decstring);
        tagBox.setText("D");


        BinaryMode(false);
        HexMode(false);
        NormalMode(true);

    }

    public void btnClick_bin(View view) {
        String currentTag = tagBox.getText().toString();
        int dec;
        switch (currentTag) {
            case "D":
                dec = Integer.parseInt(input.getText().toString());
                break;
            case "H":
                dec = Integer.parseInt(input.getText().toString(), 16);
                break;
            case "B":
                dec = Integer.parseInt(input.getText().toString(), 2);
                break;
            case "O":
                dec = Integer.parseInt(input.getText().toString(), 8);
                break;
            default:
                dec = Integer.parseInt(input.getText().toString());
                break;
        }


        String hex = Integer.toString(dec, 2);
        input.setText(hex);
        tagBox.setText("B");


        BinaryMode(true);
        NormalMode(true);
        HexMode(false);
    }


    public void btnClick_hex(View view) {
        String currentTag = tagBox.getText().toString();
        int dec;
        switch (currentTag) {
            case "D":
                dec = Integer.parseInt(input.getText().toString());
                break;
            case "H":
                dec = Integer.parseInt(input.getText().toString(), 16);
                break;
            case "B":
                dec = Integer.parseInt(input.getText().toString(), 2);
                break;
            case "O":
                dec = Integer.parseInt(input.getText().toString(), 8);
                break;
            default:
                dec = Integer.parseInt(input.getText().toString());
        }


        String hex = Integer.toString(dec, 16).toUpperCase();
        input.setText(hex);
        tagBox.setText("H");

        BinaryMode(false);
        HexMode(true);
        NormalMode(true);
    }


    public void btnClick_oct(View view) {
        String currentTag = tagBox.getText().toString();
        int dec;
        switch (currentTag) {
            case "D":
                dec = Integer.parseInt(input.getText().toString());
                break;
            case "H":
                dec = Integer.parseInt(input.getText().toString(), 16);
                break;
            case "B":
                dec = Integer.parseInt(input.getText().toString(), 2);
                break;
            case "O":
                dec = Integer.parseInt(input.getText().toString(), 8);
                break;
            default:
                dec = Integer.parseInt(input.getText().toString());
        }


        String oct = Integer.toString(dec, 8);
        input.setText(oct);
        tagBox.setText("O");

        BinaryMode(false);
        NormalMode(true);
        OctalMode(true);
        HexMode(false);

    }


    public void btnClick_equaly(View view) {

        int intnum1, intnum2 = 0, intresult;
        String intresultstring;
        if (sign == null) {
            signBox.setText("Error!");
        } else {
            value2 = input.getText().toString();
            intnum1 = Integer.parseInt(value1, 2);
            if (!hasSpecFunc)
                intnum2 = Integer.parseInt(value2, 2);
            switch (sign) {
                default:
                    break;
                case "left_shift":
                    intresult = intnum1 << intnum2;
                    input.setText(Integer.toString(intresult, 2) + "");
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + Integer.toString(intresult, 2));
                    sign = null;
                    signBox.setText(null);
                    break;

                case "right_shift":
                    intresult = intnum1 >> intnum2;
                    input.setText(Integer.toString(intresult, 2) + "");
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + Integer.toString(intresult, 2));
                    sign = null;
                    signBox.setText(null);
                    break;
                case "and":
                    intresult = intnum1 & intnum2;
                    input.setText(Integer.toString(intresult, 2) + "");
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + Integer.toString(intresult, 2));
                    sign = null;
                    signBox.setText(null);
                    break;
                case "or":
                    intresult = intnum1 | intnum2;
                    input.setText(Integer.toString(intresult, 2) + "");
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + Integer.toString(intresult, 2));
                    sign = null;
                    signBox.setText(null);
                    break;
                case "xor":
                    intresult = intnum1 ^ intnum2;
                    input.setText(Integer.toString(intresult, 2) + "");
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + Integer.toString(intresult, 2));
                    sign = null;
                    signBox.setText(null);
                    break;
                case "nor":
                    intresult = intnum1 | intnum2;
                    intresultstring = OneAndTwosComplement(Integer.toString(intresult, 2), true);
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + intresultstring);
                    input.setText(intresultstring);
                    sign = null;
                    signBox.setText(null);
                    hasSpecFunc = false;
                    break;
                case "nand":
                    intresult = intnum1 & intnum2; // 1100 & 1010 = 1000

                    intresultstring = OneAndTwosComplement(Integer.toString(intresult, 2), true);

                    createNote(Integer.toString(intnum1, 2) + " " + sign + " " + Integer.toString(intnum2, 2) + " = " + intresultstring);

                    input.setText(intresultstring);
                    sign = null;
                    signBox.setText(null);
                    hasSpecFunc = false;
                    break;
                case "1s":
                    intresultstring = OneAndTwosComplement(Integer.toString(intnum1, 2), true);
                    input.setText(intresultstring);
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " = " + intresultstring);
                    sign = null;
                    signBox.setText(null);
                    hasSpecFunc = false;
                    break;
                case "2s":
                    intresultstring = OneAndTwosComplement(Integer.toString(intnum1, 2), false);
                    input.setText(intresultstring);
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " = " + intresultstring);
                    sign = null;
                    signBox.setText(null);
                    hasSpecFunc = false;
                    break;
                case "not":
                    intresultstring = OneAndTwosComplement(Integer.toString(intnum1, 2), true);
                    input.setText(intresultstring);
                    createNote(Integer.toString(intnum1, 2) + " " + sign + " = " + intresultstring);
                    sign = null;
                    signBox.setText(null);
                    hasSpecFunc = false;
                    break;
            }//end of switch
        }

    }//end of equaly function


    //Disable and Enable for number systems
    public void BinaryMode(Boolean status) {
        btn_two.setEnabled(!status);
        btn_three.setEnabled(!status);
        btn_four.setEnabled(!status);
        btn_five.setEnabled(!status);
        btn_six.setEnabled(!status);
        btn_seven.setEnabled(!status);
        btn_eight.setEnabled(!status);
        btn_nine.setEnabled(!status);

        btn_and.setEnabled(status);
        btn_or.setEnabled(status);
        btn_nand.setEnabled(status);
        btn_nor.setEnabled(status);
        btn_xor.setEnabled(status);
        btn_not.setEnabled(status);
        one_complement.setEnabled(status);
        two_omplement.setEnabled(status);
        right_shift.setEnabled(status);
        left_shift.setEnabled(status);

    }


    public void HexMode(Boolean status) {
        btn_a.setEnabled(status);
        btn_b.setEnabled(status);
        btn_c.setEnabled(status);
        btn_d.setEnabled(status);
        btn_e.setEnabled(status);
        btn_f.setEnabled(status);
    }


    public void NormalMode(Boolean status) {
        btn_zero.setEnabled(status);
        btn_one.setEnabled(status);

    }

    public void OctalMode(Boolean status) {
        btn_eight.setEnabled(!status);
        btn_nine.setEnabled(!status);

    }
}//end of Main Activity class
