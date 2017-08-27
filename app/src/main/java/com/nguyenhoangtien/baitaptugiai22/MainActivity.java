package com.nguyenhoangtien.baitaptugiai22;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.PropertyPermission;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtNumber;
    private TextView txtResult;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;
    private Button btnNumber0;


    private Button btnCong;
    private Button btnTru;
    private Button btnNhan;
    private Button btnChia;

    private Button btnPoint;
    private Button btnResult;
    private Button btnClear;
    private Button btnClearAll;

    private final String TAG=getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        btnNumber0.setOnClickListener(this);

        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);

        btnClear.setOnClickListener(this);
        btnClearAll.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    private void addControls() {
        edtNumber= (EditText) findViewById(R.id.edtNumber);
        txtResult= (TextView) findViewById(R.id.txtResult);

        btnNumber1= (Button) findViewById(R.id.btnNumber1);
        btnNumber2= (Button) findViewById(R.id.btnNumber2);
        btnNumber3= (Button) findViewById(R.id.btnNumber3);
        btnNumber4= (Button) findViewById(R.id.btnNumber4);
        btnNumber5= (Button) findViewById(R.id.btnNumber5);
        btnNumber6= (Button) findViewById(R.id.btnNumber6);
        btnNumber7= (Button) findViewById(R.id.btnNumber7);
        btnNumber8= (Button) findViewById(R.id.btnNumber8);
        btnNumber9= (Button) findViewById(R.id.btnNumber9);
        btnNumber0= (Button) findViewById(R.id.btnNumber0);

        btnCong= (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan= (Button) findViewById(R.id.btnNhan);
        btnChia= (Button) findViewById(R.id.btnChia);

        btnClear= (Button) findViewById(R.id.btnClear);
        btnClearAll= (Button) findViewById(R.id.btnClearAll);
        btnPoint= (Button) findViewById(R.id.btnPoint);
        btnResult= (Button) findViewById(R.id.btnResult);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnNumber0:
                edtNumber.append("0");
                break;
            case R.id.btnNumber1:
                edtNumber.append("1");
                break;
            case R.id.btnNumber2:
                edtNumber.append("2");
                break;
            case R.id.btnNumber3:
                edtNumber.append("3");
                break;
            case R.id.btnNumber4:
                edtNumber.append("4");
                break;
            case R.id.btnNumber5:
                edtNumber.append("5");
                break;
            case R.id.btnNumber6:
                edtNumber.append("6");
                break;
            case R.id.btnNumber7:
                edtNumber.append("7");
                break;
            case R.id.btnNumber8:
                edtNumber.append("8");
                break;
            case R.id.btnNumber9:
                edtNumber.append("9");
                break;
            case R.id.btnClear:
                BaseInputConnection textFieldInputConnection=new BaseInputConnection(edtNumber,true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btnClearAll:
                edtNumber.setText("");
                break;
            case R.id.btnPoint:
                edtNumber.append(".");
                break;

            case R.id.btnCong:
                edtNumber.append("+");
                break;
            case R.id.btnTru:
                edtNumber.append("-");
                break;
            case R.id.btnNhan:
                edtNumber.append("*");
                break;
            case R.id.btnChia:
                edtNumber.append("/");
                break;
            case R.id.btnResult:

                // Phương thức làm tròn kết quả để ko bị 6.0
                DecimalFormat df=new DecimalFormat("###.####");
                addNumber(edtNumber.getText().toString());
                addOperation(edtNumber.getText().toString());
                //Thuật toán tính toán các biểu thức
                Evaluator evaluator =new Evaluator();
                try {
                    String kq=evaluator.evaluate(edtNumber.getText().toString());
                    txtResult.setText(df.format(kq) + "");
                }
                catch (EvaluationException e)
                {
                    System.out.println(e);
                }

                    //Log.d(TAG,"onClick: "+result ); hiển thị lên màn hình logcat





        }
    }
    // Cach nay rat dai` nen dung cach khac  ->> de vao ham`      String numberAfterRemove=deleteANumber(edtNumber.getText().toString()); // chuyen qua String de truyen vao ham` nay`
                                                            //   edtNumber.setText(numberAfterRemove);
//    public String deleteANumber(String number){
//        int lenght=number.length();
//        String tem=number.substring(0,lenght-1);
//        return tem;
//    }

    public ArrayList<String> arrOperation;
    public ArrayList<Double> arrNumber;

    // Hàm tách các dấu ra khỏi chuỗi và lưu vào mảng arrOperation
    public int addOperation(String input)
    {
        arrOperation=new ArrayList<>();
        char[] cArray=input.toCharArray();
        for (int i=0;i<cArray.length;i++)
        {
            switch (cArray[i])
            {
                case '+':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '-':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '*':
                    arrOperation.add(cArray[i]+"");
                    break;
                case '/':
                    arrOperation.add(cArray[i]+"");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //Hàm tách các số ra khỏi chuỗi và lưu vào mảng arrNumber
    public void addNumber (String input)
    {
        arrNumber=new ArrayList<>();
        Pattern regex=Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher=regex.matcher(input);
        while (matcher.find())
        {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }


}
