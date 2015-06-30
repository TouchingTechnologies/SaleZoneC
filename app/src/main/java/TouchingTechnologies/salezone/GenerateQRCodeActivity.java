package TouchingTechnologies.salezone;
import java.util.zip.Inflater;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
 
 
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
 
public class GenerateQRCodeActivity extends Activity {
 
 private String LOG_TAG = "GenerateQRCode";
 String unqcode;
 
 @SuppressLint("NewApi")
@Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.qrcode_main);
 Bundle extras = getIntent().getExtras();
 if (extras!=null){
	 unqcode = extras.getString("code");

   String qrInputText = unqcode;
   Log.v(LOG_TAG, qrInputText);
 
   //Find screen size
   WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
   Display display = manager.getDefaultDisplay();
   Point point = new Point();
   
   display.getSize(point);
   int width = point.x;
   int height = point.y;
   int smallerDimension = width < height ? width : height;
   smallerDimension = smallerDimension * 3/4;
 
   //Encode with a QR Code image
   QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText,
             null,
             Contents.Type.TEXT, 
             BarcodeFormat.QR_CODE.toString(),
             smallerDimension);
   try {
    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
    ImageView myImage = (ImageView) findViewById(R.id.imageView1);
    myImage.setImageBitmap(bitmap);
 
   } catch (WriterException e) {
    e.printStackTrace();
   }
 }
 }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	getMenuInflater().inflate(R.menu.qrcode, menu);
	return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub
	switch (item.getItemId()) {
	case R.id.done_select:
		Intent done = new Intent(this,MainActivity.class);
		startActivity(done);
		finish();
		
		
		break;

	default:
		break;
	}
	return super.onOptionsItemSelected(item);
}
 
}