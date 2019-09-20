package dicoding.adrian.consumerapp.entity;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import dicoding.adrian.consumerapp.DatabaseContract;

public class MovieItem implements Parcelable {
    private int id;
    private String poster;
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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
    }

    public MovieItem() {
    }

    public MovieItem(int id, String poster, double score) {
        this.id = id;
        this.poster = poster;
        this.score = score;
    }

    public MovieItem(Cursor cursor) {
        this.id = DatabaseContract.getColumnInt(cursor, DatabaseContract.MovieColumns._ID);
        this.poster = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.POSTER);
        this.score = DatabaseContract.getColumnDouble(cursor, DatabaseContract.MovieColumns.SCORE);
    }

    protected MovieItem(Parcel in) {
        this.id = in.readInt();
        this.poster = in.readString();
        this.score = in.readDouble();
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
