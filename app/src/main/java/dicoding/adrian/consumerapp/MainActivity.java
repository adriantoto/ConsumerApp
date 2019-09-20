package dicoding.adrian.consumerapp;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

import dicoding.adrian.consumerapp.adapter.ConsumerAdapter;
import dicoding.adrian.consumerapp.entity.MovieItem;

import static dicoding.adrian.consumerapp.DatabaseContract.MovieColumns.CONTENT_URI;
import static dicoding.adrian.consumerapp.MappingHelper.mapCursorToArrayList;

public class MainActivity extends AppCompatActivity implements LoadMoviesCallback {

    private ConsumerAdapter consumerAdapter;
    private DataObserver myObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cast Recyclerview
        RecyclerView rvMovies = findViewById(R.id.rv_movie_favorite);

        consumerAdapter = new ConsumerAdapter(this);

        // Layout Manager
        rvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Divider between item list
        DividerItemDecoration itemDecorator = new DividerItemDecoration(Objects.requireNonNull(this), DividerItemDecoration.HORIZONTAL);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(Objects.requireNonNull(this), R.drawable.divider)));
        rvMovies.addItemDecoration(itemDecorator);
        rvMovies.setHasFixedSize(true);

        // Set Adapter
        rvMovies.setAdapter(consumerAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);
        new getData(this, this).execute();
    }

    @Override
    public void postExecute(Cursor movies) {
        ArrayList<MovieItem> listMovies = mapCursorToArrayList(movies);
        if (listMovies.size() > 0) {
            consumerAdapter.setListMovies(listMovies);
        } else {
            Toast.makeText(this, "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
            consumerAdapter.setListMovies(new ArrayList<MovieItem>());
        }
    }

    private static class getData extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMoviesCallback> weakCallback;

        private getData(Context context, LoadMoviesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }
    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new getData(context, (MainActivity) context).execute();
        }
    }
}
