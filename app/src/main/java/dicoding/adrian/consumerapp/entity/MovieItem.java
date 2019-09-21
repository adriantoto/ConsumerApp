package dicoding.adrian.consumerapp.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import dicoding.adrian.consumerapp.db.DatabaseContract;

public class MovieItem implements Parcelable {

    private int id;
    private String poster;
    private double score;
    private String title;
    private String overview;
    private String backdrop;

    public String getPoster() {
        return poster;
    }

    public double getScore() {
        return score;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop() {
        return backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.poster);
        dest.writeDouble(this.score);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.backdrop);
    }

    public MovieItem() {
    }

    public MovieItem(int id, String poster, double score, String title, String overview, String backdrop) {
        this.id = id;
        this.poster = poster;
        this.score = score;
        this.title = title;
        this.overview = overview;
        this.backdrop = backdrop;
    }

    public MovieItem(Cursor cursor) {
        this.id = DatabaseContract.getColumnInt(cursor, DatabaseContract.MovieColumns._ID);
        this.poster = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.POSTER);
        this.score = DatabaseContract.getColumnDouble(cursor, DatabaseContract.MovieColumns.SCORE);
        this.title = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.TITLE);
        this.overview = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.OVERVIEW);
        this.backdrop = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.BACKDROP);
    }

    protected MovieItem(Parcel in) {
        this.id = in.readInt();
        this.poster = in.readString();
        this.score = in.readDouble();
        this.title = in.readString();
        this.overview = in.readString();
        this.backdrop = in.readString();
    }

    public static final Parcelable.Creator<MovieItem> CREATOR = new Parcelable.Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel source) {
            return new MovieItem(source);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };
}
