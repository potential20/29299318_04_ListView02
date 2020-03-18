package kr.co.tjoeun.a29299318_04_listview02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kr.co.tjoeun.a29299318_04_listview02.R;
import kr.co.tjoeun.a29299318_04_listview02.datas.Room;

public class RoomAdapter extends ArrayAdapter<Room> {

    Context mContext;
    List<Room> mList;
    LayoutInflater inf;

    public RoomAdapter(@NonNull Context context, int resource, @NonNull List<Room> objects) {
        super(context, resource, objects);

        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.room_list_item,null);
        }

        Room data = mList.get(position);
        TextView priceTxt = row.findViewById(R.id.priceTxt);
        TextView addressAndFloorTxt = row.findViewById(R.id.addressAndFloorTxt);
        TextView descText = row.findViewById(R.id.descText);

//        가격 설정 => setText에는 int값 넣지 말자.
//        1만 이상 ? 억 단위, 아니면? 숫자만, 찍어서.

        if(data.getPrice() >= 10000) {
//          ? 억 ?천으로 가공.
//            ? 억 ?
            int uk = data.getPrice() / 10000;
            int thousand = data.getPrice() % 10000;

            priceTxt.setText(String.format("%d억 %,d",uk,thousand));
        }
        else {
            priceTxt.setText(String.format("%,d",data.getPrice()));
        }

//        주소 / 층수 결합해서.

        String floorStr = "";
//        층 > 0, 층 ==0, 그외 (층 <0)

        if (data.getFloor() > 0) {
//            4 => "4층" 으로 가공
            floorStr = String.format("%d층",data.getFloor());
        }
        else if (data.getFloor() == 0) {
//            0 => "반지하" 로
            floorStr = "반지하";
        }
        else {
//            -1 => "지하 1층" 으로 가공 , -1(음수) 곱하면 양수
            floorStr = String.format("지하 %d층",data.getFloor()*-1);
        }


        addressAndFloorTxt.setText(String.format("%s, %s",data.getAddress(),floorStr));

//        설명은 들어온 그대로 출력.
        descText.setText(data.getDescription());




        return row;
    }
}
