package cs2901.utec.chat_mobile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public JSONArray elements;
    private Context mContext;
    private int userFromId;

    public MyAdapter(JSONArray elements, Context mContext, int userFromId) {
        this.elements = elements;
        this.mContext = mContext;
        this.userFromId = userFromId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView friendName;
        TextView myName;
        TextView friendLine;
        TextView myLine;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            friendName = itemView.findViewById(R.id.friend_name);
            friendLine = itemView.findViewById(R.id.element_view_friend_line);
            myLine = itemView.findViewById(R.id.element_view_me_line);
            myName = itemView.findViewById(R.id.my_name);
            container = itemView.findViewById(R.id.element_view_container);
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(
                R.layout.message_view, parent, false
        );
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        try{
            JSONObject element = elements.getJSONObject(position);
            String mFirstLine = element.getString("content");
            int userFromId = element.getInt("user_from_id");

            if(userFromId == this.userFromId){
                holder.myLine.setText(mFirstLine);
                holder.myName.setText(element.getJSONObject("user_from").getString("name"));
                holder.friendName.setText("");
                holder.friendLine.setText("");
                holder.friendLine.setBackgroundColor(80000000);
                holder.friendName.setBackgroundColor(80000000);

            }else{
                holder.friendName.setText(element.getJSONObject("user_from").getString("name"));
                holder.friendLine.setText(mFirstLine);
                holder.myLine.setText("");
                holder.myName.setText("");
                holder.myLine.setBackgroundColor(80000000);
                holder.myName.setBackgroundColor(80000000);

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return elements.length();
    }
}