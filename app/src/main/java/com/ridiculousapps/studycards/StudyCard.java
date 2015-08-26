package com.ridiculousapps.studycards;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yingjie on 19/8/15.
 */
public class StudyCard implements Parcelable {

    String question;
    String[] choices;
    int answerIndex;
    int imageResourceID; // this stores the R.id.image resource ID

    public StudyCard(String question, String[] choices, int answerIndex, int imageResourceID) {
        this.question = question;
        this.choices = choices;
        this.answerIndex = answerIndex;
        this.imageResourceID = imageResourceID;
    }

    public StudyCard(String question, String[] choices, int answerIndex) {
        this.question = question;
        this.choices = choices;
        this.answerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return question;
    }

    //-------
    //The following code is to enable Parcelable

    protected StudyCard(Parcel in) {
        question = in.readString();
        answerIndex = in.readInt();
        choices = new String[3];
        in.readStringArray(choices);
        imageResourceID = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeInt(answerIndex);
        dest.writeStringArray(choices);
        dest.writeInt(imageResourceID);
    }

    public static final Parcelable.Creator<StudyCard> CREATOR = new Parcelable.Creator<StudyCard>() {
        @Override
        public StudyCard createFromParcel(Parcel in) {
            return new StudyCard(in);
        }

        @Override
        public StudyCard[] newArray(int size) {
            return new StudyCard[size];
        }
    };
}