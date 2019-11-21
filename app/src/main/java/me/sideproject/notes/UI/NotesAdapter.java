package me.sideproject.notes.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.sideproject.notes.DataBase.Note;
import me.sideproject.notes.EditorActivity;
import me.sideproject.notes.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private final List<Note> list ;
private final Context context ;

    public NotesAdapter(List<Note> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext()) ;
        View view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
final  Note note  = list.get(i) ;
viewHolder.textView.setText(note.getText());
viewHolder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent I = new Intent(context, EditorActivity.class) ;
        I.putExtra("ID",note.getId()) ;
        context.startActivity(I);
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
@BindView(R.id.floatingActionButton)
        FloatingActionButton floatingActionButton ;
        TextView textView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
textView = itemView.findViewById(R.id.head) ;
            ButterKnife.bind(this,itemView) ;
        }
    }
}
