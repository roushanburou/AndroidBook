package com.brkc.uipractice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvShop1Sale;
    private Button btnShop1Buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        tvShop1Sale = (TextView)findViewById( R.id.tv_shop_1_sale );
        btnShop1Buy = (Button)findViewById( R.id.btn_shop_1_buy );

        btnShop1Buy.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == btnShop1Buy ) {
            final int sale = Integer.parseInt(tvShop1Sale.getText().toString()) + 100;
            android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this).
                    setMessage("当前人数过多，抢购失败")
                    .setTitle("温馨提示")
                    .setPositiveButton("我再看看", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "对不起，您所购买的商品加价了！", Toast.LENGTH_SHORT).show();
                            tvShop1Sale.setText(Integer.toString(sale));
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        }
    }

}
