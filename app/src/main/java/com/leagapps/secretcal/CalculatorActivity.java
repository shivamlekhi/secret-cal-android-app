package com.leagapps.secretcal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CalculatorActivity extends Activity implements View.OnClickListener {
    Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,
            ClearButton,
            AddOperator, SubOperator, DivideOperator, MultiplyOperator,
            decimalButton, EvalButton;

    StringBuffer CurrentNumbuffer;
    String PreviousNumber, CurrentNumber;
    TextView Screen, CurrentNumBufferView;
    char CurrentOperator;
    LinearLayout ScreenHolder;
    Typeface Bosun, Bebas;
    ScrollView ScreenScroller;
    float GrandTotal;
    ArrayList<View> screenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clac_layout);
        initUI();

        getActionBar().hide();
    }

    private void initUI() {
        CurrentNumbuffer = new StringBuffer();
        CurrentNumber = "";
        PreviousNumber = "";
        screenList = new ArrayList<View>();

        Bebas = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");
        Bosun = Typeface.createFromAsset(getAssets(), "fonts/bosun03.otf");

        ScreenScroller = (ScrollView) findViewById(R.id.screen_scroller);

        ScreenHolder = (LinearLayout) findViewById(R.id.ScreenLayout);
        num0 = (Button) findViewById(R.id.num_0_button);
        num1 = (Button) findViewById(R.id.num_1_button);
        num2 = (Button) findViewById(R.id.num_2_button);
        num3 = (Button) findViewById(R.id.num_3_button);
        num4 = (Button) findViewById(R.id.num_4_button);
        num5 = (Button) findViewById(R.id.num_5_button);
        num6 = (Button) findViewById(R.id.num_6_button);
        num7 = (Button) findViewById(R.id.num_7_button);
        num8 = (Button) findViewById(R.id.num_8_button);
        num9 = (Button) findViewById(R.id.num_9_button);

        Screen = (TextView) findViewById(R.id.screen);
        CurrentNumBufferView = (TextView) findViewById(R.id.screen_currentBuffer);

        Screen.setTypeface(Bosun);
        CurrentNumBufferView.setTypeface(Bosun);

        ClearButton = (Button) findViewById(R.id.clear_button);

        AddOperator = (Button) findViewById(R.id.operator_add);
        SubOperator = (Button) findViewById(R.id.operator_sub);
        DivideOperator = (Button) findViewById(R.id.operator_divide);
        MultiplyOperator = (Button) findViewById(R.id.operator_multiply);

        EvalButton = (Button) findViewById(R.id.eval_button);
        decimalButton = (Button) findViewById(R.id.decimal_button);

        decimalButton.setTypeface(Bebas);
        EvalButton.setTypeface(Bebas);

        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);

        num0.setTypeface(Bebas);
        num1.setTypeface(Bebas);
        num2.setTypeface(Bebas);
        num3.setTypeface(Bebas);
        num4.setTypeface(Bebas);
        num5.setTypeface(Bebas);
        num6.setTypeface(Bebas);
        num7.setTypeface(Bebas);
        num8.setTypeface(Bebas);
        num9.setTypeface(Bebas);

        ClearButton.setTypeface(Bebas);
        ClearButton.setOnClickListener(this);

        EvalButton.setOnClickListener(this);

        AddOperator.setTypeface(Bebas);
        SubOperator.setTypeface(Bebas);
        MultiplyOperator.setTypeface(Bebas);
        DivideOperator.setTypeface(Bebas);

        AddOperator.setOnClickListener(this);
        SubOperator.setOnClickListener(this);
        MultiplyOperator.setOnClickListener(this);
        DivideOperator.setOnClickListener(this);
        decimalButton.setOnClickListener(this);


        EvalButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent NotesActivity = new Intent(CalculatorActivity.this, NotesActivity.class);
                startActivity(NotesActivity);

                Toast.makeText(CalculatorActivity.this, "Secret Notes Section Opened" ,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        ClearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(ScreenHolder.getChildCount() > 0) {
                    ScreenHolder.removeAllViews();

                    PreviousNumber = "";
                    CurrentNumber = "";
                    CurrentOperator = ' ';
                    GrandTotal = 0;
                    CurrentNumbuffer.delete(0, CurrentNumbuffer.length());
                    CurrentNumBufferView.setText("");
                }
                return true;
            }
        });

        decimalButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                IndexAllNotes();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.open_about) {
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.num_0_button:
                CurrentNumbuffer.append('0');
                UpdateCurrentNumber();
                break;
            case R.id.num_1_button:
                CurrentNumbuffer.append('1');
                UpdateCurrentNumber();
                break;
            case R.id.num_2_button:
                CurrentNumbuffer.append('2');
                UpdateCurrentNumber();
                break;
            case R.id.num_3_button:
                CurrentNumbuffer.append('3');
                UpdateCurrentNumber();
                break;
            case R.id.num_4_button:
                CurrentNumbuffer.append('4');
                UpdateCurrentNumber();
                break;
            case R.id.num_5_button:
                CurrentNumbuffer.append('5');
                UpdateCurrentNumber();
                break;
            case R.id.num_6_button:
                CurrentNumbuffer.append('6');
                UpdateCurrentNumber();
                break;
            case R.id.num_7_button:
                CurrentNumbuffer.append('7');
                UpdateCurrentNumber();
                break;
            case R.id.num_8_button:
                CurrentNumbuffer.append('8');
                UpdateCurrentNumber();
                break;
            case R.id.num_9_button:
                CurrentNumbuffer.append('9');
                UpdateCurrentNumber();
                break;
            case R.id.decimal_button:
                CurrentNumbuffer.append('.');
                UpdateCurrentNumber();
                break;
            case R.id.operator_add:
                MathOperation('+');
                break;
            case R.id.operator_sub:
                MathOperation('-');
                break;
            case R.id.operator_multiply:
                MathOperation('X');
                break;
            case R.id.operator_divide:
                MathOperation('/');
                break;
            case R.id.clear_button:
                deleteLastChar();
                break;
            case R.id.eval_button:
                Eval();
                break;
        }
    }

    private void Eval() {
        CurrentNumber = CurrentNumBufferView.getText().toString();

        if(CheckForNote(CurrentNumber) != null && CurrentOperator == ' ') {
            Note curNote = CheckForNote(CurrentNumber);
            AddTextView(curNote.NoteText);
            PreviousNumber = "";
            CurrentNumber = "";

            CurrentNumBufferView.setText("");
            CurrentNumbuffer.delete(0, CurrentNumbuffer.length());
        } else {
            if (!PreviousNumber.equals("") && !CurrentNumbuffer.equals("") && CurrentOperator != ' ') {

                float current = Float.parseFloat(CurrentNumber);
                float previous = Float.parseFloat(PreviousNumber);

                float answer = 0;

                switch (CurrentOperator) {
                    case '+':
                        answer = current + previous;
                        break;
                    case '-':
                        answer = previous - current;
                        break;

                    case 'X':
                        answer = current * previous;
                        break;

                    case '/':
                        answer = previous / current;
                        break;
                    default:
                        break;
                }
                GrandTotal += answer;
                PreviousNumber = Float.toString(answer);
                CurrentNumtoScreen();
                AppendAnswer(Float.toString(GrandTotal));
                CurrentOperator = ' ';
            } else if (!PreviousNumber.equals("") && CurrentOperator != ' '){

                View view  = screenList.get(screenList.size() - 1);
                ScreenHolder.removeView(view);
                    AppendAnswer(Float.toString(GrandTotal));
            }
        }
    }

    private void MathOperation(char Operator) {
        CurrentOperator = Operator;
        CurrentNumber = CurrentNumBufferView.getText().toString();

        if(CurrentNumber.equals("") && !PreviousNumber.equals("")) {
            // If previous number is there
            AppendOperator(CurrentOperator);
        }

        else if( !CurrentNumber.equals("") ) {
            // If There is A Current Number
            CurrentNumtoScreen();

            AppendOperator(CurrentOperator);
            PreviousNumber = CurrentNumber;
            CurrentNumber = "";
            // GrandTotal += Float.parseFloat(PreviousNumber);

            CurrentNumber = "";
        } else if(!PreviousNumber.equals("") && !CurrentNumber.equals("")) {
            float current = Float.parseFloat(CurrentNumber);
            float previous = Float.parseFloat(PreviousNumber);
            float answer = 0;

            switch (Operator){
                case '+':
                    answer = current + previous;
                    break;

                case '-':
                    answer = current - previous;
                    break;

                case 'X':
                    answer = current * previous;
                    break;

                case '/':
                    answer = current / previous;
                    break;
            }
            GrandTotal += answer;
            PreviousNumber = Float.toString(answer);
            CurrentNumtoScreen();
            AppendOperator(Operator);
            PreviousNumber = Float.toString(answer);
        }
    }

    private Note CheckForNote(String Number) {
        List<Note> notes = Note.find(Note.class, "NUMBER = ?", Number);
        if(notes.size() != 0) {
            return notes.get(0);
        } else {
            return null;
        }
    }

    private void UpdateCurrentNumber() {
        CurrentNumBufferView.setText(CurrentNumbuffer.toString());
    }

    private void AddTextView(String Content) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView NewLineText = new TextView(this);
        NewLineText.setLayoutParams(params);
        NewLineText.setTextColor(Color.parseColor("#34495e"));
        NewLineText.setText(Content);
        NewLineText.setTypeface(Bosun);
        NewLineText.setTextSize(18);

        screenList.add(NewLineText);
        ScreenHolder.addView(NewLineText);

        ScrollDown();
    }

    private void AddSeperator() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2);
        params.setMargins(5, 0 , 5, 0);
        View seperator = new View(this);
        seperator.setLayoutParams(params);
        seperator.setBackgroundColor(Color.parseColor("#2c3e50"));
        seperator.setMinimumHeight(2);

        screenList.add(seperator);
        ScreenHolder.addView(seperator);

        ScrollDown();
    }

    private void AppendOperator(Character operator) {
        // Screen.setText(operator.toString() + "\n" + Screen.getText());
        AddTextView(operator.toString());
        ScrollDown();
    }

    private void CurrentNumtoScreen() {
        AddTextView(CurrentNumber);
        CurrentNumBufferView.setText("");
        CurrentNumbuffer.delete(0, CurrentNumbuffer.length());
        ScrollDown();
    }

    private void AppendAnswer(String Answer) {
        // Screen.setText("Answer: " + Answer + "\n" + Screen.getText().toString());
        AddSeperator();
        AddTextView(Answer);
        ScrollDown();
    }

    private void deleteLastChar() {
        if(CurrentNumbuffer.length() > 0) {
            CurrentNumbuffer.deleteCharAt(CurrentNumbuffer.length() - 1);
            UpdateCurrentNumber();
        }
    }

    private void ScrollDown() {
        ScreenScroller.post(new Runnable()
        {
            public void run()
            {
                ((ScrollView) findViewById(R.id.screen_scroller)).fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private void IndexAllNotes() {
        List<Note> notes = Note.listAll(Note.class);
        if(notes.size() > 0) {
            for(Note note : notes) {
                AddNoteView(note);
            }
        }
    }

    private void AddNoteView(Note note) {
        LayoutInflater inflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.screen_note_view, null);

        TextView Number = (TextView) v.findViewById(R.id.screen_note_item_number);
        TextView Text = (TextView) v.findViewById(R.id.screen_note_item_text);
        LinearLayout MainLayout = (LinearLayout) v.findViewById(R.id.note_view_main_layout);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 40);
        params.setMargins(0, 5, 0, 5);

        MainLayout.setLayoutParams(params);
        Number.setTypeface(Bebas);
        Text.setTypeface(Bosun);

        String text = note.NoteText;
        int number = note.Number;

        Number.setText(String.valueOf(number));
        Text.setText(text);

        ScreenHolder.addView(v);
    }
}
