package com.evs.jgb.model.parentModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ArticleModel  implements Parcelable {
    @SerializedName("id_cr")
    private String id_cr;
    @SerializedName("title")
    private String title;
    @SerializedName("id_element")
    private String id_element;
    @SerializedName("body_combine")
    private String body_combine;

    public static final Creator<ArticleModel> CREATOR = new Creator<ArticleModel>() {
        @Override
        public ArticleModel createFromParcel(Parcel in) {
            return new ArticleModel(in);
        }

        @Override
        public ArticleModel[] newArray(int size) {
            return new ArticleModel[size];
        }
    };

    public String getBody_combine() {
        return body_combine;
    }

    public String getId_cr() {
        return id_cr;
    }

    public String getTitle() {
        return title;
    }

    public String getId_element() {
        return id_element;
    }

    public ArticleModel(Parcel in) {
        id_cr = in.readString();
        title = in.readString();
        id_element = in.readString();
        body_combine = in.readString();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id_cr);
        dest.writeString(title);
        dest.writeString(id_element);
        dest.writeString(body_combine);
    }
}
