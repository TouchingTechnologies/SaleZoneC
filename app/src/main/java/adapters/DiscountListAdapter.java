package adapters;

import java.util.List;

import util.Vars;


import dbcontent.DiscountDB;
import TouchingTechnologies.salezone.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class DiscountListAdapter extends BaseAdapter {
	List<DiscountDB> discount;
	Context context;
	public static LayoutInflater inflater = null;
	Vars vars;

	public DiscountListAdapter(Context context, List<DiscountDB> discountlist) {
		this.context= context;
		this.discount = discountlist;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		vars = new Vars(context);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return discount.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return discount.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		if (convertView == null){
		vi = inflater.inflate(R.layout.discount_row_list, null);
		
		TextView title = (TextView) vi.findViewById(R.id.title);
		TextView descrip = (TextView) vi.findViewById(R.id.timee);
		TextView time = (TextView) vi.findViewById(R.id.time);
		
	//	CheckBox delete = (CheckBox) view.findViewById(R.id.delete_chk);
		vars.log("code==="+discount.get(position).getUnqcode());
		vars.log("decription==="+discount.get(position).getDescription());
		title.setText(discount.get(position).getTitle());
		descrip.setText(discount.get(position).getDate());
		time.setText(discount.get(position).getDescription());
		
//		delete.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, "delete this at pos "+position, Toast.LENGTH_SHORT).show();
//				// TODO Auto-generated method stub
//				
//			}
//		});
		}
		return vi;
	}

}
