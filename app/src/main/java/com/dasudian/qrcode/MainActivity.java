package com.dasudian.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView mTvResult;
    private EditText mInput;
    private ImageView mResult;
    private CheckBox mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = (TextView) findViewById(R.id.tv_result);
        mInput = (EditText) findViewById(R.id.et_text);
        mResult = (ImageView) findViewById(R.id.iv_result);
        mLogo = (CheckBox) findViewById(R.id.cb_logo);
    }

    public void scan(View view) {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class) ,0);
    }

    public void make(View view) {
        String input = mInput.getText().toString();
        if (input.trim().equals("")) {
            Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_LONG).show();
        } else {
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500,
                    mLogo.isChecked() ?
                            BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
                            : null);
            mResult.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTvResult.setText(result);
        }
    }
}
