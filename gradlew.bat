package dicoding.adrian.submission4.favorite.MovieFavorite.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import dicoding.adrian.submission4.basic.CustomOnItemClickListener;
import dicoding.adrian.submission4.favorite.MovieFavorite.DetailMovieFavoriteActivity;
import dicoding.adrian.submission4.movie.MovieItems;
import dicoding.adrian.submission4.R;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder> implements RemoteViewsService.RemoteViewsFactory{

    private ArrayList<MovieItems> listMovies = new ArrayList<>();

    private Activity activity;

    public MovieFavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<MovieItems> getListMovies() {
        return listMovies;
    }

    public void setListMovie(ArrayList<MovieItems> listMovies) {
        if (listMovies.size() > 0) {
            this.listMovies.clear();
        }
        this.listMovies.addAll(listMovies);
        notifyDataSetChanged();
    }

    public void addItem(MovieItems movieItems) {
        this.listMovies.add(movieItems);
        notifyItemInserted(listMovies.size() - 1);
    }

    public void removeItem(int position) {
        this.listMovies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listMovies.size());
    }

    @NonNull
    @Override
    public MovieFavorit