package com.example.android.xogame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
  private Button[] a= new Button[9];
  TextView player1,player2;
  boolean player1Turn=true;
  private int roundCount=0;
  int player1Points=0;
  int player2Points=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1=(TextView)findViewById(R.id.player1);
        player2=(TextView)findViewById(R.id.player2);
        for (int i =0;i<9;i++){
          String id="button"+(i+1);
          int resId=getResources().getIdentifier(id,"id",getPackageName());
          a[i]=findViewById(resId);
          a[i].setOnClickListener(this);
        }

    }

    public void reset(View view) {
        for (int i=0;i<9;i++)
            a[i].setText("");
        roundCount=0;
        player1Turn=true;
        player1Points=player2Points=0;
        player1.setText("Player 1: "+player1Points);
        player2.setText("Player 2: "+player2Points);

    }

    @Override
    public void onClick(View view) {
  if (!((Button)view).getText().toString().equals(""))
      return;
        if (player1Turn)
            ((Button)view).setText("x");
    else
          ((Button)view).setText("O");
        roundCount++;
        if(cheakForWin()){
            if(player1Turn)
              player1Win();
            else
                player2Win();
        }
        else if(roundCount==9)
            Draw();
        else
        player1Turn=!player1Turn;
    }
    private void player1Win(){
        player1Points++;
        Toast.makeText(this,"Player 1 Win",Toast.LENGTH_SHORT).show();
        player1.setText("Player 1: "+player1Points);
        roundCount=0;
        resetboard();
    }
    private void player2Win(){
        player2Points++;
        Toast.makeText(this,"Player 2 Win",Toast.LENGTH_SHORT).show();
        player2.setText("Player 2: "+player2Points);
        roundCount=0;
        resetboard();

    }
    private void Draw(){
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
          resetboard();
    }
private void resetboard(){
    for (int i=0;i<9;i++)
        a[i].setText("");
    player1Turn=true;
}
    private boolean cheakForWin(){
        String [] x=new String[9];
        for (int i=0;i<9;i++){
          x[i]=a[i].getText().toString();
        }
        if(x[0].equals(x[1])&&x[0].equals(x[2])&&!x[0].equals(""))
            return true;
        if(x[3].equals(x[4])&&x[3].equals(x[5])&&!x[3].equals(""))
            return true;
        if(x[6].equals(x[7])&&x[6].equals(x[8])&&!x[7].equals(""))
            return true;
        if(x[0].equals(x[3])&&x[0].equals(x[6])&&!x[0].equals(""))
            return true;
        if(x[1].equals(x[4])&&x[1].equals(x[7])&&!x[1].equals(""))
            return true;
        if(x[2].equals(x[5])&&x[2].equals(x[8])&&!x[2].equals(""))
            return true;
        if(x[0].equals(x[4])&&x[0].equals(x[8])&&!x[0].equals(""))
            return true;
        if(x[2].equals(x[4])&&x[2].equals(x[6])&&!x[2].equals(""))
            return true;
      return false;
    }

}
