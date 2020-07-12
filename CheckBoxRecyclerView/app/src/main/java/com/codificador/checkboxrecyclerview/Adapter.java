package com.codificador.checkboxrecyclerview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemHolder>{

    ArrayList<Item> items;
    LayoutInflater lInflater;

    public Adapter(ArrayList<Item> items, Context context){
        this.items = items;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = lInflater.inflate(R.layout.item_row, parent, false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    public ArrayList<Item> getSelectedItems(){
        ArrayList<Item> result = new ArrayList<>();
        for(Item item : items){
            if(item.isSelected())
                result.add(item);
        }
        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.checkBox.setChecked(items.get(position).isSelected());
        if(items.get(position).getQty() > 0)
            holder.editText.setText(items.get(position).getQty()+"");
        holder.textViewName.setText(items.get(position).getName());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int position = (int) compoundButton.getTag();
                System.out.println("Changed Pos : "+position+" "+b);
                items.get(position).setSelected(b);
            }
        });
        CustomTextWatcher customTextWatcher = (CustomTextWatcher) holder.editText.getTag();
        if(customTextWatcher != null){
            holder.editText.removeTextChangedListener(customTextWatcher);
        }
        CustomTextWatcher newWatcher = new CustomTextWatcher(position);
        holder.editText.addTextChangedListener(newWatcher);
    }

    class CustomTextWatcher implements TextWatcher{

        int position;
        public CustomTextWatcher(int pos){
            position = pos;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            System.out.println(position+" "+editable.toString());
            if(editable.length() > 0)
            items.get(position).setQty(Integer.parseInt(editable.toString()));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ItemHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        CheckBox checkBox;
        EditText editText;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            setIsRecyclable(false);
            checkBox = itemView.findViewById(R.id.cb);
            editText = itemView.findViewById(R.id.editText);
            textViewName = itemView.findViewById(R.id.textItem);
        }
    }
}
