package jp.miyanqii.pdfviewersample;

import android.os.Parcel;
import android.os.Parcelable;

/**
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
    private int order;

    public PdfFile(String fileName, String title, int order) {
        this.fileName = fileName;
        this.order = order;
        this.title = title;
    }

    protected PdfFile(Parcel in) {
        fileName = in.readString();
        title = in.readString();
        order = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(title);
        dest.writeInt(order);
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
                ", order='" + order + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }
}
