package jp.miyanqii.simplepdfviewer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by miyaki on 16/09/19.
 */
public class PdfFile implements Parcelable {
    public static final Creator<PdfFile> CREATOR = new Creator<PdfFile>() {
        @Override
        public PdfFile createFromParcel(Parcel in) {
            return new PdfFile(in);
        }

        @Override
        public PdfFile[] newArray(int size) {
            return new PdfFile[size];
        }
    };
    private String fileName;
    private String title;
    private String subTitle;

    public PdfFile(String fileName, String title, String subTitle) {
        this.fileName = fileName;
        this.title = title;
        this.subTitle = subTitle;
    }

    protected PdfFile(Parcel in) {
        fileName = in.readString();
        title = in.readString();
        subTitle = in.readString();
    }

    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(title);
        dest.writeString(subTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "PdfFile{" +
                "fileName='" + fileName + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }
}
