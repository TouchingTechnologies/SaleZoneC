package adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import util.Vars;

import com.touching.salezone.AppController;
import com.touching.salezone.FeedImageView;
import com.touching.salezone.R;
import com.touching.salezone.FeedItem;

import dbcontent.DiscountDB;

/**
 * Created by baia on 14-9-13.
 */
public class FeedListAdapter extends BaseAdapter {
	AlertDialog alertDialog ;
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    Vars vars;
    ImageLoader imageLoader = AppController.getInstance(activity).getImageLoader();

    public FeedListAdapter(Activity activity, List<FeedItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
        vars = new Vars(activity);
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

 
    public void setData(List<FeedItem> data) {
        this.feedItems = data;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.feed_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance(activity).getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        final TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        TextView discount = (TextView) convertView
                .findViewById(R.id.get_discount);
        TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        FeedImageView feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);

        final FeedItem item = feedItems.get(position);

        name.setText(item.getName());

        // Converting timestamp into x ago format
        final CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

        // user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
        if (item.getImageUrl() != null) {
            feedImageView.setImageUrl(item.getImageUrl(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }
        discount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LayoutInflater li = LayoutInflater.from(activity);
				View promptsView;
				vars.log("========layinf=====");
				
				 promptsView = li.inflate(R.layout.getdiscount_inflator, null);
				 TextView header = (TextView) promptsView.findViewById(R.id.header);
				 final TextView desc = (TextView) promptsView.findViewById(R.id.decription);
				 header.setText(item.getName());
				 desc.setText(item.getStatus());
				 Button cancle = (Button) promptsView.findViewById(R.id.cancle);
				 Button confirm = (Button) promptsView.findViewById(R.id.confirm);
				 confirm.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
						 if (TextUtils.isEmpty(item.getStatus())) {
					            statusMsg.setText(item.getStatus());
						 }
						Toast.makeText(activity, "Your discount has been reserved", Toast.LENGTH_LONG).show();
						DiscountDB save = new DiscountDB(activity, item.getName(), desc.getText().toString(), unqcode(timeAgo), timeAgo.toString());
						save.save();
						
					}
				});
				 cancle.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							alertDialog.dismiss();
						}
					});
				 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
					alertDialogBuilder.setView(promptsView);
					alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				
			}
		});
        return convertView;
    }
    public String unqcode(CharSequence timeAgo){
    	String code ="323nfu874m39"+timeAgo;
    	return code;
    }
}
