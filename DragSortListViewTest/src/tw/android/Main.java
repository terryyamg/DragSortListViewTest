package tw.android;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.DragSortListView.RemoveListener;

public class Main extends ListActivity {
	private ArrayAdapter<String> adapter;

	/*拖拉選項*/
	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
		public void drop(int from, int to) {
			Log.i("from", from+"");
			Log.i("to", to+"");
			if (from != to) { //選項有移動位置
				DragSortListView list = getListView();
				String item = adapter.getItem(from);
				adapter.remove(item); //移除初始A的位置
				adapter.insert(item, to); //將A加入到B的位置
				list.moveCheckState(from, to);
			}
		}
	};

	/*移除選項*/
	private RemoveListener onRemove = new DragSortListView.RemoveListener() {
		public void remove(int which) {
			Log.i("which", which+"");
			DragSortListView list = getListView();
			String item = adapter.getItem(which);
			adapter.remove(item); //移除選中位置
			list.removeCheckState(which);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*建立選單*/
		String[] array = getResources().getStringArray(R.array.mlb_team); //在res/valuse/string.xml 建立選單
		ArrayList<String> arrayList = new ArrayList<String>(
				Arrays.asList(array));

		adapter = new ArrayAdapter<String>(this, R.layout.list_item_checkable, //選單layout樣式
				R.id.text, arrayList);

		setListAdapter(adapter);

		DragSortListView list = getListView();
		list.setDropListener(onDrop);
		list.setRemoveListener(onRemove);
	}

	@Override
	public DragSortListView getListView() {
		return (DragSortListView) super.getListView();
	}

}
