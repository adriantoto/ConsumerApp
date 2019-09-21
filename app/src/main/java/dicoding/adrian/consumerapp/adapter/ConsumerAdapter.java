package dicoding.adrian.consumerapp.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import dicoding.adrian.consumerapp.R;
import dicoding.adrian.consumerapp.entity.MovieItem;

public class ConsumerAdapter extends RecyclerView.Adapter<ConsumerAdapter.MovieViewHolder> {

    private final ArrayList<MovieItem> listMovies = new ArrayList<>();
    private final Activity activity;
    private OnItemClickListener listener;

    public ConsumerAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MovieItem> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<MovieItem> listMovies) {
        this.listMovies.clear();
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consumer_note, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {

        // String value
        double score = getListMovies().get(position).getScore() * 10;
        holder.tvScore.setText(String.valueOf((int) score));

        // Image Value
        String uri = "https://image.tmdb.org/t/p/original" + getListMovies().get(position).getPoster();
        Glide.with(holder.itemView.getContext())
                .load(uri)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pgMovie.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView ivPoster;
        final TextView tvScore;
        final ProgressBar pgMovie;
        View rootView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            ivPoster = itemView.findViewById(R.id.img_item_poster_favorite_movie);
            tvScore = itemView.findViewById(R.id.tv_item_scoreAngkaHome_favorite_movie);
            pgMovie = itemView.findViewById(R.id.progressBar_item_favorite_movie);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(listMovies.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MovieItem movieItems);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
